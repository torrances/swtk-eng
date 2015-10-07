package org.swtk.eng.preprocess;

import java.util.HashMap;
import java.util.Map;

public class PopulateContractions {

	public static Map<String, String>	map	= new HashMap<String, String>();

	static {

		/* w */
		// TODO: search google unigrams for all <token>'s combinations
		map.put("what's", "what is");
	}
}
