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


public class CastleNavigatorTests {

	@Test
	public void givenMap_selectGoalAndGenerateMoves_expectedQueueOfMoves() {
		//arrange
		Map<Position, FullMapNode> map = StaticMapProvider.getStaticMap();
		
		CastleNavigator castleNavigator = new CastleNavigator(map);
		Position defaultPlayerPosition = new Position(1,6);
		
		//act
		Queue<EMove> nextMoves = castleNavigator.navigateNextMoves(defaultPlayerPosition);

		//assert
		assertThat(nextMoves.size(), is(greaterThanOrEqualTo(1)));
	}
	
	
	@Test
	public void givenMap_navigateToEnemyMapHalf_expectedQueueOfMoves() {
		//arrange
		Map<Position, FullMapNode> map = StaticMapProvider.getStaticMap();
		
		CastleNavigator castleNavigator = new CastleNavigator(map);
		Position defaultPlayerPosition = new Position(0,0);
		
		//act
		Queue<EMove> nextMoves = castleNavigator.navigateToEnemyHalfMap(defaultPlayerPosition);

		//assert
		assertThat(nextMoves.size(), is(greaterThanOrEqualTo(1)));
	}
	
	
	@Test
	public void givenMapAndCastlePosition_GenerateMovesToCastle_expectedQueueOfMoves() {
		//arrange
		Map<Position, FullMapNode> map = StaticMapProvider.getStaticMapCastlePhase();
		
		CastleNavigator castleNavigator = new CastleNavigator(map);
		Position defaultPlayerPosition = new Position(6,1);
		Position defaultCastlePosition = new Position(9,8);

		//act
		Queue<EMove> nextMoves = castleNavigator.navigateToCastle(defaultPlayerPosition, defaultCastlePosition);

		//assert
		assertThat(nextMoves.size(), is(greaterThanOrEqualTo(1)));
	}


}
