/*
 * Name: Isabel Morais
 * Date: 10-5-18
 * Class: CPS 4902 - 01
 * Assignment: Hw1
 * Purpose: To create a Decision Tree based off of a Data Set, which is a 2D array.
 * Classes Involved: hw1_2.java, FactorValue2.java, Factor2.java, DataSet2.java
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.IntStream;

public class DataSet2 {

	private String name;
	private String [][] data = null;
	private double entropy = 0;
	private HashMap<Factor2, Double> infoGains = new HashMap<Factor2,Double>();
	private Factor2 splitOnFactor = null;
	
	public DataSet2(String name, String[][] data) {
		this.name = name;
		this.data = data;
		calculateEntropy().calcInfoGains().findSplitOnFactor();
	}
	public String[][] getData(){
		return data;
	}
	public double getEntropy() {
		return entropy;
	}
	public HashMap<Factor2, Double> getInfoGains() {
		return infoGains;
	}
	public Factor2 getSplitOnFactor() {
		return splitOnFactor;
	}
	// returns name of the data set
	public String toString() {
		return name;
	}
	// deletes a column in a 2d array
	private String[][] deleteColumns(String[][] data, int toDeleteCol){
		String returnData[][] = new String[data.length][data[0].length - 1];
		IntStream.range(0, data.length).forEach(row -> {
			int colCounter = 0;
			for(int col = 0; col < data[0].length; col++) {
				if(col != toDeleteCol) {
					returnData[row][colCounter++] = data[row][col];
				}
			}
		});
		return returnData;
	}
	// returns column number given a column name
	public int getColNum(String colName) {
		int returnVal = -1;
		for(int col = 0; col < data[0].length - 1; col++) {
			if(data[0][col] == colName) {
				returnVal = col;
				break;
			}
		}
		return returnVal;
	}
	private double minusPlog2(double p) {
		double returnVal = 0;
		if(p != 0) {
			returnVal = (-1) * p * Math.log(p) / Math.log(2);
		}
		return returnVal;
	}
	private DataSet2 calculateEntropy() {
		new Factor2(data, data[0].length - 1).getFactorValues().stream().forEach(factorValue -> 
				entropy += minusPlog2((double)factorValue.getOccurences() / (data.length - 1)));
		return this;
	}
	// creates and returns a new data set
	DataSet2 createDataSet(Factor2 factor, FactorValue2 factorVal, String[][] data) {
		int col = getColNum(factor.getName());
		String [][] returnData = new String[factorVal.getOccurences() + 1][data[0].length];
		returnData[0] = data[0];
		int counter = 1;
		for(int row = 1; row < data.length; row++) {
			if(data[row][col] == factorVal.getName()) {
				returnData[counter++] = data[row];
			}
		}
		return new DataSet2(factor.getName() + ": " + factorVal.getName(), deleteColumns(returnData, col));
	}
	// calculate info gain for each factor in the data set
	private DataSet2 calcInfoGains() {
		IntStream.range(0, data[0].length - 1).forEach(col -> {
			Factor2 factor = new Factor2(data, col);
			ArrayList<DataSet2> dataSets = new ArrayList<DataSet2>();
			factor.getFactorValues().stream().forEach(factorVal ->
				dataSets.add(createDataSet(factor, factorVal, data)));
			double summation = 0;
			for(int i = 0; i < dataSets.size(); i++) {
				summation += ((double)(dataSets.get(i).getData().length - 1) / (data.length - 1)) * 
						dataSets.get(i).getEntropy();
			}
			infoGains.put(factor, entropy - summation);
		});
		return this;
	}
	// finds the best factor to split on
	private DataSet2 findSplitOnFactor() {
		Iterator<Factor2> iterator = infoGains.keySet().iterator();
		while(iterator.hasNext()) {
			Factor2 factor = iterator.next();
			if(splitOnFactor == null || infoGains.get(splitOnFactor) < infoGains.get(factor)) {
				splitOnFactor = factor;
			}
		}
		return this;
	}
	
}