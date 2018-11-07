// STOPPED AT 10:58. (next steps would be to test the code and start the Display.java program)
// could not test the code b/c I do no have the org.apache.commons... files on my computer.
// I have the .jar file on my old computer.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.IntStream;

public class ChatFilter {
	
	static String [][] Training_Data = {{"Restaurant Review", "Classification"},
			{"genuine world class restaurant",		 "+"},
			{"best new taste in town",				 "+"},
			{"tasteless bland unappealing",			 "-"},
			{"unappealing taste lacking style",		 "-"},
			{"Worst restaurant in town",			 "-"}};

	public static void main(String[] args) throws IOException, Exception {
		
		String[][] data = new String[ChatFilter.Training_Data.length - 1][];
		IntStream.range(0, ChatFilter.Training_Data.length - 1).forEach(i ->
				data[i] = ChatFilter.Training_Data[i + 1][0].toString().toLowerCase().split(" "));
		String [] label = new String[Training_Data.length - 1];
		IntStream.range(0, Training_Data.length - 1).forEach(row -> label[row] = Training_Data[row + 1][1]);
		// The hashset will contain all of the vocabulary in the training data
		HashSet<String> vocab = new HashSet<String>();
		IntStream.range(0, data.length).forEach(row -> 
				IntStream.range(0, data[row].length).forEach(column -> vocab.add(data[row][column])));
		handleCommandLine(new Logic(data, label, new ArrayList<String>(vocab)));
		
		
	}
	static void handleCommandLine(Logic nb) throws IOException{
		System.out.println();
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("> Enter text to classify expressed sentiment (or exit):");
			String[] values = buffReader.readLine().split(" ");
			if(values[0].equals("exit"))	System.exit(0);
			else	System.out.println("\n" + nb.classify(values));
		}
	}

}






