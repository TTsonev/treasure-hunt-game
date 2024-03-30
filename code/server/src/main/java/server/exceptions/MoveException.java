package server.exceptions;

public class MoveException extends GenericExampleException {

	public MoveException(String errorMessage) {
		super("Move Transfer Failed: ", errorMessage);
	}

}
