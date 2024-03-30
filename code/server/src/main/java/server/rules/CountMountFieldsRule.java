package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.GenericExampleException;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.ServerMapNode;

public class CountMountFieldsRule implements IBusinessRule {
	private static final int MIN_MOUNTAIN_FIELDS = 5;

	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		int totalMountainFields = (int) halfMap.getMapNodes().stream().filter(node -> node.getTerrain() == ETerrain.Mountain).count();

		if (totalMountainFields < MIN_MOUNTAIN_FIELDS) 
			throw new HalfMapException("HalfMap contains " + totalMountainFields + " Mountain Fields but should contain at least " + MIN_MOUNTAIN_FIELDS); 
		
	}

	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		
	}

}
