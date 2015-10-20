package org.swtk.eng.stanford.dmo;

import java.util.Collection;

import org.swtk.eng.stanford.dmo.base.ShallowParseBase;
import org.swtk.eng.stanford.dto.TreeNode;
import org.swtk.eng.stanford.dto.adapter.TreeNodeAdapter;
import org.swtk.eng.stanford.util.functions.FindAdjNounsFunction;
import org.swtk.eng.stanford.util.functions.FindNounsFunction;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;

public class ShallowParse extends ShallowParseBase {

	public static LogManager	logger	= new LogManager(ShallowParse.class);

	public ShallowParse(String input, Codepage codepage) {
		setInput(input);
		setCodepage(codepage);
	}

	public Collection<String> adjNouns() throws BusinessException {
		return FindAdjNounsFunction.process(results());
	}

	public ShallowParse combine() throws BusinessException {
		setInput(new CombineNounPhrases(getInput(), getCodepage()).infer().combine(true, true).text());
		return this;
	}

	public String json() throws BusinessException {
		return GsonUtils.toJsonFormatted(results());
	}

	public Collection<String> nouns() throws BusinessException {
		return FindNounsFunction.process(results());
	}

	public ShallowParse parse() throws BusinessException {
		setDeepParse(new DeepParse(getInput(), getCodepage()).parse());
		return this;
	}

	public TreeNode results() throws BusinessException {
		return TreeNodeAdapter.flatten(getDeepParse().results());
	}
}
