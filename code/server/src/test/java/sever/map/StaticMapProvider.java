package sever.map;

import java.util.HashSet;
import java.util.Set;

import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromclient.PlayerHalfMap;
import messagesbase.messagesfromclient.PlayerHalfMapNode;

public class StaticMapProvider {
	
	public static PlayerHalfMap getHalfMapValid() {
		Set<PlayerHalfMapNode> nodes = new HashSet<>();

		nodes.add(new PlayerHalfMapNode(0,0, true, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,2, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,3, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(1,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,1, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(1,2, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(2,0, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(2,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(3,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,3, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,4, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(3,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(4,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,5, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(4,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(4,9, ETerrain.Water));

		return new PlayerHalfMap("ABCDE", nodes);
	}
	
	public static PlayerHalfMap getHalfMapCastleOnWater() {
		Set<PlayerHalfMapNode> nodes = new HashSet<>();

		nodes.add(new PlayerHalfMapNode(0,0, true, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,2, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,3, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(1,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,1, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(1,2, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(2,0, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(2,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(3,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,3, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,4, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(3,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(4,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,5, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(4,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(4,9, ETerrain.Water));

		return new PlayerHalfMap("ABCDE", nodes);
	}
	
	public static PlayerHalfMap getHalfMapNoCastle() {
		Set<PlayerHalfMapNode> nodes = new HashSet<>();

		nodes.add(new PlayerHalfMapNode(0,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,2, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,3, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(1,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,1, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(1,2, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(2,0, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(2,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(3,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,3, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,4, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(3,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(4,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,5, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(4,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(4,9, ETerrain.Water));

		return new PlayerHalfMap("ABCDE", nodes);
	}
	
	
	public static PlayerHalfMap getHalfMapWaterEdge() {
		Set<PlayerHalfMapNode> nodes = new HashSet<>();

		nodes.add(new PlayerHalfMapNode(0,0, true, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,1, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,2, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,3, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,4, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,5, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,7, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,9, ETerrain.Water));

		
		nodes.add(new PlayerHalfMapNode(1,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,1, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(1,2, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(2,0, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(2,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(3,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,3, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,4, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(3,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(4,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,5, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(4,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(4,9, ETerrain.Water));

		return new PlayerHalfMap("ABCDE", nodes);
	}
	
	
	public static PlayerHalfMap getHalfMapIsland() {
		Set<PlayerHalfMapNode> nodes = new HashSet<>();

		nodes.add(new PlayerHalfMapNode(0,0, true, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,1, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,2, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,3, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(1,0, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,1, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(1,2, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(2,0, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(2,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(3,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,3, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,4, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(3,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(4,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,5, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(4,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(4,9, ETerrain.Water));

		return new PlayerHalfMap("ABCDE", nodes);
	}

	
	
	public static PlayerHalfMap getHalfMapWrongShape() {
		Set<PlayerHalfMapNode> nodes = new HashSet<>();

		nodes.add(new PlayerHalfMapNode(0,10, true, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,11, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,12, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,13, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,14, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,15, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,16, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,17, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,18, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,19, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(1,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,1, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(1,2, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(2,0, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(2,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(3,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,3, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,4, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(3,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(4,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,5, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(4,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(4,9, ETerrain.Water));

		return new PlayerHalfMap("ABCDE", nodes);
	}

	
	public static PlayerHalfMap getHalfMapWrongSize() {
		Set<PlayerHalfMapNode> nodes = new HashSet<>();

		nodes.add(new PlayerHalfMapNode(0,0, true, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,2, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,3, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,7, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(1,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,1, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(1,2, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(1,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(2,0, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(2,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(3,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,3, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,4, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(3,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(4,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,5, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(4,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(4,9, ETerrain.Water));

		return new PlayerHalfMap("ABCDE", nodes);
	}
	
	public static PlayerHalfMap getHalfMapLessWater() {
		Set<PlayerHalfMapNode> nodes = new HashSet<>();

		nodes.add(new PlayerHalfMapNode(0,0, true, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,2, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,3, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(0,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(0,8, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(0,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(1,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,1, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(1,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(1,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(2,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(2,9, ETerrain.Grass));

		nodes.add(new PlayerHalfMapNode(3,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,3, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,4, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(3,5, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,6, ETerrain.Water));
		nodes.add(new PlayerHalfMapNode(3,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(3,9, ETerrain.Grass));

		
		nodes.add(new PlayerHalfMapNode(4,0, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,1, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,2, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,3, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,4, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,5, ETerrain.Mountain));
		nodes.add(new PlayerHalfMapNode(4,6, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,7, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,8, ETerrain.Grass));
		nodes.add(new PlayerHalfMapNode(4,9, ETerrain.Water));

		return new PlayerHalfMap("ABCDE", nodes);
	}

}
