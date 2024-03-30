package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.MoveException;
import server.main.Position;
import server.map.ServerMapNode;

public class MoveTowardsWaterRule implements IBusinessRule {

	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		Position goalPosition = currentPosition.findNeighbor(move);
		if (gameMap.get(goalPosition).getTerrain() == ETerrain.Water) 
			throw new MoveException("Attempted Move towards Water Field: " + goalPosition.toString());

	}
	
	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		
	}

}
