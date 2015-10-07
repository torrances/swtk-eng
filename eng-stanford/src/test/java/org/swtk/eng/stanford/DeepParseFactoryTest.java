package org.swtk.eng.stanford;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.swtk.eng.stanford.util.factory.DeepParseFactory;

public final class DeepParseFactoryTest {

	@Test
	public void parse() throws Throwable {
		try {
			assertNotNull(DeepParseFactory.getInstance().deepParse("the quick brown fox"));

		} catch (Exception e) {
			/* if any exception is thrown by the above code
			 * then the test case has failed */
			assertNull(e);
		}
	}
}
