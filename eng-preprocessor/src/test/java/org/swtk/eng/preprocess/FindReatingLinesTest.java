package org.swtk.eng.preprocess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;

import org.junit.Test;
import org.swtk.eng.preprocess.functions.FindRepeatingLines;

import com.trimc.blogger.commons.type.Codepage;

public class FindReatingLinesTest {

	@Test
	public void find1() throws Throwable {

		FindRepeatingLines.THRESHOLD = 2;
		assertEquals(2, FindRepeatingLines.THRESHOLD);

		Collection<String> lines = FindRepeatingLines.process(getFile(), Codepage.UTF_8);
		assertNotNull(lines);
		assertFalse(lines.isEmpty());
	}

	private File getFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/repeating-lines-test-01").getFile());
		assertNotNull(file);
		assertTrue(file.exists());

		return file;
	}
}
