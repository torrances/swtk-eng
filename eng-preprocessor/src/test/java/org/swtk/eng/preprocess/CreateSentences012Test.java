package org.swtk.eng.preprocess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.swtk.eng.preprocess.functions.CreateSentences;

public class CreateSentences012Test {

	@Test
	public void run12() throws Throwable {

		List<String> list = new ArrayList<String>();
		list.add("This is my sentence. That was it. Here goes nothing!");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(3, lines.size());
	}
}
