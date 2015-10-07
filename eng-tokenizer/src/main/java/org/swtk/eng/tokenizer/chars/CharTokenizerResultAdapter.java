package org.swtk.eng.tokenizer.chars;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.MapUtils;

public final class CharTokenizerResultAdapter {

	public static LogManager	logger	= new LogManager(CharTokenizerResultAdapter.class);

	public static void toFile(File outFile, CharTokenizerResult result) throws BusinessException {
		try {

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile.getAbsolutePath()), result.getContract().getCodepage().toString()));
			out.write(toString(result.getContract().getMinimumThreshold(), result.getFrequency()));
			out.close();

			if (result.getContract().isIncludeIntCodes()) {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile.getAbsolutePath() + ".codes.txt"), result.getContract().getCodepage().toString()));
				out.write(MapUtils.toString1(result.getCodes(), System.lineSeparator()));
				out.close();
			}

		} catch (IOException e) {
			logger.error(e);
			throw new BusinessException("Unable to Write Result to File (path = %s)", outFile.getAbsolutePath());
		}
	}

	private static String toString(Integer threshold, Map<String, Integer> map) throws AdapterValidationException {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() < threshold) continue;
			sb.append(entry.getKey() + ", " + entry.getValue() + System.lineSeparator());
		}

		return sb.toString();
	}

	public static CharTokenizerResult transform(CharTokenizerContract contract, Map<String, Integer> freqMap, Map<Integer, Character> codeMap) throws AdapterValidationException {
		CharTokenizerResult result = new CharTokenizerResult();

		result.setContract(contract);
		result.setFrequency(freqMap);
		result.setCodes(codeMap);

		return result;
	}
}
