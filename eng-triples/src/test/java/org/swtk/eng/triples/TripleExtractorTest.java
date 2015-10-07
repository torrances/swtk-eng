package org.swtk.eng.triples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.swtk.eng.triples.dto.Triple;
import org.swtk.eng.triples.dto.adapter.TripleAdapter;
import org.swtk.eng.triples.svc.TripleExtractor;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;

public final class TripleExtractorTest {

	public static LogManager	logger	= new LogManager(TripleExtractorTest.class);

	@Test
	public void R1() throws Throwable {
		Triple t1 = test("what is the influence of technology on religion", "technology", "religion");
		Triple t2 = test("what is the influence of religion on technology", "religion", "technology");
		assertEquals(t1.hashCode(), t2.hashCode());
	}
	
	@Test
	public void R2() throws Throwable {
		test("what is the impact of greenhouse gasses on the food supply?", "greenhouse gasses", "food supply");
		test("what was the effect of the early astronauts on the moon's ecology?", "early astronauts", "moon's ecology");
	}

	private Triple test(String input, String subject, String object) throws Throwable {

		Triple triple = new TripleExtractor().process(input, Codepage.UTF_8);
		assertNotNull(triple);
		assertNotNull(triple.getSubject());
		assertNotNull(triple.getPredicate());
		assertNotNull(triple.getObject());

		assertEquals(subject, triple.getSubject());
		assertEquals(object, triple.getObject());

		logger.info("Extracted Triple (%s, hash = %s)", TripleAdapter.toLogString(triple), triple.hashCode());
		return triple;
	}
}
