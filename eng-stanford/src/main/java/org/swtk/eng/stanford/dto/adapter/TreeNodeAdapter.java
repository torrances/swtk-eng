package org.swtk.eng.stanford.dto.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.swtk.eng.stanford.dto.TreeNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.type.EngGrammar;
import com.trimc.blogger.commons.utils.string.StringUtils;
import com.trimc.blogger.commons.xml.DomUtils;

public final class TreeNodeAdapter {

	private static void flatten(List<TreeNode> list, TreeNode node) throws AdapterValidationException {
		for (TreeNode child : node.getChildren()) {
			if (!child.hasChildren()) list.add(child);
			else flatten(list, child);
		}
	}

	public static TreeNode flatten(TreeNode node) throws AdapterValidationException {
		TreeNode flat = new TreeNode();
		flat.setText(node.getText());

		List<TreeNode> list = new ArrayList<TreeNode>();
		flatten(list, node);
		flat.setChildren(list);

		return flat;
	}

	public static void index(Map<String, TreeNode> map, TreeNode node) {
		map.put(node.getText(), node);
		for (TreeNode child : node.getChildren()) {
			index(map, child);
		}
	}

	public static Map<String, TreeNode> index(TreeNode node) {
		Map<String, TreeNode> map = new HashMap<String, TreeNode>();

		for (TreeNode child : node.getChildren()) {
			index(map, child);
		}

		return map;
	}

	private static void toList(List<TreeNode> list, TreeNode treeNode) {
		if (treeNode.hasGrammarType()) list.add(treeNode);
		for (TreeNode child : treeNode.getChildren())
			toList(list, child);
	}

	public static List<TreeNode> toList(TreeNode root) {
		List<TreeNode> list = new ArrayList<TreeNode>();

		toList(list, root);

		return list;
	}

	private static void toString(StringBuilder sb, TreeNode treeNode) {

		if (null != treeNode.getPos()) {
			sb.append(String.format("text = %s", treeNode.getText()));
			sb.append(String.format(", pos = %s", treeNode.getPos().getTags()[0]));
			sb.append(System.lineSeparator());
		}

		for (TreeNode child : treeNode.getChildren())
			toString(sb, child);
	}

	public static String toString(TreeNode treeNode) {
		StringBuilder sb = new StringBuilder();

		toString(sb, treeNode);

		return sb.toString();
	}

	public static TreeNode transform(Document dom) throws AdapterValidationException {
		return transform(DomUtils.getFirstDescendantOrSelfElement(dom, "node"));
	}

	public static TreeNode transform(Element el) throws AdapterValidationException {

		TreeNode node = new TreeNode();

		String label = el.getAttribute("label");
		String text = el.getAttribute("text");

		node.setGrammarType(EngGrammar.find(label));
		node.setText(text);

		if (!node.hasGrammarType()) {
			throw new AdapterValidationException("Invalid (or Null) Part-of-Speech Tag (value = %s, text = %s)", label, text);
		} else if (!node.hasText()) {
			throw new AdapterValidationException("Invalid (or Null) Element Text (value = %s)", text);
		}

		node.setPos(EngGrammarSerAdapter.transform(node.getGrammarType()));

		for (Element child : DomUtils.getChildElements(el, "node")) {
			if (StringUtils.hasValue(child.getAttribute("text"))) node.addChildren(transform(child));
		}

		return node;
	}
}
