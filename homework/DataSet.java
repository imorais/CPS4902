import java.util.ArrayList;
import java.util.stream.IntStream;

public class DataSet {

	private String data [][] = null;
	private double entropy = 0;
	private ArrayList<Factor> factors = null;
	
	// constructor
	public DataSet(String [][] data) {
		this.data = data;
		new Factor(data, data[0].length - 1).getValues().stream().forEach(factorValue -> 
				entropy += minusPlog2((double)factorValue.getAmount() / (data.length - 1)));
	}
	
	public String[][] getData() {
		return data;
	}
	public double getEntropy() {
		return entropy;
	}
	public ArrayList<Factor> getFactors() {
		return factors;
	}
	
	public String toString() {
		StringBuffer stringBuff = new StringBuffer();
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < data[row].length; col++) {
				stringBuff.append(data[row][col]);
				IntStream.range(0, 14 - data[row][col].length()).forEach(i -> stringBuff.append(" "));
			}
			stringBuff.append("\n");
			if(row == 0) {
				IntStream.range(0, 108).forEach(i -> stringBuff.append("-"));
				stringBuff.append(" ");
			}
		}
		return stringBuff.toString();
	}
	
	// This will help in finding the entropy.
	double minusPlog2(double p) {
		double returnValue = 0;
		if(p != 0) {
			returnValue = (-1) * p * Math.log(p) / Math.log(2);
		}
		return returnValue;
	}
	

}
