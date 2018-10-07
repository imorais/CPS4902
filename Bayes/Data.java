import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * This class will contain the data for each data set.
 * For convenience. 
 */
public class Data {
	
	static String [][] Weather = {{"Outlook", "Temp", "Humidity", "Windy", "Play"},
			{"sunny",	 "hot", 		"high", 		"F", 	"no"},
			{"sunny",	 "hot", 		"high", 		"T", 	"no"},
			{"overcast",	 "hot", 		"high", 		"F", 	"yes"},
			{"rainy",	 "mild",		"high", 		"F", 	"yes"},
			{"rainy",	 "cool",		"normal", 	"F", 	"yes"},
			{"rainy", 	 "cool", 	"normal", 	"T", 	"no"},
			{"overcast",	 "cool", 	"normal", 	"T", 	"yes"},
			{"sunny",	 "mild", 	"high", 		"F", 	"no"},
			{"sunny", 	 "cool", 	"normal", 	"F", 	"yes"},
			{"rainy",	 "mild", 	"normal", 	"F", 	"yes"},
			{"sunny",	 "mild", 	"normal", 	"T", 	"yes"},
			{"overcast",	 "mild", 	"high", 		"T", 	"yes"},
			{"overcast",	 "hot", 		"normal", 	"F", 	"yes"},
			{"rainy",	 "mild", 	"high",		"T", 	"no"}};
	
	// this map contain the data sets that can be used in this program
	// Weather will be the default data set to be worked on first
	static Map<String, String[][]> datas = Collections.unmodifiableMap(new HashMap<String, String[][]>() {
		private static final long serialVersionUID = 1L;
		{
			put("Weather", Weather);
		}
	});

}
