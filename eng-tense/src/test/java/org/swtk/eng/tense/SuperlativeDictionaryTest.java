package org.swtk.eng.tense;

import static org.junit.Assert.*;
import org.junit.Test;
import org.swtk.eng.tense.dmo.SuperlativeDictionary;

public final class SuperlativeDictionaryTest {

	@Test
	public void test() throws Throwable {
		assertEquals("far", SuperlativeDictionary.reduce("farthest"));
		assertEquals("low", SuperlativeDictionary.reduce("lower"));
		assertEquals("low", SuperlativeDictionary.reduce("lowest"));
	}
}
