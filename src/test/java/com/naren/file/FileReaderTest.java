/**
 * 
 */
package com.naren.file;

import java.util.List;
import java.util.stream.Stream;

import com.naren.dto.ChildParentMapping;

/**
 * @author natanwar
 *
 */
public class FileReaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileReader reader =  new FileReader();
		String file = "singular.text";
		Stream<String> stream = reader.readFileAsStream(file);
		stream.forEach(e->System.out.println(e.toLowerCase()));
		stream.close();
		
		List<Class> list = reader.stringToObject("childparentmapping.json", ChildParentMapping.class);
		System.out.println(list.get(0));

	}

}
