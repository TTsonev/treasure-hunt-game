package client.navigation;

import java.util.Queue;

import client.main.Position;
import messagesbase.messagesfromclient.EMove;

public interface NavigationStrategy {
	
	public Queue<EMove> navigateNextMoves(Position currentPosition);

	public void addToVisited(Position position);
	
}
