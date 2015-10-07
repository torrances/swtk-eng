package org.swtk.eng.preprocess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.swtk.eng.preprocess.functions.RemoveFigureAttribution;

public class RemoveFigureAttributionTest {

	@Test public void single() throws Throwable {
		
		String j1 = "labeled in this figure equates to the Cantarell Fig. 42 Formation of Late Maastrichtian age.";
		String j2 = RemoveFigureAttribution.process(j1);
		
		assertFalse(j2.contains("Fig. 42"));
		
		assertTrue(j2.contains("figure 42"));
	}

	@Test public void multiple() throws Throwable {

		String j1 = "labeled in this figure equates Fig. 20 to the Cantarell Fig. 42 Formation of Late Maastrichtian Fig. 92 age.";
		String j2 = RemoveFigureAttribution.process(j1);

		assertFalse(j2.contains("Fig. 20"));
		assertFalse(j2.contains("Fig. 42"));
		assertFalse(j2.contains("Fig. 92"));

		assertTrue(j2.contains("figure 20"));
		assertTrue(j2.contains("figure 42"));
		assertTrue(j2.contains("figure 92"));
	}
}
