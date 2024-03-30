package server.map;

import java.util.Arrays;
import java.util.List;

import messagesbase.messagesfromclient.PlayerHalfMap;
import server.rules.IBusinessRule;
import server.rules.CastleCountRule;
import server.rules.CastleTerrainRule;
import server.rules.CountGrassFieldsRule;
import server.rules.CountMountFieldsRule;
import server.rules.CountWaterFieldsRule;
import server.rules.IslandsRule;
import server.rules.MapShapeRule;
import server.rules.MapSizeRule;
import server.rules.WaterEdgesRule;

public class MapValidator {

	private static final List<IBusinessRule> halfmapRules = Arrays.asList(
			new MapSizeRule(), new MapShapeRule(),
			new CastleCountRule(), new CastleTerrainRule(), 
			new CountGrassFieldsRule(), new CountWaterFieldsRule(), new CountMountFieldsRule(), 
			new WaterEdgesRule(), new IslandsRule());
	
	public static void validateHalfMap(PlayerHalfMap halfMap) {
		halfmapRules.forEach(rule -> rule.enforceHalfMap(halfMap));
	} 
}
