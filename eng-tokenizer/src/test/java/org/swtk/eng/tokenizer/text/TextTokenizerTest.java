package org.swtk.eng.tokenizer.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.swtk.eng.tokenizer.text.TextTokenizer;

public final class TextTokenizerTest {

	@Test
	public void simple() throws Throwable {
		assertEquals(9, new TextTokenizer("the quick brown fox jumps over the lazy dog").tokenize().array().length);
	}
}
