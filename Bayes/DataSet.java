// Stopped at 10:39. Still have to do the method getColNumb().

import java.util.HashMap;
import java.util.stream.IntStream;

public class DataSet {
	
	private String [][] data = null;
	private Factor classFactor = null;
	private HashMap<String, Double> priorProbs = new HashMap<String, Double>();
	public DataSet(String [][] data) {
		this.data = data;
	}
	public String [][] getData() {
		return data;
	}
	// This will print out the data set to the screen in table format.
	public String toString() {
		StringBuffer stringBuff = new StringBuffer();
		IntStream.range(0, data.length).forEach(row -> {
			IntStream.range(0, data[row].length).forEach(column -> {
				stringBuff.append(data[row][column]);
				IntStream.range(0, 24 - data[row][column].length()).forEach(i -> stringBuff.append(" "));
			});
			stringBuff.append("\n");
			if(row == 0) {
				IntStream.range(0, 108).forEach(i -> stringBuff.append("-"));
				stringBuff.append("\n");
			}
		});
		return stringBuff.toString();
	}
	private static String getResultsStr(DataSet dataSet, HashMap<String, String> instanceMap, HashMap<String, String> 
		logMap, Double prob, String factorValue) {
		StringBuffer resultSB = new StringBuffer(dataSet + "\n");
		String instanceStr = getInstanceStr(dataSet, instanceMap);
		resultSB.append("P(" + factorValue + "|" + instanceStr + ") = (P(" + factorValue + ")");
		IntStream.range(0, dataSet.data[0].length - 1).forEach(i -> resultSB.append("*P(" + 
				instanceMap.get(dataSet.data[0][i]) + "|" + factorValue + ")"));
		resultSB.append(") / " + "P(" + instanceStr + ")\n");
		resultSB.append("P(" + factorValue + "|" + instanceStr + ") = " + "((" + logMap.get(factorValue) + ")");
		IntStream.range(0, dataSet.data[0].length - 1).forEach(i -> resultSB.append("*(" + 
				logMap.get(dataSet.data[0][i]) + ")"));
		resultSB.append(") / " + "P(" + instanceStr + ")\n");
		resultSB.append("P(" + factorValue + "|" + instanceStr + ") = " + String.format("%.5f", prob) + " / " + 
				"P(" + instanceStr + ") \n");
		return resultSB.toString();
	}
	static String getInstanceStr(DataSet dataSet, HashMap<String, String> instanceMap) {
		StringBuffer instanceSB = new StringBuffer("<");
		IntStream.range(0, dataSet.data[0].length - 2).forEach(i -> instanceSB.append(instanceMap.get(dataSet.data[0][i]) + ", "));
		return (instanceSB.append(instanceMap.get(dataSet.data[0][dataSet.data[0].length - 2]) + ">")).toString();
	}

}









