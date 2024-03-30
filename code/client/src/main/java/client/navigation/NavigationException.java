package client.navigation;

import client.main.Position;

@SuppressWarnings("serial")
public class NavigationException extends RuntimeException{
	
	public NavigationException(Position start) {
		super("Failed to generated next moves from " + start.toString());
	}
	
	public NavigationException(Position start, Position goal) {
		super("Failed to navigate from " + start.toString() + " to " + goal.toString());
	}
}
