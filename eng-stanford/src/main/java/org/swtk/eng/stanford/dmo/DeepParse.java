package org.swtk.eng.stanford.dmo;


import java.util.Collection;

import org.swtk.eng.stanford.dmo.base.DeepParseBase;
import org.swtk.eng.stanford.dto.TreeNode;
import org.swtk.eng.stanford.dto.adapter.TreeNodeAdapter;
import org.swtk.eng.stanford.dto.adapter.TypeDependencyAdapter;
import org.swtk.eng.stanford.util.factory.DeepParseFactory;
import org.w3c.dom.Document;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.xml.DomUtils;
import com.trimc.blogger.commons.xml.XmlFormatter;

public class DeepParse extends DeepParseBase {

	public static LogManager	logger	= new LogManager(DeepParse.class);

	public DeepParse(String input, Codepage codepage) {
		setInput(input);
		setCodepage(codepage);
	}

	public DeepParse combine() throws BusinessException {
		setInput(new CombineNounPhrases(getInput(), getCodepage()).infer().combine().text());
		return this;
	}

	public DeepParse combine(Collection<String> injected) throws BusinessException {
		setInput(new CombineNounPhrases(getInput(), getCodepage()).infer().inject(injected).combine().text());
		return this;
	}

	public Document dom() throws BusinessException {
		return DomUtils.createDOMByString(xml());
	}

	public String json() throws BusinessException {
		return GsonUtils.toJsonFormatted(results());
	}

	public DeepParse normalize() {
		String input = getInput();

		input = input.toLowerCase().trim();
		input = TextUtils.removePunctuationExcept(input, ' ', '_');

		setInput(input);
		return this;
	}

	public DeepParse parse() throws BusinessException {
		setParse(DeepParseFactory.getInstance().deepParse(getInput()));
		return this;
	}

	public TreeNode results() throws BusinessException {
		return TreeNodeAdapter.transform(dom());
	}

	public String text() {
		return getInput();
	}

	public String xml() throws BusinessException {
		String xml = XmlFormatter.format(TypeDependencyAdapter.toXmlString(getParse()), getCodepage());

		/* remove header */
		xml = XmlFormatter.toCondensedString(xml, getCodepage());

		logger.debug("Deep Parse Completed:\n%s", xml);
		return xml;
	}
}
