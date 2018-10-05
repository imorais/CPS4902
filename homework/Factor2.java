/*
 * Name: Isabel Morais
 * Date: 10-5-18
 * Class: CPS 4902 - 01
 * Assignment: Hw1
 * Purpose: To create a Decision Tree based off of a Data Set, which is a 2D array.
 * Classes Involved: hw1_2.java, FactorValue2.java, Factor2.java, DataSet2.java
 */

import java.util.HashSet;
import java.util.stream.IntStream;

public class Factor2 {

	private String name = null;
	private HashSet<FactorValue2> factorValues = new HashSet<FactorValue2>();
	public Factor2(String [][] data, int column) {
		this.name = data[0][column];
		IntStream.range(1, data.length).forEach(row -> factorValues.add(new FactorValue2(data[row][column])));
		factorValues.stream().forEach(factorValue -> {
			int counter = 0;
			for(int row = 1; row < data.length; row++) {
				if(factorValue.getName() == data[row][column]) {
					factorValue.setOccurences(++counter);
				}
			}
		});
	}
	public String getName() {
		return name;
	}
	public HashSet<FactorValue2> getFactorValues(){
		return factorValues;
	}
	public String toString() {
		return name;
	}
	
}
