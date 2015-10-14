package org.swtk.eng.tense.svc;

import static org.junit.Assert.*;
import org.junit.Test;

public final class EngLemmatizerTest {

	@Test
	public void test() throws Throwable {
		
		assertEquals("ones", EngLemmatizer.lemmatize("ones"));
		
		assertEquals("fault", EngLemmatizer.lemmatize("faults"));

		assertEquals("deposit unconformable", EngLemmatizer.lemmatize("deposited unconformably"));
		assertEquals("overthrust mesozoic strata", EngLemmatizer.lemmatize("overthrusted mesozoic strata"));
		assertEquals("deep water facies", EngLemmatizer.lemmatize("deeper water facies"));
		assertEquals("shallow horizon down", EngLemmatizer.lemmatize("shallower horizons down"));
		assertEquals("fracture zone", EngLemmatizer.lemmatize("fracture zone"));
		assertEquals("heavy fault", EngLemmatizer.lemmatize("heavily faulted"));
		assertEquals("relative unfault region", EngLemmatizer.lemmatize("relatively unfaulted region"));
		assertEquals("arrange radial", EngLemmatizer.lemmatize("arranged radially"));
		
		assertEquals("produce", EngLemmatizer.lemmatize("produced"));
	}
}
