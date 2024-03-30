package client.map;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.jupiter.api.Test;

import client.main.Position;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMapNode;

public class MapGeneratorTests {
	private static final int HALFMAP_SIZE = 50;
	private static final int MIN_GRASS_FIELDS = 24;
	private static final int MIN_WATER_FIELDS = 7;
	private static final int MIN_MOUNTAIN_FIELDS = 5;
	
	@Test
	public void generateMap_expected50Nodes() {
		// arrange
		
		// act
		Map<Position, PlayerHalfMapNode> halfMap = MapGenerator.generateMap();
		
		//assert
		assertThat(halfMap.size(), is(equalTo(HALFMAP_SIZE)));
	}
	
	@Test
	public void generateMap_expectedMin25GrassFields() {
		// arrange
		Map<Position, PlayerHalfMapNode> halfMap = MapGenerator.generateMap();

		// act
		int waterFields = (int) halfMap.values().stream()
										.map(node -> node.getTerrain())
										.filter(ter -> ter==ETerrain.Grass).count();
		//assert
		assertThat(waterFields, is(greaterThanOrEqualTo(MIN_GRASS_FIELDS)));
	}
	
	
	@Test
	public void generateMap_expectedMin7WaterFields() {
		// arrange
		Map<Position, PlayerHalfMapNode> halfMap = MapGenerator.generateMap();
		
		// act
		int waterFields = (int) halfMap.values().stream()
										.map(node -> node.getTerrain())
										.filter(ter -> ter==ETerrain.Water).count();
		//assert		
		assertThat(waterFields, is(greaterThanOrEqualTo(MIN_WATER_FIELDS)));
	}
	
	@Test
	public void generateMap_expectedMin5MountainFields() {
		// arrange
		Map<Position, PlayerHalfMapNode> halfMap = MapGenerator.generateMap();
		
		// act
		int waterFields = (int) halfMap.values().stream()
										.map(node -> node.getTerrain())
										.filter(ter -> ter==ETerrain.Mountain).count();
		
		//assert
		assertThat(waterFields, is(greaterThanOrEqualTo(MIN_MOUNTAIN_FIELDS)));
	}
	
}
