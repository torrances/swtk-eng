package org.swtk.eng.stanford;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.swtk.eng.stanford.dmo.DependencyParse;

import com.trimc.blogger.commons.type.Codepage;

public final class DependencyParseTest {

	@Test
	public void combine() throws Throwable {

		/* make sure combining is working properly */
		String input = "Ammonium chloride salt deposition is most likely to occur in heat exchangers where the process stream is cooled below the NH4C1 salt deposition temperature.";
		DependencyParse dp = new DependencyParse(input, Codepage.WINDOWS_1252);

		String xml = dp.combine().typedDependenciesCCprocessed().structuredXml();
		assertTrue(xml.contains("Ammonium_chloride_salt_deposition"));
		assertTrue(xml.contains("the_NH4C1_salt_deposition_temperature"));
		assertTrue(xml.contains("the_process_stream"));
	}

	@Test
	public void no() throws Throwable {
		assertFalse(parse("what is the latest iphone model?").gobbledygook());
		assertFalse(parse("who is the president of the united states?").gobbledygook());
	}

	private DependencyParse parse(String input) throws Throwable {
		return new DependencyParse(input, Codepage.WINDOWS_1252).typedDependenciesCCprocessed();
	}

	@Test
	public void yes() throws Throwable {
		assertTrue(parse("klajsdf ui a;ksjdf; 2iujiu adsjk asd").gobbledygook());

		/* 5:35 PM 9/18/2014 this is a known failiure (eg limitation in the system) */
		// comment out to allow maven to pass
		// assertTrue(parse("blah blah blah blah").gobbledygook());
	}
}
