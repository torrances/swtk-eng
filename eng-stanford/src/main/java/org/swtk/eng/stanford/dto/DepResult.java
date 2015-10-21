package org.swtk.eng.stanford.dto;

import com.google.gson.annotations.Expose;
import com.trimc.blogger.commons.type.StanfordDependencyType;

/**
 * 	Java Object for Stanford Dependency Parse
 * 
 * 	Sample XML Representation:
 * 	<node object="the_quick_brown_fox" object-sequence="1" predicate="nsubj" subject="jumped" subject-sequence="2"/>
 *
 */
public class DepResult {

	@Expose private DepNode						object;

	@Expose private StanfordDependencyTypeSer	predicate;

	private StanfordDependencyType				predicateType;

	@Expose private DepNode						subject;

	public DepNode getObject() {
		return object;
	}

	public StanfordDependencyTypeSer getPredicate() {
		return predicate;
	}

	public StanfordDependencyType getPredicateType() {
		return predicateType;
	}

	public DepNode getSubject() {
		return subject;
	}

	public void setObject(DepNode object) {
		this.object = object;
	}

	public void setPredicate(StanfordDependencyTypeSer predicate) {
		this.predicate = predicate;
	}

	public void setPredicateType(StanfordDependencyType predicateType) {
		this.predicateType = predicateType;
	}

	public void setSubject(DepNode subject) {
		this.subject = subject;
	}
}
