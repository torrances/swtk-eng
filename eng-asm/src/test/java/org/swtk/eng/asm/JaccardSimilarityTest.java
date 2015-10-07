package org.swtk.eng.asm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;
import org.swtk.eng.asm.dmo.JaccardSimilarity;
import org.swtk.eng.asm.dto.JaccardSimilarityResult;
import org.swtk.eng.rollinghash.svc.CyclicHashService;

public final class JaccardSimilarityTest {

	@Test
	public void run() throws Throwable {

		Set<Integer> s1 = new CyclicHashService().process("the quick brown fox jumps over the lazy dog", 3);
		assertNotNull(s1);
		assertFalse(s1.isEmpty());

		Set<Integer> s2 = new CyclicHashService().process("the lazy brown fox leapt over the quick dog", 3);
		assertNotNull(s2);
		assertFalse(s2.isEmpty());

		JaccardSimilarityResult result = new JaccardSimilarity().process(s1, s2);
		assertNotNull(result);
		assertEquals((Double) 0.625d, (Double) result.getSimilarity());
	}
}
