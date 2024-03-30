package client.pathfinding;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.main.Position;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromserver.FullMapNode;

public class PathFinder {
	private static Logger loggerPathFinder = LoggerFactory.getLogger(PathFinder.class);

    public List<Position> calculatePath(Map<Position, FullMapNode> map, Position start, Position goal) {
    	// TAKEN FROM https://en.wikipedia.org/wiki/Breadth-first_search
    	// Pseudocode for the Breadth First Search Algorithm (modified to keep track of path to goal)
    	
    		// TAKEN FROM START https://en.wikipedia.org/wiki/Breadth-first_search
        Set<Position> visited = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Position> parentMap = new HashMap<>();
        
        queue.add(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
	        	Position current = queue.remove();
	        	loggerPathFinder.trace("Analyzing: {}", current);

            if (current.equals(goal)) {
                List<Position> path = new ArrayList<>();
                while (current != null) {
                		if (map.get(current).getTerrain() == ETerrain.Water) throw new RuntimeException("Water Field in Path!");
                    path.add(current);
                    current = parentMap.get(current);
                }
                Collections.reverse(path);
                
                loggerPathFinder.trace("Path found: {}", path);                
                return path;
            }
            
            for (Position neighbor : getNeighbors(map, current)) {
                if (!visited.contains(neighbor) && !(map.get(neighbor).getTerrain()==ETerrain.Water)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
         
        return Collections.emptyList();
    }
	// TAKEN FROM END https://en.wikipedia.org/wiki/Breadth-first_search

    private static List<Position> getNeighbors(Map<Position, FullMapNode> map, Position position) {
        List<Position> neighbors = new ArrayList<>();
        int xCoord = position.getX();
        int yCoord = position.getY();
        
        if (map.containsKey(new Position(xCoord, yCoord-1))) {
            neighbors.add(new Position(xCoord, yCoord-1));
        }
        if (map.containsKey(new Position(xCoord, yCoord+1))) {
            neighbors.add(new Position(xCoord, yCoord+1));
        }
        if (map.containsKey(new Position(xCoord-1, yCoord))) {
            neighbors.add(new Position(xCoord-1, yCoord));
        }
        if (map.containsKey(new Position(xCoord+1, yCoord))) {
            neighbors.add(new Position(xCoord+1, yCoord));
        }
        
        return neighbors;
    }
    
}
