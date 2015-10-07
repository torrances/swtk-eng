package org.swtk.eng.preprocess.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class RemoveLinesByLength {

	public static LogManager	logger	= new LogManager(RemoveLinesByLength.class);

	/**	
	 * @param lines			the input collection of lines 
	 * @param length		all lines must exceed this threshold or will be discarded
	 * @return				the output collection of lines
	 * @throws BusinessException
	 */
	public static Collection<String> process(Collection<String> lines, final int length) throws BusinessException {
		List<String> list = new ArrayList<String>();

		for (String line : lines) {
			String temp = line.trim();
			if (temp.length() >= length) list.add(line);
		}

		return list;
	}
}
