package org.swtk.eng.asm.dto.adapter;

import java.io.File;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.eng.asm.dto.JaccardSimilarityContract;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.StringUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;

public final class JaccardSimilarityContractAdapter {

	public static LogManager	logger	= new LogManager(JaccardSimilarityContractAdapter.class);

	private static String[] toArray(File file, int k) throws AdapterValidationException {
		try {

			Collection<String> lines = FileUtils.toList(file, Codepage.WINDOWS_1252);

			Set<String> normalized = new TreeSet<String>();
			for (String line : lines) {
				line = line.trim();

				if (!StringUtils.hasValue(line)) continue;
				if (line.length() < k) continue;

				normalized.add(line);
			}

			return (String[]) normalized.toArray(new String[normalized.size()]);

		} catch (BusinessException e) {
			logger.error(e);
			return new String[] {};
		}
	}

	private static String toLogString(JaccardSimilarityContract contract) {
		return String.format("Contract: (k = %s, normalized = %s, document-total = %s)", contract.getK(), contract.isNormalize(), StringUtils.format(contract.getDocuments().length));
	}

	public static JaccardSimilarityContract transformOneDocumentPerLine(File file, int k, boolean normalize) throws AdapterValidationException {
		JaccardSimilarityContract contract = new JaccardSimilarityContract();

		contract.setK(k);
		contract.setNormalize(normalize);
		contract.setDocuments(toArray(file, k));

		logger.info("%s", toLogString(contract));
		return contract;
	}
}
