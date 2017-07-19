package edu.cornell.hw3.calculator;

/**
 * An object of class ExpressionParser represents a parser that finds the next
 * token in an inputed string. 
 * @author Sneha Kumar and Linda Lu. 
 * Date of last modification: 18 July 2017
 */
public class ExpressionParser {

	private String input;
	private String separator = " ";
	private String operator = "+-/*()";
	private int cursor = 0;
	private static final String NUMERIC = "1234567890";

	public ExpressionParser(String input) {
		this.input = input;

	}

	/** Returns the next token in a string. */
	public String nextToken() {
		if (input.trim().length() == 0 || cursor >= input.length())
			return "";
		String currentToken = "";
		boolean isNumeric = NUMERIC
				.contains(input.substring(cursor, cursor + 1));
		for (int i = cursor; i < input.length(); i++, cursor++) {
			String currentChar = input.substring(i, i + 1);
			if (isNumeric) {
				if (NUMERIC.contains(currentChar))
					currentToken += currentChar;
				else {
					cursor = currentChar.equals(separator) ? ++i : i;
					break;
				} 
			} 

			else {
				if (operator.contains(currentChar)) {
					currentToken += currentChar;
					cursor = ++i;
					break;
				} 
				else if (NUMERIC.contains(currentChar)) {
					currentToken += currentChar;
					isNumeric = true;

				} 
			} 
		} 
		return currentToken;
	}
}
