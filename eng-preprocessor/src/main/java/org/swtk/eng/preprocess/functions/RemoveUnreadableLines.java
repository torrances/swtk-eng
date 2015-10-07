package org.swtk.eng.preprocess.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public final class RemoveUnreadableLines {

	public static LogManager	logger	= new LogManager(RemoveUnreadableLines.class);

	public static Collection<String> process(Collection<String> lines) throws BusinessException {
		List<String> list = new ArrayList<String>();

		for (String line : lines) {
			// TODO: add this back
			// if (IsHumanReadable.process(line, 0.15D)) list.add(line);
			list.add(line);
		}

		return list;
	}
}
