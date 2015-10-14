package org.swtk.eng.tense;

import java.util.Map;
import java.util.TreeMap;

import com.trimc.blogger.commons.LogManager;

public class PluralityDictionary {

	public static LogManager logger = new LogManager(PluralityDictionary.class);

	private static Map<String, String> endsWith = new TreeMap<String, String>();

	static {

		/*	e.g. "carbonates" => "carbonate" 	*/
		endsWith.put("ates", "ate");

		/*	e.g. "cycles" => "cycle"			*/
		endsWith.put("les", "le");

		/*	e.g. "reservoirs" => "reservoir"	*/
		endsWith.put("oirs", "oir");

		endsWith.put("facies", "facies");

		/*	e.g. "dolomites" => "dolomite"		*/
		endsWith.put("ites", "ite");

		endsWith.put("stones", "stone");
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
