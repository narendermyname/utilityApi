/**
 * 
 */
package com.string.test;

/**
 * @author nstanwar
 *
 */
public class TestForecastData {

	/**
	 * 
	 */
	public TestForecastData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double dataset[]={10,27,30,19.04296875,18.12744140625,15.655517578125,12.908935546875,11.165499687194824};double alpha=0.375;
		//double dataset[]={72,12,0,12,7.5,6.75};double alpha=0.5;
		//double dataset[]={71,80,80,77.94640700000001,76.92623711000002,75.77674991000002,74.62726271000001};double alpha=0.73;
		//double dataset[]={2920.56	,1687.76,	6733.49};double alpha=0.131;
		System.out.println();
		double data=getNextData(dataset,alpha);
		System.out.println("Next Fore casted Data "+data);

	}	/**
	 * Calculate Next Forecasted Data Based On dataset and alpha
	 * @param dataset
	 * @param alpha
	 * @return
	 */
	private static double getNextData(double []dataset,double alpha)
	{
		double data=0;
		int j=dataset.length-1;
		for(int i=0;i<3;i++)
		{
			data+=alpha*(Math.pow((1-alpha), i))*dataset[j--];
			//System.out.println(data);
		}
		return data;
	}
	/**
	 * Calculate Alpha from given data set
	 * @param dataset
	 * @return
	 */
	private static double getAlpha(double dataset[]){
		double alpha=0;
		for(double a:dataset)
		{
			
		}
		return 0;
	}
}
