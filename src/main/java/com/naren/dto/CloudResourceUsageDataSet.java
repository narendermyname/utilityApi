/**
 * 
 */
package com.naren.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author nstanwar
 *
 */
public class CloudResourceUsageDataSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,List<Double>> legendData;
	private Map<String,String> messageResources;
	private Map<String,Map<String,Double>> cloudResourceDataSet;
	private Map<String,Map<String,Map<Long,Double>>> threeDDataSet;
	private String currencyCode;
	/**
	 * @return the legendData
	 */
	public Map<String, List<Double>> getLegendData() {
		return legendData;
	}
	/**
	 * @param legendData the legendData to set
	 */
	public void setLegendData(Map<String, List<Double>> legendData) {
		this.legendData = legendData;
	}
	/**
	 * @return the messageResources
	 */
	public Map<String, String> getMessageResources() {
		return messageResources;
	}
	/**
	 * @param messageResources the messageResources to set
	 */
	public void setMessageResources(Map<String, String> messageResources) {
		this.messageResources = messageResources;
	}
	/**
	 * @return the cloudResourceDataSet
	 */
	public Map<String, Map<String, Double>> getCloudResourceDataSet() {
		return cloudResourceDataSet;
	}
	/**
	 * @param cloudResourceDataSet the cloudResourceDataSet to set
	 */
	public void setCloudResourceDataSet(
			Map<String, Map<String, Double>> cloudResourceDataSet) {
		this.cloudResourceDataSet = cloudResourceDataSet;
	}
	/**
	 * @return the threeDDataSet
	 */
	public Map<String, Map<String, Map<Long, Double>>> getThreeDDataSet() {
		return threeDDataSet;
	}
	/**
	 * @param threeDDataSet the threeDDataSet to set
	 */
	public void setThreeDDataSet(
			Map<String, Map<String, Map<Long, Double>>> threeDDataSet) {
		this.threeDDataSet = threeDDataSet;
	}
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CloudResourceUsageDataSet [legendData=" + legendData
				+ ", messageResources=" + messageResources
				+ ", cloudResourceDataSet=" + cloudResourceDataSet
				+ ", threeDDataSet=" + threeDDataSet + ", currencyCode="
				+ currencyCode + "]";
	}

}
