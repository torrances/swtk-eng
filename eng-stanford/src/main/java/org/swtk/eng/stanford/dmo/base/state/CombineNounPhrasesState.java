package org.swtk.eng.stanford.dmo.base.state;


import java.util.Collection;
import java.util.TreeSet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.string.StringUtils;

public abstract class CombineNounPhrasesState {

	public static LogManager	logger		= new LogManager(CombineNounPhrasesState.class);

	private Codepage			codepage;

	/**
	 * 	these are the noun phrases that have been combined 
	 * 	given the user-supplied input
	 */
	private Collection<String>	combined;

	/**
	 * 	these are noun phrases that have been inferred using a dependency parser
	 * 	given the user-supplied input
	 */
	private Collection<String>	inferredNounPhrases;

	/**
	 * 	these are noun phrases that have been supplied from the user
	 */
	private Collection<String>	injectedNounPhrases;

	/**
	 * 	this is the input from the user
	 * 	all noun phrases within this input will be turned into single entities
	 * 	
	 * 	for example
	 * 		the quick brown fox jumped over the lazy dog
	 * 	becomes
	 * 		the quick_brown_fox jumped over the lazy_dog
	 */
	private String				input;

	private boolean				isGreedy	= false;

	protected Codepage getCodepage() {
		return codepage;
	}

	protected Collection<String> getCombined() {
		return combined;
	}

	protected Collection<String> getInferredNounPhrases() {
		if (null == inferredNounPhrases) setInferredNounPhrases(new TreeSet<String>());
		return inferredNounPhrases;
	}

	protected Collection<String> getInjectedNounPhrases() {
		if (null == injectedNounPhrases) setInjectedNounPhrases(new TreeSet<String>());
		return injectedNounPhrases;
	}

	protected String getInput() {
		return input;
	}

	protected boolean isGreedy() {
		return isGreedy;
	}

	protected void setCodepage(Codepage codepage) {
		this.codepage = codepage;
	}

	protected void setCombined(Collection<String> combined) {
		this.combined = combined;
	}

	protected void setGreedy(boolean isGreedy) {
		this.isGreedy = isGreedy;
	}

	protected void setInferredNounPhrases(Collection<String> someInferredNounPhrases) {
		this.inferredNounPhrases = someInferredNounPhrases;
		if (null != someInferredNounPhrases && !someInferredNounPhrases.isEmpty()) logger.debug("Inferred Noun Phrases (total = %s)", StringUtils.format(someInferredNounPhrases.size()));
	}

	protected void setInjectedNounPhrases(Collection<String> someInjectedNounPhrases) {
		this.injectedNounPhrases = someInjectedNounPhrases;
		if (null != someInjectedNounPhrases && !someInjectedNounPhrases.isEmpty()) logger.debug("Injected Noun Phrases (total = %s)", StringUtils.format(someInjectedNounPhrases.size()));
	}

	protected void setInput(String someInput) {
		this.input = someInput;
		if (StringUtils.hasValue(someInput)) if (StringUtils.hasValue(input)) logger.debug("Modified Input (original = %s, modified = %s)", input, someInput);
	}
}
