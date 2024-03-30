package server.map;

import messagesbase.messagesfromclient.ETerrain;

public class ServerMapNode {
	private int xCoord;
	private int yCoord;
	private ETerrain terrain;
	private ECastleServer castle;
	private ETreasureServer treasure;
	private EPlayerServer playerPosition;
	
	public ServerMapNode(int xCoord, int yCoord, ETerrain terrain, ECastleServer castle, ETreasureServer treasure, EPlayerServer playerPosition) {
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.terrain = terrain;
		this.castle = castle;
		this.treasure = treasure;
		this.playerPosition = playerPosition;
	}
	
	public ServerMapNode(int xCoord, int yCoord, ETerrain terrain) {
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.terrain = terrain;
		this.castle = ECastleServer.NoCastle;
		this.treasure = ETreasureServer.NoTreasure;
	}

	public ETerrain getTerrain() {
		return terrain;
	}

	public ECastleServer getCastle() {
		return castle;
	}

	public ETreasureServer getTreasure() {
		return treasure;
	}
	
	public EPlayerServer getPlayerPosition() {
		return playerPosition;
	}

	public int getX() {
		return xCoord;
	}

	public int getY() {
		return yCoord;
	}
	
	
	
	
}
