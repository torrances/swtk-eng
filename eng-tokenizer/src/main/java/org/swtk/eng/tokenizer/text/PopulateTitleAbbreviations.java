package org.swtk.eng.tokenizer.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class PopulateTitleAbbreviations {

	/* TODO: add to COMMONS-DATA */

	public static Collection<String> process() {
		List<String> list = new ArrayList<String>();

		list.add("mr");
		list.add("mrs");
		list.add("ms");
		list.add("prof");
		list.add("dr");
		list.add("gen");
		list.add("rep");
		list.add("sen");
		list.add("st");
		list.add("sr");
		list.add("jr");

		return list;
	}
}
