package org.swtk.eng.stanford.dmo.base.state;

import java.util.Collection;

import com.trimc.blogger.commons.exception.BusinessException;

import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;

public abstract class DependencyParseInternalsState {

	private GrammaticalStructure		grammaticalStructure;

	private String						input;

	private Tree						tree;

	private Collection<TypedDependency>	typedDependencies;

	public GrammaticalStructure getGrammaticalStructure() throws BusinessException {
		return grammaticalStructure;
	}

	protected String getInput() {
		return input;
	}

	protected Tree getTree() throws BusinessException {
		return tree;
	}

	public Collection<TypedDependency> getTypedDependencies() throws BusinessException {
		return typedDependencies;
	}

	protected void setGrammaticalStructure(GrammaticalStructure grammaticalStructure) {
		this.grammaticalStructure = grammaticalStructure;
	}

	public void setInput(String input) {
		this.input = input;
	}

	protected void setTree(Tree tree) {
		this.tree = tree;
	}

	protected void setTypedDependencies(Collection<TypedDependency> typedDependencies) {
		this.typedDependencies = typedDependencies;
	}
}
