package org.swtk.eng.stanford.dto;

import com.google.gson.annotations.Expose;


public class DepNode {

	@Expose private String			name;

	@Expose private EngGrammarSer	pos;

	@Expose private Integer			sequence;

	public String getName() {
		return name;
	}

	public EngGrammarSer getPos() {
		return pos;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPos(EngGrammarSer pos) {
		this.pos = pos;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
}
