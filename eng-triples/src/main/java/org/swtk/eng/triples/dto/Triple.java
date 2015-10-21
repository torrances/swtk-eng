package org.swtk.eng.triples.dto;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.eng.triples.PredicateType;

import com.trimc.blogger.commons.utils.SetUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class Triple {

	private String			object;

	private PredicateType	predicate;

	private String			subject;

	public String getObject() {
		return object;
	}

	public PredicateType getPredicate() {
		return predicate;
	}

	public String getSubject() {
		return subject;
	}

	@Override
	public int hashCode() {
		Set<String> set = new TreeSet<String>();
		if (hasSubject()) set.add(getSubject());
		if (hasObject()) set.add(getObject());
		return Objects.hash(SetUtils.toString(set, ", "));
	}

	public boolean hasObject() {
		return StringUtils.hasValue(getObject());
	}

	public boolean hasPredicate() {
		return null != getPredicate();
	}

	public boolean hasSubject() {
		return StringUtils.hasValue(getSubject());
	}

	public void setObject(String object) {
		this.object = object;
	}

	public void setPredicate(PredicateType predicate) {
		this.predicate = predicate;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
