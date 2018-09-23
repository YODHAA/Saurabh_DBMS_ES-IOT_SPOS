package lex_example;

import java.util.Scanner;

public class add {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int a,b;
		System.out.print("Enter two numbers: ");
		a=in.nextInt();
		b=in.nextInt();
		int c=a+b;
		System.out.println("Addition of "+a+" and "+b+" is: "+c);
		a=5;
		b=10;
		c=a+b;
		System.out.println("Addition of "+a+" and "+b+" is: "+c);
	}
}

