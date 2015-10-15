package org.swtk.eng.wordnet.synonyms.svc;

import org.apache.lucene.wordnet.Syns2Index;
import org.swtk.eng.wordnet.synonyms.util.Constants;

import com.trimc.blogger.commons.utils.file.FileUtils;

public class BuildIndex {

	public static void main(String... args) throws Throwable {

		if (Constants.WORDNET_SYNONYMS_DIR.exists()) FileUtils.delete(Constants.WORDNET_SYNONYMS_DIR);
		Syns2Index.main(new String[] { "wn_s.pl", Constants.WORDNET_SYNONYMS_DIR.getAbsolutePath() });
	}
}
