package org.swtk.eng.stanford.dto.adapter;

import org.swtk.eng.stanford.dto.DependencyParseContract;
import org.w3c.dom.Document;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class DependencyParseContractAdapter {

	public static DependencyParseContract transform(Document document) throws AdapterValidationException {
		DependencyParseContract contract = new DependencyParseContract();

		contract.setDocument(document);

		return contract;
	}
}
