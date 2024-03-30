package server.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import server.main.EPlayerNumber;
import server.main.Position;

public class MapBuilderVertical extends AMapBuilder { 
	private static final int Y_SHIFT_VERTICAL = 5;

	@Override
	public Map<Position, ServerMapNode> processHalfMap(Map<Position, ServerMapNode> halfmap, EPlayerNumber playerNum) {
		
		placeTreasure(halfmap, playerNum);
		
		if (placedFirstMapHalf) {
			if (!placedShiftedMapHalf) {
				halfmap =  shiftHalfMap(halfmap, playerNum);
				placedShiftedMapHalf = true;
			}
		}
		else {
			if (random.nextInt(2) == 0) {
				halfmap = shiftHalfMap(halfmap, playerNum);
				placedShiftedMapHalf = true;
			}
		}
		placedFirstMapHalf = true;
		return halfmap;
	}

	
	@Override
	protected Map<Position, ServerMapNode> shiftHalfMap(Map<Position, ServerMapNode> halfmap, EPlayerNumber playerNum) {
		Map<Position, ServerMapNode> shiftedMap = new HashMap<>();
		
		for (ServerMapNode mapNode : halfmap.values()) {
			Position shiftedPosition = new Position(mapNode.getX(), mapNode.getY()+Y_SHIFT_VERTICAL);
			ServerMapNode shiftedNode = new ServerMapNode(shiftedPosition.getX(), shiftedPosition.getY(), 
												mapNode.getTerrain(), mapNode.getCastle(), mapNode.getTreasure(), mapNode.getPlayerPosition());
			shiftedMap.put(shiftedPosition, shiftedNode);
		}
		
		return shiftedMap;

	}
}
