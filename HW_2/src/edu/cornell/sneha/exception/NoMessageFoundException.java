package edu.cornell.sneha.exception;

public class NoMessageFoundException extends Exception {
	// This is an exception class for when no message is found.

	public NoMessageFoundException() {
		super("Exception - no message found!");
	}

	public NoMessageFoundException(String message) {
		super("Exception - no message found: " + message);
	}

}
