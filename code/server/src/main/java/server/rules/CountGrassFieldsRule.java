package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.GenericExampleException;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.ServerMapNode;

public class CountGrassFieldsRule implements IBusinessRule {
	private static final int MIN_GRASS_FIELDS = 24;

	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		int totalGrassFields = (int) halfMap.getMapNodes().stream().filter(node -> node.getTerrain() == ETerrain.Grass).count();

		if (totalGrassFields < MIN_GRASS_FIELDS) 
			throw new HalfMapException("HalfMap contains " + totalGrassFields + " Grass Fields but should contain at least " + MIN_GRASS_FIELDS); 
	}

	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		
	}

}
