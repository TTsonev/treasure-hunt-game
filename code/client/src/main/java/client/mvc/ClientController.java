package client.mvc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.main.Position;
import client.map.MapConverter;
import client.map.MapGenerator;
import client.navigation.CastleNavigator;
import client.navigation.NavigationStrategy;
import client.navigation.TreasureNavigator;
import client.network.ClientNetwork;
import messagesbase.UniqueGameIdentifier;
import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMapNode;
import messagesbase.messagesfromserver.EFortState;
import messagesbase.messagesfromserver.EPlayerGameState;
import messagesbase.messagesfromserver.EPlayerPositionState;
import messagesbase.messagesfromserver.ETreasureState;
import messagesbase.messagesfromserver.FullMap;
import messagesbase.messagesfromserver.FullMapNode;
import messagesbase.messagesfromserver.GameState;

public class ClientController {
	private UniquePlayerIdentifier uniqueId;
	private ClientNetwork network;
	private ClientModel model;
	private Position myTreasurePosition;
	private Position enemyCastlePosition;
	private GameStateParser gameStateParser;
	private static final int GAMEMAP_SIZE = 100;
	private static final int COOLDOWN_PERIOD = 400;

	private static Logger loggerController = LoggerFactory.getLogger(ClientController.class);
	
	public ClientController(String serverBaseUrl, UniqueGameIdentifier gameId, ClientModel model) {
		this.network = new ClientNetwork(serverBaseUrl, gameId); 
		this.model = model;		
		this.myTreasurePosition = null;
		this.enemyCastlePosition = null;
	}
	
	public void executeGameLogic() {
		try {
			uniqueId = network.registerForGame();
			loggerController.warn("PlayerID: {}", uniqueId);
			
			this.model.setGameStateParser(uniqueId);
			gameStateParser = new GameStateParser(uniqueId);
			
			Map<Position, PlayerHalfMapNode> halfMap = generateHalfMap();
			sendHalfMap(halfMap);
			loggerController.info("HalfMap sent!");
		
			boolean foundTreasure = searchTreasure();
			
			if (foundTreasure) {
				//this.model.setServerMessage("Treasure Found!");
				loggerController.info("Treasure Found!");
				searchCastle();
			}
			
		}			
		catch (Exception e) {
			//this.model.setServerMessage(e.getMessage());
			e.printStackTrace();
			loggerController.error("\u001B[31mError: \u001B[0m" + e);
			return;
		}
	}
	
	
	
	private Map<Position, PlayerHalfMapNode> generateHalfMap() {
		Map<Position, PlayerHalfMapNode> halfMap = MapGenerator.generateMap();
		
		displayHalfMap(halfMap);
	
		return halfMap;
	}
	
	private void displayHalfMap(Map<Position, PlayerHalfMapNode> halfMap) {
		Map<Position, FullMapNode> convertedHalfMap = MapConverter.convertHalfMapToFullMap(halfMap.values());
		GameState halfMapGameState = new GameState(new FullMap(convertedHalfMap.values()), null, "halfMapStateID");
		this.model.setGameState(halfMapGameState);
	}
	
	private void sendHalfMap(Map<Position, PlayerHalfMapNode> halfMap) throws Exception {
		while(true) {
			//request gameState and playerStatus
			GameState gameState = network.statusUpdate();
			EPlayerGameState playerStatus = gameStateParser.extractPlayerStatus(gameState);
			
			//wait unit MustAct
		  	if (playerStatus == EPlayerGameState.MustAct) break;
	        Thread.sleep(COOLDOWN_PERIOD);
		}
		
		network.sendHalfMap(halfMap);
	}
	

		
	private boolean searchTreasure() throws Exception {		
		TreasureNavigator treasureNavigator;
		Queue<EMove> nextMoves = new LinkedList<>();

		//wait unit full gameMap is available 
		Map<Position, FullMapNode> gameMap = acquireFullMap();
		
		loggerController.info("Searching for the Treasure!");
		
		Position currentPosition = extractCurrentPosition(gameMap);
		
		treasureNavigator = new TreasureNavigator(gameMap);
		nextMoves = treasureNavigator.navigateNextMoves(currentPosition);

		while(true) {
			// update data
			GameState gameState = network.statusUpdate();
			gameMap = gameStateParser.extractGameMap(gameState);
			this.model.setGameState(gameState);
			
			//check if won/lost
			if (checkIfGameOver(gameState)) return false;
			
			//check if treasure found
			if (gameStateParser.extractCollectedTreasure(gameState)) return true;

			//check neighbors if on a Mountain field
			currentPosition = extractCurrentPosition(gameMap);
			if (gameMap.get(currentPosition).getTerrain()==ETerrain.Mountain) {
				checkAroundMountain(gameMap,currentPosition, treasureNavigator);
			}
			
			// if Treasure position known - navigate there
			if (myTreasurePosition != null) {
				//this.model.setServerMessage("Treasure Detected!");
				nextMoves = treasureNavigator.navigateToTreasure(currentPosition, myTreasurePosition);
			}
			
			//if MustAct - send next EMove (or generate nextMoves)
		  	if (gameStateParser.extractPlayerStatus(gameState) == EPlayerGameState.MustAct) {
		  		if (nextMoves.isEmpty()) {
					loggerController.warn("Generating next set of moves!");
		  			nextMoves = treasureNavigator.navigateNextMoves(currentPosition);
		  		}
		  		network.sendMove(nextMoves.poll());
		  	}
		  	
		  	//if MustWait - sleep for 4ms
	        Thread.sleep(COOLDOWN_PERIOD);
		}
	}

	
	private Map<Position, FullMapNode> acquireFullMap() throws Exception {
		Map<Position, FullMapNode> gameMap = new HashMap<>();
		
		while(true) {
			//update gameState and gameMap
			GameState gameState = network.statusUpdate();
			gameMap = gameStateParser.extractGameMap(gameState);
			
			//if FullMap not yet available
			if (gameMap.size() < GAMEMAP_SIZE) continue;
			else {
				this.model.setGameState(gameState);		
				break;
			}
		}
		return gameMap;
	}
	

	
	// Go to enemy half and search for the Enemy Castle
	
