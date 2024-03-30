package server.exceptions;

import org.apache.logging.log4j.util.Strings;

public class GenericExampleException extends RuntimeException {

	private final String errorName;

	public GenericExampleException(String errorName, String errorMessage) {
		super(errorMessage);
		this.errorName = errorName;		
	}

	public String getErrorName() {
		return errorName;
	}
}
