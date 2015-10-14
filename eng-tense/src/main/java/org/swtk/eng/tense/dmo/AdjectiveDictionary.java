package org.swtk.eng.tense.dmo;

import java.util.Map;
import java.util.TreeMap;

import com.trimc.blogger.commons.LogManager;

public class AdjectiveDictionary {

	public static LogManager logger = new LogManager(AdjectiveDictionary.class);

	private static Map<String, String> endsWith = new TreeMap<String, String>();

	static {

		/*	e.g. "heavily" => "heavy"					*/
		endsWith.put("avily", "avy");

		/*	e.g. "radilly" => "radial"					*/
		endsWith.put("ially", "ial");

		/*	e.g. "relatively" => "relative"				*/
		endsWith.put("ively", "ive	");

		/*	e.g. "stratigraphically" => "stratigraphic"	*/
		endsWith.put("ically", "ic");
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
