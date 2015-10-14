package org.swtk.eng.tense.dmo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class PluralityDictionaryTest {

	@Test
	public void test() throws Throwable {
		assertEquals("dolumudstone", PluralityDictionary.reduce("dolumudstone"));
		assertEquals("dolumudstone", PluralityDictionary.reduce("dolumudstones"));
	}
}
