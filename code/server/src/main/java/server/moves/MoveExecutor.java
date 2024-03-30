package server.moves;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import server.main.EPlayerNumber;
import server.main.Position;
import server.map.ECastleServer;
import server.map.EPlayerServer;
import server.map.ETreasureServer;
import server.map.ServerMapNode;

public class MoveExecutor {
	private Map<Position, ServerMapNode> gameMap = new HashMap<>();
	
	private static final int NEEDED_MOVES_GRASS_TO_GRASS = 2;
	private static final int NEEDED_MOVES_GRASS_TO_MOUNT = 3;
	private static final int NEEDED_MOVES_MOUNT_TO_GRASS = 3;
	private static final int NEEDED_MOVES_MOUNT_TO_MOUNT = 4;

	
	public MoveExecutor(Map<Position, ServerMapNode> gameMap) {
		this.gameMap = gameMap;
	}
	
	public Map<Position, ServerMapNode> getGameMap() {
		return gameMap;
	}
	
	public EMoveResult executeMove(EMove move, EPlayerNumber playerNum, int moveStreak) {		
		EPlayerServer playerPostionValue = (playerNum == EPlayerNumber.Player1) ? EPlayerServer.Player1Present : EPlayerServer.Player2Present;
		Position playerPosition = findPlayerLocation(playerPostionValue).getKey();
		ServerMapNode playerNode = findPlayerLocation(playerPostionValue).getValue();

		
		MoveValidator.validateMove(gameMap, playerPosition, move);
		
		Position goalPosition = playerPosition.findNeighbor(move);
		ServerMapNode goalNode = gameMap.get(goalPosition);
		
		if (checkMoveEligibilty(goalNode.getTerrain(), playerNode, moveStreak))
			return movePlayerToNode(playerPosition, goalPosition, playerNum);
		
		return EMoveResult.DidNotMove;
	}
	
	
	private Entry<Position, ServerMapNode> findPlayerLocation(EPlayerServer playerPostionValue) {
		return 	gameMap.entrySet().stream()
				.filter(gameMapEntry -> gameMapEntry.getValue().getPlayerPosition() == playerPostionValue 
						|| gameMapEntry.getValue().getPlayerPosition() == EPlayerServer.BothPlayersPresent)
				.findFirst().get();
	}
	
	//Business Rule: different terrains require different number of moves
	private boolean checkMoveEligibilty(ETerrain goalTerrain, ServerMapNode playerNode, int moveStreak) {
		if (goalTerrain == ETerrain.Grass) {
			if (playerNode.getTerrain() == ETerrain.Grass && moveStreak == NEEDED_MOVES_GRASS_TO_GRASS 
					|| playerNode.getTerrain() == ETerrain.Mountain && moveStreak == NEEDED_MOVES_GRASS_TO_MOUNT) 
				return true;
		}
		
		else if (goalTerrain == ETerrain.Mountain) {
			if (playerNode.getTerrain() == ETerrain.Grass && moveStreak == NEEDED_MOVES_MOUNT_TO_GRASS 
					|| playerNode.getTerrain() == ETerrain.Mountain && moveStreak == NEEDED_MOVES_MOUNT_TO_MOUNT)
				return true;
		}

		//case Water already covered by Validator

		return false;
	}
	
		
	private EMoveResult movePlayerToNode(Position currentPos, Position goalPos, EPlayerNumber player) {
		replaceCurrentNode(currentPos, player);
		EMoveResult moveResult = replaceGoalNode(goalPos, player);
		
		ServerMapNode goalNode = gameMap.get(goalPos);
		
		if (goalNode.getCastle() == ECastleServer.Player1Castle && player == EPlayerNumber.Player2
				|| goalNode.getCastle() == ECastleServer.Player2Castle && player == EPlayerNumber.Player1) {
			return EMoveResult.ReachedEnemyCastle;
		}
		
		if (goalNode.getTerrain() == ETerrain.Mountain) {
			boolean foundEnemyCastle = goalPos.findAllVisibleNeighbors().stream().map(position -> gameMap.containsKey(position) ?  gameMap.get(position) : null)
					.filter(mapNode -> mapNode != null)						
					.filter(mapNode -> (mapNode.getCastle() == ECastleServer.Player1Castle && player == EPlayerNumber.Player2) 
													|| (mapNode.getCastle() == ECastleServer.Player2Castle && player == EPlayerNumber.Player1) )
					.count() > 0;
			if (foundEnemyCastle) return EMoveResult.DetectedEnemyCastle;												
		}

		return moveResult;
	}
	
	// replace node that the player figure leaves 
	private void replaceCurrentNode(Position currentPos, EPlayerNumber player) {
		ServerMapNode currentNode = gameMap.get(currentPos);
		EPlayerServer otherPlayerPosition = (player == EPlayerNumber.Player1) ? EPlayerServer.Player2Present : EPlayerServer.Player1Present;

		EPlayerServer replacementCurrentNodePosition = (currentNode.getPlayerPosition() == EPlayerServer.BothPlayersPresent) 
				? otherPlayerPosition : EPlayerServer.NoPlayerPresent;
			
		ServerMapNode currentNodeReplacement = new ServerMapNode(currentNode.getX(), currentNode.getY(), currentNode.getTerrain(), 
																	currentNode.getCastle(), currentNode.getTreasure(), replacementCurrentNodePosition);
		gameMap.put(currentPos, currentNodeReplacement);

	}
	
	// replace node that the player figure enters
	private EMoveResult replaceGoalNode(Position goalPos, EPlayerNumber player) {
		ServerMapNode goalNode = gameMap.get(goalPos);
		EPlayerServer playerPosition = (player == EPlayerNumber.Player1) ? EPlayerServer.Player1Present : EPlayerServer.Player2Present;

		EPlayerServer replacementGoalNodePosVal = (goalNode.getPlayerPosition() == EPlayerServer.NoPlayerPresent) 
				? playerPosition : EPlayerServer.BothPlayersPresent;

		
		boolean reachedTreasure = checkTreasure(goalPos, player);
		ETreasureServer treasurePos = reachedTreasure ? ETreasureServer.NoTreasure : goalNode.getTreasure(); 
		
		ServerMapNode goalNodeReplacement = new ServerMapNode(goalNode.getX(), goalNode.getY(), goalNode.getTerrain(), 
															goalNode.getCastle(), treasurePos, 
															replacementGoalNodePosVal);
		
		gameMap.put(goalPos, goalNodeReplacement);
		
		return reachedTreasure ? EMoveResult.ReachedTreasure : EMoveResult.Moved;

	}
	
	
	private boolean checkTreasure(Position goalPos, EPlayerNumber player) {
		ServerMapNode goalNode = gameMap.get(goalPos);
		ETreasureServer playerTreasureValue = (player == EPlayerNumber.Player1) ? ETreasureServer.Player1Treasure : ETreasureServer.Player2Treasure;
		return (goalNode.getTreasure() == playerTreasureValue);
	}
	
}
