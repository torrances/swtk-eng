package org.swtk.eng.asm;

import static org.junit.Assert.assertEquals;

import org.apache.commons.codec.language.Caverphone1;
import org.apache.commons.codec.language.Caverphone2;
import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.commons.codec.language.Soundex;
import org.junit.Test;
import org.swtk.eng.asm.dto.adapter.EncodedResultAdapter;
import org.swtk.eng.asm.svc.impl.LanguageEncodingService;

public final class LanguageEncodingServiceTest {

	@Test
	public void test1() throws Throwable {
		
		/* perform a mongodb query for all values that have the same 
		 * 	metaphone
		 * 	cologne phonetic
		 * 	caverphone1,2
		 * 	metaphaone
		 * and then look ath their soundex values
		 * 
		 * there may be some interest here ...
		 */
		
		assertEquals("craig	KRK111	KRK1111111	474	KRK	KRK	C3904	C620", tsv("craig"));
		assertEquals("kreg	KRK111	KRK1111111	474	KRK	KRK	K3904	K620", tsv("kreg"));
		assertEquals("greg	KRK111	KRK1111111	474	KRK	KRK	G4904	G620", tsv("greg"));
	}

	@Test
	public void test2() throws Throwable {
		final String value = "Craig";

		assertEquals("KRK", new DoubleMetaphone().encode(value));
		assertEquals("474", new ColognePhonetic().encode(value));
		assertEquals("KRK1111111", new Caverphone2().encode(value));
		assertEquals("KRK111", new Caverphone1().encode(value));;
		assertEquals("KRK", new Metaphone().encode(value));
		assertEquals("C3904", new RefinedSoundex().encode(value));
		assertEquals("C620", new Soundex().encode(value));
	}

	private String tsv(String value) throws Throwable {
		return EncodedResultAdapter.toTsv(new LanguageEncodingService().encode(value));
	}
}
