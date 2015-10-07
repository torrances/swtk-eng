package org.swtk.eng.preprocess.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.swtk.eng.preprocess.patterns.FigureAttributionsPatterns;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.StringUtils;

public class RemoveFigureAttribution {

	public static LogManager	logger	= new LogManager(RemoveFigureAttribution.class);

	private static String preProcess(String text) throws BusinessException {

		while (text.toLowerCase().contains("fig."))
			text = StringUtils.replaceAllMaintainCase(text, "fig.", "figure");

		while (text.toLowerCase().contains("figs."))
			text = StringUtils.replaceAllMaintainCase(text, "figs.", "figure");

		while (text.toLowerCase().contains("figure."))
			text = StringUtils.replaceAllMaintainCase(text, "figure.", "figure");

		return text;
	}

	public static Collection<String> process(Collection<String> lines) throws BusinessException {
		List<String> list = new ArrayList<String>();

		for (String line : lines)
			list.add(process(line));

		return list;
	}

	private static String process(Pattern pattern, String text) throws BusinessException {
		Matcher m = pattern.matcher(text);
		if (m.find()) text = text.replaceAll(m.group(0).trim(), "");

		return text;
	}

	public static String process(String text) throws BusinessException {
		String original = text;
		text = preProcess(text);

		for (Pattern pattern : FigureAttributionsPatterns.getPatterns())
			text = process(pattern, text);

		text = StringUtils.trim(text);
		if (!original.equalsIgnoreCase(text)) logger.trace("Figure Attributions Removed:\n\tOriginal = \t%s\n\tPost-Processed = \t%s", original, text);

		return text;
	}
}
