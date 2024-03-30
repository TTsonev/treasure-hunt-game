package server.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.main.EPlayerNumber;
import server.main.Position;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMapNode;
import messagesbase.messagesfromserver.EFortState;
import messagesbase.messagesfromserver.EPlayerPositionState;
import messagesbase.messagesfromserver.ETreasureState;
import messagesbase.messagesfromserver.FullMap;
import messagesbase.messagesfromserver.FullMapNode;

public class MapConverter {	
	private static Logger loggerMapConv = LoggerFactory.getLogger(MapConverter.class);

	public static Map<Position, ServerMapNode> convertHalfMapToServerMap(Collection<PlayerHalfMapNode> halfmapNodes, EPlayerNumber player) {
		Map<Position, ServerMapNode> serverMap = new HashMap<>();
		
		for (PlayerHalfMapNode node : halfmapNodes) {
			 int X = node.getX();
			 int Y = node.getY();
			 ECastleServer castle = node.isFortPresent() ? ECastleServer.getCastleFromEPlayer(player) : ECastleServer.NoCastle;
			 
			 EPlayerServer playerPosition = node.isFortPresent() 
					 ? (player == EPlayerNumber.Player1 ? EPlayerServer.Player1Present : EPlayerServer.Player2Present)
					 : EPlayerServer.NoPlayerPresent;
			 
			 ServerMapNode fullNode = new ServerMapNode(X, Y, node.getTerrain(), castle, ETreasureServer.NoTreasure, playerPosition);
			 serverMap.put(new Position(X,Y), fullNode);
		}
		
		return serverMap;
	}
	
	
	public static FullMap convertServerMapToFullMapFakeEnemy(Map<Position, ServerMapNode> serverMap, EPlayerNumber player, boolean foundEnemyCastle) {
		Set<FullMapNode> fullMapNodes = convertBasicMapInfo(serverMap, player, foundEnemyCastle);
		fullMapNodes = addFakeEnemyPosition(fullMapNodes, player);
		return new FullMap(fullMapNodes);
	}
	
	
	public static FullMap convertServerMapToFullMapRealEnemy(Map<Position, ServerMapNode> serverMap, EPlayerNumber player, boolean foundEnemyCastle) {		
		Set<FullMapNode> fullMapNodes = convertBasicMapInfo(serverMap, player, foundEnemyCastle);
		ServerMapNode enemyNode = findRealEnemyPosition(serverMap, player);
		fullMapNodes = addRealEnemyPosition(fullMapNodes, enemyNode);			
		return new FullMap(fullMapNodes);
	}
	
	
	private static Set<FullMapNode> convertBasicMapInfo(Map<Position, ServerMapNode> serverMap, EPlayerNumber player, boolean foundCastle) {
		Set<FullMapNode> fullMapNodes = new HashSet<>();
		Set<Position> visibleNeighbours = getVisibleNodes(serverMap, player);
		
		for (Entry<Position,ServerMapNode> serverMapEntry : serverMap.entrySet()) {
			ServerMapNode serverMapNode = serverMapEntry.getValue();

			ETerrain terrain = serverMapNode.getTerrain();
			
			EPlayerPositionState playerPos = checkClientPositionValue(serverMapNode.getPlayerPosition(), player);	
			
			EFortState fortState = EFortState.NoOrUnknownFortState;
			ETreasureState treasureState = ETreasureState.NoOrUnknownTreasureState;
			
			if (playerPos == EPlayerPositionState.MyPlayerPosition 
				|| playerPos == EPlayerPositionState.BothPlayerPosition	
				|| visibleNeighbours.contains(serverMapEntry.getKey())) {
				
				fortState = checkEnemyFortState(serverMapNode.getCastle(), player);
				treasureState = checkTreasureState(serverMapNode.getTreasure(), player);
			}
			
			if (foundCastle) fortState = checkEnemyFortState(serverMapNode.getCastle(), player);

			if (fortState != EFortState.EnemyFortPresent) fortState = checkMyFortState(serverMapNode.getCastle(), player); 

			fullMapNodes.add(new FullMapNode(terrain, playerPos, treasureState, fortState, serverMapNode.getX(), serverMapNode.getY()));
		}
		
		return fullMapNodes;
	}
	
	private static EFortState checkEnemyFortState(ECastleServer castlePos, EPlayerNumber player) {
		EFortState fortState = EFortState.NoOrUnknownFortState;
		switch (castlePos) {
			case Player1Castle : if (player == EPlayerNumber.Player2) fortState = EFortState.EnemyFortPresent; break;
			case Player2Castle : if (player == EPlayerNumber.Player1) fortState = EFortState.EnemyFortPresent; break;
		}
		return fortState;
	}
	
	private static EFortState checkMyFortState(ECastleServer castlePos, EPlayerNumber player) {
		EFortState fortState = EFortState.NoOrUnknownFortState;
		switch (castlePos) {
			case Player1Castle : if (player == EPlayerNumber.Player1) fortState = EFortState.MyFortPresent; break;
			case Player2Castle : if (player == EPlayerNumber.Player2) fortState = EFortState.MyFortPresent; break;
		}
		return fortState;
	}
	
