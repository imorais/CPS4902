import java.util.HashSet;
import java.util.stream.IntStream;

public class Factor {

	private String name = null;
	private HashSet<FactorValue> values = new HashSet<FactorValue>();
	
	// This is the constructor that will take in the data from the data set.
	// column is the column of this Factor within in the data.
	public Factor(String [][] data, int column) {
		// The first row contains all of the factors
		this.name = data[0][column];
		IntStream.range(1, data.length).forEach (row -> values.add(new FactorValue(data[row][column])));
		values.stream().forEach(factorValue -> {
			int count = 0;
			// Find the occurrences for each factor
			for(int row = 1; row < data.length; row++) {
				if(factorValue.getName() == data[row][column]) {
					factorValue.setAmount(++count);
				}
			}
		});
	}
	
	public String getName() {
		return name;
	}
	public HashSet<FactorValue> getValues() {
		return values;
	}
	@Override
	public String toString() {
		return name;
	}
	
	

}
