/**
 * 
 */
package com.naren.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author nstanwar
 *
 */
public class JavaMap {

	/**
	 * 
	 */
	//private String year;
	private Map graph_data[];

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JavaMap [graph_data=" + Arrays.toString(graph_data) + "]";
	}

	/**
	 * @return the graph_data
	 */
	public Map[] getGraph_data() {
		return graph_data;
	}

	/**
	 * @param graph_data the graph_data to set
	 */
	public void setGraph_data(Map[] graph_data) {
		this.graph_data = graph_data;
	}

}
