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

public class CastleTerrainRule implements IBusinessRule {

	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		PlayerHalfMapNode castleNode = halfMap.getMapNodes().stream()
				.filter(node -> node.isFortPresent() == true)
				.findFirst().get();
		
		if (castleNode.getTerrain() != ETerrain.Grass) 
			throw new HalfMapException("Fort must be on a GRASS Field (PlayerID: " + halfMap.getUniquePlayerID() + ")");		
	}
	
	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position map, EMove move) {
		
	}
}
