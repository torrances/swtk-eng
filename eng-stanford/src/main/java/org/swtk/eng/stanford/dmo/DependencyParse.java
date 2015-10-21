package org.swtk.eng.stanford.dmo;

import java.util.Collection;

import org.swtk.eng.stanford.dmo.base.frame.DependencyParseFrame;
import org.swtk.eng.stanford.dto.DepResult;
import org.swtk.eng.stanford.dto.DepResults;
import org.swtk.eng.stanford.dto.adapter.DepResultsAdapter;
import org.swtk.eng.stanford.dto.adapter.DependencyParseContractAdapter;
import org.swtk.eng.stanford.dto.adapter.TypeDependencyAdapter;
import org.swtk.eng.stanford.util.functions.RemoveCircularDependenciesFunction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.type.StanfordDependencyType;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.xml.DomUtils;

public class DependencyParse extends DependencyParseFrame {

	public static LogManager	logger	= new LogManager(DependencyParse.class);

	public DependencyParse(String input, Codepage codepage) {
		setCodepage(codepage);
		setInternals(new DependencyParseInternals(input));
	}

	public DependencyParse combine() throws BusinessException {
		setCombineNounPhrases(new CombineNounPhrases(text(), getCodepage()).infer().combine(true, true));
		getInternals().setInput(getCombineNounPhrases().text());
		return this;
	}

	public DependencyParse combine(Collection<String> injected) throws BusinessException {
		setCombineNounPhrases(new CombineNounPhrases(text(), getCodepage()).infer().inject(injected).combine(true, true));
		getInternals().setInput(getCombineNounPhrases().text());
		return this;
	}

	@Override
	public Document dom() throws BusinessException {
		if (null == getDom()) setDom(DomUtils.createDOMByString(xml()));
		return getDom();
	}

	/**
	 * @return	return false if the dependency parse is missing common predicates
	 * 			this might indicate that the sentence parsed was gobbledygook
	 * @throws BusinessException
	 */
	public boolean gobbledygook() throws BusinessException {
		for (DepResult result : getResults()) {

			/* if any of the predicates listed here exist 
			 * then the question is not considered to be gobbledygook (aka gibberish) */
			if (StanfordDependencyType.NSUBJ == result.getPredicateType()) return false;
			if (StanfordDependencyType.DOBJ == result.getPredicateType()) return false;
		}

		return true;
	}

	public DependencyParse inject(CombineNounPhrases combineNounPhrases) {
		setCombineNounPhrases(combineNounPhrases);
		if (null != getCombineNounPhrases()) getInternals().setInput(getCombineNounPhrases().text());
		return this;
	}

	public String json() throws BusinessException {
		return GsonUtils.toJsonFormatted(this.results());
	}

	public DependencyParse pos() throws BusinessException {
		DepResultsAdapter.enhance(getResults(), getDeepParse().results());
		return this;
	}

	public DependencyParse removeCircularDependencies() throws BusinessException {
		for (Element el : RemoveCircularDependenciesFunction.process(DependencyParseContractAdapter.transform(dom()))) {
			logger.debug("Removing Circular Dependency: %s, %s", el.getAttribute("object"), el.getAttribute("object-sequence"));
			DomUtils.detachFromParent(el);
		}

		return this;
	}

	public DepResults results() throws BusinessException {
		return this.getResults();
	}

	public DependencyParse typedDependencies() throws BusinessException {
		getInternals().typedDependencies();
		return this;
	}

	public DependencyParse typedDependenciesCCprocessed() throws BusinessException {
		getInternals().typedDependenciesCCprocessed();
		return this;
	}

	public String xml() throws BusinessException {
		if (null != getXml()) return getXml();
		setXml(TypeDependencyAdapter.toXmlString(text(), getCodepage(), getInternals().getTypedDependencies(), true));

		logger.debug("Dependency Parse Completed:\n%s", getXml());
		return getXml();
	}
}
