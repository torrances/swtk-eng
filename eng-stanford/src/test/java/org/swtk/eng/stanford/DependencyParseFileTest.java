package org.swtk.eng.stanford;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.swtk.eng.stanford.dmo.DependencyParseFile;

import com.trimc.blogger.commons.type.Codepage;

public final class DependencyParseFileTest {

	@Test
	public void no() throws Throwable {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/Test001.dat").getFile());
		assertNotNull(file);
		assertTrue(file.exists());

		DependencyParseFile dpf = new DependencyParseFile(file, Codepage.WINDOWS_1252).combine().parse();
		assertNotNull(dpf);
		assertNotNull(dpf.dom());
		assertNotNull(dpf.xml());

		System.err.println(dpf.xml());
	}
}
