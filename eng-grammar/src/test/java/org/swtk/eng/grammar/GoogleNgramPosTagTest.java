package org.swtk.eng.grammar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.swtk.eng.grammar.types.GoogleNgramPosTag;

public final class GoogleNgramPosTagTest {

	@Test
	public void run() throws Throwable {
		assertEquals(GoogleNgramPosTag.PUNCTUATION, GoogleNgramPosTag.find("."));
	}
}
