package server.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import messagesbase.messagesfromclient.ETerrain;
import server.main.EPlayerNumber;
import server.main.Position;

public abstract class AMapBuilder {
	protected boolean placedFirstMapHalf = false;
	protected boolean placedShiftedMapHalf = false;
	protected static Random random = new Random();	
	
	public abstract Map<Position, ServerMapNode> processHalfMap(Map<Position, ServerMapNode> halfmap, EPlayerNumber playerNum);
	protected abstract Map<Position, ServerMapNode> shiftHalfMap(Map<Position, ServerMapNode> halfmap, EPlayerNumber playerNum);
	
	protected static Map<Position, ServerMapNode> placeTreasure(Map<Position, ServerMapNode> halfmap, EPlayerNumber playerNum) {

		ServerMapNode targetNode = chooseRandomGrassNode(halfmap);
		
		ETreasureServer treasure = (playerNum == EPlayerNumber.Player1) ? ETreasureServer.Player1Treasure : ETreasureServer.Player2Treasure;
		
		ServerMapNode castleNode = new ServerMapNode(targetNode.getX(), targetNode.getY(), 
		targetNode.getTerrain(), targetNode.getCastle(), treasure, targetNode.getPlayerPosition());
		
		halfmap.put( new Position(castleNode.getX(), castleNode.getY()), castleNode);
		
		return halfmap;		
	}
	
	
	private static ServerMapNode chooseRandomGrassNode(Map<Position, ServerMapNode> halfmap) {
		ServerMapNode targetNode;
		
		List<ServerMapNode> mapNodes = new ArrayList<>(halfmap.values()); 
		while(true) {
			int randomIdx = random.nextInt(mapNodes.size());
			targetNode = mapNodes.get(randomIdx);
			if (targetNode.getTerrain() == ETerrain.Grass) break;
		}
		
		return targetNode;
	}

}
