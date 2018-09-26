public class FactorValue {
	private String name;
	private int amount;
	
	public FactorValue(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	// This is to compare factor values with each other.
	public int hashCode() {
		return name.hashCode();
	}
	public boolean equals(Object object) {
		boolean returnValue = true;
		if(object == null || (getClass() != object.getClass())) {
			returnValue = false;
		}
		if(name == null)	{
			if(((FactorValue) object).name != null){
				returnValue = false;
			} 
		}
		else if(!name.equals(((FactorValue) object).name)) {
			returnValue = false;
		}
		return returnValue;
	}

}
