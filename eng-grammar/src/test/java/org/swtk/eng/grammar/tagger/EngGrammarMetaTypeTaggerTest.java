package org.swtk.eng.grammar.tagger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.swtk.eng.core.EngIocContainer;

import com.trimc.blogger.commons.type.EngGrammarMetaType;

public class EngGrammarMetaTypeTaggerTest {

	@Test
	public void number() throws Throwable {
		assertEquals(EngGrammarMetaType.NUMBER, tagger().parse("12345"));
		assertEquals(EngGrammarMetaType.NUMBER, tagger().parse("quick brown 123"));
		assertEquals(EngGrammarMetaType.NUMBER, tagger().parse("hello123"));
		
		assertNotEquals(EngGrammarMetaType.NUMBER, tagger().parse("abc"));
		assertNotEquals(EngGrammarMetaType.NUMBER, tagger().parse("!@$#"));
		assertNotEquals(EngGrammarMetaType.NUMBER, tagger().parse("the quick brown fox jumped over the lazy dog."));
	}

	@Test
	public void stopWords() throws Throwable {
		assertEquals(EngGrammarMetaType.CLOSED_WORD, tagger().parse("it"));
		assertEquals(EngGrammarMetaType.CLOSED_WORD, tagger().parse("the quick brown"));
		assertEquals(EngGrammarMetaType.CLOSED_WORD, tagger().parse("these words"));
		
		assertNotEquals(EngGrammarMetaType.CLOSED_WORD, tagger().parse("quick brown fox"));
		assertNotEquals(EngGrammarMetaType.CLOSED_WORD, tagger().parse("!@$#"));
		assertNotEquals(EngGrammarMetaType.CLOSED_WORD, tagger().parse("here123"));
	}

	private EngGrammarMetaTypeTagger tagger() throws Throwable {
		EngGrammarMetaTypeTagger tagger = EngIocContainer.getContext().getBean(EngGrammarMetaTypeTagger.class);
		assertNotNull(tagger);

		return tagger;
	}
}
