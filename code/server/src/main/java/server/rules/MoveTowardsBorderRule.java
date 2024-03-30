package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.exceptions.MoveException;
import server.main.EPlayerNumber;
import server.main.Position;
import server.map.EPlayerServer;
import server.map.ServerMapNode;

public class MoveTowardsBorderRule implements IBusinessRule {

	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {
		
		Position goalPosition = currentPosition.findNeighbor(move);
		if (!gameMap.containsKey(goalPosition)) throw new MoveException("Goal Position " + goalPosition.toString() + "invalid!");;
	}
	
	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {

	}

}
