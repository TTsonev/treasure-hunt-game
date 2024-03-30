package client.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.main.Position;
import messagesbase.messagesfromclient.PlayerHalfMapNode;
import messagesbase.messagesfromserver.EFortState;
import messagesbase.messagesfromserver.EPlayerPositionState;
import messagesbase.messagesfromserver.ETreasureState;
import messagesbase.messagesfromserver.FullMapNode;

public class MapConverter {	
	private static Logger loggerMapConv = LoggerFactory.getLogger(MapConverter.class);

	public static Map<Position, FullMapNode> convertHalfMapToFullMap(Collection<PlayerHalfMapNode> collection){
		Map<Position, FullMapNode> fullMap = new HashMap<>();
		
		for (PlayerHalfMapNode node : collection) {
			 EFortState fort = node.isFortPresent() ? EFortState.MyFortPresent : EFortState.NoOrUnknownFortState;
			 int X = node.getX();
			 int Y = node.getY();
			 FullMapNode fullNode = new FullMapNode(node.getTerrain(),  EPlayerPositionState.NoPlayerPresent, 
					 								ETreasureState.NoOrUnknownTreasureState, fort, X, Y);
			 fullMap.put(new Position(X,Y), fullNode);
		}
		
		loggerMapConv.warn("Converting HalfMaNodes to FullMapNodes");
		return fullMap;
		
	}
}
