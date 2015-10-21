package org.swtk.eng.preprocess.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RemoveParenthesis {

	public static LogManager	logger	= new LogManager(RemoveParenthesis.class);

	public static Collection<String> process(Collection<String> lines) throws BusinessException {
		List<String> list = new ArrayList<String>();

		for (String line : lines)
			list.add(process(line));

		return list;
	}

	public static String process(String text) throws BusinessException {
		String original = text;

		text = remove(text);
		text = text.replaceAll("\\(\\)", "");
		text = StringUtils.trim(text);

		if (!original.equalsIgnoreCase(text)) {
			logger.trace("Parenthesis Removed:\n\t%s\n\t%s", original, text);
		}

		return text;
	}

	private static String remove(String text) throws BusinessException {
		while (text.contains("(")) {

			int x = StringUtils.lastIndexOf(text, "(");//text.indexOf("(");
			String tmp = text.substring(x, text.length());

			int y = StringUtils.indexOf(tmp, ")");
			if (y == -1) return text;
			y += x;

			StringBuilder sb = new StringBuilder();

			sb.append(text.substring(0, x));
			sb.append(text.substring(y + 1));
			text = StringUtils.trim(sb.toString());
		}

		return text;
	}
}
