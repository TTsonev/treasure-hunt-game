package client.map;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.main.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMapNode;


public class MapGenerator {
	// TAKEN FROM https://en.wikipedia.org/wiki/Flood_fill
	// Pseudocode for the Floodfill Algorithm (modified to count connected non-water fields)		
	private static Logger loggerMapGenerator = LoggerFactory.getLogger(MapGenerator.class);
	private static Map<Position, PlayerHalfMapNode> halfMap;
	
	private static final int HALFMAP_SIZE = 50;
	private static final int HALFMAP_X = 10;
	private static final int HALFMAP_Y = 5;
	private static final int MIN_GRASS_FIELDS = 24;
	private static final int MIN_WATER_FIELDS = 7;
	private static final int MIN_MOUNTAIN_FIELDS = 5;

	public static Map<Position, PlayerHalfMapNode> generateMap() {
		loggerMapGenerator.warn("Generating new HalfMap!");
		halfMap = new HashMap<Position, PlayerHalfMapNode>();
		fillMap();
		setCastleNextToMountain();
		//setCastleAwayFromMountain();
		//setCastleOnFirstHashedGrass();
		if (validateMap()) return halfMap; 		
		else return generateMap();
	}
	
	
	private static void fillMap() {
		List<ETerrain> terrains = new ArrayList<ETerrain>(); 
		for(int i=0;i<MIN_GRASS_FIELDS;i++) terrains.add(ETerrain.Grass);
		for(int i=0;i<MIN_WATER_FIELDS;i++) terrains.add(ETerrain.Water);
		for(int i=0;i<MIN_MOUNTAIN_FIELDS;i++) terrains.add(ETerrain.Mountain);
		
		Random random = new Random();
		for (int xCoord=0; xCoord<HALFMAP_X; xCoord++) {
			for (int yCoord=0; yCoord<HALFMAP_Y; yCoord++) {
				ETerrain randomTerrain = terrains.get(random.nextInt(terrains.size()));
				halfMap.put(new Position(xCoord, yCoord), new PlayerHalfMapNode(xCoord, yCoord, false, randomTerrain));
				loggerMapGenerator.debug("Added {} node at ({}|{})", randomTerrain, xCoord, yCoord);
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private static void setCastleNextToMountain() {	
		Set<Position> attemptedPosition = new HashSet<>();
		
		while(true) {
			Position mountainPosition = halfMap.entrySet().stream()
												.filter(entry -> entry.getValue().getTerrain()==ETerrain.Mountain)
												.findAny().get().getKey();
			// next
			if (attemptedPosition.contains(mountainPosition)) continue;
			attemptedPosition.add(mountainPosition);
			
			int xMountain = mountainPosition.getX();
			int yMountain = mountainPosition.getY();

			int[] shiftDirections = {-1,0,1};
			for (int xShift : shiftDirections) {
				for (int yShift : shiftDirections) {
					if (xShift == 0 && yShift == 0) continue;
					int xCoord = xMountain + xShift;
					int yCoord = yMountain + yShift;
					PlayerHalfMapNode visibleNode = halfMap.get(new Position(xCoord, yCoord));
					if (visibleNode != null && visibleNode.getTerrain()==ETerrain.Grass) {
		    				halfMap.put(new Position(xCoord, yCoord), new PlayerHalfMapNode(xCoord, yCoord, true, ETerrain.Grass));
		    				loggerMapGenerator.warn("Set castle next to mountain at ({}|{})", xCoord, yCoord);
		    				return;
					}
				}
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private static void setCastleAwayFromMountain() {		
		Set<Position> attemptedPosition = new HashSet<>();
		
		while(true) {
			Position grassPosition = halfMap.entrySet().stream()
												.filter(entry -> entry.getValue().getTerrain()==ETerrain.Grass)
												.findAny().get().getKey();
			
			if (attemptedPosition.contains(grassPosition)) continue;
			attemptedPosition.add(grassPosition);
			
			int xGrass = grassPosition.getX();
			int yGrass = grassPosition.getY();
			
			boolean nextToMount = false;
			
			int[] shiftDirections = {-1,0,1};
			for (int xShift : shiftDirections) {
				for (int yShift : shiftDirections) {
					if (xShift == 0 && yShift == 0) continue;
					int xCoord = xGrass + xShift;
					int yCoord = yGrass + yShift;
					PlayerHalfMapNode neighborNode = halfMap.get(new Position(xCoord, yCoord));
					if (neighborNode != null && neighborNode.getTerrain()==ETerrain.Mountain) {
						nextToMount = true;
					}
				}
			}
			if (nextToMount) continue;
			else {
				halfMap.put(new Position(xGrass,yGrass), new PlayerHalfMapNode(xGrass,yGrass, true, ETerrain.Grass));
				loggerMapGenerator.warn("Set castle away from mountain at ({}|{})", xGrass,yGrass);
				return;
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private static void setCastleOnFirstHashedGrass() {
		for (Map.Entry<Position, PlayerHalfMapNode> node : halfMap.entrySet()) {
			if (node.getValue().getTerrain() == ETerrain.Grass) {
	        			halfMap.put(node.getKey(), new PlayerHalfMapNode(node.getKey().getX(), node.getKey().getY(), true, ETerrain.Grass));
	    				loggerMapGenerator.warn("Set castle at first hashed Grass field ({}|{})", node.getKey().getX(), node.getKey().getY());
	        			break;
			}
	    }
	}
	
	
	private static boolean validateMap() {
		int totalGrassFields = (int) halfMap.values().stream().filter(n -> n.getTerrain() == ETerrain.Grass).count();
		int totalWaterFields = (int) halfMap.values().stream().filter(n -> n.getTerrain() == ETerrain.Water).count();
		int totalMountainFields = HALFMAP_SIZE - (totalGrassFields + totalWaterFields);
		
		loggerMapGenerator.debug("Map contains {} Grass | {} Water | {} Mountain Fields", totalGrassFields, totalWaterFields, totalMountainFields);
		
		if (totalGrassFields < MIN_GRASS_FIELDS ||
			totalWaterFields < MIN_WATER_FIELDS ||
			totalMountainFields < MIN_MOUNTAIN_FIELDS ||
			halfMap.values().stream().filter(n -> n.getX() == 0).filter(n -> n.getTerrain() == ETerrain.Water).count() > 2 ||
			halfMap.values().stream().filter(n -> n.getX() == 9).filter(n -> n.getTerrain() == ETerrain.Water).count() > 2 ||
			halfMap.values().stream().filter(n -> n.getY() == 0).filter(n -> n.getTerrain() == ETerrain.Water).count() > 4 ||
			halfMap.values().stream().filter(n -> n.getY() == 4).filter(n -> n.getTerrain() == ETerrain.Water).count() > 4) {
				loggerMapGenerator.warn("Map invalid (number of fields)!");	
				return false;
		}
				
		int connectedAccessibleFields = 0;
		
		for (Map.Entry<Position, PlayerHalfMapNode> node : halfMap.entrySet()) {
			if (node.getValue().getTerrain() == ETerrain.Grass || node.getValue().getTerrain() == ETerrain.Mountain) {
				connectedAccessibleFields = countFloodfill(node.getValue(), new HashSet<PlayerHalfMapNode>());
				break;
			}
	    }
				
		if (connectedAccessibleFields == (totalGrassFields + totalMountainFields)) {
			loggerMapGenerator.warn("Map valid!");
			return true;
		}
		else {
			loggerMapGenerator.warn("Map invalid (islands)!");
			return false;
		}
		
	}
		
	private static int countFloodfill(PlayerHalfMapNode node, Set<PlayerHalfMapNode> visited) {
		// TAKEN FROM START https://en.wikipedia.org/wiki/Flood_fill
		Queue<PlayerHalfMapNode> neighborsQueue = new LinkedList<>();
		int countConnectedGrassAndMount = 0;
		
		neighborsQueue.add(node);
		
		while (!neighborsQueue.isEmpty()) {
			PlayerHalfMapNode currentNode = neighborsQueue.poll();
			if (currentNode == null || currentNode.getTerrain() == ETerrain.Water || visited.contains(currentNode)) continue;
			int xCoord = currentNode.getX();
			int yCoord = currentNode.getY();
			neighborsQueue.add(halfMap.get(new Position(xCoord+1, yCoord)));
			neighborsQueue.add(halfMap.get(new Position(xCoord-1, yCoord)));
			neighborsQueue.add(halfMap.get(new Position(xCoord, yCoord+1)));
			neighborsQueue.add(halfMap.get(new Position(xCoord, yCoord-1)));
			visited.add(currentNode);
			countConnectedGrassAndMount++;
		}
		return countConnectedGrassAndMount;
		// TAKEN FROM END https://en.wikipedia.org/wiki/Flood_fill
	}

	
	
	
}
