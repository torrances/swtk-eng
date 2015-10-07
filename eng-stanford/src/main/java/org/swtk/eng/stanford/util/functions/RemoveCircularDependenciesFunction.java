package org.swtk.eng.stanford.util.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.swtk.eng.stanford.dto.DependencyParseContract;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.xml.Xpath;

public final class RemoveCircularDependenciesFunction {

	public static Collection<Element> process(DependencyParseContract contract) throws BusinessException {
		return process(contract.getDocument());
	}

	private static Collection<Element> process(Document dom) throws BusinessException {

		List<Element> list = new ArrayList<Element>();
		for (Element el1 : new Xpath(dom).evaluate("descendant-or-self::node").elements()) {

			String s = el1.getAttribute("subject");
			String o = el1.getAttribute("object");

			String expr = String.format("descendant-or-self::node[@subject='%s' and @object='%s']", o, s);
			for (Element el2 : new Xpath(dom).evaluate(expr).elements()) {

				list.add(el2);
			}
		}

		return list;
	}
}

/* first determine if the graph has cycles 
 * then remove a cycle */