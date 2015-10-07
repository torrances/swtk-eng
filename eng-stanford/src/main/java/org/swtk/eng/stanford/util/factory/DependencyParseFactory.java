package org.swtk.eng.stanford.util.factory;

import java.io.StringReader;
import java.util.Collection;
import java.util.List;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
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

public final class DependencyParseFactory {

	private static DependencyParseFactory	instance;

	public static LogManager				logger	= new LogManager(DependencyParseFactory.class);

	public static DependencyParseFactory getInstance() {
		if (null == instance) {
			instance = new DependencyParseFactory();
		}

		return instance;
	}

	private LexicalizedParser	lp	= null;

	private DependencyParseFactory() {}

	public GrammaticalStructure getGrammaticalStructure(String input) {
		return getGrammaticalStructure(parse(input));
	}

	public GrammaticalStructure getGrammaticalStructure(Tree parse) {
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();

		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		return gsf.newGrammaticalStructure(parse);
	}

	public LexicalizedParser getLexicalizedParser() {
		if (null == lp) {
			logger.debug("Initializing Lexical Parser Model ... ");
			this.lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		}

		return lp;
	}

	public Tree parse(String input) {
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		Tokenizer<CoreLabel> tok = tokenizerFactory.getTokenizer(new StringReader(input));
		List<CoreLabel> rawWords2 = tok.tokenize();

		return getLexicalizedParser().apply(rawWords2);
	}

	public Collection<TypedDependency> typedDependencies(String input) throws BusinessException {
		return getGrammaticalStructure(input).typedDependencies();
	}

	public Collection<TypedDependency> typedDependenciesCCprocessed(String input) throws BusinessException {
		return getGrammaticalStructure(input).typedDependenciesCCprocessed();
	}

	public Collection<TypedDependency> typedDependenciesCollapsed(String input) throws BusinessException {
		return getGrammaticalStructure(input).typedDependenciesCollapsed();
	}

	public Collection<TypedDependency> typedDependenciesCollapsedTree(String input) throws BusinessException {
		return getGrammaticalStructure(input).typedDependenciesCollapsedTree();
	}
}
