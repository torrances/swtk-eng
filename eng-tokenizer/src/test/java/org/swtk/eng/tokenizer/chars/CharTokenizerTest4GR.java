package org.swtk.eng.tokenizer.chars;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.swtk.common.framework.type.LanguageTag;

import com.trimc.blogger.commons.type.Codepage;

public class CharTokenizerTest4GR {

	private static final Integer[]	SKIP_CODES	= new Integer[] {

												/* blanks */
												10, 13, 32,

												/* numbers */
												48, 49, 50, 51, 52, 53, 54, 55, 56, 57,

												/* punctuation */
												33, 38, 39, 40, 41, 42, 44, 46, 183, 58, 59, 91, 92, 93, 8212, 8217 };

	public static final String		TEST_OUT	= "../org.swtk.eng.tokenizer/Library/OUTFILE-%s.txt";

	private File getFile(String name) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(name).getFile());
		assertNotNull(file);
		assertTrue(file.exists());

		return file;
	}

	private void deleteOutFile(String outPath) throws Throwable {
		new File(outPath).delete();
		assertFalse(new File(outPath).exists());
	}

	private CharTokenizerContract getContract(int size) throws Throwable {
		CharTokenizerContract contract = new CharTokenizerContract();

		if (1 == size) contract.setIncludeIntCodes(true);

		contract.setSize(size);
		contract.setToLowerCase(true);
		contract.setMinimumThreshold(15);
		contract.setCodepage(Codepage.UTF_8);
		contract.setLanguageTag(LanguageTag.GREEK);
		contract.setSkipCodes(SKIP_CODES);
		contract.setFiles(new File[] { new File(getInputPath()) });

		return contract;
	}

	private String getInputPath() throws Throwable {
		File file = getFile("file/GR001b.txt");
		return file.getAbsolutePath();
	}

	@Test
	public void n1() throws Throwable {
		run(getContract(1), "RUN-01");
	}

	@Test
	public void n2() throws Throwable {
		run(getContract(2), "RUN-02");
	}

	@Test
	public void n3() throws Throwable {
		run(getContract(3), "RUN-03");
	}

	private void run(CharTokenizerContract contract, String outFileName) throws Throwable {
		CharTokenizerResult result = new CharTokenizer(contract).process().getResult();
		assertNotNull(result);
		assertNotNull(result.getContract());
		assertNotNull(result.getFrequency());
		assertFalse(result.getFrequency().isEmpty());

		String outPath = String.format(TEST_OUT, outFileName);
		deleteOutFile(outPath);
		writeOutFile(result, outPath);
	}

	@Test
	public void startsWithPrefixOf3() throws Throwable {
		CharTokenizerContract contract = getContract(3);
		contract.setStartsWithOnly(true);
		run(contract, "RUN-03-PREFIX");
	}

	private void writeOutFile(CharTokenizerResult result, String outPath) throws Throwable {
		CharTokenizerResultAdapter.toFile(new File(outPath), result);
		assertTrue(new File(outPath).exists());
	}
}
