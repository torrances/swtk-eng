package org.swtk.eng.asm.dmo.base;

import java.util.Set;
import java.util.TreeSet;

import org.swtk.eng.asm.dto.JaccardSimilarityResult;

import com.trimc.blogger.commons.exception.BusinessException;

public abstract class JaccardSimilarityBase {

	protected JaccardSimilarityResult process(int[] a1, int[] a2) throws BusinessException {
		return process(toSet(a1), toSet(a2));
	}

	protected JaccardSimilarityResult process(Integer[] a1, Integer[] a2) throws BusinessException {
		return process(toSet(a1), toSet(a2));
	}

	public abstract JaccardSimilarityResult process(Set<Integer> s1, Set<Integer> s2) throws BusinessException;

	protected Set<Integer> toSet(int[] arr) {
		Set<Integer> set = new TreeSet<Integer>();
		for (int i : arr)
			set.add(i);

		return set;
	}

	protected Set<Integer> toSet(Integer[] arr) {
		Set<Integer> set = new TreeSet<Integer>();
		for (Integer i : arr)
			set.add(i);

		return set;
	}
}
