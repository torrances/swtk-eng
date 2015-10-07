package org.swtk.eng.asm.dmo;

import java.util.Set;

import org.swtk.eng.asm.dmo.base.JaccardSimilarityBase;
import org.swtk.eng.asm.dto.JaccardSimilarityResult;

import com.google.common.collect.Sets;
import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.SetUtils;

/**
 * 	Purpose
 * 	Compute Jaccard Similiarity of two sets
 *
 */
public class JaccardSimilarity extends JaccardSimilarityBase {

	public static LogManager	logger	= new LogManager(JaccardSimilarity.class);

	@Override
	@SuppressWarnings("unchecked")
	public JaccardSimilarityResult process(Set<Integer> s1, Set<Integer> s2) throws BusinessException {

		Set<Integer> universal = SetUtils.merge(s1, s2);
		final double TOTAL_SIZE = (double) universal.size();

		Set<Integer> intersection = Sets.intersection(s1, s2);
		final double INTERSECTION_SIZE = (double) intersection.size();

		final double SIMILARITY = INTERSECTION_SIZE / TOTAL_SIZE;
		logger.debug("total-size = %s, intersection-size = %s, similarity = %s", TOTAL_SIZE, INTERSECTION_SIZE, SIMILARITY);

		JaccardSimilarityResult result = new JaccardSimilarityResult();
		result.setSimilarity(SIMILARITY);
		result.setIntersectionSize(INTERSECTION_SIZE);
		result.setUniversalSet(universal);

		return result;
	}
}
