package client.mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.main.Position;
import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromserver.FullMapNode;
import messagesbase.messagesfromserver.GameState;

public class ClientModel {
	private static Logger loggerModel = LoggerFactory.getLogger(ClientModel.class);
	private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
	private String serverMessage = "";
	private GameState gameState;
	private GameStateParser gameStateParser;
	private boolean foundMyTreasure = false; 
	private boolean foundEnemyTreasure = false;

	private Map<Position, FullMapNode> map;
	
	public void setGameStateParser(UniquePlayerIdentifier uniqueId) {
		gameStateParser = new GameStateParser(uniqueId);
	}
	
	public void setServerMessage(String serverMessage) {
		this.serverMessage = serverMessage;
		loggerModel.warn("Set Game Message: {}", serverMessage);
		changes.firePropertyChange("GameState", "message", serverMessage);
	}
	
	public void setGameState(GameState newGameState) {
		map = gameStateParser.extractGameMap(newGameState);
		
		if ( ! newGameState.getGameStateId().equals("halfMapStateID")) {
			foundMyTreasure = gameStateParser.extractCollectedTreasure(newGameState);
			foundEnemyTreasure = gameStateParser.extractCollectedEnemyTreasure(newGameState);
		}
		
		if (this.gameState == null)
			changes.firePropertyChange("GameStateID", "emptyState", newGameState.getGameStateId());
		else changes.firePropertyChange("GameStateID", this.gameState.getGameStateId(), newGameState.getGameStateId());
		
		this.gameState = newGameState;
		
		loggerModel.debug("Set Game State: {}", gameState.getGameStateId());
	}

	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener(listener);
	}
		
	public GameState getGameState() {
		return gameState;
	} 
	
	public String getServerMessage() {
		return serverMessage;
	}
	
	public Map<Position, FullMapNode> getMap() {
		return map;
	}

	public boolean isFoundMyTreasure() {
		return foundMyTreasure;
	}

	public boolean isFoundEnemyTreasure() {
		return foundEnemyTreasure;
	}


}
