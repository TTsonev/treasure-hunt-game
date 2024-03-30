package server.main;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import messagesbase.UniquePlayerIdentifier;
import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromserver.EPlayerGameState;
import messagesbase.messagesfromserver.PlayerState;

public class Player {
	private String firstName;
	private String lastName;
	private String uAccount;
	private UniquePlayerIdentifier playerId;
	private EPlayerGameState playerStatus;
	private EPlayerNumber playerNumber;
	private boolean sentHalfMap;
	private boolean foundTreasure = false;
	private boolean foundEnemyCastle = false;

	private Optional<EMove> lastMoveDir = Optional.empty();
	private int moveStreak = 0;
	
	public Player(String firstName, String lastName, String uAccount, EPlayerNumber playerNumber) {
		this.playerId = new UniquePlayerIdentifier(UUID.randomUUID().toString());;
		this.firstName = firstName;
		this.lastName = lastName;
		this.uAccount = uAccount;
		
		this.playerNumber = playerNumber;
		this.sentHalfMap = false;
		this.playerStatus = EPlayerGameState.MustWait;
	}
	
	public void updateFoundEnemyCastleTrue() {
		foundEnemyCastle = true;
		restartMoveStreak();
	}
	
	public boolean hasFoundEnemyCastle() {
		return foundEnemyCastle;
	}
	
	public Optional<EMove> getLastMoveDir() {
		return lastMoveDir;
	}

	public void setLastMoveDir(EMove lastMoveDir) {
		this.lastMoveDir = Optional.of(lastMoveDir);
		
		//Business Rule: when changing direction -> move streak gets reset
		restartMoveStreak();
		incrementMoveStreak();
	}

	public void incrementMoveStreak() {
		moveStreak++;
	}
	
	public void restartMoveStreak() {
		moveStreak = 0;
	}

	public int getMoveStreak() {
		return moveStreak;
	}

	public void updateFoundTreasure() {
		foundTreasure = true;
		restartMoveStreak();
	}

	public void setPlayerStatus(EPlayerGameState status) {
		this.playerStatus = status;
	}
	
	public EPlayerGameState getPlayerStatus() {
		return playerStatus;
	}

	public UniquePlayerIdentifier getPlayerId() {
		return playerId;
	}
	
	public EPlayerNumber getPlayerNumber() {
		return playerNumber;
	}
	
	public boolean hasFoundTreasure() {
		return foundTreasure;
	}	
	
	public boolean hasSentHalfMap() {
		return sentHalfMap;
	}
	
	public void updateSentHalfMap() {
		this.sentHalfMap = true;
	}
	
	public PlayerState generatePlayerState() {
		return new PlayerState(firstName, lastName, uAccount, playerStatus, playerId, foundTreasure);
	}
	
	public PlayerState generateFakePlayerState() {
		UniquePlayerIdentifier fakePlayerId = new UniquePlayerIdentifier(UUID.randomUUID().toString());
		return new PlayerState(firstName, lastName, uAccount, playerStatus, fakePlayerId, foundTreasure);
	}

}
