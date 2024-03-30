package server.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import messagesbase.UniqueGameIdentifier;
import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import messagesbase.messagesfromserver.EPlayerGameState;
import messagesbase.messagesfromserver.FullMap;
import messagesbase.messagesfromserver.GameState;
import messagesbase.messagesfromserver.PlayerState;
import server.exceptions.GenericExampleException;
import server.exceptions.HalfMapException;
import server.exceptions.MoveException;
import server.exceptions.PlayerRegistrationException;
import server.exceptions.StatusRequestException;
import server.map.ECastleServer;
import server.map.ETreasureServer;
import server.map.AMapBuilder;
import server.map.MapBuilderHorizontal;
import server.map.MapBuilderVertical;
import server.map.MapConverter;
import server.map.MapValidator;
import server.map.ServerMapNode;
import server.moves.EMoveResult;
import server.moves.MoveExecutor;

public class Game {
	private UniqueGameIdentifier gameID;
	private String gameStateID;
	private Map<UniquePlayerIdentifier, Player> players = new HashMap<>();
	private Map<Position, ServerMapNode> gameMap = new HashMap<>();
	private AMapBuilder mapBuilder;
	
	private int turns = 0;
	private MoveExecutor moveExecutor;
	
	private static Random random = new Random();	
	private static Logger loggerGame = LoggerFactory.getLogger(Game.class);
	
	private static final int MAX_PLAYERS = 2;
	private static final int GAME_MAP_SIZE = 100;
	private static final int ENEMY_HIDDEN_TURNS = 16;
	private static final int MAX_TURNS = 320;

	
	public Game() {
		this.gameID = generateRandomGameID();
		
		//Business Rule: pick HalfMap combination strategy with a (circa) 50% chance
		this.mapBuilder = (random.nextInt(2) == 0) ? new MapBuilderHorizontal() : new MapBuilderVertical();
		
		if (this.mapBuilder instanceof MapBuilderHorizontal) loggerGame.debug("GameMap for Game {} = Rectangle!", gameID.getUniqueGameID());
		else loggerGame.debug("GameMap for Game {} = Square!", gameID.getUniqueGameID());
		updateGameStateID();
		
		loggerGame.info("Created new Game with ID: {}", gameID.getUniqueGameID());
	}
		
	// Methods that handle Player Registration requests
	public UniquePlayerIdentifier registerPlayer(String firstName, String lastName, String uAccount) {

		//Business Rule: max 2 players per game
		if (players.size() >= MAX_PLAYERS) throw new PlayerRegistrationException("Maximum Number of Player for Game " + gameID.getUniqueGameID() + " already reached!");
		
		EPlayerNumber playerNumber = (players.isEmpty()) ? EPlayerNumber.Player1 : EPlayerNumber.Player2;
		Player player = new Player(firstName, lastName, uAccount, playerNumber);
		
		players.put(player.getPlayerId(), player);
		loggerGame.debug("Registered Player {} for Game {}", player.getPlayerId().getUniquePlayerID(), gameID.getUniqueGameID());
		
		if (players.size() == MAX_PLAYERS) {
			Player nextToAct = (random.nextInt(2) == 0) ? player : getOtherPlayer(player.getPlayerId()).get();
			nextToAct.setPlayerStatus(EPlayerGameState.MustAct);
		}
		
		updateGameStateID(); 
		return player.getPlayerId();
	}
	
		
	// Methods that handle Status Update Requests
	
	public GameState provideGameStatus(UniquePlayerIdentifier playerID) {
		Set<PlayerState> playerStatesSet = new HashSet<>();
		
		if ( !validatePlayer(playerID)) 
			throw new StatusRequestException("No Player with ID +" + playerID.getUniquePlayerID() + " in Game " + gameID.getUniqueGameID());
		
		Player player = players.get(playerID);
		playerStatesSet.add(player.generatePlayerState());
		
		Optional<Player> otherPlayer = getOtherPlayer(playerID);
		if (!otherPlayer.isEmpty()) 
			playerStatesSet.add(otherPlayer.get().generateFakePlayerState());
		
		FullMap fullMap = aquireFullMap(player.getPlayerNumber(), player.hasFoundEnemyCastle());
		
		return new GameState(fullMap, playerStatesSet, gameStateID);
	}
	
