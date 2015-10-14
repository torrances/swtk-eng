package org.swtk.eng.tense;

import static org.junit.Assert.*;
import org.junit.Test;

public final class SuperlativeDictionaryTest {

	@Test
	public void test() throws Throwable {
		assertEquals("far", SuperlativeDictionary.base("farthest"));
		assertEquals("low", SuperlativeDictionary.base("lower"));
		assertEquals("low", SuperlativeDictionary.base("lowest"));
	}
}
