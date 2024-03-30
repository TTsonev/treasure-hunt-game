package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.GenericExampleException;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.ServerMapNode;

public class WaterEdgesRule implements IBusinessRule {
	private static final int LEFT_EDGE_X = 0;
	private static final int RIGHT_EDGE_X = 9;
	private static final int MAX_WATER_VERTICAL_EDGE = 2;

	private static final int TOP_EDGE_Y = 0;
	private static final int BOTTOM_EDGE_Y = 5;
	private static final int MAX_WATER_HORIZONTAL_EDGE = 4;

	
	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		if (	halfMap.getMapNodes().stream()
				.filter(node -> node.getX() == LEFT_EDGE_X && node.getTerrain() == ETerrain.Water)
				.count() > MAX_WATER_VERTICAL_EDGE) 
			throw new HalfMapException("Left Edge had too many Water Fields! (PlayerID: " + halfMap.getUniquePlayerID() + ")");
		
		if (	halfMap.getMapNodes().stream()
				.filter(node -> node.getX() == RIGHT_EDGE_X && node.getTerrain() == ETerrain.Water)
				.count() > MAX_WATER_VERTICAL_EDGE) 
			throw new HalfMapException("Right Edge had too many Water Fields! (PlayerID: " + halfMap.getUniquePlayerID() + ")");
		
		if (	halfMap.getMapNodes().stream()
				.filter(node -> node.getX() == TOP_EDGE_Y && node.getTerrain() == ETerrain.Water)
				.count() > MAX_WATER_HORIZONTAL_EDGE) 
			throw new HalfMapException("Top Edge had too many Water Fields! (PlayerID: " + halfMap.getUniquePlayerID() + ")");
		
		if (	halfMap.getMapNodes().stream()
				.filter(node -> node.getX() == BOTTOM_EDGE_Y && node.getTerrain() == ETerrain.Water)
				.count() > MAX_WATER_HORIZONTAL_EDGE) 
			throw new HalfMapException("Bottom Edge had too many Water Fields! (PlayerID: " + halfMap.getUniquePlayerID() + ")");
	}


	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		
	}

	
}
