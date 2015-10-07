package org.swtk.eng.stanford.dmo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.swtk.eng.stanford.dmo.base.frame.DependencyParseInternalsFrame;
import org.swtk.eng.stanford.util.factory.DependencyParseFactory;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

public class DependencyParseInternals extends DependencyParseInternalsFrame {

	public static LogManager	logger	= new LogManager(DependencyParseInternals.class);

	public DependencyParseInternals(String input) {
		setInput(input);
	}

	@Override
	public GrammaticalStructure getGrammaticalStructure() throws BusinessException {
		if (null == super.getGrammaticalStructure()) super.setGrammaticalStructure(getGrammaticalStructure(getTree()));
		return super.getGrammaticalStructure();
	}

	private GrammaticalStructure getGrammaticalStructure(Tree parse) {
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();

		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		return gsf.newGrammaticalStructure(parse);
	}

	@Override
	public Tree getTree() throws BusinessException {
		if (null == super.getTree()) super.setTree(parse(getInput()));
		return super.getTree();
	}

	@Override
	public Collection<TypedDependency> getTypedDependencies() throws BusinessException {
		if (null == super.getTypedDependencies()) super.setTypedDependencies(new ArrayList<TypedDependency>());
		return super.getTypedDependencies();
	}

	private Tree parse(String input) {
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		Tokenizer<CoreLabel> tok = tokenizerFactory.getTokenizer(new StringReader(input));
		List<CoreLabel> rawWords2 = tok.tokenize();

		return DependencyParseFactory.getInstance().getLexicalizedParser().apply(rawWords2);
	}

	public Collection<TypedDependency> typedDependencies() throws BusinessException {
		setTypedDependencies(getGrammaticalStructure().typedDependencies());
		logger.debug("Created Typed Dependencies (count = %s)", getTypedDependencies().size());

		return getTypedDependencies();
	}

	public Collection<TypedDependency> typedDependenciesCCprocessed() throws BusinessException {
		setTypedDependencies(getGrammaticalStructure().typedDependenciesCCprocessed());
		logger.debug("Created Typed Dependencies Processed (count = %s)", getTypedDependencies().size());

		return getTypedDependencies();
	}
}
