/**
 * 
 */
package com.naren.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nstanwar
 *
 */
public class FileWriterUtil {
	
	private static final String FILE_PATH = "D:\\dd\\hello.txt";
	private static final String TAB = "\t";
	private static final String NEW_LINE =  "\n";

	public static String createCSV(String path, String data) throws IOException {
		// Write file with FIles class
		createFileJava8();
		File file = new File(path);
		FileWriter fileWritter = new FileWriter(file);
		fileWritter.append("Name");
		fileWritter.append(",");
		fileWritter.append("age");
		fileWritter.append("\n");

		fileWritter.append("Narender Singh	");

		fileWritter.append("25");
		fileWritter.close();
		return file.getAbsolutePath();
	}

	/**
	 * Write data to file
	 * @throws IOException
	 */
	private static void createFileJava8() {
		List<List<String>> lists = getData();
		
		final StringBuffer lines = new StringBuffer();
		
		lists.parallelStream().forEach(line -> {
			lines.append(line.stream().collect(Collectors.joining(TAB))).append(NEW_LINE);
		});
		
		//String lines_ = lists.parallelStream().map( l -> l.stream().collect(Collectors.joining(TAB))).collect(Collectors.joining(NEW_LINE));
		
		OpenOption option[] = { StandardOpenOption.CREATE };
		try {
			Files.write(Paths.get(FILE_PATH), lines.toString().getBytes(), option);
		} catch (IOException e) {
			System.out.println("Error : "+e.getMessage());
		}
	}
	
	/**
	 * Get dummy data to write in file.
	 * @return
	 */
	private static List<List<String>> getData() {
		List<List<String>> lists = Arrays.asList(Arrays.asList("1", "11", "111", "1111"), Arrays.asList("2", "22", "222", "2222"),
				Arrays.asList("3", "33", "333", "3333"));
		return lists;
	}

	public static void main(String... a) throws IOException {
		createFileJava8();
	}
	
	
}
