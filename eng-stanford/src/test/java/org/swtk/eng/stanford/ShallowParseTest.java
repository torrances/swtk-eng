package org.swtk.eng.stanford;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.swtk.eng.stanford.dmo.CombineNounPhrases;
import org.swtk.eng.stanford.dmo.ShallowParse;
import org.swtk.eng.stanford.dto.TreeNode;
import org.swtk.eng.stanford.dto.adapter.TreeNodeAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.SetUtils;

public final class ShallowParseTest {

	private static final Codepage	CODEPAGE	= Codepage.WINDOWS_1252;
	private static final String		INPUT		= "The quick brown fox jumped over the lazy dog.";
	public static LogManager		logger		= new LogManager(ShallowParseTest.class);

	@Test
	public void run1() throws Throwable {

		/*
		 * 	Expected Output
		 * 
		 * 	text = The, pos = DT
		 * 	text = quick, pos = JJ
		 * 	text = brown, pos = JJ
		 * 	text = fox, pos = NN
		 * 	text = jumped, pos = VBD
		 * 	text = over, pos = IN	
		 * 	text = the, pos = DT	
		 * 	text = lazy, pos = JJ
		 * 	text = dog, pos = NN
		 * 	text = ., pos = #
		 */

		ShallowParse shallowParse = new ShallowParse(INPUT, CODEPAGE).parse();
		assertNotNull(shallowParse);

		TreeNode treenode = shallowParse.results();
		assertNotNull(treenode);
		logger.info("%s", TreeNodeAdapter.toString(treenode));
		assertEquals(10, treenode.getChildren().size());

		/* NOUNS */
		Collection<String> nouns = shallowParse.nouns();
		assertNotNull(nouns);
		assertFalse(nouns.isEmpty());
		assertEquals(2, nouns.size());

		String result1 = SetUtils.toString(nouns, ", ");
		assertEquals("dog, fox", result1);
		logger.info("Nouns:\n%s", result1);

		/* ADJ-NOUNS */
		Collection<String> adjNouns = shallowParse.adjNouns();
		assertNotNull(adjNouns);
		assertFalse(adjNouns.isEmpty());
		assertEquals(2, adjNouns.size());

		String result2 = SetUtils.toString(adjNouns, ", ");
		assertEquals("lazy dog, quick brown fox", result2);
		logger.info("Adj-Nouns:\n%s", result2);
	}

	@Test
	public void run2() throws Throwable {

		/*
		 * 	Expected Output
		 * 
		 * 	text = The_quick_brown_fox, pos = NNP
		 * 	text = jumped, pos = VBD
		 * 	text = over, pos = IN
		 * 	text = the_lazy_dog, pos = VBG				<== this is a problem in the CombineNounPhrases class
		 * 	text = ., pos = #
		 */
		String text = new CombineNounPhrases(INPUT, CODEPAGE).infer().combine(true, true).text();
		assertEquals("The_quick_brown_fox jumped over the_lazy_dog.", text);

		ShallowParse shallowParse = new ShallowParse(text, CODEPAGE).parse();
		assertNotNull(shallowParse);

		TreeNode treenode = shallowParse.results();
		assertNotNull(treenode);
		logger.info("Shallow Parse Output:\n%s", TreeNodeAdapter.toString(treenode));
		assertEquals(5, treenode.getChildren().size());

		Collection<String> nouns = shallowParse.nouns();
		assertNotNull(nouns);
		assertFalse(nouns.isEmpty());
		assertEquals(2, nouns.size());

		String result1 = SetUtils.toString(nouns, ", ");
		assertEquals("The_quick_brown_fox, the_lazy_dog", result1);
		logger.info("Nouns:\n%s", result1);
	}
}
