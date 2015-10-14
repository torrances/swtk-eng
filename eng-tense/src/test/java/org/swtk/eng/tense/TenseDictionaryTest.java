package org.swtk.eng.tense;

import static org.junit.Assert.*;
import org.junit.Test;
import org.swtk.eng.tense.dmo.PluralityDictionary;

public final class TenseDictionaryTest {

	@Test
	public void test() throws Throwable {
		assertEquals("dolumudstone", PluralityDictionary.reduce("dolumudstone"));
		assertEquals("dolumudstone", PluralityDictionary.reduce("dolumudstones"));
	}
}
