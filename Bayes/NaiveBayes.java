import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class NaiveBayes {

	static String dataKey = Data.datas.keySet().iterator().next();
	
	public static void main(String[] args) throws IOException {
		DataSet dataSet = new DataSet(Data.datas.get(dataKey));
		System.out.println("[" + dataKey + " DATASET]\n" + dataSet);
		BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		while(flag) {
			System.out.println("> What do you want to do? (calc prob, change dataset, exit)");
			String command = buffRead.readLine();
			switch (command) {
			case "calc prob":
				System.out.print("> Please enter values for: ");
				for(int i = 0; i < dataSet.getData()[0].length - 2; i++) {
					System.out.print(dataSet.getData()[0][i] + ", ");
				}
				System.out.println(dataSet.getData()[0][dataSet.getData()[0].length - 2] + " (seprated by commas)");
				String [] values = (buffRead.readLine()).split(",");
				HashMap<String, String> instMap = new HashMap<String, String>();
				for(int i = 0; i < dataSet.getData()[0].length - 1; i++) {
					instMap.put(dataSet.getData()[0][i], values[i].trim());
				}
				HashMap<String, Double> condProbs = dataSet.calcCondProbs(instMap);
				double allProbs = 0;
				Iterator<Double> probsIterator = condProbs.values().iterator();
				while(probsIterator.hasNext()) {
					allProbs += probsIterator.next();
				}
				Iterator<String> keyIterator = condProbs.keySet().iterator();
				while(keyIterator.hasNext()) {
					String next = keyIterator.next();
					System.out.println("P(" + next + "|" + DataSet.getInstanceStr(dataSet, instMap) + ") = " + 
							String.format("%.5f", condProbs.get(next)) + "/" + String.format("%.5f", allProbs) + 
							" = " + String.format("%.5f", condProbs.get(next) / allProbs));
				}
				System.out.println();
				break;
			// This will have the user change to a different data set
			case "change dataset":
				System.out.println("> Choose DataSet: (" + Data.datas.keySet() + ")");
				String value = buffRead.readLine();
				if(Data.datas.keySet().contains(value)) {
					dataKey = value;
					dataSet = new DataSet(Data.datas.get(dataKey));
					System.out.println(dataSet);
				}
				else {
					System.out.println("> Error! Please enter a valid Data Set");
				}
				break;
			case "exit":
				flag = false;
				break;
			}
		}
		System.exit(0);
	}
}







