package org.swtk.eng.grammar.tagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.swtk.common.dict.beans.StopWordsDictionary;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.EngGrammarMetaType;
import com.trimc.blogger.commons.utils.TextUtils;

@Lazy
@Component
public class EngGrammarMetaTypeTagger {

	public static LogManager	logger	= new LogManager(EngGrammarMetaTypeTagger.class);

	@Autowired
	private StopWordsDictionary	dictionary;

	private boolean containsStopWord(String text) {
		String[] tokens = text.toLowerCase().split(" ");
		for (String token : tokens)
			if (dictionary.entries().contains(token)) return true;

		return false;
	}

	public EngGrammarMetaType parse(String text) throws BusinessException {

		if (TextUtils.containsNumeric(text)) {
			return EngGrammarMetaType.NUMBER;
		} else if (TextUtils.containsPunctuationExcept(text, ' ')) {
			return EngGrammarMetaType.PUNCTUATION;
		}

		if (containsStopWord(text)) return EngGrammarMetaType.CLOSED_WORD;

		return EngGrammarMetaType.OTHER;
	}
}
