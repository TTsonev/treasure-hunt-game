package sever.map;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import messagesbase.messagesfromclient.PlayerHalfMap;
import server.main.EPlayerNumber;
import server.main.Position;
import server.map.MapConverter;
import server.map.ServerMapNode;

public class MapConverterTests {
	
	@Test
	public void givenPlayerHalfMap_ConvertToServerMap_expectedAllNode() {
		// arrange
		PlayerHalfMap playerHalfMap = StaticMapProvider.getHalfMapValid();
		EPlayerNumber placeholderNum = EPlayerNumber.Player1; 
		
		//act
		Map<Position, ServerMapNode> serverHalfMap = MapConverter.convertHalfMapToServerMap(playerHalfMap.getMapNodes(), placeholderNum);
	
		//assert
		assertThat(serverHalfMap.size(), equalTo(playerHalfMap.getMapNodes().size()));
	}
}