	private void searchCastle() throws Exception {
		loggerController.info("Searching for the enemy Castle!");
		
		//Wait ???
		GameState gameState = network.statusUpdate();
		Map<Position, FullMapNode> gameMap = gameStateParser.extractGameMap(gameState);
		
		Position currentPosition = extractCurrentPosition(gameMap);
		
		CastleNavigator castleNavigator = new CastleNavigator(gameMap);
		Queue<EMove> nextMoves = castleNavigator.navigateToEnemyHalfMap(currentPosition);
		
		while(true) {
			gameState = network.statusUpdate();
			gameMap = gameStateParser.extractGameMap(gameState);
			this.model.setGameState(gameState);

			//check if won/lost
			if (checkIfGameOver(gameState)) return;			

			//check neighbors if on a Mountain field
			currentPosition = extractCurrentPosition(gameMap);
			if (gameMap.get(currentPosition).getTerrain()==ETerrain.Mountain) {
				checkAroundMountain(gameMap,currentPosition, castleNavigator);
			}
			
			// if Castle position known - navigate there
			if (enemyCastlePosition != null) {
				//this.model.setServerMessage("Enemy Castle Detected!");
				nextMoves = castleNavigator.navigateToCastle(currentPosition, enemyCastlePosition);
			}
			
			//if MustAct - send next EMove (or generate nextMoves)
			if (gameStateParser.extractPlayerStatus(gameState) == EPlayerGameState.MustAct) {
		  		if (nextMoves.isEmpty()) {
		  			nextMoves = castleNavigator.navigateNextMoves(currentPosition);
		  		}
		  		network.sendMove(nextMoves.poll());
		  	}

			//if MustWait - sleep for 4ms
	        Thread.sleep(COOLDOWN_PERIOD);
		}

	}
	
		
	
	private boolean checkIfGameOver(GameState gameState) {
		EPlayerGameState playerStatus = gameStateParser.extractPlayerStatus(gameState);
		if (playerStatus == EPlayerGameState.Lost) {
			this.model.setServerMessage("\033[41m\033[1mDefeat :(\u001B[0m");
			loggerController.info("Client has lost the game!");
			return true;
		} 
		if (playerStatus == EPlayerGameState.Won) {
			this.model.setServerMessage("\033[43m\033[1mVictory :)\u001B[0m");
			loggerController.info("Client has won the game!");
			return true;
		}
		return false;
	}
	
	
	private Position extractCurrentPosition(Map<Position, FullMapNode> gameMap) {
		Position currentPosition = gameMap.entrySet().stream()
										.filter(entry -> entry.getValue().getPlayerPositionState()==EPlayerPositionState.MyPlayerPosition
												|| entry.getValue().getPlayerPositionState()==EPlayerPositionState.BothPlayerPosition)
										.findFirst().get().getKey();
		
		return currentPosition;
	}
	
	private void checkAroundMountain(Map<Position, FullMapNode> gameMap, Position currentPosition, NavigationStrategy navigator) {
		int xCoord = currentPosition.getX();
		int yCoord = currentPosition.getY();
		int[] shiftDirections = {-1,0,1};
		for (int xShift : shiftDirections) {
			for (int yShift : shiftDirections) {
				if (xShift == 0 && yShift == 0) continue;
				
				Position neighborPosition = new Position(xCoord+xShift, yCoord+yShift);
				FullMapNode neighborNode = gameMap.get(neighborPosition);
				
				if (neighborNode == null) continue;
				
				boolean enemyCastleDetection = neighborNode.getFortState()==EFortState.EnemyFortPresent;
				boolean myTreasureDetection = neighborNode.getTreasureState()==ETreasureState.MyTreasureIsPresent;

				if (enemyCastleDetection) {
					this.enemyCastlePosition = neighborPosition;
					loggerController.warn("Enemy Castle detected at {}", enemyCastlePosition);
				}
				if (myTreasureDetection) {
					this.myTreasurePosition = neighborPosition;
					loggerController.warn("Treasure detected at {}", myTreasurePosition);
				}
				if (!myTreasureDetection && !enemyCastleDetection && !(neighborNode.getTerrain()==ETerrain.Mountain)) {
					//key contained
					navigator.addToVisited(neighborPosition);
				}
			}
		}
	}

}
