package org.swtk.eng.preprocess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.swtk.eng.preprocess.functions.RemoveParenthesis;

public class ParenthesisRemovalTest {

	@Test public void run1() throws Throwable {

		String i1 = "during which major carbonate reservoirs were deposited (Figs. 5 and ). ";
		assertTrue(i1.contains(")"));
		assertTrue(i1.contains("("));
		assertTrue(i1.contains("(Figs. 5 and )"));

		String i2 = RemoveParenthesis.process(i1);
		assertFalse(i2.contains(")"));
		assertFalse(i2.contains("("));
		assertFalse(i2.contains("(Figs. 5 and )"));
	}
}
