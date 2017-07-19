Sneha Kumar, Haitian (Linda) Lu — sk2279, hl723 — CS 2110 Summer 2017 — HW 3

#############################################################

Question 1 

Main Classes: 
	Calculator.java

Supporting Classes: 
	ExpressionParser.java
	Node.java
	MyStack.java
	
JUnit Test Classes:
	CalculatorTest.java
	ParserTest.java
	 
I created a calculator class with methods to validate Infix and RPN expressions, convert from one notation to the other, and finally evaluate the expression. I created an expression parser class which would parse the input string and return each next token (either number or operator) in the string. I used the MyStack class primarily to store the parsed numbers and evaluations at each iteration. I also added a user interface prompting the user to enter a string. If it is not in valid Infix or Reverse Polish Notation, the user will be informed and the program will terminate.   	


Non-implemented changes:
	1. If the user enters an invalid expression, prompt them to try again rather than terminating the program.
	2. Add an option for using prefix notation as well.

#############################################################


Question 2

Main classes:
	Graph.java
	
Supporting classes:
	MyQueue.java
	Person.java
	Arraylist.java
	Node.java

JUnit Test classes:
	GraphTest.java
	

Program has a built-in adjacency list  with the capability to search for the path from one random person for another and output the process. There are three available methods: breadth-first search, depth-first search and bidirectional breadth-
first search. Output shows the name of start and finish person . The person encountered on the path are also printed, the printing order is the direct path leading from start to finish person for BFS. and for DFS and BidirectionalBFS it is the search order. Bidirectional has the steadiest performance on average and BFS has some extreme cases where it takes very long because it's good mostly for closer elements while DFS is better for further ones. Bidirectional is noticeably better for larger data.

	
