/**
 * 
 */
package com.naren.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

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
		
		
		String abString = "26886.0|5242.0|12687.0|8305.0|99991.0|26016.0|26076.0|9388.1|27411.0|26072.0|27058.0|26406.1|26286.1|27190.0|25563.1|13942.1|27243.0|27392.0|16128.1|26239.1|24464.0|26753.0|26875.0|25266.0|26360.1|16386.0";
		String [] abArr = abString.trim().split("\\|");
		abString = Arrays.asList(abArr).stream().map(ab->{
			String abWithVal[] = ab.split("\\.");
			if("0".equals(abWithVal[1]))
			{
				return abWithVal[0]+"|0|0";
			} else {
				return abWithVal[0]+"|1|1";
			}
		}).collect(Collectors.joining(":"));
		
		System.out.println(abString);
		
		System.out.println("26886.0|5242.0|12687.0|8305.0|99991.0|26016.0|26076.0|9388.1|27411.0|26072.0|27058.0|26406.1|26286.1|27190.0|25563.1|13942.1|27243.0|27392.0|16128.1|26239.1|24464.0|26753.0|26875.0|25266.0|26360.1|16386.0".equals("26886.0|5242.0|12687.0|8305.0|99991.0|26016.0|26076.0|9388.1|27411.0|26072.0|27058.0|26406.1|26286.1|27190.0|25563.1|13942.1|27243.0|27392.0|16128.1|26239.1|24464.0|26753.0|26875.0|25266.0|26360.1|16386.0"));
	}

}
