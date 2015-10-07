package org.swtk.eng.preprocess.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.SetUtils;

public class RemoveLinesUsingBlacklist {

	public static LogManager	logger	= new LogManager(RemoveLinesUsingBlacklist.class);

	/**	
	 * @param blacklist		a collection of blacklisted lines 
	 * @param lines			the input collection of lines 		(may have blacklisted lines)
	 * @return				the output collection of lines 		(sans blacklisted lines)
	 * @throws BusinessException
	 */
	public static Collection<String> process(Collection<String> blacklist, Collection<String> lines) throws BusinessException {
		List<String> list = new ArrayList<String>();
		Iterator<String> iter = lines.iterator();

		/* for each line */
		while (iter.hasNext()) {
			String aLine = iter.next();

			/* skip blacklisted lines */
			if (SetUtils.memberOfCaseInsensitive(aLine, blacklist)) continue;

			list.add(aLine);
		}

		return list;
	}
}
