package edu.cornell.sneha.exception;

public class QuitApplicationException extends Exception {
	// This exception class warns the user that the program is terminating.

	public QuitApplicationException() {
		super("Exiting application!");
	}

	public QuitApplicationException(String message) {
		super(message);
	}

}
