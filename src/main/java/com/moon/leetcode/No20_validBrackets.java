package com.moon.leetcode;

import java.util.HashMap;
import java.util.Stack;

public class No20_validBrackets {

	// public static boolean isValid(String s) {
	// if (s.length() % 2 == 1) {
	// return false;
	// }
	// char arr[] = s.toCharArray();
	// Stack<Character> stack = new Stack<Character>();
	// for (int i = 0; i < s.length(); i++) {
	// if (stack.empty()) {
	// stack.push(arr[i]);
	// continue;
	// }
	// if ((stack.peek() == '{' && arr[i] == '}') || (stack.peek() == '[' && arr[i]
	// == ']') || (stack.peek() == '(' && arr[i] == ')')) {
	// stack.pop();
	// } else {
	// stack.push(arr[i]);
	// }
	// }
	//
	// return stack.empty();
	//
	// }

	// Hash table that takes care of the mappings.
	private HashMap<Character, Character> mappings;

	// Initialize hash map with mappings. This simply makes the code easier to read.
	public No20_validBrackets() {
		this.mappings = new HashMap<Character, Character>();
		this.mappings.put(')', '(');
		this.mappings.put('}', '{');
		this.mappings.put(']', '[');
	}

	public boolean isValid(String s) {

		// Initialize a stack to be used in the algorithm.
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// If the current character is a closing bracket.
			if (this.mappings.containsKey(c)) {

				// Get the top element of the stack. If the stack is empty, set a dummy value of
				// '#'
				char topElement = stack.empty() ? '#' : stack.pop();

				// If the mapping for this bracket doesn't match the stack's top element, return
				// false.
				if (topElement != this.mappings.get(c)) {
					return false;
				}
			} else {
				// If it was an opening bracket, push to the stack.
				stack.push(c);
			}
		}

		// If the stack still contains elements, then it is an invalid expression.
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		//System.out.println(isValid("()[]{}"));
		System.out.println(new No20_validBrackets().isValid("()[]{}"));
	}
}
