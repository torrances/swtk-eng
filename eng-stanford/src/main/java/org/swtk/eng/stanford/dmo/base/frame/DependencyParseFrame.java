package org.swtk.eng.stanford.dmo.base.frame;

import org.swtk.eng.stanford.dmo.DeepParse;
import org.swtk.eng.stanford.dmo.DependencyParseInternals;
import org.swtk.eng.stanford.dmo.base.state.DependencyParseState;
import org.swtk.eng.stanford.dto.DepResults;
import org.swtk.eng.stanford.dto.adapter.DepResultsAdapter;
import org.swtk.eng.stanford.dto.adapter.DependencyParseContractAdapter;
import org.swtk.eng.stanford.util.functions.CreateStructuredDomFunction;
import org.w3c.dom.Document;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.xml.XmlFormatter;

public abstract class DependencyParseFrame extends DependencyParseState {

	private Document createStructuredDom() throws BusinessException {
		return CreateStructuredDomFunction.process(DependencyParseContractAdapter.transform(dom()));
	}

	private String createStructuredXml() throws BusinessException {
		return XmlFormatter.toCondensedString(structuredDom(), getCodepage());
	}

	@Override protected DeepParse getDeepParse() throws BusinessException {
		if (null == super.getDeepParse()) super.setDeepParse(new DeepParse(text(), getCodepage()).parse());
		return super.getDeepParse();
	}

	@Override protected DepResults getResults() throws BusinessException {
		if (null == super.getResults()) super.setResults(DepResultsAdapter.transform(text(), dom()));
		return super.getResults();
	}

	public DependencyParseInternals internals() {
		return getInternals();
	}

	public Document structuredDom() throws BusinessException {
		if (null == getStructuredDom()) setStructuredDom(createStructuredDom());
		return getStructuredDom();
	}

	public String structuredXml() throws BusinessException {
		if (null == getStructuredXml()) setStructuredXml(createStructuredXml());
		return getStructuredXml();
	}

	public String text() {
		return getInternals().text();
	}
}
