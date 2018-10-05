/*
 * Name: Isabel Morais
 * Date: 10-5-18
 * Class: CPS 4902 - 01
 * Assignment: Hw1
 * Purpose: To create a Decision Tree based off of a Data Set, which is a 2D array.
 * Classes Involved: hw1_2.java, FactorValue2.java, Factor2.java, DataSet2.java
 */

public class FactorValue2 {
	
	private String name;
	private int occurences;
	
	public FactorValue2(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getOccurences() {
		return occurences;
	}
	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}
	public int hashCode() {
		return name.hashCode();
	}
	public boolean equals(Object obj) {
		boolean returnValue = true;
		if(obj == null || (getClass() != obj.getClass())) {
			returnValue = false;
		}
		if(name == null) {
			if(((FactorValue2) obj).name != null) {
				returnValue = false;
			}
		}
		else if(!name.equals(((FactorValue2) obj).name)) {
			returnValue = false;
		}
		return returnValue;
	}
	public String toString() {
		return name;
	}
	

}
