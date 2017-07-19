package edu.cornell.hw3.graph;

import java.util.Random;

/**
 * An object of class Graph creates a representation of a tree, and searches the
 * tree through BFS, DFS, and Bidirectional BFS.
 */
public class Graph {
	protected static ArrayList<ArrayList<Person>> adjacencyList;
	protected static ArrayList<Person> allPerson;

	public Graph() {
		adjacencyList = new ArrayList<ArrayList<Person>>();
		allPerson = new ArrayList<Person>();
	}

	/** Builds adjacencyList and returns a random list of two people to test. */
	public Person[] populate() {
		Person Tyler = new Person("Tyler");
		Person Holly = new Person("Holly");
		Person Linda = new Person("Linda");
		Person Sneha = new Person("Sneha");
		Person Vincent = new Person("Vincent");
		Person Henry = new Person("Henry");
		Person Jeremy = new Person("Jeremy");
		Person Sophia = new Person("Sophia");
		Person Sam = new Person("Sam");
		Person Emma = new Person("Emma");
		Person Anna = new Person("Anna");
		Person Valentina = new Person("Valentina");
		Person Spencer = new Person("Spencer");
		Person Joey = new Person("Joey");
		Person William = new Person("William");
		Person Jackson = new Person("Jackson");
		Person Molly = new Person("Molly");
		ArrayList<Person> tyler = new ArrayList<Person>();
		tyler.add(Tyler);
		tyler.add(Spencer);
		tyler.add(Valentina);
		tyler.add(Linda);
		tyler.add(William);
		add(tyler);
		ArrayList<Person> holly = new ArrayList<Person>();
		holly.add(Holly);
		holly.add(Tyler);
		holly.add(Spencer);
		holly.add(Henry);
		add(holly);
		ArrayList<Person> linda = new ArrayList<Person>();
		linda.add(Linda);
		linda.add(Jeremy);
		linda.add(William);
		linda.add(Valentina);
		linda.add(Sophia);
		add(linda);
		ArrayList<Person> sneha = new ArrayList<Person>();
		sneha.add(Sneha);
		sneha.add(Henry);
		sneha.add(Emma);
		add(sneha);
		ArrayList<Person> vincent = new ArrayList<Person>();
		vincent.add(Vincent);
		vincent.add(Joey);
		vincent.add(Holly);
		add(vincent);
		ArrayList<Person> henry = new ArrayList<Person>();
		henry.add(Henry);
		henry.add(Sam);
		henry.add(Spencer);
		add(henry);
		ArrayList<Person> joey = new ArrayList<Person>();
		joey.add(Joey);
		joey.add(Spencer);
		joey.add(Sam);
		add(joey);
		ArrayList<Person> jeremy = new ArrayList<Person>();
		jeremy.add(Jeremy);
		jeremy.add(Linda);
		jeremy.add(Sophia);
		add(jeremy);
		ArrayList<Person> sophia = new ArrayList<Person>();
		sophia.add(Sophia);
		sophia.add(Linda);
		sophia.add(Jackson);
		sophia.add(William);
		add(sophia);
		ArrayList<Person> spencer = new ArrayList<Person>();
		spencer.add(Spencer);
		spencer.add(Henry);
		spencer.add(Tyler);
		add(spencer);
		ArrayList<Person> sam = new ArrayList<Person>();
		sam.add(Sam);
		sam.add(Emma);
		sam.add(Joey);
		add(sam);
		ArrayList<Person> emma = new ArrayList<Person>();
		emma.add(Emma);
		emma.add(Sneha);
		emma.add(Sam);
		add(emma);
		ArrayList<Person> valentina = new ArrayList<Person>();
		valentina.add(Valentina);
		valentina.add(Anna);
		valentina.add(Molly);
		add(valentina);
		ArrayList<Person> molly = new ArrayList<Person>();
		molly.add(Molly);
		molly.add(Anna);
		add(molly);
		ArrayList<Person> william = new ArrayList<Person>();
		william.add(William);
		william.add(Linda);
		william.add(Joey);
		add(william);
		ArrayList<Person> jackson = new ArrayList<Person>();
		jackson.add(Jackson);
		jackson.add(William);
		jackson.add(Linda);
		jackson.add(Vincent);
		add(jackson);
		ArrayList<Person> anna = new ArrayList<Person>();
		anna.add(Anna);
		anna.add(Molly);
		anna.add(Valentina);
		add(anna);
		Person[] testPeople = new Person[2];

		// get random people used for search
		testPeople[0] = allPerson
				.get(new Random().nextInt(allPerson.size() - 1));
		testPeople[1] = allPerson
				.get(new Random().nextInt(allPerson.size() - 1));

		System.out.println(
				"From " + testPeople[0].name + " to " + testPeople[1].name);
		return testPeople;
	}

	/** Does breadth-first search. */
	public static void BFS(Person start, Person finish) {
		System.out.println("Performing BFS...");
		if (start.equals(finish)) {
			System.out.println("Oops, same person!");
		} else {
			boolean found = false;
			markAsVisited(start);
			MyQueue queue = new MyQueue();
			queue.join(start);
			while ((!queue.isEmpty()) && found == false) {
				Person out = queue.leave();
				System.out.println(out.name);
				if (out.equals(finish)) {
					found = true;
				} else {
					ArrayList<Person> newOne = findPerson(adjacencyList, out);
					for (int i = 1; i < newOne.size(); i++) {
						Person current = newOne.get(i);
						if (!visited(current)) {
							markAsVisited(current);
							queue.join(current);
						}
					}
				}
			}
		}

	}

