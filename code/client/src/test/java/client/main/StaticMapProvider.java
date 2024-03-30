package client.main;

import java.util.HashMap;
import java.util.Map;

import messagesbase.messagesfromclient.ETerrain;
import messagesbase.messagesfromserver.EFortState;
import messagesbase.messagesfromserver.EPlayerPositionState;
import messagesbase.messagesfromserver.ETreasureState;
import messagesbase.messagesfromserver.FullMapNode;

public class StaticMapProvider {
	public static Map<Position, FullMapNode> getStaticMap() {
		Map<Position, FullMapNode> map = new HashMap<>();

		map.put(new Position(0,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.MyPlayerPosition, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 0));
		map.put(new Position(0,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 1));
		map.put(new Position(0,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 2));
		map.put(new Position(0,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 3));
		map.put(new Position(0,4), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 4));
		map.put(new Position(0,5), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 5));
		map.put(new Position(0,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 6));
		map.put(new Position(0,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 7));
		map.put(new Position(0,8), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 8));
		map.put(new Position(0,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 9));

		map.put(new Position(1,0), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 0));
		map.put(new Position(1,1), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 1));
		map.put(new Position(1,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 2));
		map.put(new Position(1,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 3));
		map.put(new Position(1,4), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 4));
		map.put(new Position(1,5), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 5));
		map.put(new Position(1,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 6));
		map.put(new Position(1,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 7));
		map.put(new Position(1,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 8));
		map.put(new Position(1,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 9));

		map.put(new Position(2,0), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 0));
		map.put(new Position(2,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 1));
		map.put(new Position(2,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 2));
		map.put(new Position(2,3), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 3));
		map.put(new Position(2,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 4));
		map.put(new Position(2,5), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 5));
		map.put(new Position(2,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 6));
		map.put(new Position(2,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 7));
		map.put(new Position(2,8), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 8));
		map.put(new Position(2,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 9));

		map.put(new Position(3,0), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 0));
		map.put(new Position(3,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.MyTreasureIsPresent, EFortState.NoOrUnknownFortState, 3, 1));
		map.put(new Position(3,2), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 2));
		map.put(new Position(3,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 3));
		map.put(new Position(3,4), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 4));
		map.put(new Position(3,5), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 5));
		map.put(new Position(3,6), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 6));
		map.put(new Position(3,7), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 7));
		map.put(new Position(3,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 8));
		map.put(new Position(3,9), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 9));

		map.put(new Position(4,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 0));
		map.put(new Position(4,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 1));
		map.put(new Position(4,2), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 2));
		map.put(new Position(4,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 3));
		map.put(new Position(4,4), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 4));
		map.put(new Position(4,5), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 5));
		map.put(new Position(4,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 6));
		map.put(new Position(4,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 7));
		map.put(new Position(4,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 8));
		map.put(new Position(4,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 9));

		map.put(new Position(5,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 0));
		map.put(new Position(5,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 1));
		map.put(new Position(5,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 2));
		map.put(new Position(5,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 3));
		map.put(new Position(5,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 4));
		map.put(new Position(5,5), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 5));
		map.put(new Position(5,6), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 6));
		map.put(new Position(5,7), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 7));
		map.put(new Position(5,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 8));
		map.put(new Position(5,9), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 9));

		map.put(new Position(6,0), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 0));
		map.put(new Position(6,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 1));
		map.put(new Position(6,2), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 2));
		map.put(new Position(6,3), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 3));
		map.put(new Position(6,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 4));
		map.put(new Position(6,5), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 5));
		map.put(new Position(6,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 6));
		map.put(new Position(6,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 7));
		map.put(new Position(6,8), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 8));
		map.put(new Position(6,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 9));

		map.put(new Position(7,0), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 0));
		map.put(new Position(7,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 1));
		map.put(new Position(7,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 2));
		map.put(new Position(7,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 3));
		map.put(new Position(7,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 4));
		map.put(new Position(7,5), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 5));
		map.put(new Position(7,6), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 6));
		map.put(new Position(7,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 7));
		map.put(new Position(7,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 8));
		map.put(new Position(7,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 9));

		map.put(new Position(8,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 0));
		map.put(new Position(8,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 1));
		map.put(new Position(8,2), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 2));
		map.put(new Position(8,3), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 3));
		map.put(new Position(8,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 4));
		map.put(new Position(8,5), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 5));
		map.put(new Position(8,6), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 6));
		map.put(new Position(8,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 7));
		map.put(new Position(8,8), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 8));
		map.put(new Position(8,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 9));

		map.put(new Position(9,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 0));
		map.put(new Position(9,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 1));
		map.put(new Position(9,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 2));
		map.put(new Position(9,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 3));
		map.put(new Position(9,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 4));
		map.put(new Position(9,5), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 5));
		map.put(new Position(9,6), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 6));
		map.put(new Position(9,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 7));
		map.put(new Position(9,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 8));
		map.put(new Position(9,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 9));

		return map;
	}
	
	
	public static Map<Position, FullMapNode> getStaticMapCastlePhase() {
		Map<Position, FullMapNode> map = new HashMap<>();

		map.put(new Position(0,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.MyFortPresent, 0, 0));
		map.put(new Position(0,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 1));
		map.put(new Position(0,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 2));
		map.put(new Position(0,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 3));
		map.put(new Position(0,4), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 4));
		map.put(new Position(0,5), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 5));
		map.put(new Position(0,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 6));
		map.put(new Position(0,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 7));
		map.put(new Position(0,8), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 8));
		map.put(new Position(0,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 0, 9));

		map.put(new Position(1,0), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 0));
		map.put(new Position(1,1), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 1));
		map.put(new Position(1,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 2));
		map.put(new Position(1,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 3));
		map.put(new Position(1,4), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 4));
		map.put(new Position(1,5), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 5));
		map.put(new Position(1,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 6));
		map.put(new Position(1,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 7));
		map.put(new Position(1,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 8));
		map.put(new Position(1,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 1, 9));

		map.put(new Position(2,0), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 0));
		map.put(new Position(2,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 1));
		map.put(new Position(2,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 2));
		map.put(new Position(2,3), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 3));
		map.put(new Position(2,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 4));
		map.put(new Position(2,5), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 5));
		map.put(new Position(2,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 6));
		map.put(new Position(2,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 7));
		map.put(new Position(2,8), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 8));
		map.put(new Position(2,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 2, 9));

		map.put(new Position(3,0), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 0));
		map.put(new Position(3,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 1));
		map.put(new Position(3,2), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 2));
		map.put(new Position(3,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 3));
		map.put(new Position(3,4), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 4));
		map.put(new Position(3,5), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 5));
		map.put(new Position(3,6), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 6));
		map.put(new Position(3,7), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 7));
		map.put(new Position(3,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 8));
		map.put(new Position(3,9), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 3, 9));

		map.put(new Position(4,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 0));
		map.put(new Position(4,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 1));
		map.put(new Position(4,2), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 2));
		map.put(new Position(4,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 3));
		map.put(new Position(4,4), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 4));
		map.put(new Position(4,5), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 5));
		map.put(new Position(4,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 6));
		map.put(new Position(4,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 7));
		map.put(new Position(4,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 8));
		map.put(new Position(4,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 4, 9));

		map.put(new Position(5,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 0));
		map.put(new Position(5,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 1));
		map.put(new Position(5,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 2));
		map.put(new Position(5,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 3));
		map.put(new Position(5,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 4));
		map.put(new Position(5,5), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 5));
		map.put(new Position(5,6), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 6));
		map.put(new Position(5,7), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 7));
		map.put(new Position(5,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 8));
		map.put(new Position(5,9), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 5, 9));

		map.put(new Position(6,0), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 0));
		map.put(new Position(6,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.MyPlayerPosition, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 1));
		map.put(new Position(6,2), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 2));
		map.put(new Position(6,3), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 3));
		map.put(new Position(6,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 4));
		map.put(new Position(6,5), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 5));
		map.put(new Position(6,6), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 6));
		map.put(new Position(6,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 7));
		map.put(new Position(6,8), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 8));
		map.put(new Position(6,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 6, 9));

		map.put(new Position(7,0), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 0));
		map.put(new Position(7,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 1));
		map.put(new Position(7,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 2));
		map.put(new Position(7,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 3));
		map.put(new Position(7,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 4));
		map.put(new Position(7,5), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 5));
		map.put(new Position(7,6), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 6));
		map.put(new Position(7,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 7));
		map.put(new Position(7,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 8));
		map.put(new Position(7,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 7, 9));

		map.put(new Position(8,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 0));
		map.put(new Position(8,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 1));
		map.put(new Position(8,2), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 2));
		map.put(new Position(8,3), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 3));
		map.put(new Position(8,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 4));
		map.put(new Position(8,5), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 5));
		map.put(new Position(8,6), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 6));
		map.put(new Position(8,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 7));
		map.put(new Position(8,8), new FullMapNode(ETerrain.Water, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 8));
		map.put(new Position(8,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 8, 9));

		map.put(new Position(9,0), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 0));
		map.put(new Position(9,1), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 1));
		map.put(new Position(9,2), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 2));
		map.put(new Position(9,3), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 3));
		map.put(new Position(9,4), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 4));
		map.put(new Position(9,5), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 5));
		map.put(new Position(9,6), new FullMapNode(ETerrain.Mountain, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 6));
		map.put(new Position(9,7), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 7));
		map.put(new Position(9,8), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.EnemyFortPresent, 9, 8));
		map.put(new Position(9,9), new FullMapNode(ETerrain.Grass, EPlayerPositionState.NoPlayerPresent, ETreasureState.NoOrUnknownTreasureState, EFortState.NoOrUnknownFortState, 9, 9));

		return map;
	}

}
