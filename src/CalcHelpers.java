/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sam
 */

import java.util.Stack; 
import java.lang.Math;

public class CalcHelpers {
    /* Java implementation to convert infix expression to postfix*/
	// Note that here we use Stack class for Stack operations 

	// A utility function to return precedence of a given operator 
	// Higher returned value means higher precedence 
    static int Prec(char ch) {
    	switch(ch) {
    		case '+':
    		case '-':
    			return 1;
    		case '*': 
	        case '/': 
	            return 2; 
	        case '^': 
	            return 3; 
        } 
		return -1; 
    }

    // The main method that converts given infix expression 
    // to postfix expression.  
    public static String infixToPostfix(String exp) { 
        // initializing empty String for result 
        String result = new String(""); 
          
        // initializing empty stack 
        Stack<Character> stack = new Stack<>(); 
          
        for (int i = 0; i < exp.length(); ++i) { 
            char c = exp.charAt(i); 
              
             // If the scanned character is an operand, add it to output. 
            if (Character.isLetterOrDigit(c)) 
                result += c; 
               
            // If the scanned character is an '(', push it to the stack. 
            else if (c == '(') 
                stack.push(c); 
              
            //  If the scanned character is an ')', pop and output from the stack  
            // until an '(' is encountered. 
            else if (c == ')') { 
                while (!stack.isEmpty() && stack.peek() != '(') 
                    result += stack.pop(); 
                  
                if (!stack.isEmpty() && stack.peek() != '(') 
                    return "Invalid Expression"; // invalid expression                 
                else
                    stack.pop(); 
            } else { // an operator is encountered  
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){ 
                    if(stack.peek() == '(') 
                        return "Invalid Expression"; 
                    result += stack.pop(); 
             } 
                stack.push(c); 
            } 
       
        } 
       
        // pop all the operators from the stack 
        while (!stack.isEmpty()){ 
            if(stack.peek() == '(') 
                return "Invalid Expression"; 
            result += stack.pop(); 
         } 
        return result; 
    }

    // Method to evaluate value of a postfix expression 
    public static double evaluatePostfix(String exp) { 
        //create a stack 
        Stack<Double> stack = new Stack<>(); 
          
        // Scan all characters one by one 
        for (int i = 0; i < exp.length(); i++) { 
            char c = exp.charAt(i); 
              
            // If the scanned character is an operand (number here), 
            // push it to the stack. 
            if (Character.isDigit(c)) 
                stack.push((double) c - '0'); 
              
            // If the scanned character is an operator, pop two 
            // elements from stack apply the operator 
            else { 
                double val1 = stack.pop(); 
                double val2 = stack.pop(); 
                  
                switch(c) { 
                    case '+': 
                        stack.push(val2 + val1); 
                        break; 
                    case '-': 
                        stack.push(val2 - val1); 
                        break; 
                    case '/': 
                        stack.push(val2 / val1); 
                        break; 
                    case '*': 
                        stack.push(val2 * val1); 
                        break; 
                    case '^': 
                        stack.push(Math.pow(val2, val1)); 
                        break; 
                } 
            } 
        } 
        return stack.pop();     
    } 
}
