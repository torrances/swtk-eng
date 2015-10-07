package org.swtk.eng.stanford.dmo.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;

import com.trimc.blogger.commons.type.Codepage;

public abstract class DependencyParseFileBase {

	private Codepage			codepage;

	private Document			dom;

	private File				in;

	private Collection<String>	nounPhrases;

	private boolean				useNounPhraseCombinations	= false;

	private String				xml;

	protected Codepage getCodepage() {
		return codepage;
	}

	protected Document getDom() {
		return dom;
	}

	protected File getIn() {
		return in;
	}

	protected Collection<String> getNounPhrases() {
		if (null == nounPhrases) setNounPhrases(new ArrayList<String>());
		return nounPhrases;
	}

	protected String getXml() {
		return xml;
	}

	protected boolean isUseNounPhraseCombinations() {
		return useNounPhraseCombinations;
	}

	protected void setCodepage(Codepage codepage) {
		this.codepage = codepage;
	}

	protected void setDom(Document dom) {
		this.dom = dom;
	}

	protected void setIn(File in) {
		this.in = in;
	}

	protected void setNounPhrases(Collection<String> nounPhrases) {
		this.nounPhrases = nounPhrases;
	}

	protected void setUseNounPhraseCombinations(boolean useNounPhraseCombinations) {
		this.useNounPhraseCombinations = useNounPhraseCombinations;
	}

	protected void setXml(String xml) {
		this.xml = xml;
	}
}
