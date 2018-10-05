
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class hw1_2 extends JFrame {
	
	private static final long serialVersionUID = 1L;

		// Define data sets
		static String [][] Restaurant = {{"Alt", "Bar", "Fri/Sat", "Hun", "Pat", "Price", "Rain", "Reserv", 
			"Type", "Est", "Target Wait"},
			{"T", "F", "F", "T", "Some", "$$$", "F", "T", "French", "0-10", "T"},
			{"T", "F", "F", "T", "Full", "$", "F", "F", "Thai", "30-60", "F"},
			{"F", "T", "F", "F", "Some", "$", "F", "F", "Burger", "0-10", "T"},
			{"T", "F", "T", "T", "Full", "$", "F", "F", "Thai", "10-30", "T"},
			{"T", "F", "T", "F", "Full", "$$$", "F", "T", "French", ">60", "F"},
			{"F", "T", "F", "T", "Some", "$$", "T", "T", "Italian", "0-10", "T"},
			{"F", "T", "F", "F", "None", "$", "T", "F", "Burger", "0-10", "F"},
			{"F", "F", "F", "T", "Some", "$$", "T", "T", "Thai", "0-10", "T"},
			{"F", "T", "T", "F", "Full", "$", "T", "F", "Burger", ">60", "F"},
			{"T", "T", "T", "T", "Full", "$$$", "F", "T", "Italian", "10-30", "F"},
			{"F", "F", "F", "F", "None", "$", "F", "F", "Thai", "0-10", "F"},
			{"T", "T", "T", "T", "Full", "$", "F", "F", "Burger", "30-60", "T"}};
		
		static String [][] Weather = {{"Outlook", "Temp", "Humidity", "Windy", "Play"},
				{"sunny", "hot", "high", "F", "no"},
				{"sunny", "hot", "high", "T", "no"},
				{"overcast", "hot", "high", "F", "yes"},
				{"rainy", "mild", "high", "F", "yes"},
				{"rainy", "cool", "normal", "F", "yes"},
				{"rainy", "cool", "normal", "T", "no"},
				{"overcast", "cool", "normal", "T", "yes"},
				{"sunny", "mild", "high", "F", "no"},
				{"sunny", "cool", "normal", "F", "yes"},
				{"rainy", "mild", "normal", "F", "yes"},
				{"sunny", "mild", "normal", "T", "yes"},
				{"overcast", "mild", "high", "T", "yes"},
				{"overcast", "hot", "normal", "F", "yes"},
				{"rainy", "mild", "high", "T", "no"}};
		
		// this map contain the data sets that can be used in this program
		static Map<String, String[][]> datas = Collections.unmodifiableMap(new HashMap<String, String[][]>() {
			private static final long serialVersionUID = 1L;
			{
				put("Restaurant", Restaurant);
				put("Weather", Weather);
			}
		});
		// we will initially pick the first item in the map to use in the program, which is Restaurant
		static String dataKey = datas.keySet().iterator().next();

	public static void main(String[] args) throws IOException {
		
		hw1_2 work = new hw1_2();
		// decision tree will be displayed in a JTree
		JTree tree = null;
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		while(flag) {
			System.out.println("> What do you want to do? (build tree, choose dataset, exit)");
			String command = buffReader.readLine();
			switch(command) {
			case "build tree":
				DataSet2 dataSet = new DataSet2(dataKey, datas.get(dataKey));
				// The first node on the tree will be what's returned from getSplitOnFactor
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(dataSet.getSplitOnFactor().getName());
				// this will build the decision tree
				work.processDataSet(dataSet, node, "");
				if(tree != null) {
					work.remove(tree);
				}
				// The next 5 lines will display the tree in the Swing window
				tree = new JTree(node);
				work.add(tree);
				work.setSize(350, 350);
				work.setTitle(dataKey + " DATASET");
				work.setVisible(true);
				break;
			case "choose dataset":
				System.out.println("> Choose dataset (" + datas.keySet() + " ?");
				String value = buffReader.readLine();
				if(datas.keySet().contains(value)) {
					dataKey = value;
				}
				else {
					System.out.println("> Error. Please enter a vaild dataset");
				}
				break;
			case "exit":
				flag = false;
				break;
			}
		}
		System.exit(0);
	}
	void processDataSet(DataSet2 dataSet, DefaultMutableTreeNode node, String factorValueName) {
		if(dataSet.toString() != null) {
			System.out.println(dataSet);
		}
		// The if is when we are not at the end node of a tree
		if(dataSet.getEntropy() != 0) {
			System.out.println("Current Information Gain Values: " + dataSet.getInfoGains());
			System.out.println("Best factor to split on is: " + dataSet.getSplitOnFactor() + " " + 
					dataSet.getSplitOnFactor().getFactorValues());
			System.out.println("-------This is a test line.-------");
			HashMap<String, DataSet2> factorDataSets = new HashMap<String, DataSet2>();
			dataSet.getSplitOnFactor().getFactorValues().forEach(factorValue -> 
					factorDataSets.put(factorValue.getName(), dataSet.createDataSet(dataSet.getSplitOnFactor(), 
								factorValue, dataSet.getData())));
			processDataSets(factorDataSets, node);
		}
		// The else is for when we are at an end, "leaf", node
		else {
			String[][] data = dataSet.getData();
			String decision = " [" + data[0][data[0].length -1] + " = " + data[1][data[0].length -1] + "]";
			node.add(new DefaultMutableTreeNode(factorValueName + "  :  " + decision));
			System.out.println("Decision ==> " + decision);
		}
	}
	void processDataSets(HashMap<String, DataSet2> dataSets, DefaultMutableTreeNode node) {
		// for each dataSet we will create a new node if it is not a leaf node
		dataSets.keySet().forEach(dataSet -> {
			if(dataSets.get(dataSet).getEntropy() != 0) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(dataSet + "  : [ " +
						dataSets.get(dataSet).getSplitOnFactor().getName() + "]");
				node.add(newNode);
				processDataSet(dataSets.get(dataSet), newNode, dataSet);
			}
			else {
				processDataSet(dataSets.get(dataSet), node, dataSet);
			}
		});
	}

}