	private FullMap aquireFullMap(EPlayerNumber playerNum, boolean foundEnemyCastle) {
		FullMap fullMap;
		
		//Business Rule: Enemy hidden in the beginning
		if (turns >= ENEMY_HIDDEN_TURNS) fullMap = MapConverter.convertServerMapToFullMapRealEnemy(gameMap, playerNum, foundEnemyCastle);
		else fullMap = MapConverter.convertServerMapToFullMapFakeEnemy(gameMap, playerNum, foundEnemyCastle);
		return fullMap;
	}
	
	
	// Methods that handle HalfMap Transfers
	
	public void recieveHalfMap(UniquePlayerIdentifier playerId, PlayerHalfMap halfmap) {
		Player player = players.get(playerId);
		
		// check if playerID valid
		if ( !validatePlayer(playerId)) 
			throw new HalfMapException("No Player with ID +" + playerId.getUniquePlayerID() + " in Game " + gameID.getUniqueGameID());

		//Business Rule: player can send halfmap only during own turn
		if (player.getPlayerStatus() != EPlayerGameState.MustAct) {
			setPlayerToLoserAndOtherToWinner(player);
			throw new HalfMapException("Player (ID: " + playerId.getUniquePlayerID() + ") sent halfmap while status!=MustAct");
		}
				
		checkOneMapPerPlayer(player);
		
		validateHalfMap(player, halfmap);
		
		addHalfMapToGameMap(player, halfmap);
		
		updateGameAfterNewHalfMap(player);
		
	}
	
	//Business Rule: player can send halfmap only once
	private void checkOneMapPerPlayer(Player player) {
		if (player.hasSentHalfMap()) {
			setPlayerToLoserAndOtherToWinner(player);
			throw new HalfMapException("Player (ID: " + player.getPlayerId().getUniquePlayerID() + ") has already sent a halfmap!");
		}
	}
	
	private void validateHalfMap(Player player, PlayerHalfMap halfmap) {
		try {
			MapValidator.validateHalfMap(halfmap);
		}
		catch (GenericExampleException exception) {
			setPlayerToLoserAndOtherToWinner(player);
			throw exception;
		}
	}
	
	private void addHalfMapToGameMap(Player player, PlayerHalfMap halfmap) {
		Map<Position, ServerMapNode> convertedHalfMap = MapConverter.convertHalfMapToServerMap(halfmap.getMapNodes(), player.getPlayerNumber());
		convertedHalfMap = mapBuilder.processHalfMap(convertedHalfMap, player.getPlayerNumber());
		gameMap.putAll(convertedHalfMap);

	}
	
	private void updateGameAfterNewHalfMap(Player player) {
		player.updateSentHalfMap();
		changeTurns();
		updateGameStateID();
		
		if (gameMap.size() == GAME_MAP_SIZE) {
			moveExecutor = new MoveExecutor(gameMap);
		}
	} 
	
	
	// Methods that handle Move Transfers	
	
	public void processMove(UniquePlayerIdentifier playerID, EMove move) {
		Player player = players.get(playerID);
		
		if (!validatePlayer(playerID)) 
			throw new MoveException("No Player with ID +" + playerID.getUniquePlayerID() + " in Game " + gameID.getUniqueGameID());;
		
		// Business Rule: player can only send moves during own turn
		if (gameMap.size() != GAME_MAP_SIZE) {
			setPlayerToLoserAndOtherToWinner(player);
			throw new MoveException("Both halfmaps need to be transfered before moves!");
		}
		
		incrementTurnCount(player);
		
		movePlayerFigure(player, move);
		
		changeTurns();
	}
	
