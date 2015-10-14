package org.swtk.eng.tense;

import java.util.Map;
import java.util.TreeMap;

import com.trimc.blogger.commons.LogManager;

public class TenseDictionary {

	public static LogManager logger = new LogManager(TenseDictionary.class);

	private static Map<String, String> endsWith = new TreeMap<String, String>();

	static {

		/*	e.g. "fractured" => "fracture"	*/
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
