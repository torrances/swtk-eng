package org.swtk.eng.asm.svc.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.eng.asm.dmo.CosineDocumentSimilarity;
import org.swtk.eng.asm.dto.CosineDocumentSimilarityResult;
import org.swtk.eng.asm.dto.adapter.CosineDocumentSimilarityResultAdapter;
import org.swtk.eng.asm.svc.CosineDocumentSimilarityService;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.FileUtils;
import com.trimc.blogger.commons.utils.Stopwatch;
import com.trimc.blogger.commons.utils.StringUtils;

public class CosineDocumentSimilarityServiceImpl implements CosineDocumentSimilarityService {

	public static LogManager	logger	= new LogManager(CosineDocumentSimilarityServiceImpl.class);

	private double getCosineSimilarity(String s1, String s2) throws BusinessException {
		try {
			return new CosineDocumentSimilarity(s1, s2).getCosineSimilarity();

		} catch (IOException e) {
			logger.error(e, "Unable to Compute Cosine Similarity");
			return -1;
		}
	}

	@Override
	public CosineDocumentSimilarityResult process(Collection<File> files, Codepage codepage) throws BusinessException {
		return process((File[]) files.toArray(new File[files.size()]), codepage);
	}

	private CosineDocumentSimilarityResult process(File[] files, Codepage codepage) throws BusinessException {

		Stopwatch master = new Stopwatch();
		Map<String, Map<String, Double>> map = new TreeMap<String, Map<String, Double>>();

		final int TOTAL = files.length;
		for (int i = 0; i < TOTAL; i++) {

			File file1 = files[i];
			Stopwatch timer = new Stopwatch();

			String output1 = FileUtils.toString(file1, Codepage.UTF_8);
			if (!StringUtils.hasValue(output1)) continue;

			String filename1 = FileUtils.getName(file1);
			Map<String, Double> inner = map.containsKey(filename1) ? map.get(filename1) : new TreeMap<String, Double>();

			for (int j = TOTAL - 1; j > i; j--) {
				File file2 = files[j];

				String output2 = FileUtils.toString(file2, Codepage.UTF_8);
				if (!StringUtils.hasValue(output2)) continue;

				String filename2 = FileUtils.getName(file2);
				inner.put(filename2, getCosineSimilarity(output1, output2));
			}

			map.put(filename1, inner);
			logger.info("Processed File (%s - %s) in (file = %s, total = %s)", (i + 1), TOTAL, timer.getTotalTime(), master.getTotalTime());
		}

		return CosineDocumentSimilarityResultAdapter.transform(TOTAL, map);
	}
}
