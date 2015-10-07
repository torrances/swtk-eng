package org.swtk.eng.wordnet.synonyms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.swtk.eng.wordnet.synonyms.svc.QueryIndex;

import com.trimc.blogger.commons.utils.SetUtils;

public final class QueryIndexTest {

	@Test
	public void run() throws Throwable {

		QueryIndex queryIndex = new QueryIndex();
		assertNotNull(queryIndex);

		Collection<String> list = queryIndex.process("automobile");
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals("auto, car, machine, motorcar", SetUtils.toString(list, ", "));

		queryIndex.close();
	}
}
