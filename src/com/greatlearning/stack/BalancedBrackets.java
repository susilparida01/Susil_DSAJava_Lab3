package com.greatlearning.stack;

import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false; // Unbalanced - no matching opening bracket
                }

                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return false; // Unbalanced - mismatched brackets
                }
            }
        }

        return stack.isEmpty(); // If stack is empty, all brackets are balanced
    }

    public static void main(String[] args) {
        String input1 = "( [ [ { } ] ] )";
        String input2 = "( [ [ { } ] ] ) )";

        if (isBalanced(input1)) {
            System.out.println("The entered String has Balanced Brackets");
        } else {
            System.out.println("The entered String does not contain Balanced Brackets");
        }

        if (isBalanced(input2)) {
            System.out.println("The entered String has Balanced Brackets");
        } else {
            System.out.println("The entered String does not contain Balanced Brackets");
        }
    }
}
