package org.swtk.eng.tense.dmo;

import static org.junit.Assert.*;
import org.junit.Test;

public final class SuperlativeDictionaryTest {

	 @Test
	public void test() throws Throwable {
		assertEquals("far", SuperlativeDictionary.reduce("farthest"));
		assertEquals("low", SuperlativeDictionary.reduce("lower"));
		assertEquals("low", SuperlativeDictionary.reduce("lowest"));
	}
}
