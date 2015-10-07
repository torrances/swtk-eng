package org.swtk.eng.tokenizer.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.swtk.eng.tokenizer.text.TextTokenizer;

public final class TextTokenizerTest4GR {

	@Test
	public void simple() throws Throwable {
		assertEquals(10, new TextTokenizer("της προφήτιδος Κασσάνδρας, που οδύρεται για την αιχμαλωσία της").tokenize().array().length);
	}
}
