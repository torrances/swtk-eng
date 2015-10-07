package org.swtk.eng.stanford.dmo.base;

import org.swtk.eng.stanford.dmo.DeepParse;

import com.trimc.blogger.commons.type.Codepage;

public abstract class ShallowParseBase {

	private Codepage	codepage;

	private DeepParse	deepParse;

	private String		input;

	public Codepage getCodepage() {
		return codepage;
	}

	public DeepParse getDeepParse() {
		return deepParse;
	}

	public String getInput() {
		return input;
	}

	public void setCodepage(Codepage codepage) {
		this.codepage = codepage;
	}

	public void setDeepParse(DeepParse deepParse) {
		this.deepParse = deepParse;
	}

	public void setInput(String input) {
		this.input = input;
	}
}
