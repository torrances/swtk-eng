package org.swtk.eng.tokenizer.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class TextTokenizerState {

	public static final String	MAIL_PATTERN	= "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

	private Collection<String>	abbreviatedTitles;

	private Collection<String>	fwdSlashDelimited;

	private String				input;

	private List<String>		tokens			= null;

	protected Collection<String> getAbbreviatedTitles() {
		if (null == abbreviatedTitles) setAbbreviatedTitles(PopulateTitleAbbreviations.process());

		return abbreviatedTitles;
	}

	protected Collection<String> getFwdSlashDelimited() {
		if (null == fwdSlashDelimited) setFwdSlashDelimited(PopulateSlashDelimitedTokens.process());

		return fwdSlashDelimited;
	}

	protected String getInput() {
		return input;
	}

	protected List<String> getTokens() {
		if (null == tokens) setTokens(new ArrayList<String>());
		return tokens;
	}

	protected void setAbbreviatedTitles(Collection<String> abbreviatedTitles) {
		this.abbreviatedTitles = abbreviatedTitles;
	}

	protected void setFwdSlashDelimited(Collection<String> fwdSlashDelimited) {
		this.fwdSlashDelimited = fwdSlashDelimited;
	}

	protected void setInput(String input) {
		this.input = input;
	}

	private void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
}
