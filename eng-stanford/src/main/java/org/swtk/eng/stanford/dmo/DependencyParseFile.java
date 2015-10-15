package org.swtk.eng.stanford.dmo;


import java.io.File;
import java.util.Collection;

import org.swtk.eng.stanford.dmo.base.DependencyParseFileBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.StringUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.xml.DomUtils;
import com.trimc.blogger.commons.xml.XmlFormatter;
import com.trimc.blogger.commons.xml.Xpath;

public final class DependencyParseFile extends DependencyParseFileBase {

	public static LogManager	logger	= new LogManager(DependencyParseFile.class);

	public DependencyParseFile(File in, Codepage codepage) {
		setIn(in);
		setCodepage(codepage);
	}

	public DependencyParseFile combine() {
		setUseNounPhraseCombinations(true);
		return this;
	}

	public DependencyParseFile combine(Collection<String> nounPhrases) {
		setNounPhrases(nounPhrases);
		setUseNounPhraseCombinations(true);
		return this;
	}

	public Document dom() {
		try {

			if (null == getDom() && StringUtils.hasValue(getXml())) {
				setDom(DomUtils.createDOMByString(getXml()));
			}

		} catch (BusinessException e) {
			logger.error(e, "Unable to create DOM from string:\n%s", getXml());
		}

		return getDom();
	}

	private Document getDom(String line) throws BusinessException {
		if (isUseNounPhraseCombinations()) return new DependencyParse(line, getCodepage()).combine(getNounPhrases()).typedDependenciesCCprocessed().dom();
		return new DependencyParse(line, getCodepage()).typedDependenciesCCprocessed().dom();
	}

	public DependencyParseFile parse() throws BusinessException {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("<?xml version=\"1.0\" encoding=\"%s\"?>", getCodepage().toString()));
		sb.append("<file>");

		for (String line : FileUtils.toList(getIn(), getCodepage())) {
			try {
				parse(sb, line);
			} catch (BusinessException e) {
				logger.error(e);
			}
		}

		sb.append("</file>");
		setXml(XmlFormatter.format(sb, getCodepage()));

		return this;
	}

	private void parse(StringBuilder sb, String line) throws BusinessException {
		try {

			sb.append("<result>");
			sb.append(String.format("<line><![CDATA[%s]]></line>", line));

			Document dom = getDom(line);
			for (Element element : new Xpath(dom).evaluate("descendant-or-self::node").elements()) {
				sb.append(XmlFormatter.toCondensedString(element, getCodepage()) + System.lineSeparator());
			}

			sb.append("</result>");

		} catch (Exception e) {
			sb.append("</result>");
			throw new BusinessException("Failed to parse line:\n\t%s", line);
		}
	}

	public String xml() {
		return getXml();
	}
}
