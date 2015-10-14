package org.swtk.eng.tense;

import static org.junit.Assert.*;
import org.junit.Test;

public final class PluralityDictionaryTest {

	@Test
	public void test() throws Throwable {
		assertEquals("dolumudstone", PluralityDictionary.reduce("dolumudstone"));
		assertEquals("dolumudstone", PluralityDictionary.reduce("dolumudstones"));
	}
}
