/**
 * 
 */
package com.string.test;

import java.util.Date;

/**
 * @author nstanwar
 *
 */
public class StringTest {

	/**
	 * 
	 */
	public StringTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(reverseString("narender".toCharArray()));
		Date date=new Date();
		int dateString=date.getSeconds();
		System.out.println(date.getTime());
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
