package org.swtk.eng.tense.dmo;

import java.util.Map;
import java.util.TreeMap;

import com.trimc.blogger.commons.LogManager;

public class PluralityDictionary {

	public static LogManager logger = new LogManager(PluralityDictionary.class);

	private static Map<String, String> endsWith = new TreeMap<String, String>();

	static {

		/*	e.g. "carbonates" => "carbonate" 	*/
		endsWith.put("ates", "ate");

		/*	e.g. "faults" => "fault" 			*/
		endsWith.put("ults", "ult");

		/*	e.g. "cycles" => "cycle"			*/
		endsWith.put("les", "le");

		/*	e.g. "reservoirs" => "reservoir"	*/
		endsWith.put("oirs", "oir");

		/*	e.g. "beds" => "bed"				*/
		endsWith.put("eds", "ed");

		/*	e.g. "forces" => "force"			*/
		endsWith.put("ces", "ce");

		/*	e.g. "intervals" => "interval"		*/
		endsWith.put("als", "al");

		/*	e.g. "clastics" => "clastic"		*/
		endsWith.put("ics", "ic");

		/*	e.g. "deposits" => "deposit"		*/
		endsWith.put("its", "it");

		/*	e.g. "sequences" => "sequence"		*/
		endsWith.put("ences", "ence");

		/*	e.g. "systems" => "system"			*/
		endsWith.put("ems", "em");

		/*	e.g. "fields" => "field"			*/
		endsWith.put("lds", "ld");

		/*	e.g. "horizons" => "horizon"		*/
		endsWith.put("ons", "on");

		endsWith.put("facies", "facies");

		/*	e.g. "dolomites" => "dolomite"		*/
		endsWith.put("ites", "ite");

		/*	e.g. "platforms" => "platform"		*/
		endsWith.put("orms", "orm");

		endsWith.put("ones", "one");
	}

	public static String reduce(String term) {
		String lower = term.toLowerCase();
		for (String key : endsWith.keySet()) {
			if (lower.equals(key)) continue;
			if (!lower.endsWith(key)) continue;
			return term.substring(0, term.length() - key.length()) + endsWith.get(key);
		}

		return term;
	}
}
