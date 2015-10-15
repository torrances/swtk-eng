package org.swtk.eng.preprocess.functions;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.MapUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;

public final class FindRepeatingLines {

	/* minimum length of a repeating line
	 * for example, "a" or "e" can repeat frequently but these should not be considered 
	 * default is 2 */
	public static int LENGTH = 2;

	public static LogManager logger = new LogManager(FindRepeatingLines.class);

	/* minimum number of times a line can repeat to be considered repeating 
	 * default is 2 */
	public static int THRESHOLD = 10;

	private static Collection<String> filter(Collection<String> lines) throws BusinessException {
		List<String> list = new ArrayList<String>();

		for (String line : lines) {
			line = line.trim();
			if (line.length() >= LENGTH) {
				System.err.println(line);
				list.add(line);
			}
		}

		return list;
	}

	/**
	 * @param files		the corpus from which the repeating lines will be extracted
	 * @param codepage	codepage to read corpus with
	 * @return			a collection of repeating lines
	 * @throws BusinessException
	 */
	private static Collection<String> getRepeatingLines(Collection<File> files, Codepage codepage) throws BusinessException {
		Map<String, String> map = new TreeMap<String, String>();

		for (File file : files)
			MapUtils.add2Map(map, toMap(FileUtils.toList(file, codepage)));

		return filter(getRepeatingLines(map));
	}

	/**
	 * @param map		a map of lines by frequency (key=line, value=frequency)
	 * @return			a filtered set of lines that meet the threshold requirements (eg. frequency > n)
	 * @throws BusinessException
	 */
	private static Collection<String> getRepeatingLines(Map<String, String> map) throws BusinessException {
		Set<String> set = new TreeSet<String>();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (Integer.parseInt(entry.getValue()) < THRESHOLD) continue;
			set.add(entry.getKey());
		}

		return set;
	}

	/**
	 * @param corpus	the corpus from which the repeating lines will be extracted
	 * @param codepage	codepage to read corpus with
	 * @return			a collection of repeating lines
	 * @throws BusinessException
	 */
	public static Collection<String> process(File corpus, Codepage codepage) throws BusinessException {
		if (corpus.isDirectory()) {
			return getRepeatingLines(FileUtils.getDescendantFilesInFolder(corpus.getAbsolutePath(), "*"), codepage);
		} else {
			return getRepeatingLines(FileUtils.toList(corpus), codepage);
		}
	}

	public static Collection<String> process(String corpus, Codepage codepage) throws BusinessException {
		return process(new File(corpus), codepage);
	}

	/**
	 * @param lines		the lines from a given file
	 * @return			a map of lines by frequency (key=line, value=frequency)
	 * @throws BusinessException
	 */
	private static Map<String, String> toMap(Collection<String> lines) throws BusinessException {
		Map<String, String> map = new HashMap<String, String>();

		for (String line : lines)
			MapUtils.add2Map(map, line.toLowerCase().trim());

		return map;
	}
}
