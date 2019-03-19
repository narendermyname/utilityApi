/**
 * 
 */
package com.naren.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author natanwar
 *
 */
public class FileReader {

	private static final Logger LOG = LoggerFactory.getLogger(FileReader.class);

	public String readFile(String filePath) {
		String response = null;
		try {
			StringBuilder data = new StringBuilder();
			Stream<String> lines = readFileAsStream(filePath);
			lines.forEach(line -> data.append(line).append("\n"));
			lines.close();
			response = data.toString();
		} catch (Exception e) {
			LOG.error("Error while reading file {} error ", filePath, e);
		}
		return response;

	}

	public Stream<String> readFileAsStream(String filePath) {
		try {
			Path path = Paths.get(FileReader.class.getClassLoader().getResource(filePath).toURI());
			return Files.lines(path);
		} catch (Exception e) {
			LOG.error("Error while reading file {} error ", filePath, e);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Class> stringToObject(String jsonFile, Class classz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return  objectMapper.readValue(readFile(jsonFile),
					TypeFactory.defaultInstance().constructCollectionType(List.class, classz));
		} catch (IOException ex) {
			LOG.error("Error while reading file.",ex);
		}
		return null;
	}
}
