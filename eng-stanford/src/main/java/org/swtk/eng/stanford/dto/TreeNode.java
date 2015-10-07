package org.swtk.eng.stanford.dto;

import java.util.ArrayList;
import java.util.List;

import org.swtk.eng.grammar.types.EngGrammar;

import com.google.gson.annotations.Expose;
import com.trimc.blogger.commons.utils.StringUtils;

public class TreeNode {

	@Expose
	private List<TreeNode>	children;

	private EngGrammar		grammarType;

	@Expose
	private EngGrammarSer	pos;

	@Expose
	private String			text;

	public void addChildren(TreeNode... children) {
		for (TreeNode child : children)
			getChildren().add(child);
	}

	public List<TreeNode> getChildren() {
		if (null == children) setChildren(new ArrayList<TreeNode>());
		return children;
	}

	public EngGrammar getGrammarType() {
		return grammarType;
	}

	public EngGrammarSer getPos() {
		return pos;
	}

	public String getText() {
		return text;
	}

	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}

	public boolean hasGrammarType() {
		return null != getGrammarType();
	}

	public boolean hasText() {
		return StringUtils.hasValue(text);
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public void setGrammarType(EngGrammar grammarType) {
		this.grammarType = grammarType;
	}

	public void setPos(EngGrammarSer pos) {
		this.pos = pos;
	}

	public void setText(String text) {
		this.text = text;
	}
}
