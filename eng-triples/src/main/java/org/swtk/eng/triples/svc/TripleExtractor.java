package org.swtk.eng.triples.svc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.swtk.eng.stanford.dmo.CombineNounPhrases;
import org.swtk.eng.triples.PredicateType;
import org.swtk.eng.triples.dto.Triple;
import org.swtk.eng.triples.dto.adapter.TripleAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class TripleExtractor {

	public static LogManager	logger	= new LogManager(TripleExtractor.class);

	/*
	 * 	TODO: maybe this can be moved to the "eng" package
	 * 	the purpose of the topic extractor is, given a sentence, extract topics
	 * 	no, we need more of a triple extractor
	 * 
	 * 	q: "what is the influence of technology on religion"
	 * 	there really is no syntactic triple, but semantically we have
	 * 
	 * 	"technology" --> "religion" 	edge=influences
	 * 	of X on Y
	 * 
	 */

	private String preprocess(String input, Codepage codepage) throws BusinessException {
		String original = input;

		input = new CombineNounPhrases(input, codepage).infer().greedy().combine(true, true).text();

		if (!original.equals(input)) logger.debug("Preprocessed Input (original = %s, modified = %s)", original, input);
		return input;
	}
	
	/*
	 * 	NOTES
	 * 	There's a difference between sentences constructed in prose
	 * 	and sentences constructed for the purpose of runtime NLP
	 * 
	 * 	I don't think it's at all wrong to lightly impose structure on runtime NLP
	 * 		of <X> on <Y>
	 * 	should be a certain type of query, for example
	 * 
	 *  But during the Information Discovery phase, I should withdraw the predicate
	 *  	or at least give it less weight
	 *  	and perhaps this is where some ML can come into play
	 */

	public Triple process(String input, Codepage codepage) throws BusinessException {

		/* check for key elements before running reg-exp */
		boolean containsOF = input.contains(" of ");
		boolean containsON = input.contains(" on ");

		/* this is more efficient */
		if (!(containsOF && containsON)) {
			logger.debug("Key Elements Not Found (pattern = influencer, input = %s)", input);
			return null;
		}

		input = preprocess(input, codepage);

		/* 	FALSE POSITIVES:
		 * 	1. "of the fly on the left"
		 * 	2. "of the fly on the right"
		 * 	3. "the second biggest exporter of cotton on Earth"
		 * 	4. "phenomenon of management of the commons on a local scale"
		 * 	5. "of <X> on coming up with ..."
		 */

		Pattern p = Pattern.compile("of [a-z\\_]+ on [a-z\\_]+", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(input);

		if (!m.find()) {
			logger.debug("Pattern Not Matched (pattern = influencer, input = %s)", input);
			return null;
		}

		String result = m.group(0);

		/* extract subject */
		String subject = StringUtils.substringAfter(StringUtils.substringBefore(result, " on "), "of ").trim();

		/* extract object */
		String object = StringUtils.substringAfter(result, " on ").trim();
		if ("the".equals(object)) object = StringUtils.substringAfter(input, " on the ").trim();

		return TripleAdapter.transform(subject, PredicateType.INFLUENCES, object);
	}
}
