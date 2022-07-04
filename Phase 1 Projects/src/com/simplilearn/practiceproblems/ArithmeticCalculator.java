/* ***************************************
 * Project: Arithmetic Calculator
 * Student: Nia Kelley
 * Date: 23 May 2022
 * ***************************************
 */
package com.simplilearn.practiceproblems;

import java.util.Scanner;

/* public class will be visible to everyone*/
public class ArithmeticCalculator {

	public static void main(String[] args) {
		
		//Class variables with Initializations
		double num1 = 0.0d; //d is optional for double data types; 'f' only required for floats
		double num2 = 0.0;
		double result = 0.0;
		int operator = 0;
		String choice = "y";
		
		//Open a Scanner to read input from the console
		Scanner sc = new Scanner(System.in);
		
		while(choice.equalsIgnoreCase("y")) {
			System.out.println("Enter first operand: ");
			num1 = sc.nextDouble();
			System.out.println("Enter second operand: ");
			num2 = sc.nextDouble();
			System.out.println("Choose desired math operation\n1: Addition (+)"
					+ "\n2: Subtraction (-)\n3: Multiplication (*)\n4: Division (/)\n");
			operator = sc.nextInt();		
				
			switch(operator) {
			case 1:
				result = num1 + num2;				
				break;
			case 2:
				result = num1 - num2;
				break;
			case 3:
				result = num1 * num2;				
				break;
			case 4:
				result = num1 / num2;				
				break;						
			}//end switch
			
			System.out.println("Result = " + result);
		
			//TODO Implement error checking for the choice value
			System.out.println("Do you want to continue - y or n?");
			choice = sc.next();
		}//end while
		
		sc.close(); //prevents memory leaks
		
	}//end main

}//end class