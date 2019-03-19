/**
 * 
 */
package com.naren.file;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author natanwar
 *
 */
public class FileUtil {

	public static void main(String[] args) {
		String mainDataFile = "data_animal/mainFile.txt";
		String addDataFile = "data_animal/add.txt";
		String removeDataFile = "data_animal/remove.txt";
		mergeFilesContent(mainDataFile, removeDataFile, addDataFile);
	}
	
	public static void mergeFilesContent(String mainFile, String removeFile, String addFile)
	{
		FileReader reader = new FileReader();
		Stream<String> mainData = reader.readFileAsStream(mainFile) ;
		Stream<String> removeData = reader.readFileAsStream(removeFile);
		Stream<String> addData = reader.readFileAsStream(addFile);

		Set<String> mainDataList = mainData.collect(Collectors.toSet());
		
		mainDataList.addAll(addData.collect(Collectors.toSet()));
		mainDataList.removeAll(removeData.collect(Collectors.toSet()));
		
		mainDataList.stream().forEach(e->{
			System.out.println(e);
		});
	}

}
