package org.swtk.eng.asm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.swtk.eng.asm.dto.JaccardSimilarityContract;
import org.swtk.eng.asm.dto.adapter.JaccardSimilarityContractAdapter;
import org.swtk.eng.asm.svc.JaccardSimilarityService;
import org.swtk.eng.asm.utils.ArrayUtils;

public final class JaccardSimilarityServiceTest {

	private File file(String path) throws Throwable {
		File file = new File(getClass().getClassLoader().getResource(path).getFile());
		assertTrue(file.exists());

		return file;
	}

	private double[][] run(File file) throws Throwable {

		JaccardSimilarityContract contract = JaccardSimilarityContractAdapter.transformOneDocumentPerLine(file, 6, false);
		assertNotNull(contract);

		double[][] rM = new JaccardSimilarityService().process(contract);
		assertNotNull(rM);

		System.err.println(file.getAbsolutePath() + "\n" + ArrayUtils.toString2(rM));
		return rM;
	}

	@Test
	public void test1() throws Throwable {
		run(file("file/bandaids1.txt"));
	}

	@Test
	public void test2() throws Throwable {
		run(file("file/bandaids2.txt"));
	}

	@Test
	public void test3() throws Throwable {
		double[][] rM = run(file("file/bandaids3.txt"));

		assertEquals((Double) 1d, (Double) rM[0][0]);
		assertEquals((Double) 1d, (Double) rM[1][1]);
		assertEquals((Double) 1d, (Double) rM[2][2]);
		assertEquals((Double) 1d, (Double) rM[3][3]);
		assertEquals((Double) 1d, (Double) rM[4][4]);
		assertEquals((Double) 1d, (Double) rM[5][5]);
	}
}
