package org.swtk.eng.tokenizer.text;

import java.util.Set;
import java.util.TreeSet;

public final class PopulateSlashDelimitedTokens {

	/* TODO: these values should be dependency injected -- hardcoded here for convenience */

	public static Set<String> process() {
		Set<String> set = new TreeSet<String>();

		set.add("and/or");
		set.add("or/and");
		set.add("to/from");
		set.add("from/to");

		return set;
	}
}
