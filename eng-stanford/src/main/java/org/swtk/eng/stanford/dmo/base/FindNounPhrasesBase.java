package org.swtk.eng.stanford.dmo.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import com.trimc.blogger.commons.type.Codepage;

public abstract class FindNounPhrasesBase {

	private Codepage			codepage;

	private Collection<File>	files;

	private File				in;

	private Collection<String>	nouns;

	protected Codepage getCodepage() {
		return codepage;
	}

	protected Collection<File> getFiles() {
		if (null == files) setFiles(new ArrayList<File>());
		return files;
	}

	protected File getIn() {
		return in;
	}

	protected Collection<String> getNouns() {
		if (null == nouns) setNouns(new TreeSet<String>());
		return nouns;
	}

	protected void setCodepage(Codepage codepage) {
		this.codepage = codepage;
	}

	protected void setFiles(Collection<File> files) {
		this.files = files;
	}

	protected void setIn(File in) {
		this.in = in;
	}

	protected void setNouns(Collection<String> nouns) {
		this.nouns = nouns;
	}
}
