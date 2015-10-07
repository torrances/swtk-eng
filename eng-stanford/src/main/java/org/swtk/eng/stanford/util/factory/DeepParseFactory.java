package org.swtk.eng.stanford.util.factory;

import java.io.StringReader;

import com.trimc.blogger.commons.LogManager;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;

public final class DeepParseFactory {

	private static DeepParseFactory	instance;

	public static LogManager		logger	= new LogManager(DeepParseFactory.class);

	public static DeepParseFactory getInstance() {
		if (null == instance) {
			instance = new DeepParseFactory();
		}

		return instance;
	}

	private LexicalizedParser	lp	= null;

	private DeepParseFactory() {}

	public Tree deepParse(String input) {
		return parse(getLexicalizedParser(), input);
	}

	private LexicalizedParser getLexicalizedParser() {
		if (null == lp) {
			logger.debug("Initializing Lexical Parser Model ... ");
			this.lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		}

		return lp;
	}

	private Tree parse(LexicalizedParser lp, String input) {
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		Tokenizer<CoreLabel> tok = tokenizerFactory.getTokenizer(new StringReader(input));
		return lp.apply(tok.tokenize());
	}
}
