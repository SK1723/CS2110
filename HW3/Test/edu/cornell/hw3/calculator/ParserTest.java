package edu.cornell.hw3.calculator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cornell.hw3.calculator.ExpressionParser;

/**This class is a JUnit test suite to test the methods of the ExpressionParser class.*/
public class ParserTest {

	@Test
	public void testEmpty() {
		String input = "";
		ExpressionParser parser = new ExpressionParser(input);
		assertEquals("", parser.nextToken());
	}
	
	@Test
	public void testInfix() {
		String input = " 30 *5+24     51 2/ 60";
		ExpressionParser parser = new ExpressionParser(input);
		
		assertEquals("30", parser.nextToken());
		assertEquals("*", parser.nextToken());
		assertEquals("5", parser.nextToken());
		assertEquals("+", parser.nextToken());
		assertEquals("24", parser.nextToken());
		assertEquals("51", parser.nextToken());
		assertEquals("2", parser.nextToken());
		assertEquals("/", parser.nextToken());
		assertEquals("60", parser.nextToken());
		assertEquals("", parser.nextToken());
	}

	@Test
	public void testRPN() {
		String input = "5 2 3+*";
		ExpressionParser parser = new ExpressionParser(input);
		
		assertEquals("5", parser.nextToken());
		assertEquals("2", parser.nextToken());
		assertEquals("3", parser.nextToken());
		assertEquals("+", parser.nextToken());
		assertEquals("*", parser.nextToken());
	}
}
