package client.navigation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Map;
import java.util.Queue;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.jupiter.api.Test;

import client.main.Position;
import client.main.StaticMapProvider;
import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromserver.FullMapNode;


public class TreasureNavigatorTests {
	
	@Test
	public void givenMap_selectGoalAndGenerateMoves_expectedQueueOfMoves() {
		//arrange
		Map<Position, FullMapNode> map = StaticMapProvider.getStaticMap();
		
		TreasureNavigator treasureNavigator = new TreasureNavigator(map);
		Position defaultPlayerPosition = new Position(0,0);
		
		//act
		Queue<EMove> nextMoves = treasureNavigator.navigateNextMoves(defaultPlayerPosition);

		//assert
		assertThat(nextMoves.size(), is(greaterThanOrEqualTo(1)));
	}
	
	
	@Test
	public void givenMapAndTreasurePosition_generateMovesToTreasure_expectedQueueOfMoves() {
		//arrange
		Map<Position, FullMapNode> map = StaticMapProvider.getStaticMap();
		
		TreasureNavigator treasureNavigator = new TreasureNavigator(map);
		Position defaultPlayerPosition = new Position(0,0);
		Position defaultTreasurePosition = new Position(3,1);

		//act
		Queue<EMove> nextMoves = treasureNavigator.navigateToTreasure(defaultPlayerPosition, defaultTreasurePosition);

		//assert
		assertThat(nextMoves.size(), is(greaterThanOrEqualTo(1)));
	}

	
}
