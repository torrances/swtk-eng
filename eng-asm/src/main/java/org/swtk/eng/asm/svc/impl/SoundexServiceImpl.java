package org.swtk.eng.asm.svc.impl;

import org.apache.commons.codec.language.Soundex;
import org.swtk.eng.asm.svc.SoundexService;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.TextUtils;

public class SoundexServiceImpl implements SoundexService {

	public static LogManager	logger	= new LogManager(SoundexServiceImpl.class);

	@Override
	public int difference(String s1, String s2) throws BusinessException {
		try {
			return new Soundex().difference(s1, s2);

		} catch (Exception e) {
			// logger.error(e);
			throw new BusinessException("Unable to Compute Difference (s1 = %s, s2 = %s)", s1, s2);
		}
	}

	@Override
	public String encode(String s1) throws BusinessException {
		try {

			if (!TextUtils.isAlphaPunctuationOnly(s1)) throw new BusinessException("Unable to Encode String (value = %s)", s1);
			return new Soundex().encode(s1);

		} catch (Exception e) {
			// logger.error(e);
			throw new BusinessException("Unable to Encode String (value = %s)", s1);
		}
	}

	@Override
	public boolean isEqual(String s1, String s2) throws BusinessException {
		try {
			return new Soundex().encode(s1).equals(new Soundex().encode(s2));

		} catch (Exception e) {
			// logger.error(e);
			throw new BusinessException("Unable to Compare Equality (s1 = %s, s2 = %s)", s1, s2);
		}
	}
}
