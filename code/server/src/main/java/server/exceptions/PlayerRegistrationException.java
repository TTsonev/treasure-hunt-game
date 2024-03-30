package server.exceptions;

public class PlayerRegistrationException extends GenericExampleException {

	public PlayerRegistrationException(String errorMessage) {
		super("Player Regsitration Failed: ", errorMessage);
	}
	

}
