package org.swtk.eng.stanford.dmo;

import java.io.File;
import java.util.Collection;

import org.swtk.eng.stanford.dmo.base.FindNounPhrasesBase;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.FileUtils;

public class FindNounPhrases extends FindNounPhrasesBase {

	public static LogManager	logger	= new LogManager(FindNounPhrases.class);

	public FindNounPhrases(File in, Codepage codepage) throws BusinessException {
		setIn(in);
		setCodepage(codepage);
		initializeFiles();
	}

	public FindNounPhrases combine() throws BusinessException {

		for (File file : getFiles()) {

			Collection<String> lines = FileUtils.toList(file, getCodepage());
			for (String line : lines) {

				Collection<String> nouns = new CombineNounPhrases(line, getCodepage()).infer().combine().nouns();
				int current = getNouns().size();

				getNouns().addAll(nouns);
				int now = getNouns().size() - current;

				if (now > current) logger.debug("Updated Nouns (original = %s, current = %s, file = %s)", current, now, file.getAbsolutePath());
			}
		}

		return this;
	}

	private void initializeFiles() throws BusinessException {
		if (!getIn().exists()) throw new BusinessException("Input File does not exist (path = %s)", getIn().getAbsolutePath());
		if (getIn().isDirectory()) setFiles(FileUtils.getDescendantFilesInFolder(getIn(), "*"));
		else setFiles(FileUtils.toCollection(getIn()));
		logger.debug("Initialized Input Files (total = %s)", getFiles().size());
	}

	public Collection<String> nouns() {
		return getNouns();
	}
}
