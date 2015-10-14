package org.swtk.eng.tense.svc;

import static org.junit.Assert.*;
import org.junit.Test;

public final class EngLemmatizerTest {

	@Test
	public void test() throws Throwable {
		
		assertEquals("fault", EngLemmatizer.lemmatize("faults"));

		assertEquals("heavy fault", EngLemmatizer.lemmatize("heavily faulted"));

		assertEquals("produce", EngLemmatizer.lemmatize("produced"));
	}
}