	/** Does depth-first search */
	public static void DFS(Person start, Person finish) {
		System.out.println("Performing DFS...");
		if (start.equals(finish)) {
			System.out.println("Same person!");
		} else {
			boolean found = false;
			dfs(start, finish, found);
			System.out.print(finish.name);
		}
	}

	/** Does BFS in both directions until paths collide */
	public static void BidirectionalBFS(Person beg, Person end) {
		System.out.println("Performing Bidirectional BFS...");
		if (beg.equals(end)) {
			System.out.println("Same person!");
		} else {
			boolean collision = false;
			MyQueue start = new MyQueue();
			MyQueue finish = new MyQueue();
			start.join(beg);
			finish.join(end);
			System.out.println(beg.name);
			System.out.println(end.name);
			ArrayList<Person> visitedForStart = new ArrayList<Person>();
			ArrayList<Person> visitedForFinish = new ArrayList<Person>();
			visitedForStart.add(beg);
			visitedForFinish.add(end);
			while ((!start.isEmpty()) && (!collision) && (!finish.isEmpty())) {
				Person star = start.leave();
				Person fini = finish.leave();
				if (visitedForStart.contains(fini)
						|| visitedForFinish.contains(star)) {
					collision = true;
				}

				ArrayList<Person> newOne = findPerson(adjacencyList, star);
				ArrayList<Person> newTwo = findPerson(reverseDirection(), fini);
				for (int i = 1; i < newOne.size(); i++) {
					Person person = newOne.get(i);
					if (!visitedForStart.contains(person)) {
						start.join(person);
						visitedForStart.add(person);
						System.out.println(person.name);
					}
				}
				for (int i = 1; i < newTwo.size(); i++) {
					Person per = newTwo.get(i);
					if (!visitedForFinish.contains(per)) {
						finish.join(per);
						visitedForFinish.add(per);
						System.out.println(per.name);
					}
				}
			}
		}
	}

	private static void add(ArrayList<Person> l) {
		adjacencyList.add(l);
		allPerson.add(l.get(0));
	}

	/** Does depth-first search recursively */
	private static void dfs(Person start, Person finish, boolean found) {
		if (!start.equals(finish) && found == false) {
			System.out.println(start.name);
			markAsVisited(start);
			ArrayList<Person> newOne = findPerson(adjacencyList, start);
			for (int i = 1; (i < newOne.size()) && (found == false); i++) {
				Person person = newOne.get(i);
				if ((!person.equals(finish))) {
					if (!visited(person)) {
						markAsVisited(person);
						dfs(person, finish, found);// recursive call, ends when
													// found
					}
				} else {
					found = true;
				}
			}
		}
	}

	/** Reverse the directed graph */
	private static ArrayList<ArrayList<Person>> reverseDirection() {
		ArrayList<ArrayList<Person>> modified = new ArrayList<ArrayList<Person>>();
		for (int i = 0; i < allPerson.size(); i++) { // new list to store
														// reversed graph
			ArrayList<Person> newList = new ArrayList<Person>();
			newList.add(allPerson.get(i));
			modified.add(newList);
		}
		modified = reverse(modified, adjacencyList.get(0));
		for (int i = 0; i < allPerson.size(); i++) { // set all true visited
														// status back to false
														// to begin actual
														// search
			allPerson.get(i).visited = false;
		}
		return modified;
	}

	/** Reverses the direction for all edges in unvisited neighbor */
	private static ArrayList<ArrayList<Person>> reverse(
			ArrayList<ArrayList<Person>> modified, ArrayList<Person> initial) {
		Person from = initial.get(0);
		markAsVisited(from);
		// keep list of unvisited neighbors for a particular person
		ArrayList<Person> neighbors = new ArrayList<Person>();
		for (int i = 1; i < initial.size(); i++) {
			Person person = initial.get(i);
			if (!visited(person)) { // record all reversed edges for unvisited
									// neighbors
				int k = 0;
				while ((k < modified.size())
						&& (!modified.get(i).get(0).equals(person))) {
					k++;
				}
				modified.get(i).add(from);
				neighbors.add(person);
			}
		}
		if (!neighbors.isEmpty()) {
			for (int i = 0; i < neighbors.size(); i++) {
				// recursive call until all neighbors are visited
				reverse(modified, findPerson(adjacencyList, neighbors.get(i)));
			}
		}
		return modified;
	}

	// general helper methods

	/** Finds the neighbors list belonging to a particular person */
	private static ArrayList<Person> findPerson(
			ArrayList<ArrayList<Person>> list, Person target) {
		int i = 0;
		while ((!list.get(i).get(0).equals(target)) && (i < list.size())) {
			i++;
		}
		if (i == list.size()) {
			System.out.println("Person not found!");
			return null;
		} else {
			return list.get(i);
		}
	}

	/** Returns the visited status of a person */
	private static boolean visited(Person p) {
		boolean done = false;
		for (int i = 0; (i < allPerson.size()) && (!done); i++) {
			if ((allPerson.get(i).visited) && (allPerson.get(i).equals(p))) {
				done = true;
			}
		}
		if (done) {
			return true;
		} else {
			return false;
		}
	}

	/** Marks a person as visited */
	private static void markAsVisited(Person p) {
		boolean done = false;
		for (int i = 0; (i < allPerson.size()) && (!done); i++) {
			if ((!allPerson.get(i).visited) && (allPerson.get(i).equals(p))) {
				allPerson.get(i).visited = true;
				done = true;
			}
		}
	}

}