	private static ETreasureState checkTreasureState(ETreasureServer treasurePos, EPlayerNumber player) {
		ETreasureState treasureState = ETreasureState.NoOrUnknownTreasureState;
		switch (treasurePos) {
			case Player1Treasure : if (player == EPlayerNumber.Player1) treasureState = ETreasureState.MyTreasureIsPresent; break;
			case Player2Treasure : if (player == EPlayerNumber.Player2) treasureState = ETreasureState.MyTreasureIsPresent; break;
		}
		return treasureState;
	}
	
	private static EPlayerPositionState checkClientPositionValue(EPlayerServer serverPositionValue, EPlayerNumber player) {
		EPlayerPositionState playerPos = EPlayerPositionState.NoPlayerPresent;	

		switch (serverPositionValue) {
			case BothPlayersPresent : playerPos = EPlayerPositionState.BothPlayerPosition; break;
			case Player1Present : if (player == EPlayerNumber.Player1) playerPos = EPlayerPositionState.MyPlayerPosition; break;
			case Player2Present : if (player == EPlayerNumber.Player2) playerPos = EPlayerPositionState.MyPlayerPosition; break;
		}
		
		return playerPos;
	}
	
	private static Set<Position> getVisibleNodes(Map<Position, ServerMapNode> serverMap, EPlayerNumber player) {
		EPlayerServer playerPositionValue = (player == EPlayerNumber.Player1) ? EPlayerServer.Player1Present : EPlayerServer.Player2Present;

		Optional<Entry<Position, ServerMapNode>> playerMapEntry = serverMap.entrySet().stream()
				.filter(mapNode -> mapNode.getValue().getPlayerPosition() == playerPositionValue ||
						mapNode.getValue().getPlayerPosition() == EPlayerServer.BothPlayersPresent)
				.findFirst();

		Set<Position> visibleNeighbours = new HashSet<>();
		
		if ( playerMapEntry.isPresent() && playerMapEntry.get().getValue().getTerrain() == ETerrain.Mountain) {
			visibleNeighbours.addAll(playerMapEntry.get().getKey().findAllVisibleNeighbors());
		}
		
		return visibleNeighbours;
	}
	
	
	private static Set<FullMapNode> addFakeEnemyPosition(Set<FullMapNode> fullMapNodes, EPlayerNumber player) {
		Optional<FullMapNode> enemyPositionTargetNodeOptional = fullMapNodes.stream()
				.filter(node -> node.getPlayerPositionState() == EPlayerPositionState.NoPlayerPresent)
				.findAny();

		if (!enemyPositionTargetNodeOptional.isEmpty()) {
			
			FullMapNode enemyPositionTargetNode = enemyPositionTargetNodeOptional .get();
			
			FullMapNode enemyPositionNode = new FullMapNode(enemyPositionTargetNode.getTerrain(), EPlayerPositionState.EnemyPlayerPosition, 
			enemyPositionTargetNode.getTreasureState(), enemyPositionTargetNode.getFortState(), 
			enemyPositionTargetNode.getX(), enemyPositionTargetNode.getY());
			
			fullMapNodes.remove(enemyPositionTargetNode);
			fullMapNodes.add(enemyPositionNode);
			loggerMapConv.trace("Included Fake Enemy at ({}|{})", enemyPositionNode.getX(), enemyPositionNode.getY());
		}
		return fullMapNodes;
	}
	
	
	private static ServerMapNode findRealEnemyPosition(Map<Position, ServerMapNode> serverMap, EPlayerNumber player) {
		EPlayerServer otherPlayerPos = (player == EPlayerNumber.Player1) ? EPlayerServer.Player2Present : EPlayerServer.Player1Present;
		
		return serverMap.entrySet().stream()
						.filter(mapNode -> mapNode.getValue().getPlayerPosition() == otherPlayerPos 
								||mapNode.getValue().getPlayerPosition() == EPlayerServer.BothPlayersPresent)
						.findFirst().get().getValue();
	}
	
	private static Set<FullMapNode> addRealEnemyPosition(Set<FullMapNode> fullMapNodes, ServerMapNode enemyNode) {
		FullMapNode targetNode = fullMapNodes.stream()
								  .filter(mapNode -> mapNode.getX() == enemyNode.getX() &&  mapNode.getY() == enemyNode.getY())
								  .findFirst().get();
		
		EPlayerPositionState enemyPosValue = (enemyNode.getPlayerPosition() == EPlayerServer.BothPlayersPresent) 
				? EPlayerPositionState.BothPlayerPosition : EPlayerPositionState.EnemyPlayerPosition;
		
		FullMapNode replacementNode = new FullMapNode(targetNode.getTerrain(), 
				enemyPosValue, targetNode.getTreasureState() , targetNode.getFortState(), targetNode.getX(), targetNode.getY());
		
		fullMapNodes.remove(targetNode);
		fullMapNodes.add(replacementNode);
		return fullMapNodes;
	} 
	
}
