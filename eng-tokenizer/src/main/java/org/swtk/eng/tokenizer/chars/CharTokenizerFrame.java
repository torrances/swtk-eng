package org.swtk.eng.tokenizer.chars;

import com.trimc.blogger.commons.exception.BusinessException;

public abstract class CharTokenizerFrame extends CharTokenizerState {

	public CharTokenizerResult getResult() throws BusinessException {
		return CharTokenizerResultAdapter.transform(getContract(), getFreqMap(), getCodeMap());
	}
}