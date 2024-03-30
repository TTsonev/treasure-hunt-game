package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.GenericExampleException;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.ServerMapNode;

public class CountWaterFieldsRule implements IBusinessRule {
	private static final int MIN_WATER_FIELDS = 7;

	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		int totalWaterFields = (int) halfMap.getMapNodes().stream().filter(node -> node.getTerrain() == ETerrain.Water).count();

		if (totalWaterFields < MIN_WATER_FIELDS) 
			throw new HalfMapException("HalfMap contains " + totalWaterFields + " Water Fields but should contain at least" + MIN_WATER_FIELDS); 
		
	}

	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		
	}

}
