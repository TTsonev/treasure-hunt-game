package server.exceptions;

public class StatusRequestException extends GenericExampleException {

	public StatusRequestException(String errorMessage) {
		super("Status Request Failed: ", errorMessage);
	}
	

}
