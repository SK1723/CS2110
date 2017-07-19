package edu.cornell.hw3.calculator;

import edu.cornell.hw3.util.Node;

/**An object of class MyStack represents a stack data structure implemented using a singly linked list.*/
public class MyStack<T> {
	private Node<T> top;
	private int length;

	/**Pushes an element onto the stack.*/
	public void push(T i) {
		top = new Node<T>(i, top);
		length++;
	}

	/**Returns a popped element off the top of the stack.*/
	public T pop() {
		if (!isEmpty()) {
			T temp = top.getData();
			top = top.getNext();
			length--;
			return temp;
		} else {
			System.out.println("Stack is empty!");
			return null;
		}
	}
	
	/**Returns the element at the top of the stack without removing it from the stack.*/
	public T peek(){
		if(!isEmpty())
			return top.getData();
		else{
			System.out.println("Stack is empty!");
			return null;
		}
	}

	public int getLength() {
		return this.length;
	}

	public boolean isFull() {
		return false;
	}

	public boolean isEmpty() {
		return (length == 0);
	}

	public MyStack(int n) {
		length = 0;
	}

	public MyStack() {
		this(0);
	}

}
