package org.swtk.eng.tense.dmo;

import java.util.Map;
import java.util.TreeMap;

import com.trimc.blogger.commons.LogManager;

public class TenseDictionary {

	public static LogManager logger = new LogManager(TenseDictionary.class);

	private static Map<String, String> endsWith = new TreeMap<String, String>();

	static {

		/*	e.g. "arranged" => "arrange"			*/
		endsWith.put("ged", "ge");

		/*	e.g. "faulted" => "fault"	
		 * 	interestingly enough, this is not past-tense; but adj. */
		endsWith.put("aulted", "ault");

		/*	e.g. "produced" => "produce"			*/
		endsWith.put("duced", "duce");
		
		/*	e.g. "overthrusted" => "overthrust" 	*/
		endsWith.put("sted", "st");
		
		/*	e.g. "waves" => "wave"					*/
		endsWith.put("ves", "ve");
		
		/*	e.g. "mountains" => "mountain"			*/
		endsWith.put("tains", "tain");

		/*	e.g. "overthrusted" => "overthrust" 	*/
		endsWith.put("sited", "sted");

		/*	e.g. "fractured" => "fracture"			*/
		endsWith.put("ured", "ure");
	}

	public static String reduce(String term) {
		String lower = term.toLowerCase();
		for (String key : endsWith.keySet()) {
			if (!lower.endsWith(key)) continue;
			return term.substring(0, term.length() - key.length()) + endsWith.get(key);
		}

		return term;
	}
}
