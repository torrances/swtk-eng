package org.swtk.eng.asm.svc.impl;

import org.apache.commons.codec.language.Caverphone1;
import org.apache.commons.codec.language.Caverphone2;
import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.commons.codec.language.Soundex;
import org.swtk.eng.asm.dto.EncodedResult;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.TextUtils;

public class LanguageEncodingService {

	private String caverphone1(String value) throws BusinessException {
		return new Caverphone1().encode(value);
	}

	private String caverphone2(String value) throws BusinessException {
		return new Caverphone2().encode(value);
	}

	private String colognePhonetic(String value) throws BusinessException {
		return new ColognePhonetic().encode(value);
	}

	private String doubleMetaphone(String value) throws BusinessException {
		return new DoubleMetaphone().encode(value);
	}

	public EncodedResult encode(String value) throws BusinessException {
		if (!TextUtils.isAlphaPunctuationOnly(value)) throw new BusinessException("Unable to Encode String (value = %s)", value);

		EncodedResult result = new EncodedResult();

		result.setValue(value);
		result.setCaverphone1(caverphone1(value));
		result.setCaverphone2(caverphone2(value));
		result.setColognePhonetic(colognePhonetic(value));
		result.setDoubleMetaphone(doubleMetaphone(value));
		result.setMetaphone(metaphone(value));
		result.setRefinedSoundex(refinedSoundex(value));
		result.setSoundex(soundex(value));

		return result;
	}

	private String metaphone(String value) throws BusinessException {
		return new Metaphone().encode(value);
	}

	private String refinedSoundex(String value) throws BusinessException {
		return new RefinedSoundex().encode(value);
	}

	private String soundex(String value) throws BusinessException {
		return new Soundex().encode(value);
	}
}
