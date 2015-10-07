package org.swtk.eng.asm.dto;

public class JaccardSimilarityContract {

	private String[]	documents;

	private int			k;

	private boolean		normalize;

	public String[] getDocuments() {
		return documents;
	}

	public int getK() {
		return k;
	}

	public boolean isNormalize() {
		return normalize;
	}

	public void setDocuments(String[] documents) {
		this.documents = documents;
	}

	public void setK(int k) {
		this.k = k;
	}

	public void setNormalize(boolean normalize) {
		this.normalize = normalize;
	}
}
