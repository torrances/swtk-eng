package org.swtk.eng.preprocess;

import java.util.HashMap;
import java.util.Map;

public class PopulateAbbreviationsMap {

	public static Map<String, String>	map	= new HashMap<String, String>();

	static {

		/* note: avoid replacing a. b. c. etc ... these are often be used as list
		 * enumerations and rarely mean abbreviations unless specifically followed by a date
		 * eg. "d. 1987" or "b. 2001" */

		/* punctuation */
		map.put(".,", ".");

		/* a */
		map.put("a.b.", "bachelor of arts");
		map.put("a.d.", "ad");
		map.put("a.m.", "am");
		map.put("abbr.", "abbreviation");
		map.put("acad.", "academy");
		map.put("alt.", "altitude");
		map.put("assn.", "association");
		map.put("at. no.", "atomic number");
		map.put("at. wt.", "atomic weight");
		map.put("aug.", "august");
		map.put("ave.", "avenue");

		/* b */
		// map.put("b.", "born in");
		map.put("b.a.", "bachelor of arts");
		map.put("b.c.", "before christ");
		map.put("b.p.", "boiling point");
		map.put("b.s.", "bachelor of science");

		/* c */
		// map.put("c.", "circa");
		map.put("capt.", "captain");
		map.put("cent.", "century");
		map.put("cm", "centimeter");
		map.put("co.", "county");
		map.put("col.", "colonel");
		map.put("comdr.", "commander");
		map.put("corp.", "corporation");
		map.put("cpl.", "corporal");

		/* d */
		// map.put("d.", "died in");
		map.put("d.c.", "district of columbia");
		map.put("dec.", "december");
		map.put("dept.", "department");
		map.put("dist.", "district");
		map.put("div.", "division");
		map.put("dr.", "doctor");

		/* e */
		map.put("ed.", "edited");
		map.put("e.g.", "for example");
		map.put("etc.", "et-cetera");
		map.put("est.", "established");
		map.put("et al.", "et al");

		/* f */
		map.put("feb.", "february");
		map.put("fl.", "floruit");

		/* g */
		map.put("gal.", "gallons");
		map.put("gen.", "general");
		map.put("gov.", "governor");
		map.put("grad.", "graduated");

		/* h */
		map.put("hon.", "the honorable");

		/* i */
		map.put("i.e.", "for example");
		map.put("in.", "inch");
		map.put("inc.", "incorporated");
		map.put("inst.", "institution");

		/* j */
		map.put("jan.", "january");
		map.put("jr.", "junior");

		/* l */
		map.put("lat.", "latitude");
		map.put("lib.", "library");
		map.put("long.", "longitude");
		map.put("lt.", "lieutenant");
		map.put("ltd.", "limited");

		/* m */
		map.put("m.d.", "doctor");
		map.put("mr.", "mr");
		map.put("mrs.", "mrs");
		map.put("mt.", "mountain");
		map.put("mts.", "mountains");
		map.put("mus.", "museum");

		/* n */
		map.put("no.", "number");
		map.put("nov.", "november");

		/* o */
		map.put("oas", "organization of american states");
		map.put("oct.", "october");
		map.put("op.", "opus");

		/* p */
		map.put("p.m.", "pm");
		map.put("p.o.", "post office");
		map.put("pl.", "plural");
		map.put("pop.", "population");
		map.put("pseud.", "pseudonym");
		map.put("pt.", "part");
		map.put("pub.", "published");

		/* r */
		map.put("r.n.", "registered nurse");
		map.put("rev.", "revised");
		map.put("rev.", "the reverend");
		map.put("rd.", "road");

		/* s */
		map.put("sept.", "september");
		map.put("ser.", "series");
		map.put("sgt.", "sergeant");
		map.put("sr.", "senior");
		map.put("st.", "street");

		/* u */
		map.put("u.s.", "united states");
		map.put("uninc.", "unincorporated");
		map.put("univ.", "university");

		/* v */
		map.put("vol.", "volume");
		map.put("vs.", "versus");

		/* w */
		map.put("wt.", "weight");
	}
}
