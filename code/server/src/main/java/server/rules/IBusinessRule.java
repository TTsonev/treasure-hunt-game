package server.rules;

import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.PlayerHalfMap;
import server.main.Position;
import server.map.ServerMapNode;

public interface IBusinessRule {
	public void enforceHalfMap(PlayerHalfMap halfMap);
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move);
}
