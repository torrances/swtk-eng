package org.swtk.eng.asm.dto;

import java.util.Set;

public class JaccardSimilarityResult {

	private double			intersectionSize;

	private Set<Integer>	set1;

	private Set<Integer>	set2;

	private double			similarity;

	private Set<Integer>	universalSet;

	private int				universalSetSize;

	public double getIntersectionSize() {
		return intersectionSize;
	}

	public Set<Integer> getSet1() {
		return set1;
	}

	public Set<Integer> getSet2() {
		return set2;
	}

	public double getSimilarity() {
		return similarity;
	}

	public Set<Integer> getUniversalSet() {
		return universalSet;
	}

	public int getUniversalSetSize() {
		return universalSetSize;
	}

	public void setIntersectionSize(double intersectionSize) {
		this.intersectionSize = intersectionSize;
	}

	public void setSet1(Set<Integer> set1) {
		this.set1 = set1;
	}

	public void setSet2(Set<Integer> set2) {
		this.set2 = set2;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public void setUniversalSet(Set<Integer> universalSet) {
		this.universalSet = universalSet;
	}

	public void setUniversalSetSize(int universalSetSize) {
		this.universalSetSize = universalSetSize;
	}
}
