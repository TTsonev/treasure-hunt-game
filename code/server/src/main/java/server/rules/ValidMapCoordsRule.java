package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.ServerMapNode;

public class ValidMapCoordsRule implements IBusinessRule {
	private static final int MIN_COORD_LIMIT = 0;
	
	
	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		if (halfMap.getMapNodes().stream()
				.anyMatch(mapNode -> mapNode.getX() < MIN_COORD_LIMIT || mapNode.getY() < MIN_COORD_LIMIT))
			throw new HalfMapException("HalfMap should not contain any negative-value coordinates!");
	}

	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		
	}
	

}
