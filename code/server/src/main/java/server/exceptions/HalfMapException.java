package server.exceptions;

public class HalfMapException extends GenericExampleException {

	public HalfMapException(String errorMessage) {
		super("HalfMap Transfer Failed: ", errorMessage);
	}

}
