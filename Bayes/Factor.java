import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.IntStream;

public class Factor {
	
	private String name = null;
	private String [][] data = null;
	private HashSet<FactorValue> factorValues = new HashSet<FactorValue>();
	private double probability;
	// The column is the column that we are looking at for the factor
	public Factor(String [][] data, int column) {
		this.name = data[0][column];
		this.data = data;
		IntStream.range(1, data.length).forEach(row -> factorValues.add(new FactorValue(data[row][column])));
		// This will calculate the occurances of each factor
		factorValues.stream().forEach(factorValue -> {
			int counter = 0;
			for(int row = 1; row < data.length; row++) {
				if(factorValue.getName() == data[row][column]) {
					factorValue.setOccurances(++counter);
				}
			}
		});
	}
	public double getProbablity() {
		return probability;
	}
	public String getName() {
		return name;
	}
	public HashSet<FactorValue> getFactorValues(){
		return factorValues;
	}
	public String toString() {
		return name;
	}
	// Returns the values of a factor given the factor's name
	public FactorValue getFactorValue(String factorValueName) {
		FactorValue returnValue = null;
		Iterator<FactorValue> iterator = factorValues.iterator();
		while(iterator.hasNext()) {
			FactorValue factorValue = iterator.next();
			if(factorValue.getName().equals(factorValueName)) {
				returnValue = factorValue;
				break;
			}
		}
		return returnValue;
	}
	public Factor calcProb(String factorValueName, HashMap<String, String> logMap) {
		if(getFactorValue(factorValueName) != null) {
			// probability is the number of occurrences of a factor value over the data's length - row that contains
			// factor names.
			probability = ((double)getFactorValue(factorValueName).getOccurances()) / (data.length - 1);
			logMap.put(this.name, getFactorValue(factorValueName).getOccurances() + "/" + (data.length - 1));
		}
		else {
			probability = 0;
			logMap.put(this.name, "0/" + (data.length - 1));
		}
		return this;
	}

}










