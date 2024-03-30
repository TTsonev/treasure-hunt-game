package server.rules;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import messagesbase.messagesfromclient.PlayerHalfMapNode;
import server.exceptions.HalfMapException;
import server.main.Position;
import server.map.MapConverter;
import server.map.ServerMapNode;

public class IslandsRule implements IBusinessRule {
	// TAKEN FROM https://en.wikipedia.org/wiki/Flood_fill
	// Pseudocode for the Floodfill Algorithm (modified to count connected non-water fields)		

	private static final int HALF_MAP_SIZE = 50;
	private static Logger loggerIslands = LoggerFactory.getLogger(IslandsRule.class);

	
	@Override
	public void enforceHalfMap(PlayerHalfMap halfMap) {
		int totalWaterFields = (int) halfMap.getMapNodes().stream()
											.filter(halfMapNode -> halfMapNode.getTerrain() == ETerrain.Water)
											.count();
		
		PlayerHalfMapNode nonWaterNode = halfMap.getMapNodes().stream()
												.filter(halfMapNode -> halfMapNode.getTerrain() != ETerrain.Water)
												.findAny().get();
		
		
		int countConnectedGrassAndMount = countFloodfill(halfMap, nonWaterNode);
		
		loggerIslands.debug("Counted {} Grass/Mountain Fields and {} Water Fields", countConnectedGrassAndMount, totalWaterFields);		
		if (countConnectedGrassAndMount != (HALF_MAP_SIZE - totalWaterFields) ) throw new HalfMapException("Islands detected!");
	}
	
	
	private static int countFloodfill(PlayerHalfMap halfMap, PlayerHalfMapNode node) {
		// TAKEN FROM START https://en.wikipedia.org/wiki/Flood_fill

		Queue<PlayerHalfMapNode> neighborsQueue = new LinkedList<>();
		Set<PlayerHalfMapNode> visited = new HashSet<>();
		int countConnectedGrassAndMount = 0;
		
		neighborsQueue.add(node);
		
		while (!neighborsQueue.isEmpty()) {
			PlayerHalfMapNode currentNode = neighborsQueue.poll();
			loggerIslands.debug("Floodfill reached ({}|{})", currentNode.getX(), currentNode.getY());		

			if (visited.contains(currentNode)) continue;

			neighborsQueue.addAll(getNeighbors(halfMap, currentNode));
			
			visited.add(currentNode);
			countConnectedGrassAndMount++;
		}
		return countConnectedGrassAndMount;
		// TAKEN FROM START https://en.wikipedia.org/wiki/Flood_fill
	}
	
	
	private static List<PlayerHalfMapNode> getNeighbors(PlayerHalfMap halfMap, PlayerHalfMapNode currentNode) {
		List<PlayerHalfMapNode> neighbors = new ArrayList<>();
		
		final int leftShift = -1;
		final int rightShift = 1;
		final int downShift = 1;
		final int upShit = -1;
		final int noShift = 0;

		
		int[][] shiftDirections = {{rightShift, noShift}, {leftShift, noShift}, {noShift, downShift}, {noShift, upShit}};
		for (int[] shiftPair : shiftDirections) {
			int xShift = shiftPair[0];
			int yShift = shiftPair[1];
			Optional<PlayerHalfMapNode> neighborNode = halfMap.getMapNodes().stream()
					.filter(halfMapNode -> halfMapNode.getX() == currentNode.getX()+xShift 
								&& halfMapNode.getY() == currentNode.getY()+yShift)
					.findFirst();
			if (neighborNode.isPresent() && neighborNode.get().getTerrain() != ETerrain.Water) 
				neighbors.add(neighborNode.get());
		}
		return neighbors;
	}
	
	@Override
	public void enforceMove(Map<Position, ServerMapNode> gameMap, Position currentPosition, EMove move) {		
	}
	
}
