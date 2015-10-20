package org.swtk.eng.stanford.base;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.swtk.eng.stanford.dmo.CombineNounPhrases;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.TextUtils;

public abstract class CombineNounPhrasesTestBase {
	
	public static LogManager	logger	= new LogManager(CombineNounPhrasesTestBase.class);

	protected boolean contains(String line, String value) {
		int x = line.indexOf(value);
		if (x == -1) return false;

		int y = x + value.length();
		if (y + 1 >= line.length()) return true;

		String next = line.substring(y, y + 1);
		return !TextUtils.isAlpha(next.toLowerCase());
	}

	protected void handleNulls(String input, Codepage codepage) throws Throwable {
		try {

			/* the code being tested must handle potential NPEs */
			new CombineNounPhrases(input, codepage).combine(true, true);

		} catch (NullPointerException e) {
			/* if this assertion fails it means a null-pointer-exception was thrown
			 * and the test case has failed */
			assertNull(e);

		} catch (AdapterValidationException e) {
			/* this is the correct exception: 
			 * the code should throw an adapter-validation-exception if any of the input is null */
			assertNotNull(e);
		}
	}

	protected String infer(String input) throws Throwable {
		String result = new CombineNounPhrases(input, Codepage.WINDOWS_1252).infer().greedy().combine(true, true).text();
		assertNotNull(result);
		assertNotEquals(result, input);
		logger.debug("Inferred Results:\n\t%s\n\t%s)", input, result);

		return result;
	}

	protected String inferThenInject(Collection<String> injected, String input) throws Throwable {
		String result = new CombineNounPhrases(input, Codepage.WINDOWS_1252).infer().inject(injected).combine(true, true).text();
		assertNotNull(result);
		assertNotEquals(result, input);
		logger.debug("Inferred then Injected Results:\n\t%s\n\t%s)", input, result);

		return result;
	}

	protected String inject(Collection<String> injected, String input) throws Throwable {
		String result = new CombineNounPhrases(input, Codepage.WINDOWS_1252).inject(injected).combine(true, true).text();
		assertNotNull(result);
		assertNotEquals(result, input);
		logger.debug("Injected Results:\n\t%s\n\t%s)", input, result);

		return result;
	}

	protected String injectThenInfer(Collection<String> injected, String input) throws Throwable {
		String result = new CombineNounPhrases(input, Codepage.WINDOWS_1252).inject(injected).infer().combine(true, true).text();
		assertNotNull(result);
		assertNotEquals(result, input);
		logger.debug("Injected then Inferred Results:\n\t%s\n\t%s)", input, result);

		return result;
	}
}
