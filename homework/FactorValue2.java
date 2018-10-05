
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
