package org.swtk.eng.tokenizer.chars;

import org.swtk.eng.tokenizer.chars.adapter.CharTokenizerResultAdapter;

import com.trimc.blogger.commons.exception.BusinessException;

public abstract class CharTokenizerFrame extends CharTokenizerState {

	public CharTokenizerResult getResult() throws BusinessException {
		return CharTokenizerResultAdapter.transform(getContract(), getFreqMap(), getCodeMap());
	}
}