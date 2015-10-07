package org.swtk.eng.tokenizer.chars;

import java.util.Map;

public class CharTokenizerResult {

	private Map<Integer, Character>		codes;

	private CharTokenizerContract	contract;

	private Map<String, Integer>		frequency;

	public Map<Integer, Character> getCodes() {
		return codes;
	}

	public CharTokenizerContract getContract() {
		return contract;
	}

	public Map<String, Integer> getFrequency() {
		return frequency;
	}

	public void setCodes(Map<Integer, Character> codes) {
		this.codes = codes;
	}

	public void setContract(CharTokenizerContract contract) {
		this.contract = contract;
	}

	public void setFrequency(Map<String, Integer> frequency) {
		this.frequency = frequency;
	}
}
