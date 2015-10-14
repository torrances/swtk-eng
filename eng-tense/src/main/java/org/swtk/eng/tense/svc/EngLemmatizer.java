package org.swtk.eng.tense.svc;

import org.swtk.eng.tense.dmo.AdjectiveDictionary;
import org.swtk.eng.tense.dmo.PluralityDictionary;
import org.swtk.eng.tense.dmo.SuperlativeDictionary;
import org.swtk.eng.tense.dmo.TenseDictionary;

public class EngLemmatizer {

	public static String lemmatize(String term) {

		String modified = AdjectiveDictionary.reduce(term);
		if (!modified.equals(term)) return modified;

		modified = PluralityDictionary.reduce(term);
		if (!modified.equals(term)) return modified;

		modified = SuperlativeDictionary.reduce(term);
		if (!modified.equals(term)) return modified;

		modified = TenseDictionary.reduce(term);
		if (!modified.equals(term)) return modified;

		return term;
	}
}
