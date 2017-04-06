package com.copartchallnge.main;
import java.util.Scanner;
import com.copartchallange.util.IntegerConversion;


/**
 * @author Aniparna Sengupta
 *
 */

public class StringToIntMain {

	public static void main(String[] args) {
		 		Scanner sc=new Scanner(System.in);
		 		String number = sc.nextLine();    
		        System.out.println("String Before Conversion :  "+ number);
		        int value=IntegerConversion.stringConvInt( number );
		        System.out.println("Integer value "+ value);
		    
	}

}
