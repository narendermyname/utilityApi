package com.naren.java8;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestJava8 {

	public static void main(String[] args) {
		testPredicate();

	}

	private static void testPredicate() {
		int count = Stream.of(1,3,7,2,7,8).filter(isTrue()).map(e->{
			System.out.println("Mapping "+e); 
			return e*e; 
			}).sorted().collect(Collectors.toList()).size();
		System.out.println(count);
	}
	
	public static Predicate<Integer> isTrue() {
		
		return i -> {
			System.out.println("Filter "+i);
			return i> 3;
		};
	}

}
