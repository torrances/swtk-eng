package org.swtk.eng.preprocess;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.trimc.blogger.commons.utils.CollectionUtils;


public class PopulateCliches {

	public static Map<String, String>	map	= new HashMap<String, String>();

	static {

		/* a cliche is defined as a phrase that adds no value to a sentence 
		 * each phrase can be removed without 'damaging' the sentence */

		/* a */
		map.put("an example of", "");
		map.put("a form of", "");
		map.put("as described earlier", "");
		map.put("are required to", "");

		/* b */
		map.put("usually (but not always)", "often");

		/* c */
		map.put("consequently, ", "");
		map.put("can be used to", "can");
		map.put("can also", "can");

		/* d */
		map.put("developed specifically", "used");

		/* f */
		map.put("for this reason", "");

		/* g */
		map.put("generally classified as either", "");
		map.put("generally classified in", "in");
		map.put("generally results", "results");

		/* h */
		map.put("has previously been defined as", "is");
		map.put("however, ", "");
		map.put("have occurred in conjunction with", "occurs in");

		/* i */
		map.put("in many respects", "");
		map.put("a good example of", "");
		map.put("is used, however, as a", "is a");
		map.put("in some cases", "");
		map.put("indeed,", "");
		map.put("in general, ", "");
		map.put("it has been observed that", "");
		map.put("is able to", "can");
		map.put("is actually considered", "is");

		/* m */
		map.put("more or less", "");
		map.put("many factors including", "");

		/* n */
		map.put("nevertheless, ", "");

		/* o */
		map.put("of which ", "");

		/* s */
		map.put("should be considered to be", "are");

		/* t */
		map.put("tend to", "");
		map.put("that can be applied", "");
		map.put("this is however not a problem as", "");
		map.put("typically used for", "for");
		map.put("the pitfall, however, is that", "");
		map.put("that subsequently provide", "causing");

		/* w */
		map.put("will result in", "causes");
	}

	public static Collection<String> sorted() {
		Map<Integer, List<String>> sorted = new TreeMap<Integer, List<String>>();

		for (String key : map.keySet()) {
			List<String> inner = sorted.containsKey(key.length()) ? sorted.get(key.length()) : new ArrayList<String>();
			inner.add(key);
			sorted.put(key.length(), inner);
		}

		return CollectionUtils.reverse(CollectionUtils.toList2(sorted.values()));
	}

	public static void main(String... args) throws Throwable {
		for (String s : sorted())
			System.err.println(s);
	}
}
