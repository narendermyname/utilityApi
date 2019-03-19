package com.string.test;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Random rand = new Random(10);
		 int max = 5, min = 2;
		 IntStream.rangeClosed(1, 1000).forEach(i-> {
			 System.out.println(rand.nextInt((max-min)+1)+min);
		 });
	}

}
