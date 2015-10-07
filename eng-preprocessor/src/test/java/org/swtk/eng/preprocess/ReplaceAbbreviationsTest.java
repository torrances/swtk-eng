package org.swtk.eng.preprocess;

import org.junit.Test;
import org.swtk.eng.preprocess.functions.ReplaceAbbreviations;

public final class ReplaceAbbreviationsTest {

	@Test public void temp() throws Throwable {
		String result = ReplaceAbbreviations.process("using an adaptive process (i.e., not an exact mathematical conversion).");
		System.err.println(result);
	}
}
