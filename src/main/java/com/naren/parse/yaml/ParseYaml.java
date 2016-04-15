/**
 * 
 */
package com.naren.parse.yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map.Entry;

import org.ho.yaml.Yaml;

/**
 * @author nstanwar
 *
 */
public class ParseYaml {

	/**
	 * 
	 */
	public ParseYaml() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Entry entry = (Entry) Yaml.load(new File("spring-mail.xml"));
		System.out.println(entry.getKey());
		System.out.println(entry.getValue());
	}

}
