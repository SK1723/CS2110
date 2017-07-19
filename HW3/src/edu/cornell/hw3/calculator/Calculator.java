package edu.cornell.hw3.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * An object of class Calculator represents a calculator that finds the value of
 * an inputed string.
 * 
 * @author Sneha Kumar and Linda Lu. Date of last modification: 18 July 2017
 */
public class Calculator {

	private static final String OPEN_PARENTHESIS = "(";
	private static final String END_PARENTHESIS = ")";
	private static final String NUMERIC_REGEX = "-?\\d+(\\.\\d+)?";
	private static String input;
	private String operators = "+-/*";
	private String parentheses = "()";
	private MyStack<String> operatorsStack = new MyStack<String>();

	public static void main(String[] args) throws IOException {
		System.out.println(
				"Enter an expression in either Reverse Polish or Infix notation:");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		input = br.readLine();
		Calculator calc = new Calculator();
		calc.evaluateInfixOrRPN(input);

	}

	/**
	 * Determine whether the inputed string is in Infix or Reverse Polish
	 * notation, and convert to the opposite notation, finally calling on the
	 * calculate method based on the result.
	 */
	public void evaluateInfixOrRPN(String input) {

		if (validateRPN(input)) {
			System.out.println(
					"You entered an expression in Reverse Polish notation. Here it is converted to Infix notation: \n\t"
							+ convertRPNtoInfix(input));
			System.out.println("Calculated answer: "
					+ calculateInfix(convertRPNtoInfix(input)));

		} else if (validateInfix(input)) {
			System.out.println(
					"You entered an expression in Infix notation. Here it is converted to Reverse Polish notation: \n\t"
							+ convertInfixtoRPN(input));
			System.out.println("Calculated answer: "
					+ calculateRPN(convertInfixtoRPN(input)));
		}

		else {
			System.out.println(
					"Sorry, your string does not contain a proper expression. Please try again!");
		}

	}

	/** Return true if the inputed string is in valid Infix notation. */
	public boolean validateInfix(String input) {
		int currentlyOpenParentheses = 0;
		ExpressionParser parser = new ExpressionParser(input);
		String token = parser.nextToken();
		if (input.isEmpty())
			return false;
		while (!token.isEmpty()) {
			if (token.equals(OPEN_PARENTHESIS))
				currentlyOpenParentheses++;
			else if (token.equals(END_PARENTHESIS))
				currentlyOpenParentheses--;
			if (currentlyOpenParentheses < 0)
				return false;
			String nextToken = parser.nextToken();
			if (isNumeric(token) && isNumeric(nextToken))
				return false; // invalid if two numbers in a row
			if (isOperator(token) && isOperator(nextToken))
				return false; // invalid if two operates in a row
			if (!isNumeric(token) && !isParen(token) && !isOperator(token)) {
				return false; // invalid if letters or non-opeartors are used
			}
			token = nextToken;
		} // end of while loop

		if (currentlyOpenParentheses != 0) // invalid if uneven parentheses
			return false;
		return true;

	}

	/** Return true if the token is a parenthesis. */
	public boolean isParen(String token) {
		return parentheses.contains(token);
	}

	/** Return true if the token is an operator. */
	public boolean isOperator(String token) {
		return operators.contains(token);
	}

	/** Return true if the token is a number. */
	public boolean isNumeric(String token) {
		return token.matches(NUMERIC_REGEX);
	}

	/**
	 * Return true if the inputed string is in valid Reverse Polish notation.
	 */
	public boolean validateRPN(String input) {
		ExpressionParser parser = new ExpressionParser(input);
		int counter = 0;
		String token = parser.nextToken();
		if (input.isEmpty())
			return false;
		while (!token.isEmpty()) {
			if (isOperator(token)) {
				counter -= 2;
				if (counter < 0) {
					return false;
				}
				counter++;
			} else if (isNumeric(token)) { // it's numeric
				counter++;
			} else {
				return false;
			}
			token = parser.nextToken();
		}

		if (counter == 1)
			return true;
		else
			return false;
	}

