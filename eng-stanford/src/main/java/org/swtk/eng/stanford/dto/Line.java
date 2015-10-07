package org.swtk.eng.stanford.dto;

import com.google.gson.annotations.Expose;

public class Line {

	@Expose private String	modified;

	@Expose private String	original;

	public String getModified() {
		return modified;
	}

	public String getOriginal() {
		return original;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public void setOriginal(String original) {
		this.original = original;
	}
}
