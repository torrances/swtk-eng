package org.swtk.eng.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.swtk.eng.core.EngIocContainer;

public class EngIocContainerTest {

	@Test
	public void initialize() throws Throwable {
		assertNotNull(EngIocContainer.getContext());
	}
}
