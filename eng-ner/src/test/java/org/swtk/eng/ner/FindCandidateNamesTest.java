package org.swtk.eng.ner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;

import com.trimc.blogger.commons.utils.SetUtils;

public final class FindCandidateNamesTest {

	private FindCandidateNames init() throws Throwable {
		return new FindCandidateNames();
	}
	
	@Test
	public void test() throws Throwable {
		test("Paris Hilton was in Paris in the Hilton", "paris hilton");
		test("Paris hilton was in Paris in the Hilton");
		test("And we think in a gene-centric view -- maybe going back to Richard Dawkins' ideas -- than in a genome-centric view, which are different constructs of these gene components.", "richard dawkins");
		test("We've extended this to the air now with a grant from the Sloan Foundation here.", "sloan foundation");
		test("We've extended this to the air now with a grant from the Sloan Foundation.", "sloan foundation");
	}

	private void test(String input, String... names) throws Throwable {
		Set<String> set = init().process(SetUtils.toList(input));
		assertNotNull(set);
		assertEquals(names.length, set.size());

		int counter = 0;
		for (String name : set)
			assertEquals(names[counter++], name);
	}
}
