package org.swtk.eng.stanford.dto.adapter;

import java.util.Collection;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.string.StringUtils;
import com.trimc.blogger.commons.xml.DomUtils;
import com.trimc.blogger.commons.xml.XmlFormatter;

import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;

public class TypeDependencyAdapter {

	public static final String	DEPENDENCY_XML_START	= "<node subject=\"%s\" subject-sequence=\"%s\" object=\"%s\" object-sequence=\"%s\" predicate=\"%s\" />";

	private static Integer getSequence(String value) {
		value = value.replaceAll("'", "");

		return Integer.parseInt(StringUtils.substringAfterLast(value, "-").trim());
	}

	private static void getText(StringBuilder sb, Tree parse) {
		if (0 == parse.children().length) sb.append(parse.label() + " ");
		for (Tree child : parse.getChildrenAsList()) {
			getText(sb, child);
		}
	}

	private static String getText(Tree parse) {
		StringBuilder sb = new StringBuilder();

		getText(sb, parse);

		return sb.toString().trim();
	}

	public static String toXmlString(String input, Codepage codepage, Collection<TypedDependency> typedDependencies, boolean showHeader) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		sb.append("<results>");

		if (StringUtils.hasValue(input)) {
			sb.append("<line> <![CDATA[");
			sb.append(input);
			sb.append("]]></line>");
		}

		for (TypedDependency typedDependency : typedDependencies) {

			String line = typedDependency.toString(false);

			String predicate = StringUtils.substringBefore(line, "(").trim();
			String subject = StringUtils.substringBefore(StringUtils.substringAfter(line, "("), ",").trim();
			String object = StringUtils.substringBefore(StringUtils.substringAfter(line, ","), ")").trim();

			Integer subjectSequence = getSequence(subject);
			Integer objectSequence = getSequence(object);

			subject = DomUtils.cleanse(StringUtils.substringBeforeLast(subject, "-").trim());
			object = DomUtils.cleanse(StringUtils.substringBeforeLast(object, "-").trim());

			sb.append(String.format(DEPENDENCY_XML_START, subject, subjectSequence, object, objectSequence, predicate));
			sb.append(System.lineSeparator());
		}

		sb.append("</results>");

		String output = XmlFormatter.format(sb, codepage);
		return (showHeader) ? output : output.substring(39);
	}

	public static void toXmlString(StringBuilder sb, Tree parse) {

		sb.append("<node text=\"" + getText(parse) + "\" label=\"" + parse.label() + "\">");

		for (Tree child : parse.getChildrenAsList()) {
			if (0 != child.children().length) toXmlString(sb, child);
		}

		sb.append("</node>");
	}

	public static String toXmlString(Tree parse) {
		StringBuilder sb = new StringBuilder();
		sb.append("<results timestamp=\"" + System.currentTimeMillis() + "\">");

		for (Tree child : parse.getChildrenAsList())
			toXmlString(sb, child);

		sb.append("</results>");

		return sb.toString();
	}
}
