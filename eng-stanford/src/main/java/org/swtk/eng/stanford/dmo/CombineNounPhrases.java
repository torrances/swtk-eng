package org.swtk.eng.stanford.dmo;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.PatternSyntaxException;

import org.swtk.eng.stanford.dmo.base.frame.CombineNounPhrasesFrame;
import org.swtk.eng.stanford.util.functions.FindNounPhrasesFunction;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;

public class CombineNounPhrases extends CombineNounPhrasesFrame {

	public static LogManager	logger	= new LogManager(CombineNounPhrases.class);

	public CombineNounPhrases(String input, Codepage codepage) throws AdapterValidationException {
		transform(input, codepage);
	}

	public CombineNounPhrases combine() {
		String input = getInput();

		for (String nounPhrase : getInjectedNounPhrases()) {
			if (!isGreedy() && contains(input, nounPhrase)) continue;
			input = replaceAll(input, nounPhrase);
		}

		for (String nounPhrase : getInferredNounPhrases()) {
			if (!isGreedy() && contains(input, nounPhrase)) continue;
			input = replaceAll(input, nounPhrase);
		}

		input = input.replaceAll("_and_", " and ");

		setInput(input);
		return this;
	}

	private boolean contains(String input, String nounPhrase) {
		int x = input.indexOf(nounPhrase);
		int y = x + nounPhrase.length();

		if (x == -1) return false;
		if (x > 0) if ("_".equals(input.substring(x - 1, x))) return true;
		if (y < input.length()) if ("_".equals(input.substring(y, y + 1))) return true;

		return false;
	}

	public CombineNounPhrases greedy() {
		setGreedy(true);
		return this;
	}

	public CombineNounPhrases infer() {
		try {

			setInferredNounPhrases(FindNounPhrasesFunction.process(getInput(), getCodepage()));

		} catch (BusinessException e) {
			logger.error(e, "Failed to Infer Noun Phrases (input = %s)", getInput());
		}

		return this;
	}

	public CombineNounPhrases inject(Collection<String> nounPhrases) {
		setInjectedNounPhrases(nounPhrases);
		return this;
	}

	public Collection<String> nouns() {
		Set<String> set = new TreeSet<String>();

		set.addAll(getInjectedNounPhrases());
		set.addAll(getInferredNounPhrases());

		return set;
	}

	private String replaceAll(String input, String nounPhrase) {
		try {

			String _np = nounPhrase.replaceAll(" ", "_").trim();
			input = input.replaceAll(nounPhrase, _np);

		} catch (PatternSyntaxException e) {
			logger.error(e, "Pattern Replacement Failed (noun-phrase = %s)", nounPhrase);
		}

		return input;
	}

	public String text() {
		return getInput();
	}
}
