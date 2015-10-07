package org.swtk.eng.tokenizer.chars;

import java.util.HashMap;
import java.util.Map;

public abstract class CharTokenizerState {

	private Map<Integer, Character>		codeMap;

	private CharTokenizerContract	contract;

	private Map<String, Integer>		freqMap;

	protected Map<Integer, Character> getCodeMap() {
		if (null == codeMap) setCodeMap(new HashMap<Integer, Character>());
		return codeMap;
	}

	protected CharTokenizerContract getContract() {
		return contract;
	}

	protected Map<String, Integer> getFreqMap() {
		if (null == freqMap) setFreqMap(new HashMap<String, Integer>());
		return freqMap;
	}

	private void setCodeMap(Map<Integer, Character> codeMap) {
		this.codeMap = codeMap;
	}

	protected void setContract(CharTokenizerContract contract) {
		this.contract = contract;
	}

	private void setFreqMap(Map<String, Integer> freqMap) {
		this.freqMap = freqMap;
	}
}
