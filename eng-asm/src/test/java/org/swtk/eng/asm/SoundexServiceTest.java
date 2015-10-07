package org.swtk.eng.asm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.swtk.eng.asm.svc.SoundexService;
import org.swtk.eng.asm.svc.impl.SoundexServiceImpl;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.TextUtils;

public final class SoundexServiceTest {

	/* TODO: I want a T620 (..., ..., ...) */
	
	/* 	current implementation shortcomings:
	 * 		
	 * 		1. this currently fails
	 * 		assertTrue(hasEqualEncoding("knight", "night", "N300")); 
	 * 		<http://creativyst.com/Doc/Articles/SoundEx1/SoundEx1.htm#SoundExConverter>
	 * 
	 */

	@Test
	public void difference() throws Throwable {
		assertEquals(4, getService().difference("John Checker", "Jon Cecker"));
		assertEquals(4, getService().difference("John Checker", "John Checker"));
		assertEquals(2, getService().difference("John Checker", "John Doe"));
		assertEquals(1, getService().difference("John Checker", "Barack Obama"));
		assertEquals(4, getService().difference("Checker", "Cecker"));
	}

	@Test
	public void encode() throws Throwable {
		assertTrue(hasEqualEncoding("Jon Cecker", "John Checker", "J522"));
		assertTrue(hasEqualEncoding("Jon Smythe", "John Smith", "J525"));
		assertTrue(hasEqualEncoding("Jemima", "Jemimah", "Jemina", "JHEMIMAH", "Jhemimhah", "J550"));
		assertTrue(hasEqualEncoding("Jeremiah", "Jeremy", "J650"));

		/* this is strange: these sound different to me ... */
		assertTrue(hasEqualEncoding("craig", "charice", "C620"));

		/* what are the other *620's? */
		assertEquals("C620", encode("Craig"));
		assertEquals("G620", encode("Greg"));

		assertEquals("T500", encode("Tim"));
		assertTrue(hasEqualEncoding("Trin", "Trinh", "Trim", "T650"));
	}

	private String encode(String value) throws Throwable {
		return getService().encode(value);
	}

	@Test(expected = BusinessException.class)
	public void encodeFailiures() throws Throwable {
		getService().encode("م");
	}

	@Test
	public void equals() throws Throwable {
		assertTrue(getService().isEqual("Jon Cecker", "John Checker"));
		assertFalse(getService().isEqual("Barack Obama", "John Checker"));
	}

	private SoundexService getService() {
		return new SoundexServiceImpl();
	}

	private boolean hasEqualEncoding(String... values) throws Throwable {
		Set<String> set = new HashSet<String>();

		for (String value : values) {
			if (4 == value.length() && TextUtils.isNumeric(value.substring(1, value.length()))) set.add(value);
			else set.add(encode(value));
		}

		return 1 == set.size();
	}
}
