package org.swtk.eng.preprocess.functions;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.swtk.eng.preprocess.PopulateCliches;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RemoveCliches {

	public static LogManager	logger	= new LogManager(RemoveCliches.class);

	public static Collection<String> process(Collection<String> lines) throws BusinessException {
		List<String> list = new ArrayList<String>();

		for (String line : lines)
			list.add(process(line));

		return list;
	}

	public static String process(String text) throws BusinessException {
		String original = text;

		for (String cliche : PopulateCliches.sorted())
			if (StringUtils.containsIgnoreCase(text, cliche)) {
				text = StringUtils.replaceAllMaintainCase(text, cliche);
			}

		text = text.trim();
		if (!original.equalsIgnoreCase(text)) logger.trace("Cliches Removed:\n\t%s\n\t%s", original, text);

		return text;
	}

	@Test
	public void test1() throws Throwable {
		String t1 = "the pitfall, however, is that most nitrogen blanket gas used offshore can contain up to 4% oxygen.";
		String t2 = RemoveCliches.process(t1);

		assertFalse(t2.contains("the pitfall"));
		assertFalse(t2.contains("the pitfall, however, is that"));
	}
}
