package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.ServerMapNode;

public class MapShapeRule implements IBusinessRule {
	
	private static final int MAX_X_COORD = 9;
	private static final int MAX_Y_COORD = 4;
	

	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		if (halfMap.getMapNodes().stream().anyMatch(mapNode -> mapNode.getX() > MAX_X_COORD)) 
			throw new HalfMapException("Halfmap should not contain X-coordinates over " + MAX_X_COORD);
	
		if (halfMap.getMapNodes().stream().anyMatch(mapNode -> mapNode.getY() > MAX_Y_COORD)) 
			throw new HalfMapException("Halfmap should not contain Y-coordinates over " + MAX_Y_COORD);

	}

	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		
	}

}
