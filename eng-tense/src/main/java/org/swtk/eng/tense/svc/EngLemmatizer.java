package org.swtk.eng.tense.svc;

import org.swtk.eng.tense.dmo.AdjectiveDictionary;
import org.swtk.eng.tense.dmo.PluralityDictionary;
import org.swtk.eng.tense.dmo.SuperlativeDictionary;
import org.swtk.eng.tense.dmo.TenseDictionary;
//import org.swtk.eng.tense.dmo.AdjectiveDictionary;
//import org.swtk.eng.tense.dmo.PluralityDictionary;
//import org.swtk.eng.tense.dmo.SuperlativeDictionary;
//import org.swtk.eng.tense.dmo.TenseDictionary;
import org.swtk.eng.tokenizer.text.TextTokenizer;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class EngLemmatizer {

	public static String lemmatize(String term) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		for (String token : new TextTokenizer(term).tokenize().array())
			sb.append(lemmatize2(token) + " ");

		String modified = StringUtils.trim(sb.toString());
		while (modified.contains("  "))
			modified = modified.replaceAll("  ", " ");

		return modified;
	}

	private static String lemmatize2(String term) {

		String modified = AdjectiveDictionary.reduce(term);
		if (!modified.equals(term)) return modified;

		modified = PluralityDictionary.reduce(term);
		if (!modified.equals(term)) return modified;

		modified = SuperlativeDictionary.reduce(term);
		if (!modified.equals(term)) return modified;

		modified = TenseDictionary.reduce(term);
		if (!modified.equals(term)) return modified;

		//	modified = GerundsDictionary.reduce(term);
		//	if (!modified.equals(term)) return modified;

		return term;
	}
}
