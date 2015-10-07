package org.swtk.eng.asm.svc;

import java.util.Set;

import org.swtk.eng.asm.dmo.JaccardSimilarity;
import org.swtk.eng.asm.dto.JaccardSimilarityContract;
import org.swtk.eng.asm.utils.ArrayUtils;
import org.swtk.eng.rollinghash.svc.CyclicHashService;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.Stopwatch;
import com.trimc.blogger.commons.utils.StringUtils;

public class JaccardSimilarityService {

	public static LogManager	logger	= new LogManager(JaccardSimilarityService.class);

	public double[][] process(JaccardSimilarityContract contract) throws BusinessException {
		double[][] rM = ArrayUtils.dsDouble(contract.getDocuments().length, 0);

		int comparisons = 0;
		Stopwatch timer = new Stopwatch();

		Set<Integer>[] sets = shingle(contract.getK(), contract.isNormalize(), contract.getDocuments());
		for (int i = 0; i < sets.length; i++) {

			for (int j = 0; j < sets.length; j++) {
				if (i == j) {
					rM[i][j] = 1;
					rM[j][i] = 1;
				} else {
					double similarity = new JaccardSimilarity().process(sets[i], sets[j]).getSimilarity();;
					rM[i][j] = similarity;
					rM[j][i] = similarity;
					comparisons++;
				}
			}
		}

		logger.info("Computed Jaccard Similarity (time = %s, comparisons = %s)", timer.getTotalTime(), StringUtils.format(comparisons));
		return rM;
	}

	@SuppressWarnings("unchecked")
	private Set<Integer>[] shingle(int k, boolean normalize, String... documents) throws BusinessException {
		Set<?>[] sets = new Set<?>[documents.length];
		for (int i = 0; i < documents.length; i++)
			sets[i] = new CyclicHashService().process(documents[i], k);

		return (Set<Integer>[]) sets;
	}
}
