/**
 * 
 */
package com.naren.string;

import java.util.HashMap;

/**
 * @author nstanwar
 *
 */
public class StrtingPlay {

	/**
	 * 
	 */
	public StrtingPlay() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name="narender";
		String name2=new String("narender");
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		map.put(name,1);
		map.put(name2,1);
		System.out.println(map.size());
		for(String key:map.keySet())
		{
			System.out.println("Key "+key+" Val"+map.get(key));
		}
	}

}
