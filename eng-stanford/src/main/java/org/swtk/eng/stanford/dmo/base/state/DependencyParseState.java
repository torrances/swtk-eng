package org.swtk.eng.stanford.dmo.base.state;

import org.swtk.eng.stanford.dmo.CombineNounPhrases;
import org.swtk.eng.stanford.dmo.DeepParse;
import org.swtk.eng.stanford.dmo.DependencyParseInternals;
import org.swtk.eng.stanford.dto.DepResults;
import org.w3c.dom.Document;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;

public abstract class DependencyParseState {

	private Codepage					codepage;

	private CombineNounPhrases			combineNounPhrases;

	private DeepParse					deepParse;

	private Document					dom;

	private DependencyParseInternals	internals;

	private DepResults					results;

	private Document					structuredDom;

	private String						structuredXml;

	private String						xml;

	public abstract Document dom() throws BusinessException;

	protected Codepage getCodepage() {
		return codepage;
	}

	public CombineNounPhrases getCombineNounPhrases() {
		return combineNounPhrases;
	}

	protected DeepParse getDeepParse() throws BusinessException {
		return deepParse;
	}

	protected Document getDom() {
		return dom;
	}

	protected DependencyParseInternals getInternals() {
		return internals;
	}

	protected DepResults getResults() throws BusinessException {
		return results;
	}

	protected Document getStructuredDom() {
		return structuredDom;
	}

	protected String getStructuredXml() {
		return structuredXml;
	}

	protected String getXml() {
		return xml;
	}

	protected void setCodepage(Codepage codepage) {
		this.codepage = codepage;
	}

	public void setCombineNounPhrases(CombineNounPhrases combineNounPhrases) {
		this.combineNounPhrases = combineNounPhrases;
	}

	protected void setDeepParse(DeepParse deepParse) {
		this.deepParse = deepParse;
	}

	protected void setDom(Document dom) {
		this.dom = dom;
	}

	protected void setInternals(DependencyParseInternals internals) {
		this.internals = internals;
	}

	protected void setResults(DepResults results) {
		this.results = results;
	}

	protected void setStructuredDom(Document structuredDom) {
		this.structuredDom = structuredDom;
	}

	protected void setStructuredXml(String structuredXml) {
		this.structuredXml = structuredXml;
	}

	protected void setXml(String xml) {
		this.xml = xml;
	}
}