	/**
	 * Return a string converted from Infix notation to Reverse Polish notation.
	 */
	public String convertInfixtoRPN(String input) {
		StringBuilder output = new StringBuilder();
		ExpressionParser parser = new ExpressionParser(input);
		String token = parser.nextToken();
		token = useEachToken(output, parser, token);

		while (!operatorsStack.isEmpty()) {
			output.append(operatorsStack.pop() + " ");
		}

		return output.toString();
	}

	/** Return a string token until the input string runs out of tokens. */
	public String useEachToken(StringBuilder output, ExpressionParser parser,
			String token) {
		while (!token.isEmpty()) {
			if (isNumeric(token))
				output.append(token + " ");
			else if (token.equals(OPEN_PARENTHESIS))
				operatorsStack.push(token);
			else if (token.equals(END_PARENTHESIS)) {
				String savedOperator = operatorsStack.pop();
				while (!savedOperator.equals(OPEN_PARENTHESIS)) {
					output.append(savedOperator + " ");
					savedOperator = operatorsStack.pop();
				}
			} else if (isOperator(token)) {
				while (!operatorsStack.isEmpty() && (getPrecedence(
						token) <= getPrecedence(operatorsStack.peek()))) {
					output.append(operatorsStack.pop() + " ");
				}
				operatorsStack.push(token);
			}
			token = parser.nextToken();
		}
		return token;
	}

	/**
	 * Return precedence of each operator. '*' and '/' have a higher precedence
	 * than '+' and '-'.
	 */
	private int getPrecedence(String operator) {
		int precedence = 0;
		if ("*/".contains(operator))
			precedence = 2;
		else if ("+-".contains(operator))
			precedence = 1;

		return precedence;
	}

	/**
	 * Return a string converted from Reverse Polish notation to Infix notation.
	 */
	public String convertRPNtoInfix(String input) {
		MyStack<String> numberStack = new MyStack<String>();
		ExpressionParser parser = new ExpressionParser(input);
		String token = parser.nextToken();
		while (!token.isEmpty()) {
			if (isNumeric(token))
				numberStack.push(token);
			else if (isOperator(token)) {
				String numberTwo = numberStack.pop();
				String numberOne = numberStack.pop();
				String tempExpression = numberOne + token + numberTwo;
				if (token.equals("+") || token.equals("-"))
					numberStack.push(OPEN_PARENTHESIS + tempExpression
							+ END_PARENTHESIS);
				else {
					numberStack.push(tempExpression);
				}
			}
			token = parser.nextToken();
		}
		return numberStack.pop();
	}

	/** Return the calculated value of an input in Reverse Polish notation. */
	public double calculateRPN(String input) {
		MyStack<Double> numberStack = new MyStack<Double>();
		ExpressionParser parser = new ExpressionParser(input);
		String token = parser.nextToken();
		double calculation = 0;
		while (!token.isEmpty()) {
			if (isNumeric(token))
				numberStack.push(Double.parseDouble(token));
			else if (isOperator(token)) {
				double numberTwo = numberStack.pop();
				double numberOne = numberStack.pop();
				calculation = evaluate(token, numberOne, numberTwo);
				numberStack.push(calculation);
			}
			token = parser.nextToken();
		}
		return numberStack.pop();
	}

	/**
	 * Return the calculation of two numbers based on which operator the token
	 * is.
	 */
	public double evaluate(String operator, double numberOne,
			double numberTwo) {
		double calculation = 0;
		switch (operator) {
		case "+":
			calculation = numberOne + numberTwo;
			break;
		case "-":
			calculation = numberOne - numberTwo;
			break;
		case "*":
			calculation = numberOne * numberTwo;
			break;
		case "/":
			calculation = numberOne / numberTwo;
		default:
			break;
		}
		return calculation;
	}

	/** Return the calculated value of an input in Infix notation. */
	public double calculateInfix(String input) {
		return calculateRPN(convertInfixtoRPN(input));
	}
}
