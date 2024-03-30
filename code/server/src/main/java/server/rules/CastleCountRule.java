package server.rules;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import messagesbase.messagesfromclient.PlayerHalfMapNode;
import server.exceptions.GenericExampleException;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.ServerMapNode;

public class CastleCountRule implements IBusinessRule {
	private static final int EXPECTED_CASTLE_COUNT = 1;
	
	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		List<PlayerHalfMapNode> nodesWithCastle = halfMap.getMapNodes().stream().filter(node -> node.isFortPresent() == true).collect(Collectors.toList());
		if (nodesWithCastle.size() != EXPECTED_CASTLE_COUNT) 
			throw new HalfMapException("Expected 1 Fort but found " + nodesWithCastle.size() + "(PlayerID: " + halfMap.getUniquePlayerID() + ")");		
	}
	
	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position map, EMove move) {
		
	}

}
