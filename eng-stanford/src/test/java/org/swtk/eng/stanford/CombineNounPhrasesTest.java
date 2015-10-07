package org.swtk.eng.stanford;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.swtk.eng.stanford.base.CombineNounPhrasesTestBase;
import org.swtk.eng.stanford.dmo.CombineNounPhrases;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.SetUtils;

public final class CombineNounPhrasesTest extends CombineNounPhrasesTestBase {

	public static final String	INPUT_01	= "the quick brown fox jumped over the lazy dog.";

	public static LogManager	logger		= new LogManager(CombineNounPhrasesTest.class);

	@Test
	public void bugfix1() throws Throwable {
		String r1 = inferThenInject(SetUtils.toList("flange faces", "capillary attraction", "crevice breakers"), "flange faces can be machined with grooves which reduce capillary attraction and act as 'crevice breakers'.");
		assertTrue(contains(r1, "flange_faces"));
		assertTrue(contains(r1, "capillary_attraction"));
		assertTrue(contains(r1, "crevice_breakers"));
		String r2 = infer("flange faces can be machined with grooves which reduce capillary attraction and act as 'crevice breakers'.");
		assertTrue(contains(r2, "flange_faces"));
		assertTrue(contains(r2, "capillary_attraction"));
		assertTrue(contains(r2, "crevice_breakers"));
	}

	@Test
	public void combined1() throws Throwable {

		Collection<String> collection = SetUtils.toList("flange faces", "capillary attraction", "crevice breakers");
		Collection<String> combined = new CombineNounPhrases(INPUT_01, Codepage.WINDOWS_1252).infer().greedy().inject(collection).combine().combined();

		assertNotNull(combined);
		assertFalse(combined.isEmpty());

		assertTrue(SetUtils.memberOf("the lazy dog", combined));
		assertTrue(SetUtils.memberOf("the quick brown fox", combined));
	}

	@Test
	public void combined2() throws Throwable {
		Collection<String> collection = SetUtils.toList("general corrosion");

		String input = "Oxidation in elevated temperature gaseous and hydrocarbon liquid environments and general corrosion in aqueous environments are the two corrosion processes most commonly encountered in refining and chemical processing operations.";
		Collection<String> combined = new CombineNounPhrases(input, Codepage.WINDOWS_1252).inject(collection).greedy().inject(collection).combine().combined();

		assertNotNull(combined);
		assertFalse(combined.isEmpty());
		assertTrue(SetUtils.memberOf("general corrosion", combined));
	}

	@Test
	public void doNotCombine1() throws Throwable {

		Collection<String> list = SetUtils.toList("hydrogen reference", "electrode reaction", "reference electrode reaction", "standard reference electrode");

		String input = "The half cell potentials given in the table are measured with respect to the hydrogen reference electrode reaction.";
		Collection<String> entities = new CombineNounPhrases(input, Codepage.WINDOWS_1252).inject(list).combine().combined();
		assertFalse(SetUtils.memberOf("hydrogen reference electrode reaction", entities));
	}

	@Test
	public void greedy() throws Throwable {

		Collection<String> list = SetUtils.toList("hydrogen reference", "electrode reaction", "reference electrode", "reference electrode reaction", "hydrogen reference electrode");

		String input = "The half cell potentials given in the table are measured with respect to the hydrogen reference electrode reaction.";
		Collection<String> e1 = new CombineNounPhrases(input, Codepage.WINDOWS_1252).inject(list).combine().combined();
		assertFalse(SetUtils.memberOf("hydrogen reference electrode reaction", e1));

		Collection<String> e2 = new CombineNounPhrases(input, Codepage.WINDOWS_1252).inject(list).greedy().combine().combined();
		assertTrue(SetUtils.memberOf("hydrogen reference electrode reaction", e2));
	}

	@Test
	public void infer() throws Throwable {
		String result = infer(INPUT_01);
		assertTrue(contains(result, "the_quick_brown_fox"));
		assertTrue(contains(result, "the_lazy_dog"));
	}

	@Test
	public void inferThenInject() throws Throwable {
		String result = inferThenInject(SetUtils.toList("quick brown fox", "lazy dog"), INPUT_01);
		assertFalse(contains(result, "the_quick_brown_fox"));
		assertFalse(contains(result, "the_lazy_dog"));
	}

	@Test
	public void inject() throws Throwable {
		String result = inject(SetUtils.toList("quick brown fox", "lazy dog"), INPUT_01);
		assertTrue(contains(result, "quick_brown_fox"));
		assertFalse(contains(result, "the_quick_brown_fox"));
		assertTrue(contains(result, "lazy_dog"));
		assertFalse(contains(result, "the_lazy_dog"));
	}

	@Test
	public void injectThenInfer() throws Throwable {
		String result = injectThenInfer(SetUtils.toList("quick brown fox", "lazy dog"), INPUT_01);
		assertFalse(contains(result, "the_quick_brown_fox"));
		assertFalse(contains(result, "the_lazy_dog"));
	}

	@Test
	public void nulls() throws Throwable {
		handleNulls(null, Codepage.WINDOWS_1252);
		handleNulls("my input", null);
		handleNulls(null, null);
	}
}
