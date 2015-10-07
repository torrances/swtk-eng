package org.swtk.eng.stanford.util.functions;

import java.util.Collection;

import org.swtk.eng.stanford.dmo.DeepParse;
import org.w3c.dom.Document;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.xml.Xpath;

public class FindNounPhrasesFunction {

	public static LogManager	logger	= new LogManager(FindNounPhrasesFunction.class);

	public static Collection<String> process(Document dom) throws BusinessException {
		return new Xpath(dom).evaluate("descendant::*[@label='NP'][not(descendant::*[@label='NP'])]/@text").texts();
	}

	public static Collection<String> process(String input, Codepage codepage) throws BusinessException {
		return process(new DeepParse(input, codepage).parse().dom());
	}
}
