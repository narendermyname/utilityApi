/**
 * 
 */
package com.naren.dto;

import java.util.Map;

/**
 * @author nstanwar
 *
 */
public class JAVA {

	/**
	 * 
	 */
	private String name;
	private String email;
	private String data;
	private Map<String,double [][]> mapData;
	private Map<String ,JavaMap []> graph_data;
	/**
	 * @return the graph_data
	 */
	public Map<String, JavaMap[]> getGraph_data() {
		return graph_data;
	}
	/**
	 * @param graph_data the graph_data to set
	 */
	public void setGraph_data(Map<String, JavaMap[]> graph_data) {
		this.graph_data = graph_data;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	public JAVA() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the mapData
	 */
	public Map<String, double[][]> getMapData() {
		return mapData;
	}
	/**
	 * @param mapData the mapData to set
	 */
	public void setMapData(Map<String, double[][]> mapData) {
		this.mapData = mapData;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JAVA [name=" + name + ", email=" + email + ", data=" + data
				+ ", mapData=" + mapData + ", graph_data=" + graph_data + "]";
	}
	
}
