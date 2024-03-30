package client.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import client.main.Position;
import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromserver.EPlayerGameState;
import messagesbase.messagesfromserver.FullMapNode;
import messagesbase.messagesfromserver.GameState;
import messagesbase.messagesfromserver.PlayerState;

public class GameStateParser {
	private final UniquePlayerIdentifier uniqueId;
	
	public GameStateParser(String uniqueId) {
		this.uniqueId = new UniquePlayerIdentifier(uniqueId);
	}
	
	public GameStateParser(UniquePlayerIdentifier uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public EPlayerGameState extractPlayerStatus(GameState gameState) {
		Iterator<PlayerState> playerIterator = gameState.getPlayers().iterator();
		PlayerState playerInfo = playerIterator.next();
		if (playerInfo.getUniquePlayerID().equals(uniqueId.getUniquePlayerID()))
			return playerInfo.getState();
		else return playerIterator.next().getState();
	}
	
	public EPlayerGameState extractEnemyStatus(GameState gameState) {
		Iterator<PlayerState> playerIterator = gameState.getPlayers().iterator();
		PlayerState playerInfo = playerIterator.next();
		if ( !playerInfo.getUniquePlayerID().equals(uniqueId.getUniquePlayerID()))
			return playerInfo.getState();
		else return playerIterator.next().getState();
	}
	
	public boolean extractCollectedTreasure(GameState gameState) {
		Iterator<PlayerState> playerIterator = gameState.getPlayers().iterator();
		PlayerState playerInfo = playerIterator.next();
		if (playerInfo.getUniquePlayerID().equals(uniqueId.getUniquePlayerID()))
			return playerInfo.hasCollectedTreasure();
		else return playerIterator.next().hasCollectedTreasure();
	}
	
	public boolean extractCollectedEnemyTreasure(GameState gameState) {
		Iterator<PlayerState> playerIterator = gameState.getPlayers().iterator();
		PlayerState playerInfo = playerIterator.next();
		if (! playerInfo.getUniquePlayerID().equals(uniqueId.getUniquePlayerID()))
			return playerInfo.hasCollectedTreasure();
		else return playerIterator.next().hasCollectedTreasure();
	}
	
	public Map<Position, FullMapNode> extractGameMap(GameState gameState) {
		List<FullMapNode> mapSet = new ArrayList<>(gameState.getMap().getMapNodes());
		Map<Position, FullMapNode> gameMap = new HashMap<>();
		for (FullMapNode node : mapSet) {
			gameMap.put(new Position(node.getX(), node.getY()), node);
		}
		return gameMap;
	}




}
