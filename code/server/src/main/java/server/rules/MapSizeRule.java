package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.ServerMapNode;

public class MapSizeRule implements IBusinessRule {
	private static final int HALFMAP_SIZE = 50;

	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		int halfMapSize = halfMap.getMapNodes().size();
		if (halfMapSize != HALFMAP_SIZE) 
			throw new HalfMapException("HalfMap had " + halfMapSize + " Nodes (PlayerID: " + halfMap.getUniquePlayerID() + ")");
	}

	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		
	}

}
