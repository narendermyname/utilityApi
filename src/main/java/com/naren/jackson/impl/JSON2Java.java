/**
 * 
 */
package com.naren.jackson.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.naren.dto.CloudResourceUsageDataSet;
import com.naren.dto.JAVA;

/**
 * @author nstanwar
 *
 */
public class JSON2Java {

	/**
	 * 
	 */
	public JSON2Java() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		JAVA obj=new JAVA();
		Map<String,double [][]> mapData=new HashMap<String,double[][]>();
		double dd[][]={{111111111,232},{22222222,22323},{44444444,3434},{4555555,33432}};
		mapData.put("data 13",dd);
		obj.setData("KKKKKKKKKKKKK");
		obj.setEmail("NARENDERMYNAME@GMAIL.COM");
		obj.setName("NARENDER SINGH TANWAR");
		
		//Java 2 JSON convert
		//ListServersRequest listS=new ListServersRequest();
		
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		try {
			mapper.writeValue(strWriter, "onhecj");
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userDataJSON = strWriter.toString();
		//CloudResourceUsageDataSet io=getUsageJson();
		System.out.println(userDataJSON);
		
	}
	
	public static CloudResourceUsageDataSet getUsageJson() {
		CloudResourceUsageDataSet cloudResourceDataSet=new CloudResourceUsageDataSet();
		Map<String,Map<String,Map<String,Double>>> threeDDataSet =new HashMap<String,Map<String,Map<String,Double>>>();
		Map<Long ,Double> instanceDataMap=new HashMap<Long,Double>();
		instanceDataMap.put(new Date().getTime()+1, new Double(100));
		instanceDataMap.put(new Date().getTime()+2, new Double(101));
		instanceDataMap.put(new Date().getTime()+3, new Double(102));
		instanceDataMap.put(new Date().getTime()+4, new Double(103));
		Map<Long ,Double> venderDataMap=new HashMap<Long,Double>();
		venderDataMap.put(new Date().getTime()+6, new Double(1010));
		venderDataMap.put(new Date().getTime()+7, new Double(1021));
		venderDataMap.put(new Date().getTime()+8, new Double(1032));
		venderDataMap.put(new Date().getTime()+9, new Double(1043));
		Map<String,Map<Long,Double>> dataMap=new HashMap<String,Map<Long,Double>>();
		dataMap.put("Instcnace1", instanceDataMap);
		dataMap.put("Instcnace2", venderDataMap);
	//	dataMap.put("Instcnace2", instanceDataMap);
		//dataMap.put("vender1", venderDataMap);
	//	dataMap.put("vender2", venderDataMap);
		Map<String,Map<String,Map<Long,Double>>> overallDataMap=new HashMap<String,Map<String,Map<Long,Double>>>();
		overallDataMap.put("INSTANCE", dataMap);
		//overallDataMap.put("VENDER", dataMap);
		//threeDDataSet.add(overallDataMap);
		cloudResourceDataSet.setCurrencyCode("$");
		cloudResourceDataSet.setThreeDDataSet(overallDataMap);
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		try {
			mapper.writeValue(strWriter, cloudResourceDataSet);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userDataJSON = strWriter.toString();
		System.out.println("JSON "+userDataJSON);
		//String userDataJSON = "{\"userId\":\"100\",\"userName\":{\"firstname\":\"K\",\"middlename\":\"Siva\",\"lastname\":\"Prasad\"},\"dob\":1300878089906}";
		CloudResourceUsageDataSet userFromJSON=null;
		try {
			userFromJSON = mapper.readValue(userDataJSON, CloudResourceUsageDataSet.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Map<String,Map<Long,Double>>> threeDDataSet2= userFromJSON.getThreeDDataSet();
		Map<String,Map<Long,Double>> daaaa=threeDDataSet2.get("INSTANCE");
		for(String instanceName:daaaa.keySet())
		{
			System.out.println("Instance Name"+instanceName);
			Map<Long ,Double> dataset=daaaa.get(instanceName);
			for(Long month:dataset.keySet())
			{
				System.out.println("Maonth" +month+" "+dataset.get(month));
			}
		}
		System.out.println(userFromJSON);
		return cloudResourceDataSet;
	}
}
