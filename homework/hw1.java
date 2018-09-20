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
	
	public static void main(String[] args) {
		
		hw1 num1 = new hw1();
		HashMap<String, String[][]> datas = new HashMap<String, String[][]>();
		// This is using the Restaurant data set above.
		datas.put("Target Wait", Restaurant);
		// go over keys in each data set
		datas.keySet().forEach(data -> {
			HashMap<Factor, Double> factorInfoGain = new HashMap<Factor, Double>();
			// The DataSet class is what we made.
			//It is different from the instance, dataSet, which we are making right now.
			DataSet dataSet = new DataSet(datas.get(data)); 
			IntStream.range(0, datas.get(data)[0].length - 1).forEach(col -> {
				Factor factor = new Factor(datas.get(data), col);
				ArrayList<DataSet> dataSets = new ArrayList<DataSet>();
				factor.getValues().stream().forEach(factorValue -> 
					dataSets.add(num1.createDataSet(factorValue, col, datas.get(data))));
				// Calculate summation for all the factor's entropy
				double summation = 0;
				for(int i = 0; i < dataSets.size(); i++) {
					summation += ((double)(dataSets.get(i).getData().length - 1) / 
							(datas.get(data).length - 1)) * dataSets.get(i).getEntropy();
				}
				// This will get the info gain
				factorInfoGain.put(factor, dataSet.getEntropy() - summation);
			});
			// Display all the info
			System.out.println("<" + data + "DATASET>:\n" + dataSet);
			System.out.println(num1.generateInfoGainDisplayTable(factorInfoGain));
			System.out.println("Best factor to split on is: " + num1.determineSplitOnFeature(factorInfoGain)
					+ "\n");
			System.out.println("\n\n");
		});
		
	}
	
	DataSet createDataSet(FactorValue factorValue, int col, String[][] data) {
		String [][] returnData = new String[factorValue.getAmount()+1][data[0].length];
		returnData[0] = data[0];
		int count = 1;
		for(int row = 1; row < data.length; row++) {
			if(data[row][col] == factorValue.getName()) {
				returnData[count++] = data[row];
			}
		}
		return new DataSet(deleteCol(returnData, col));
	}
	
	Factor determineSplitOnFeature(HashMap<Factor, Double> factorsInfoGain) {
		Factor splitOnFactor = null;
		Iterator<Factor> iterator = factorsInfoGain.keySet().iterator();
		while(iterator.hasNext()) {
			Factor factor = iterator.next();
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
	StringBuffer generateInfoGainDisplayTable(HashMap<Factor, Double> factorsInfoGain) {
		StringBuffer stringBuff = new StringBuffer();
		stringBuff.append("Factor			Information Gain\n");
		IntStream.range(0, 38).forEach(i -> stringBuff.append("-"));
		stringBuff.append("\n");
		Iterator<Factor> iterator = factorsInfoGain.keySet().iterator();
		while(iterator.hasNext()) {
			Factor factor = iterator.next();
			stringBuff.append(factor);
			IntStream.range(0, 21 - factor.getName().length()).forEach(i -> stringBuff.append(" "));
			stringBuff.append(String.format("%.8f", factorsInfoGain.get(factor)) + "\n");
		}
		return stringBuff;
	}
	
	// method to delete columns
	String [][] deleteCol(String [][] data, int deleteCol){
		String [][] returnData = new String[data.length][data[0].length - 1];
		for(int row = 0; row < data.length; row++) {
			int colCount = 0;
			for(int col = 0; col < data[0].length; col++) {
				if(col != deleteCol) {
					returnData[row][colCount++] = data[row][col];
				}
			}
		}
		return returnData;
	}

}
