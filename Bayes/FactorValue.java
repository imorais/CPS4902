
public class FactorValue {
	
	private String name;
	private int occurances;
	public FactorValue(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getOccurances() {
		return occurances;
	}
	public void setOccurances(int occurances) {
		this.occurances = occurances;
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
			if(((FactorValue) obj).name != null) {
				returnValue = false;
			}
		}
		else if(!name.equals(((FactorValue) obj).name)) {
			returnValue = false;
		}
		return returnValue;
	}
	public String toString() {
		return name;
	}

}
