package org.swtk.eng.stanford.util.functions;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.eng.grammar.types.EngGrammarUpperType;
import org.swtk.eng.stanford.dto.TreeNode;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class FindNounsFunction {

	public static LogManager	logger	= new LogManager(FindNounsFunction.class);

	private static void process(Set<String> set, TreeNode treeNode) throws BusinessException {

		/* sometimes the parser incorrectly tags strings_like_this */
		if (treeNode.getText().contains("_")) {
			set.add(treeNode.getText());

		} else

		/* otherwise, just get the nouns */
		if (EngGrammarUpperType.NOUN == treeNode.getGrammarType().getUpperType()) {
			set.add(treeNode.getText());
		}

		for (TreeNode child : treeNode.getChildren())
			process(set, child);
	}

	public static Collection<String> process(TreeNode root) throws BusinessException {
		Set<String> set = new TreeSet<String>();

		for (TreeNode treeNode : root.getChildren())
			process(set, treeNode);

		logger.debug("Nouns Found (total = %s)", set.size());
		return set;
	}
}
