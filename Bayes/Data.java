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
	
	static String [][] num6 = {{"Author", "Thread", "Length", "Where Read", "User Action"},
			{"known",	"new",			"long",		"home", 		"skips"},		//1
			{"unknown",	"new",			"short",		"work", 		"reads"},		//2
			{"unknown",	"follow up",		"long", 		"work", 		"skips"},		//3
			{"known",	"follow up",		"long",		"home", 		"skips"},		//4	
			{"known",	"new",			"short",		"home", 		"reads"},		//5
			{"known",	"follow up",		"long",		"work", 		"skips"},		//6
			{"unknown",	"follow up",		"short", 	"work", 		"skips"},		//7
			{"unknown",	"new", 			"short",		"work", 		"reads"},		//8
			{"known",	"follow up",		"long", 		"home", 		"skips"},		//9
			{"known",	"new", 			"long",		"work", 		"skips"},		//10
			{"unknown",	"follow up",		"short", 	"home", 		"skips"},		//11
			{"known",	"new",			"long",		"work", 		"skips"},		//12
			{"known",	"follow up",		"short",		"home", 		"reads"},		//13
			{"known",	"new",			"short",		"work", 		"reads"},		//14
			{"known",	"new",			"short",		"home", 		"reads"},		//15
			{"known",	"follow up",		"short",		"work", 		"reads"},		//16
			{"known",	"new",			"short",		"home", 		"reads"},		//17
			{"unknown",	"new",			"short",		"work", 		"reads"}};		//18
	
	static String[][] num7 = {{"Outlook", "Tempurature", "Humidity", "Wind", "Play Tennis"},
			{"sunny",	 "hot", 		"high", 		"weak",		"no"},		//1
			{"sunny",	 "hot", 		"high", 		"strong",	"no"},		//2
			{"overcast",	 "hot", 		"high", 		"weak",		"yes"},		//3
			{"rain",		"mild",		"high", 		"weak", 		"yes"},		//4
			{"rain",		"cool",		"normal", 	"weak", 		"yes"},		//5
			{"rain", 	 "cool", 	"normal", 	"strong", 	"no"},		//6
			{"overcast",	 "cool", 	"normal", 	"strong", 	"yes"},		//7
			{"sunny",	 "mild", 	"high", 		"weak", 		"no"},		//8
			{"sunny", 	 "cool", 	"normal", 	"weak", 		"yes"},		//9
			{"rain",		"mild",		"normal", 	"weak", 		"yes"},		//10
			{"sunny",	 "mild", 	"normal", 	"strong", 	"yes"},		//11
			{"overcast",	 "mild", 	"high", 		"strong", 	"yes"},		//12
			{"overcast",	 "hot", 		"normal", 	"weak", 		"yes"}};		//13
	
	// this map contain the data sets that can be used in this program
	// Weather will be the default data set to be worked on first
	static Map<String, String[][]> datas = Collections.unmodifiableMap(new HashMap<String, String[][]>() {
		private static final long serialVersionUID = 1L;
		{
			put("Weather", Weather);
			put("Num6", num6);
			put("Num7", num7);
		}
	});

}
