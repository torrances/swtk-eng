package org.swtk.eng.stanford;

import org.junit.Test;
import org.swtk.eng.stanford.dmo.DependencyParse;

import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.xml.XmlFormatter;

public final class CreateStructuredDomTest {

	/* note - a cyclic graph will cause a StackOverflowError */

	private static final Codepage	CODEPAGE	= Codepage.WINDOWS_1252;

	@Test
	public void test() throws Throwable {

		String input = "DBNPA (2,2-Dibrmo-3-nitrilopropionamide): This is a_fast_biocide that can be very effective even at low_concentrations.";
		DependencyParse dp = new DependencyParse(input, CODEPAGE);

		/* if this doesn't hang or cause an exception, it passes */
		//		Document dom = dp.typedDependenciesCCprocessed().removeCircularDependencies().structuredDom();
		System.err.println(XmlFormatter.toCondensedString(dp.typedDependenciesCCprocessed().removeCircularDependencies().dom(), CODEPAGE));
	}
}
