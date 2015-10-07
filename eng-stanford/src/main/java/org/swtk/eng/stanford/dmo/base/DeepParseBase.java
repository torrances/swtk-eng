package org.swtk.eng.stanford.dmo.base;

import com.trimc.blogger.commons.type.Codepage;

import edu.stanford.nlp.trees.Tree;

public abstract class DeepParseBase {

	private Codepage	codepage;

	private String		input;

	private Tree		parse;

	public Codepage getCodepage() {
		return codepage;
	}

	protected String getInput() {
		return input;
	}

	protected Tree getParse() {
		return parse;
	}

	public void setCodepage(Codepage codepage) {
		this.codepage = codepage;
	}

	protected void setInput(String input) {
		this.input = input;
	}

	protected void setParse(Tree parse) {
		this.parse = parse;
	}
}
