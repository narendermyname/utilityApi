/**
 * 
 */
package com.naren.write.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author nstanwar
 *
 */
public class FileWriterUtil {

	/**
	 * 
	 */
	public FileWriterUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String createCSV(String path,String data) throws IOException{
		File file=new File(path);
		FileWriter fileWritter=new FileWriter(file);
		fileWritter.append("Name");
		fileWritter.append(",");
		fileWritter.append("age");
		fileWritter.append("\n");
		
		fileWritter.append("Narender Singh	");
		
		fileWritter.append("25");
		fileWritter.close();
		return file.getAbsolutePath();
	}
}
