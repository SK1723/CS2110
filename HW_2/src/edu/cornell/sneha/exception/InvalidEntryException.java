package edu.cornell.sneha.exception;

public class InvalidEntryException extends Exception {
	// This is an exception class for an invalid entry.

	public InvalidEntryException() {
		super("Exception - invalid entry!");
	}

	public InvalidEntryException(String message) {
		super("Excpetion - invalid entry: " + message);

	}

}