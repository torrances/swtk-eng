package org.swtk.eng.tokenizer.chars.adapter;

import java.io.File;

import org.swtk.common.framework.type.LanguageTag;
import org.swtk.eng.tokenizer.chars.CharTokenizerContract;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.type.Codepage;

public final class CharTokenizerContractAdapter {

	public static CharTokenizerContract transform(Codepage codepage, File[] files) throws AdapterValidationException {
		return transform(codepage, LanguageTag.ENGLISH_US, files, false, 1, new Integer[] {}, false);
	}

	public static CharTokenizerContract transform(Codepage codepage, LanguageTag languageTag, File[] files, boolean includeIntCodes, Integer size, Integer[] skipCodes, boolean toLowerCase) throws AdapterValidationException {
		CharTokenizerContract contract = new CharTokenizerContract();

		contract.setLanguageTag(languageTag);
		contract.setCodepage(codepage);
		contract.setIncludeIntCodes(includeIntCodes);
		contract.setSize(size);
		contract.setSkipCodes(skipCodes);
		contract.setToLowerCase(toLowerCase);

		return contract;
	}
}
