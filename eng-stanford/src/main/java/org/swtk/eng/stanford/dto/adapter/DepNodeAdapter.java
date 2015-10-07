package org.swtk.eng.stanford.dto.adapter;

import org.swtk.eng.stanford.dto.DepNode;
import org.w3c.dom.Element;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class DepNodeAdapter {

	public static DepNode transformObject(Element el) throws AdapterValidationException {
		DepNode node = new DepNode();

		node.setName(el.getAttribute("object"));
		node.setSequence(Integer.parseInt(el.getAttribute("object-sequence")));

		return node;
	}

	public static DepNode transformSubject(Element el) throws AdapterValidationException {
		DepNode node = new DepNode();

		node.setName(el.getAttribute("subject"));
		node.setSequence(Integer.parseInt(el.getAttribute("subject-sequence")));

		return node;
	}
}
