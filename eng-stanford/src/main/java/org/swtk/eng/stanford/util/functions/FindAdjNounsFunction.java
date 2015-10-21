package org.swtk.eng.stanford.util.functions;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.eng.stanford.dto.TreeNode;
import org.swtk.eng.stanford.dto.adapter.TreeNodeAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.EngGrammarUpperType;

public class FindAdjNounsFunction {

	public static LogManager	logger	= new LogManager(FindAdjNounsFunction.class);

	public static Collection<String> process(TreeNode root) throws BusinessException {
		Set<String> set = new TreeSet<String>();

		List<TreeNode> list = TreeNodeAdapter.toList(root);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			TreeNode current = list.get(i);

			boolean isAdj = EngGrammarUpperType.ADJECTIVE == current.getGrammarType().getUpperType();
			boolean isNoun = EngGrammarUpperType.NOUN == current.getGrammarType().getUpperType();
			boolean isNp = current.getText().contains("_");

			if (isAdj) {
				sb.append(current.getText() + " ");
			} else if (isNoun || isNp) {
				sb.append(current.getText());
				set.add(sb.toString().trim());
				sb = new StringBuilder();
			}
		}

		logger.debug("Adj-Nouns Found (total = %s)", set.size());
		return set;
	}
}
