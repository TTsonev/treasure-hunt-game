package server.moves;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import messagesbase.messagesfromclient.EMove;
import server.main.Position;
import server.map.ServerMapNode;
import server.rules.IBusinessRule;
import server.rules.MoveTowardsBorderRule;
import server.rules.MoveTowardsWaterRule;

public class MoveValidator {
	private static final List<IBusinessRule> moveRules = Arrays.asList(new MoveTowardsBorderRule(), new MoveTowardsWaterRule());

	public static void validateMove(Map<Position, ServerMapNode> gameMap, Position playerPosition, EMove move) {
		moveRules.forEach(rule -> rule.enforceMove(gameMap, playerPosition, move));
	} 
}
