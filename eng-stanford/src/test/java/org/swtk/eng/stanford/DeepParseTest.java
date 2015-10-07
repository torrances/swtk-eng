package org.swtk.eng.stanford;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.swtk.eng.stanford.dmo.DeepParse;
import org.swtk.eng.stanford.dto.TreeNode;
import org.w3c.dom.Document;

import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.xml.XmlFormatter;

public final class DeepParseTest {

	@Test
	public void no() throws Throwable {
		Document dom = parse("The rate of penetration in pitting corrosion can be high as the metal loss is concentrated at a small anode site and the cathodic reaction occurs over a large area of the metal surface.");
		assertNotNull(dom);
		System.err.println(XmlFormatter.toCondensedString(dom, Codepage.WINDOWS_1252));
	}

	@Test
	public void json() throws Throwable {
		String input = "Copper and copper-nickel alloys have the benefit that copper is naturally biocidal and so is able to control biological fouling in flowing conditions.";

		String json1 = new DeepParse(input, Codepage.WINDOWS_1252).combine().parse().json();
		assertNotNull(json1);
		System.err.println(json1);

		TreeNode treenode = GsonUtils.toObject(json1, TreeNode.class);
		assertNotNull(treenode);
		String json2 = GsonUtils.toJsonFormatted(treenode);

		assertEquals(json1, json2);
	}

	private Document parse(String input) throws Throwable {
		return new DeepParse(input, Codepage.WINDOWS_1252).combine().parse().dom();
	}
}
