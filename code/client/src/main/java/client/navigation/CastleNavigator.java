package client.navigation;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.main.Position;
import client.pathfinding.PathFinder;
import messagesbase.messagesfromclient.EMove;
import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromserver.EPlayerPositionState;
import messagesbase.messagesfromserver.FullMapNode;

public class CastleNavigator implements NavigationStrategy {
	private static Logger loggerCastleNav = LoggerFactory.getLogger(CastleNavigator.class);

	private Map<Position, FullMapNode> enemyHalfMap;
	private Map<Position, FullMapNode> gameMap;
	private Set<Position> visited;
	private PathFinder pathFinder;

	public CastleNavigator(Map<Position, FullMapNode> gameMap) {
		this.enemyHalfMap = extractOppositeMapHalf(gameMap);
		this.gameMap = gameMap;
		visited = new HashSet<>();
		
		pathFinder = new PathFinder();
	}
	
	
	@Override
	public Queue<EMove> navigateNextMoves(Position currentPosition) {
		visited.add(currentPosition);

		Optional<Position> goal = chooseMountainGoal(currentPosition);
		
		if (goal.isEmpty()) {
			goal = chooseGrassGoal(currentPosition);
		}
		
		if (goal.isEmpty()) throw new NavigationException(currentPosition);
		
		loggerCastleNav.debug("Requesting Path from {} to {}", currentPosition, goal.get());
		List<Position> path = pathFinder.calculatePath(enemyHalfMap, currentPosition, goal.get());
		
		if (path.isEmpty()) throw new NavigationException(currentPosition, goal.get());
		path.remove(0);

		return translatePathToMoves(path, currentPosition);
	}
	
	
	private Optional<Position> chooseMountainGoal(Position currentPosition) {
	    Optional<Position> goal = Optional.empty();
	    
	    visited.add(currentPosition);
	    
		for (Entry<Position, FullMapNode> mapEntry : enemyHalfMap.entrySet()) {
			if (!visited.contains(mapEntry.getKey())
				&& mapEntry.getValue().getTerrain()==ETerrain.Mountain) {
					goal = Optional.of(mapEntry.getKey());
					break;
			}
		}
		return goal;
	}
	
	private Optional<Position> chooseGrassGoal(Position currentPosition) {
	    Optional<Position> goal = Optional.empty();
	    
	    visited.add(currentPosition);
	    
		for (Entry<Position, FullMapNode> mapEntry : enemyHalfMap.entrySet()) {
			if (!visited.contains(mapEntry.getKey())
				&& mapEntry.getValue().getTerrain()==ETerrain.Grass) {
					goal = Optional.of(mapEntry.getKey());
					break;
			}
		}
		return goal;
	}
	
	
	public Queue<EMove> navigateToEnemyHalfMap(Position currentPosition) {				
		Optional<Position> goal = chooseMountainGoal(currentPosition);
		if (goal.isEmpty()) {
			goal = chooseGrassGoal(currentPosition);
		}

		loggerCastleNav.debug("Requesting Path from {} to {}", currentPosition, goal.get());
		List<Position> path = pathFinder.calculatePath(gameMap, currentPosition, goal.get());
		path.remove(0);
		
		return translatePathToMoves(path, currentPosition);
	}
	
	
	public Queue<EMove> navigateToCastle(Position currentPosition, Position goal) {	
		loggerCastleNav.debug("Navigating from {} to Castle at {}", currentPosition, goal);
		List<Position> path = pathFinder.calculatePath(gameMap, currentPosition, goal);
		if (path.isEmpty()) throw new NavigationException(currentPosition, goal);
		path.remove(0);
		return translatePathToMoves(path, currentPosition);
	}
	
	
	
	private Position extractCurrentPosition(Map<Position, FullMapNode> gameMap) {
		Position currentPosition = gameMap.entrySet().stream()
				.filter(node -> node.getValue().getPlayerPositionState().equals(EPlayerPositionState.MyPlayerPosition) 
							 || node.getValue().getPlayerPositionState().equals(EPlayerPositionState.BothPlayerPosition))
				.findFirst().get().getKey();
		loggerCastleNav.debug("Extracted Current Position: {})", currentPosition);
		return currentPosition;
	}
	
	private Comparator<Position> getPositionComparator() {
		return new Comparator<Position>() {
		    @Override
		    public int compare(Position pos1, Position pos2) {
		        return Integer.compare(pos1.getY(), pos2.getY());
		    }
		};
	}
	
	private Map<Position, FullMapNode> extractOppositeMapHalf(Map<Position, FullMapNode> gameMap) {
		
		Position currentPosition = extractCurrentPosition(gameMap);
		Comparator<Position> posComparator = getPositionComparator();

		int maxY = Collections.max(gameMap.keySet(), posComparator).getY();
		
		if (maxY == 4) {
			if (currentPosition.getX() <= 9) {
				loggerCastleNav.debug("Extracted [Right] Half of [Horizontal] Map");
				return gameMap.entrySet().stream().filter(node -> node.getKey().getX()>9)
						 		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}
			else {
				loggerCastleNav.debug("Extracted [Left] Half of [Horizontal] Map");
				return gameMap.entrySet().stream().filter(node -> node.getKey().getX()<=9)
						 		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}
		}
		
		if (maxY == 9) {
			if (currentPosition.getY() <= 4) {
				loggerCastleNav.debug("Extracted [Bottom] Half of [Square] Map)");
				return gameMap.entrySet().stream().filter(node -> node.getKey().getY()>4)
				 		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}
			else {
				loggerCastleNav.debug("Extracted [Top] Half of [Square] Map)");
				return gameMap.entrySet().stream().filter(node -> node.getKey().getY()<=4)
						 	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			}
		}
		else throw new RuntimeException("Map shape wrong!");
	}
	
	
	@Override
	public void addToVisited(Position position) {
		//if (gameMap.containsKey(position)) throw new RuntimeException("Position not in the map!");
		visited.add(position);
	}
	

	private Queue<EMove> translatePathToMoves(List<Position> path, Position currentPosition) {
		loggerCastleNav.trace("Translating Path: {}", path);

		Queue<EMove> nextMoves = new LinkedList<>();

		for (Position nextPosition : path) {	
			int factorCurr = 0;
			int factorNext = 0;
			if(gameMap.get(currentPosition).getTerrain()==ETerrain.Mountain) factorCurr++;
			if(gameMap.get(nextPosition).getTerrain()==ETerrain.Mountain) factorNext++;
			if(gameMap.get(nextPosition).getTerrain()==ETerrain.Water) throw new RuntimeException("Attempted move towards a Water Field " + nextPosition);
			for (int i=0; i<2+factorCurr+factorNext; i++) {
				if(currentPosition.getX() - nextPosition.getX() == -1) nextMoves.add(EMove.Right);
				else if(currentPosition.getX() - nextPosition.getX() == 1) nextMoves.add(EMove.Left);
				else if(currentPosition.getY() - nextPosition.getY() == 1) nextMoves.add(EMove.Up);
				else if(currentPosition.getY() - nextPosition.getY() == -1) nextMoves.add(EMove.Down);
				else loggerCastleNav.error("Attempted Teleportation from {} to {}", currentPosition, nextPosition);
			}
			visited.add(currentPosition);
			loggerCastleNav.debug("{} added to visited", currentPosition);
			currentPosition = nextPosition;
		}
		loggerCastleNav.trace("Next set of moves: {}", nextMoves);
		return nextMoves;
	}

}
