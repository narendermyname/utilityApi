/**
 * 
 */
package com.naren.java8.findandreplaceinfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author natanwar
 *
 */
public class FindAndReplaceAnimalActivityIds {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String newActivityIdsFile = "/Users/natanwar/Desktop/animalActivityIds.txt";
		String existingActivityIdsFile = "/Users/natanwar/Computer/git/expedia/lxweb/src/main/resources/static-ds/animalActivities/Animal_Activities.txt";
		
		Supplier<Stream<String>> stream = () -> readFile(existingActivityIdsFile);
		
		stream.get().parallel().forEach(e->System.out.println(e));
		
		Stream<String> newFileData = readFile(newActivityIdsFile);
		
		String existingFileStringData = streamToString(stream.get());
		
		filterAndPrintNewIds(existingFileStringData, newFileData);
	}
	
	private static void filterAndPrintNewIds(String existingData, Stream<String> newFileData) {
		newFileData.filter(data -> !"".equals(data.trim()) && !existingData.trim().contains(data.trim())).forEach(acvtityId -> System.out.println(acvtityId));
	}

	private static Stream<String> readFile(String filePath) {
		try {
			Stream<String> stream = Files.lines(Paths.get(filePath));
			return stream;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Stream.empty();
	}

	private static String streamToString(Stream<String> stream) {
		return stream.parallel().collect(Collectors.joining(" "));
	}

}
