package client.pathfinding;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import client.main.Position;
import client.main.StaticMapProvider;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromserver.FullMapNode;


public class PathFinderTests {

	@ParameterizedTest
	@MethodSource("pathFinderTestDataSource")
	public void givenMapStartTarget_calculatePath_expectedNoWaterFields(Position start, Position goal) {
		//arrange
		Map<Position, FullMapNode> map = StaticMapProvider.getStaticMap();
		PathFinder pathFinder = new PathFinder();
		//act
		List<Position> path = pathFinder.calculatePath(map, start, goal);
		
		//enforce
		int waterFields = (int) path.stream().map(node -> map.get(node).getTerrain()).filter(ter -> ter==ETerrain.Water).count();
		assertThat(waterFields, is(equalTo(0)));
	}
	
	private static Stream<Arguments> pathFinderTestDataSource() {
		return Stream.of(Arguments.of(new Position(0,0), new Position(2,3)), 
						Arguments.of(new Position(1,7), new Position(1,2)),
						Arguments.of(new Position(3,3), new Position(4,1)),
						Arguments.of(new Position(4,2), new Position(1,3)),
						Arguments.of(new Position(0,9), new Position(1,6)),
						Arguments.of(new Position(1,3), new Position(0,7)),
						Arguments.of(new Position(0,2), new Position(1,9)),
						Arguments.of(new Position(2,9), new Position(0,3)));
	}
	
}
