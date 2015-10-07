package org.swtk.eng.rollinghash;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.swtk.eng.rollinghash.svc.CyclicHashService;

public final class CyclicHashServiceTest {

	@Test
	public void run() throws Throwable {

		final Integer n = 10;
		final String input = "The quick brown fox jumps over the lazy dog";

		Set<Integer> set = new CyclicHashService().process(input, n);
		assertEquals(34, set.size());
	}
}
