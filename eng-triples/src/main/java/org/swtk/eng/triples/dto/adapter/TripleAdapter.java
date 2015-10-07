package org.swtk.eng.triples.dto.adapter;

import org.swtk.eng.triples.PredicateType;
import org.swtk.eng.triples.dto.Triple;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.StringUtils;

public final class TripleAdapter {

	public static LogManager	logger	= new LogManager(TripleAdapter.class);

	private static String cleanse(String input) {
		String original = input;

		if (input.startsWith("the_")) input = input.substring(4, input.length());
		input = input.replaceAll("_", " ");
		if (StringUtils.endsWith(input, "?")) input = input.substring(0, input.length() - 1);

		if (!original.equals(input)) logger.debug("Cleansed Node (original = %s, modified = %s)", original, input);
		return input.trim();
	}

	public static String toLogString(Triple triple) {
		StringBuilder sb = new StringBuilder();

		sb.append(triple.getSubject() + ", " + triple.getPredicate() + ", " + triple.getObject());

		return sb.toString();
	}

	public static Triple transform(String subject, PredicateType predicate, String object) throws AdapterValidationException {
		Triple triple = new Triple();

		triple.setSubject(cleanse(subject));
		triple.setPredicate(predicate);
		triple.setObject(cleanse(object));

		logger.info("Created Triple (s = %s, p = %s, o = %s)", subject, predicate, object);
		return triple;
	}
}
