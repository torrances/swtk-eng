package org.swtk.eng.preprocess.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.RegexUtils;
import com.trimc.blogger.commons.utils.StringUtils;
import com.trimc.blogger.commons.utils.TextUtils;

public class CreateSentences {

	public static LogManager logger = new LogManager(CreateSentences.class);

	public static final String[] PUNCTUATION = new String[] { ".", "?", "!" };

	private static void add2List(StringBuilder sb, Collection<String> collection) {
		String line = sb.toString().trim();
		line = RegexUtils.reverseDotNotation(line);

		collection.add(line);
	}

	private static Collection<String> addTerminatingPeriods(Collection<String> lines) {
		List<String> list = new ArrayList<String>();

		for (String line : lines) {
			line = line.trim();
			if (!StringUtils.hasValue(StringUtils.trim(line)))
				continue;
			if (!TextUtils.isPunctuation(line.substring(line.length() - 1)))
				line = line + ".";
			list.add(line);
		}

		return list;
	}

	private static Collection<String> cleanse(Collection<String> lines) {
		List<String> list = new ArrayList<String>();

		for (String line : lines)
			list.add(cleanse(line));

		return list;
	}

	private static String cleanse(String line) {
		while (line.contains("  "))
			line = line.replaceAll("  ", " ");

		if (line.startsWith(")"))
			line = StringUtils.substringAfter(line, ")").trim();
		line = RegexUtils.reverseDotNotation(line);

		return line;
	}

	/*
	 * private static String sentencify(StringBuilder sb, List<String> list,
	 * String line, String punctuation) { while (line.contains(punctuation)) {
	 * 
	 * String temp = StringUtils.substringBefore(line, punctuation);
	 * 
	 * sb.append(" " + temp + " "); add2List(sb, list); sb = new
	 * StringBuilder();
	 * 
	 * line = StringUtils.substringAfter(line, punctuation).trim(); }
	 * 
	 * return line; }
	 */
	public static Collection<String> process(Collection<String> lines) throws BusinessException {
		List<String> list = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = lines.iterator();

		while (iter.hasNext()) {
			String line = iter.next();

			/* pattern for temporarily removing "." in numerical strings */
			line = RegexUtils.useDotNotationForNumbers(line);

			/* added 2:10 PM 9/16/2014 -- why was this deleted? */
			line = RegexUtils.useDotNotationForAbbreviations(line);
			logger.debug("Processing Line (%s)", line);

			for (String punctuation : PUNCTUATION) {
				if (line.endsWith(punctuation))
					continue;
				while (StringUtils.contains(line, punctuation)) {

					String temp = StringUtils.substringBefore(line, punctuation);
					sb.append(" " + temp + " ");
					add2List(sb, list);
					sb = new StringBuilder();
					line = StringUtils.substringAfter(line, punctuation).trim();
				}
			}

			sb.append(line + " ");
		}

		add2List(sb, list);
		return cleanse(addTerminatingPeriods(list));
	}
}
