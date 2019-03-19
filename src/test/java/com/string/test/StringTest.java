/**
 * 
 */
package com.string.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author nstanwar
 *
 */
public class StringTest {

	private static final Comparator<String> STRING_SORT = (str1, str2)->{
		return str1.compareTo(str2);
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		sortStirng();
	}
	
	private static void sortStirng() {
		List<String> list = Arrays.asList("Valid for 48 Hours, Rome Hop-on Hop-off +Colosseum Skip the Line",
				"Valid for 24 Hours, Rome Hop-on Hop-off +Colosseum Skip the Line", 
				"Valid for 24 Hours, Rome Hop-on Hop-off + Vatican Museum Skip the Line",
				"Valid for 48 Hours, Rome Hop-on Hop-off + Vatican and Colosseum Skip the Line + Public Transport",
				"Valid for 48 Hours, Rome Hop-on Hop-off + Colosseum and Vatican Skip the Line");
		Collections.sort(list,STRING_SORT);
		list.stream().forEach(l->System.out.println(l));
	}
	
	private static  String reverseString(char aa[])
	{
		//int i=0,j=0;
		char a1[]=new char[aa.length];
		for(int i=aa.length-1,j=0 ;i>=0;i--,j++)
		{
			a1[j]=aa[i];
		}
		
		return String.valueOf(a1);
	}
	
	private static  String reverseString2(char aa[])
	{
		char a1[]=new char[aa.length];
		for(int i=0 ;i>=0;i++)
		{
			a1[i]=aa[i];
		}
		
		return String.valueOf(a1);
	}

}
