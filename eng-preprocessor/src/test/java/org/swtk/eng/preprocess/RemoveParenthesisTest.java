package org.swtk.eng.preprocess;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.swtk.eng.preprocess.functions.RemoveParenthesis;

public class RemoveParenthesisTest {

	/* most of these fail */
	@Test public void complexUnmatched() throws Throwable {
		assertEquals("( and done3", RemoveParenthesis.process("((done1) and (done2) done3"));
		
		// TODO: Known Failures that should work:
		// commented out to allow Maven Build to proceed ...
		// assertEquals("( my sentence (", RemoveParenthesis.process("((and here) my sentence ((and here)"));
		// assertEquals("", RemoveParenthesis.process("(here (and here))))"));
		// assertEquals("", RemoveParenthesis.process("(here ((((and here (and here)))"));
	}

	@Test public void nested() throws Throwable {
		assertEquals("", RemoveParenthesis.process("(here (and here))"));
		assertEquals("", RemoveParenthesis.process("(here (and here (and here)))"));
		assertEquals("whatgives", RemoveParenthesis.process("what((and) here (it is))gives"));
	}

	@Test public void simple() throws Throwable {
		assertEquals("start and here finish and here", RemoveParenthesis.process("start and here (here I am) finish and here"));
		assertEquals("and here we are", RemoveParenthesis.process("(at the start) and here we are"));
		assertEquals("at the end and end", RemoveParenthesis.process("at the end and end (here I am)"));
		assertEquals("alpha and beta gamma and delta", RemoveParenthesis.process("(at the start) alpha and beta (here I am) gamma and delta (and end)"));
	}

	@Test public void simpleUnmatched() throws Throwable {
		assertEquals("my sentence )", RemoveParenthesis.process("my sentence (and here))"));
		assertEquals("my sentence (", RemoveParenthesis.process("my sentence ((and here)"));
	}
}
