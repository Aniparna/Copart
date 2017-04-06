/**
 * To convert string to integer , first we need to know what place value each digit 
 * must be multiplied by. Working through the digits left to right seems problematic 
 * because we don’t know what the place value of the first digit is until
 *  we know how long the number is. For example, the first character of "367" is identical to 
 *  that of "31"; although it represents 300 in the first case and 30 in the second case.
 *  The most obvious solution is to scan the digits from right to left because the
 *   rightmost position is always the one’s place, the next to rightmost is always the ten’s,
 *    and so on.This enables us to start at the right end of the string with a place value of
 *     1 and work backward through the string, multiplying the place value by 10 each time we 
 *     move to a new place.

 */
package com.copartchallange.util;

/**
 * @author Aniparna Sengupta
 *
 */
public class IntegerConversion {
	    
	    public static int stringConvInt( String str ){
	        int i = 0, number = 0;
	        boolean isNegative = false;
	        int len = str.length();
	        if( str.charAt(0) == '-' ){
	            isNegative = true;
	            i = 1;
	        }
	        for( int k =0; k<len;k++ ){
	            number *= 10;
	            number += ( str.charAt(i++) - '0');
	        }
	        if( isNegative )
	        number = -number;
	        return number;
	    }   
}
