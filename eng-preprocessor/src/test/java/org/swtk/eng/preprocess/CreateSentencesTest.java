package org.swtk.eng.preprocess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.swtk.eng.preprocess.functions.CreateSentences;
import org.swtk.eng.preprocess.functions.ReplaceAbbreviations;

public class CreateSentencesTest {

	private Collection<String> createSentences(String... lines) throws Throwable {
		Collection<String> list = CreateSentences.process(list(lines));
		assertNotNull(list);

		return list;
	}

	private List<String> list(String... lines) throws Throwable {
		List<String> list = new ArrayList<String>();

		for (String line : lines)
			list.add(ReplaceAbbreviations.process(line));

		return list;
	}

	@Test
	public void run01() throws Throwable {

		List<String> list = new ArrayList<String>();
		list.add("the quick brown fox");
		list.add("jumped over lazy dog.  this is the first");
		list.add("sentence");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(2, lines.size());
	}

	@Test
	public void run02() throws Throwable {

		List<String> list = new ArrayList<String>();
		list.add(
				"the gulf of mexico region was originally formed by the break-up of pangea (). late triassic to early late jurassic rifting was characterized by complex systems of linear grabens and half grabens, which controlled sedimentation and initially led to the formation of predominantly non-marine red bed sequences and associated volcanics and, during the middle jurassic, to the accumulation of extensive salt deposits (figure 9b");
		list.add("and ). rifting continued to the extent that");
		list.add("sea-floor spreading began to create oceanic crust in the central part of the gulf of mexico during the latest callovian or early oxfordian (figure 9b and");
		list.add("). sea-floor spreading split the previously");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(4, lines.size());
	}

	// TODO: FIX THIS (8:16 PM 12/1/2014)
	// @Test
	public void run03() throws Throwable {
		List<String> list = new ArrayList<String>();
		list.add("successfully supported reservoir pressure in all");
		list.add("three fields.	Increasing watercut, which in an");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(2, lines.size());

		String[] arr = (String[]) lines.toArray(new String[lines.size()]);
		assertEquals("Increasing watercut, which in an .", arr[1]);
	}

	@Test
	public void run04() throws Throwable {
		List<String> list = new ArrayList<String>();
		list.add("� 2001 american association of petroleum geologists. all rights reserved. reprinted with permission of");
		list.add("american association of petroleum geologists.");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(3, lines.size());

		String[] arr = (String[]) lines.toArray(new String[lines.size()]);
		assertEquals("� 2001 american association of petroleum geologists.", arr[0]);
	}

	// TODO: FIX THIS (8:16 PM 12/1/2014)
	// @Test
	public void run05() throws Throwable {
		List<String> list = new ArrayList<String>();
		list.add("This well-laminated lithofacies comprises -80% of the cored interval in Al Noor-2 well (Amthor et al.");
		list.add(", 2005).");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(1, lines.size());

		String[] arr = (String[]) lines.toArray(new String[lines.size()]);
		assertEquals("This well-laminated lithofacies comprises -80% of the cored interval in Al Noor-2 well (Amthor et al., 2005)", arr[0]);
	}

	@Test
	public void run06() throws Throwable {
		Collection<String> lines = createSentences("The maximum shear stress theory of failure was originally proposed for use in the u.s.");
		assertEquals(1, lines.size());
	}

	@Test
	public void run07() throws Throwable {
		Collection<String> lines = createSentences("was the question mark resolved successfully?  I hope so!");
		assertEquals(2, lines.size());
	}

	@Test
	public void run08() throws Throwable {

		List<String> list = new ArrayList<String>();
		list.add("Did you try and take us back to our own time? DOCTOR: Well, I got you away from that other time, didn't I? IAN: That isn't what I asked you.");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(3, lines.size());
	}

	@Test
	public void run09() throws Throwable {

		List<String> list = new ArrayList<String>();
		list.add("Well, I suggest before we go outside and explore, let us clean ourselvesup. SUSAN: Oh, yes.");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(2, lines.size());
	}

	@Test
	public void run10() throws Throwable {

		List<String> list = new ArrayList<String>();
		list.add("DOCTOR: Now what does the radiation read, Susan? SUSAN: It's reading normal, Grandfather.");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(2, lines.size());
	}

	@Test
	public void run11() throws Throwable {

		List<String> list = new ArrayList<String>();
		list.add("What, then? BARBARA: Ian, I must talk to someone about this, but I don't want to get the girl into trouble.");

		Collection<String> lines = CreateSentences.process(list);
		assertNotNull(lines);
		assertEquals(2, lines.size());
	}
}
