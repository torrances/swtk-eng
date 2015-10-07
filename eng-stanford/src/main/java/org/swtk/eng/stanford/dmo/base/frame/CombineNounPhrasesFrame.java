package org.swtk.eng.stanford.dmo.base.frame;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.eng.stanford.dmo.base.state.CombineNounPhrasesState;
import org.swtk.eng.tokenizer.text.TextTokenizer;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.StringUtils;

public abstract class CombineNounPhrasesFrame extends CombineNounPhrasesState {

	public Collection<String> combined() {
		return getCombined();
	}

	protected Collection<String> getCombined() {
		if (null == super.getCombined()) {
			Set<String> set = new TreeSet<String>();

			try {

				for (String token : new TextTokenizer(getInput()).tokenize().collection())
					if (token.contains("_")) set.add(token.replaceAll("_", " "));

			} catch (BusinessException e) {
				logger.error(e);
			}

			super.setCombined(set);
		}

		return super.getCombined();
	}

	protected void transform(String input, Codepage codepage) throws AdapterValidationException {
		if (!StringUtils.hasValue(input)) throw new AdapterValidationException("Input must not be null");
		if (null == codepage) throw new AdapterValidationException("Codepage must not be null");
		setInput(input);
		setCodepage(codepage);
	}
}
