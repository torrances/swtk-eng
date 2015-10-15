package org.swtk.eng.triples;

import java.io.File;

import org.swtk.eng.triples.dto.Triple;
import org.swtk.eng.triples.dto.adapter.TripleAdapter;
import org.swtk.eng.triples.svc.TripleExtractor;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;

public final class TripleExtractor4TED {

	public static LogManager	logger	= new LogManager(TripleExtractor4TED.class);

	public static void main(String... args) throws Throwable {

		for (File file : FileUtils.getDescendantFilesInFolder("c:/Backup/Data/ted/03", "*")) {
			for (String line : FileUtils.toList(file, Codepage.UTF_8)) {

				Triple triple = new TripleExtractor().process(line, Codepage.UTF_8);
				if (null == triple) continue;

				logger.info("line = %s, triple = %s", line, TripleAdapter.toLogString(triple));
			}
		}
	}
}
