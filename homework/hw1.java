/*
 * Inputs: the data set inputed into the program through line 55's datas.put()
 * Outputs: the data set given in the main method,
 * the entropy,
 * the information gain and remainder of each factor,
 * and the best factor to split on.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.IntStream;

// This will be the main method.

public class hw1 {

	// Define data sets
	static String [][] Restaurant = {{"Alt", "Bar", "Fri/Sat", "Hun", "Pat", "Price", "Rain", "Reserv", 
		"Type", "Est", "Target Wait"},
		{"T", "F", "F", "T", "Some", "$$$", "F", "T", "French", "0-10", "T"},
		{"T", "F", "F", "T", "Full", "$", "F", "F", "Thai", "30-60", "F"},
		{"F", "T", "F", "F", "Some", "$", "F", "F", "Burger", "0-10", "T"},
		{"T", "F", "T", "T", "Full", "$", "F", "F", "Thai", "10-30", "T"},
		{"T", "F", "T", "F", "Full", "$$$", "F", "T", "French", ">60", "F"},
		{"F", "T", "F", "T", "Some", "$$", "T", "T", "Italian", "0-10", "T"},
		{"F", "T", "F", "F", "None", "$", "T", "F", "Burger", "0-10", "F"},
		{"F", "F", "F", "T", "Some", "$$", "T", "T", "Thai", "0-10", "T"},
		{"F", "T", "T", "F", "Full", "$", "T", "F", "Burger", ">60", "F"},
		{"T", "T", "T", "T", "Full", "$$$", "F", "T", "Italian", "10-30", "F"},
		{"F", "F", "F", "F", "None", "$", "F", "F", "Thai", "0-10", "F"},
		{"T", "T", "T", "T", "Full", "$", "F", "F", "Burger", "30-60", "T"}};
	
	static String [][] Weather = {{"Outlook", "Temp", "Humidity", "Windy", "Play"},
		{"sunny", "hot", "high", "F", "no"},
		{"sunny", "hot", "high", "T", "no"},
		{"overcast", "hot", "high", "F", "yes"},
		{"rainy", "mild", "high", "F", "yes"},
		{"rainy", "cool", "normal", "F", "yes"},
		{"rainy", "cool", "normal", "T", "no"},
		{"overcast", "cool", "normal", "T", "yes"},
		{"sunny", "mild", "high", "F", "no"},
		{"sunny", "cool", "normal", "F", "yes"},
		{"rainy", "mild", "normal", "F", "yes"},
		{"sunny", "mild", "normal", "T", "yes"},
		{"overcast", "mild", "high", "T", "yes"},
		{"overcast", "hot", "normal", "F", "yes"},
		{"rainy", "mild", "high", "T", "no"}};
	
	public static void main(String[] args) {
		
		hw1 num1 = new hw1();
		HashMap<String, String[][]> datas = new HashMap<String, String[][]>();
		// This is using the Restaurant data set above.
		datas.put("Target Wait ", Weather);
		doEntnInfoG(datas, num1);
		
	}
	
	static void doEntnInfoG(HashMap<String, String[][]> datas, hw1 num1) {
		// go over keys in each data set
		datas.keySet().forEach(data -> {
		// calculate info gain for each key in the data set and puts it in the hash map
		HashMap<Factor1, Double> factorInfoGain = new HashMap<Factor1, Double>();
		// This will hold the summations for each factor in a hash map. This will be used when we print the infoGain table
		HashMap<Factor1, Double> factorSummation = new HashMap<Factor1, Double>();
		// The DataSet class is what we made.
		//It is different from the instance, dataSet, which we are making right now.
		DataSet1 dataSet = new DataSet1(datas.get(data)); 
		// The range for this stream is 0 to the length of the row - 1.
		// It then goes into each column and grabs that factor in that column and does the following.
		IntStream.range(0, datas.get(data)[0].length - 1).forEach(col -> {
			Factor1 factor = new Factor1(datas.get(data), col);
			ArrayList<DataSet1> dataSets = new ArrayList<DataSet1>();
			factor.getValues().stream().forEach(factorValue -> 
				dataSets.add(num1.createDataSet(factorValue, col, datas.get(data))));
			// Calculate summation for all the factor's entropy
			double summation = 0;
			for(int i = 0; i < dataSets.size(); i++) {
				summation += ((double)(dataSets.get(i).getData().length - 1) / 
						(datas.get(data).length - 1)) * dataSets.get(i).getEntropy();
			}
			// This will put the summation into the hashmap
			factorSummation.put(factor, summation);
			// This will get the info gain
			factorInfoGain.put(factor, dataSet.getEntropy() - summation);
		});
		// Display all the info
		System.out.println("<" + data + "DATASET>:\n" + dataSet);
		//Print the entropy
		System.out.println("The entropy is: " + dataSet.getEntropy() + "\n");
		System.out.println(num1.generateInfoGainDisplayTable(factorInfoGain, factorSummation));
		System.out.println("Best factor to split on is: " + num1.determineSplitOnFeature(factorInfoGain)
				+ "\n");
		System.out.println("\n\n");
		});
	}
	
	DataSet1 createDataSet(FactorValue1 factorValue, int col, String[][] data) {
		String [][] returnData = new String[factorValue.getAmount()+1][data[0].length];
		returnData[0] = data[0];
		int count = 1;
		for(int row = 1; row < data.length; row++) {
			if(data[row][col] == factorValue.getName()) {
				returnData[count++] = data[row];
			}
		}
		return new DataSet1(deleteCol(returnData, col));
	}
	
	Factor1 determineSplitOnFeature(HashMap<Factor1, Double> factorsInfoGain) {
		Factor1 splitOnFactor = null;
		Iterator<Factor1> iterator = factorsInfoGain.keySet().iterator();
		while(iterator.hasNext()) {
			Factor1 factor = iterator.next();
			if(splitOnFactor == null) {
				splitOnFactor = factor;
			}
			if(factorsInfoGain.get(splitOnFactor) < factorsInfoGain.get(factor)) {
				splitOnFactor = factor;
			}
		}
		return splitOnFactor;
	}
	
	// Displays table of Info Gain
	StringBuffer generateInfoGainDisplayTable(HashMap<Factor1, Double> factorsInfoGain, HashMap<Factor1, Double> factorSum) {
		StringBuffer stringBuff = new StringBuffer();
		stringBuff.append("Factor			Information Gain			Summation\n");
		IntStream.range(0, 66).forEach(i -> stringBuff.append("-"));
		stringBuff.append("\n");
		Iterator<Factor1> iterator = factorsInfoGain.keySet().iterator();
		while(iterator.hasNext()) {
			Factor1 factor = iterator.next();
			stringBuff.append(factor);
			// This line formats the two columns nicely
			IntStream.range(0, 25 - factor.getName().length()).forEach(i -> stringBuff.append(" "));
			stringBuff.append(String.format("%.8f", factorsInfoGain.get(factor)));
			IntStream.range(0, 20).forEach(j -> stringBuff.append(" "));
			stringBuff.append(String.format("%.8f", factorSum.get(factor)) + "\n");
		}
		return stringBuff;
	}
	
	// method to delete columns
	String [][] deleteCol(String [][] data, int deleteThisCol){
		String [][] returnData = new String[data.length][data[0].length - 1];
		for(int row = 0; row < data.length; row++) {
			int colCount = 0;
			for(int col = 0; col < data[0].length; col++) {
				if(col != deleteThisCol) {
					returnData[row][colCount++] = data[row][col];
				}
			}
		}
		return returnData;
	}

}