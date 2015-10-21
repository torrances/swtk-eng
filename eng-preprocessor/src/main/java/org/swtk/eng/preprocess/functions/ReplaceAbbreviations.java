package org.swtk.eng.preprocess.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.swtk.eng.preprocess.PopulateAbbreviationsMap;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class ReplaceAbbreviations {

	public static LogManager	logger	= new LogManager(ReplaceAbbreviations.class);

	public static Collection<String> process(Collection<String> lines) throws BusinessException {
		List<String> list = new ArrayList<String>();

		for (String line : lines)
			list.add(process(line));

		return list;
	}

	public static String process(String input) throws BusinessException {

		String original = input;
		String temp = input.toLowerCase();

		for (Map.Entry<String, String> entry : PopulateAbbreviationsMap.map.entrySet()) {

			int x = temp.indexOf(entry.getKey());
			if (x == -1) continue;

			/* abbreviation must start a line or ... */
			/* ... be preceded by a space or open_paren */
			boolean startsLine = temp.startsWith(entry.getKey());
			boolean precededBySpace = (x - 1 > 0 && temp.substring(x - 1, x).equals(" "));
			boolean precededByOpenParen = (x - 1 > 0 && temp.substring(x - 1, x).equals("("));
			if (!startsLine && !(precededBySpace || precededByOpenParen)) continue;

			int y = entry.getKey().length();

			/* replace the key with the value */
			StringBuilder sb = new StringBuilder();

			sb.append(input.substring(0, x));
			sb.append(" ");
			sb.append(entry.getValue());
			sb.append(" ");
			sb.append(input.substring(x + y, input.length()));

			input = sb.toString();
		}

		input = StringUtils.trim(input);
		if (!original.equals(input)) {

			logger.debug("Replaced Abbreviations:\n\t%s\n\t%s", original, input);
			input = process(input);
		}

		return input;
	}
}