	private void incrementTurnCount(Player movedLast) {
		//Business Rule: turn limit of 320
		if (++turns > MAX_TURNS) {
			setPlayerToLoserAndOtherToWinner(movedLast);
			throw new MoveException("Game should not go over " + MAX_TURNS + " turns!");
		}		
	}
	
	private void movePlayerFigure(Player player, EMove move) {
		if (player.getLastMoveDir().isEmpty()) player.setLastMoveDir(move);
		
		else {
			if (player.getLastMoveDir().get() == move) player.incrementMoveStreak();
			else player.setLastMoveDir(move); 
			
			EMoveResult moveResult = moveExecutor.executeMove(move, player.getPlayerNumber(), player.getMoveStreak());
			
			updatePlayerStatusAfterMove(player, moveResult);
			gameMap = moveExecutor.getGameMap();
		} 
	}
	
	private void updatePlayerStatusAfterMove(Player player, EMoveResult moveResult) {
		switch(moveResult) {
			case Moved: player.restartMoveStreak(); break;
			case ReachedTreasure: player.updateFoundTreasure(); break;
			case ReachedEnemyCastle: {
				if (player.hasFoundTreasure()) setPlayerToWinnerAndOtherToLoser(player);
				player.updateFoundEnemyCastleTrue();
				break;
			}
			case DetectedEnemyCastle: player.updateFoundEnemyCastleTrue(); break;
		}
	}
	
	
	// Methods that support other methods
	
	private boolean validatePlayer(UniquePlayerIdentifier playerId) {
		return players.containsKey(playerId);
	}
	
	public UniqueGameIdentifier getGameId() {
		return gameID;
	}
	
	private void updateGameStateID() {
		gameStateID = UUID.randomUUID().toString();
	}
	
	
	private void changeTurns() {
		Optional<Player> waitingPlayer = players.values().stream()
						.filter(player -> player.getPlayerStatus() == EPlayerGameState.MustWait)
						.findFirst();
		
		Optional<Player> actingPlayer = players.values().stream()
					.filter(player -> player.getPlayerStatus() == EPlayerGameState.MustAct)
					.findFirst();
		
		if ( !waitingPlayer.isEmpty()) {
			loggerGame.trace("Player {} set to MustAct", waitingPlayer.get().getPlayerId().getUniquePlayerID());
			waitingPlayer.get().setPlayerStatus(EPlayerGameState.MustAct);
		}
		if ( !actingPlayer.isEmpty()) {
			loggerGame.trace("Player {} set to MustWait", actingPlayer.get().getPlayerId().getUniquePlayerID());
			actingPlayer.get().setPlayerStatus(EPlayerGameState.MustWait);		
		} 
	}
	
	
	private UniqueGameIdentifier generateRandomGameID() {
		final int GAME_ID_LENGTH = 5;
		final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012456789";

		String gameIdString = "";
		for (int i=0; i<GAME_ID_LENGTH; i++) {
			char randomSymbol = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
			gameIdString += randomSymbol;
		}
		return new UniqueGameIdentifier(gameIdString);
	}
	
	
	private Optional<Player> getOtherPlayer(UniquePlayerIdentifier playerId) {
		return players.values().stream()
						.filter(playerEntry -> ! playerEntry.getPlayerId()
						.equals(playerId))
						.findFirst();
	}
	
	
	private void setPlayerToLoserAndOtherToWinner(Player loser) {
		loser.setPlayerStatus(EPlayerGameState.Lost);
		
		Player winner = getOtherPlayer(loser.getPlayerId()).get();
		winner.setPlayerStatus(EPlayerGameState.Won);
	}
	
	private void setPlayerToWinnerAndOtherToLoser(Player winner) {
		winner.setPlayerStatus(EPlayerGameState.Won);
		
		Player loser = getOtherPlayer(winner.getPlayerId()).get();
		loser.setPlayerStatus(EPlayerGameState.Lost);
	}
	
}
