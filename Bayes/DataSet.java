
import java.util.ArrayList;
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
	private static String getResultStr(DataSet dataSet, HashMap<String, String> instanceMap, HashMap<String, String> 
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
	// returns the column number gives the column name
	private int getColNum(String colName) {
		int returnValue = -1;
		for(int column = 0; column < data[0].length; column++) {
			if(data[0][column] == colName) {
				returnValue = column;
				break;
			}
		}
		return returnValue;
	}
	private DataSet createDataSet(FactorValue classFactorValue) {
		String [][] returnData = new String[classFactorValue.getOccurances() + 1][data[0].length];
		returnData[0] = data[0];
		int counter = 1;
		for(int row = 1; row < data.length; row++) {
			if(data[row][data[0].length - 1].equals(classFactorValue.getName())) {
				returnData[counter++] = data[row];
			}
		}
		return new DataSet(returnData);
	}
	private DataSet calcPriorProbs() {
		classFactor = new Factor(data, data[0].length - 1);
		classFactor.getFactorValues().stream().forEach(factorValue -> 
				priorProbs.put(factorValue.getName(), (double)factorValue.getOccurances() / (data.length - 1)));
		return this;
	}
	// The next two methods calculate the conditional probabilities
	public HashMap<String, Double> calcCondProbs(HashMap<String, String> instance){
		calcPriorProbs();
		HashMap<String, Double> condProbs = new HashMap<String, Double>();
		classFactor.getFactorValues().forEach(factorValue -> {
			HashMap<String, String> logMap = new HashMap<String, String>();
			logMap.put(factorValue.getName(), factorValue.getOccurances() + "/" + (data.length - 1));
			DataSet newDataSet = createDataSet(classFactor.getFactorValue(factorValue.getName()));
			double condProb = calcCondProb(newDataSet, factorValue.getName(), instance, logMap);
			condProbs.put(factorValue.getName(), condProb);
			System.out.println(getResultStr(newDataSet, instance, logMap, condProb, factorValue.getName()));
		});
		return condProbs;
	}
	private double calcCondProb(DataSet newDataSet, String classFactorValue, HashMap<String, String> instanceMap, 
				HashMap<String, String> logMap) {
		ArrayList<Factor> factors = new ArrayList<Factor>();
		instanceMap.keySet().stream().forEach(factorName -> 
				factors.add(new Factor(newDataSet.data, getColNum(factorName)).calcProb(instanceMap.get(factorName), 
				logMap)));
		double condProb = priorProbs.get(classFactorValue);
		for(int i = 0; i < factors.size(); i++) {
			condProb *= factors.get(i).getProbablity();
		}
		return condProb;
	}

}









