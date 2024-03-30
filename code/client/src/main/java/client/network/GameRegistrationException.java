package client.network;

import messagesbase.UniqueGameIdentifier;

@SuppressWarnings("serial")
public class GameRegistrationException extends Exception{
	
	public GameRegistrationException(UniqueGameIdentifier gameID, String message) {
		super("Failed Registration Attempt (ID: " + gameID.getUniqueGameID() + ") : " + message);
	}

}
