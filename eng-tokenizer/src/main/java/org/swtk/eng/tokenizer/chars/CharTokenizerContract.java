package org.swtk.eng.tokenizer.chars;

import java.io.File;

import org.swtk.common.framework.type.LanguageTag;

import com.trimc.blogger.commons.type.Codepage;

public class CharTokenizerContract {

	private Codepage	codepage;

	/* TODO: Feature Not Implemented Yet */
	private boolean		endsWithOnly		= false;

	private File[]		files;

	private boolean		includeIntCodes		= false;

	private LanguageTag	languageTag;

	private Integer		minimumThreshold	= 1;

	private Integer		size				= 1;

	private Integer[]	skipCodes			= new Integer[] {};

	/* TODO: Feature Not Implemented Yet */
	private boolean		startsWithOnly		= false;

	private boolean		toLowerCase			= false;

	public Codepage getCodepage() {
		return codepage;
	}

	public File[] getFiles() {
		return files;
	}

	public LanguageTag getLanguageTag() {
		return languageTag;
	}

	public Integer getMinimumThreshold() {
		return minimumThreshold;
	}

	public Integer getSize() {
		return size;
	}

	public Integer[] getSkipCodes() {
		return skipCodes;
	}

	public boolean hasLanguageTag() {
		return null != getLanguageTag();
	}

	public boolean isEndsWithOnly() {
		return endsWithOnly;
	}

	public boolean isIncludeIntCodes() {
		return includeIntCodes;
	}

	public boolean isStartsWithOnly() {
		return startsWithOnly;
	}

	public boolean isToLowerCase() {
		return toLowerCase;
	}

	public void setCodepage(Codepage codepage) {
		this.codepage = codepage;
	}

	public void setEndsWithOnly(boolean endsWithOnly) {
		this.endsWithOnly = endsWithOnly;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public void setIncludeIntCodes(boolean includeIntCodes) {
		this.includeIntCodes = includeIntCodes;
	}

	public void setLanguageTag(LanguageTag languageTag) {
		this.languageTag = languageTag;
	}

	public void setMinimumThreshold(Integer minimumThreshold) {
		this.minimumThreshold = minimumThreshold;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setSkipCodes(Integer[] skipCodes) {
		this.skipCodes = skipCodes;
	}

	public void setStartsWithOnly(boolean startsWithOnly) {
		this.startsWithOnly = startsWithOnly;
	}

	public void setToLowerCase(boolean toLowerCase) {
		this.toLowerCase = toLowerCase;
	}
}
