package server.main;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import messagesbase.UniqueGameIdentifier;
import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromclient.PlayerHalfMap;
import messagesbase.messagesfromclient.PlayerMove;
import messagesbase.messagesfromserver.GameState;
import server.exceptions.HalfMapException;
import server.exceptions.MoveException;
import server.exceptions.PlayerRegistrationException;
import server.exceptions.StatusRequestException;

public class GameCoordinator {
	private static Map<UniqueGameIdentifier, Game> gameCoordinator = new LinkedHashMap<>();
	private static final int MAX_PARALELL_GAMES = 100;
	private static Logger loggerCoordinator = LoggerFactory.getLogger(GameCoordinator.class);

	
	public static UniqueGameIdentifier createNewGame() {
		Game availabeGame = new Game();
		if (gameCoordinator.size() >= MAX_PARALELL_GAMES) removeOldestGame();
		
		gameCoordinator.put(availabeGame.getGameId(), availabeGame);
		
		return availabeGame.getGameId();
	}
	
	
	public static UniquePlayerIdentifier registerPlayer(UniqueGameIdentifier gameID, String firstName, String lastName, String uAccount) {
		if ( !gameCoordinator.containsKey(gameID)) throw new PlayerRegistrationException("No Game with ID " + gameID.toString());
		Game game = gameCoordinator.get(gameID);
		
		return game.registerPlayer(firstName, lastName, uAccount);
	}
	
	
	public static GameState transferHalfMap(UniqueGameIdentifier gameID, PlayerHalfMap playerHalfMap) {
		if ( !gameCoordinator.containsKey(gameID)) throw new HalfMapException("Invalid GameID (" + gameID.getUniqueGameID() + ")");
		Game game = gameCoordinator.get(gameID);

		UniquePlayerIdentifier playerId = new UniquePlayerIdentifier(playerHalfMap.getUniquePlayerID());
		
		game.recieveHalfMap(playerId, playerHalfMap);
		
		return game.provideGameStatus(playerId);

	}
	
	
	public static GameState transferStatusRequest(UniqueGameIdentifier gameID, UniquePlayerIdentifier playerID) {
		if ( !gameCoordinator.containsKey(gameID)) throw new StatusRequestException("No Game with ID: " + gameID.getUniqueGameID());
		Game game = gameCoordinator.get(gameID);
						
		return game.provideGameStatus(playerID);
	}
	
	
	public static void transferMove(UniqueGameIdentifier gameID, PlayerMove playerMove) {
		if ( !gameCoordinator.containsKey(gameID)) throw new MoveException("No Game with ID: " + gameID.getUniqueGameID());
		Game game = gameCoordinator.get(gameID);
		UniquePlayerIdentifier playerID = new UniquePlayerIdentifier(playerMove.getUniquePlayerID());
				
		game.processMove(playerID, playerMove.getMove());

	}
	
	
	private static void removeOldestGame() {
		UniqueGameIdentifier oldestGameID = gameCoordinator.entrySet().iterator().next().getKey();
		gameCoordinator.remove(oldestGameID);
		loggerCoordinator.warn("Limit of {} Games exceeded! Removed Game {} !", MAX_PARALELL_GAMES, oldestGameID.getUniqueGameID());
	}
}
