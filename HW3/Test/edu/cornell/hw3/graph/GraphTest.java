package edu.cornell.hw3.graph;

import org.junit.Test;

import edu.cornell.hw3.graph.Graph;
import edu.cornell.hw3.graph.Person;

/**This class is a JUnit test suite to test the methods of the Graph class.*/
public class GraphTest {

	@Test
	public void testDFS(){
		Graph graph= new Graph();
		Person[] people = new Person[2];
		people = graph.populate();
		Graph.DFS(people[0],people[1]);
	}

	@Test
	public void testBFS(){
		Graph graph= new Graph();
		Person[] people = new Person[2];
		people = graph.populate();
		Graph.BFS(people[0],people[1]);
	}

	@Test
	public void testBiDirectionalBFS(){
		Graph graph= new Graph();
		Person[] people = new Person[2];
		people = graph.populate();
		Graph.BidirectionalBFS(people[0],people[1]);
	}

}
