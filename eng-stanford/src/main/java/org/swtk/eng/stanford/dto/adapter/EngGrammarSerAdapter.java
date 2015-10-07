package org.swtk.eng.stanford.dto.adapter;

import org.swtk.eng.grammar.types.EngGrammar;
import org.swtk.eng.stanford.dto.EngGrammarSer;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class EngGrammarSerAdapter {

	public static EngGrammarSer transform(EngGrammar type) throws AdapterValidationException {
		EngGrammarSer ser = new EngGrammarSer();

		ser.setLongName(type.getLabel());
		ser.setMetaType(type.getMetaType().toString());
		ser.setUpperType(type.getUpperType().toString());
		ser.setTags(type.getTags());

		return ser;
	}
}
