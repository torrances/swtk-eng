package org.swtk.eng.tokenizer.chars;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class CharTokenizer extends CharTokenizerFrame {

	public static LogManager	logger	= new LogManager(CharTokenizer.class);

	public CharTokenizer(CharTokenizerContract contract) {
		setContract(contract);
	}

	private String getBuffer(StringBuilder sb) {
		String buffer = sb.toString();
		if (getContract().isToLowerCase()) {
			if (getContract().hasLanguageTag()) return buffer.toLowerCase(Locale.forLanguageTag(getContract().getLanguageTag().toString()));
			return buffer.toLowerCase();
		}

		return buffer;
	}

	public CharTokenizer process() throws BusinessException {
		for (File file : getContract().getFiles()) {
			try {

				read(file);

			} catch (IOException e) {
				logger.error(e);
				throw new BusinessException("Unable to Parse File (path = %s)", file.getAbsolutePath());
			}
		}

		return this;
	}

	private void read(File file) throws IOException {

		StringBuilder sb = new StringBuilder();
		Reader reader = new InputStreamReader(new FileInputStream(file.getAbsolutePath()), getContract().getCodepage().toString());

		int data = reader.read();
		while (data != -1) {

			char ch = (char) data;
			if (!getCodeMap().containsKey(data)) getCodeMap().put(data, ch);

			/* skip this code and read the next char */
			if (ArrayUtils.contains(getContract().getSkipCodes(), data)) {
				data = reader.read();
				continue;
			}

			sb.append(ch);
			if (getContract().getSize() == sb.length()) {

				String buffer = getBuffer(sb);
				sb = new StringBuilder();

				int freq = (getFreqMap().containsKey(buffer)) ? getFreqMap().get(buffer) : 0;
				getFreqMap().put(buffer, ++freq);

				if (getContract().getSize() > 1) sb.append(buffer.substring(1));
			}

			data = reader.read();
		}

		reader.close();
	}
}
