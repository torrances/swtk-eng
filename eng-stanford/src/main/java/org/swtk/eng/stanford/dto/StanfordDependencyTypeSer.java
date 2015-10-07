package org.swtk.eng.stanford.dto;

import com.google.gson.annotations.Expose;

public class StanfordDependencyTypeSer {

	@Expose private String	description;

	@Expose private boolean	isCollapsed;

	@Expose private String	longName;

	@Expose private String	shortName;

	public String getDescription() {
		return description;
	}

	public String getLongName() {
		return longName;
	}

	public String getShortName() {
		return shortName;
	}

	public boolean isCollapsed() {
		return isCollapsed;
	}

	public void setCollapsed(boolean isCollapsed) {
		this.isCollapsed = isCollapsed;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
