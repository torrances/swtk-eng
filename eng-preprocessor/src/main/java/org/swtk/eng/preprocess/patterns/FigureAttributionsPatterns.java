package org.swtk.eng.preprocess.patterns;

import java.util.regex.Pattern;

public class FigureAttributionsPatterns {

	/* Figure 16 
	 * (Fig. 12A)
	 * (Fig. 10F)
	 * (Figs. 2B and 3)
	 * Fig. 16 
	 * Figs. 6 */

	public static Pattern	FIG_01	= Pattern.compile("\\(figure [0-9]+[a-z]*\\)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	public static Pattern[] getPatterns() {
		return new Pattern[] { FIG_01 };
	}
}
