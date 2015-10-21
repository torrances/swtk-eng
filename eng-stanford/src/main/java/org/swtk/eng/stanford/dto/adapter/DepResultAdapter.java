package org.swtk.eng.stanford.dto.adapter;

import org.swtk.eng.stanford.dto.DepResult;
import org.w3c.dom.Element;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.type.StanfordDependencyType;

public final class DepResultAdapter {

	public static DepResult transform(Element el) throws AdapterValidationException {
		DepResult result = new DepResult();

		/* expected format:
		 * <node object="atoms" object-sequence="4" predicate="prep_of" subject="consists" subject-sequence="2"/> */

		String predicate = el.getAttribute("predicate");

		result.setSubject(DepNodeAdapter.transformSubject(el));
		result.setObject(DepNodeAdapter.transformObject(el));

		result.setPredicateType(StanfordDependencyType.find(predicate));
		if (null == result.getPredicateType()) {
			throw new AdapterValidationException("Predicate Not Recognized (name = %s)", predicate);
		}

		result.setPredicate(StanfordDependencyTypeSerAdapter.transform(result.getPredicateType()));

		return result;
	}
}
