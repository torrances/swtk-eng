package org.swtk.eng.asm;

import java.io.File;
import java.util.Collection;

import org.swtk.eng.asm.dto.CosineDocumentSimilarityResult;
import org.swtk.eng.asm.dto.adapter.CosineDocumentSimilarityResultAdapter;
import org.swtk.eng.asm.svc.impl.CosineDocumentSimilarityServiceImpl;

import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;

public final class CosineDocumentSimilarityService4FileDir {

	public static void main(String... args) throws Throwable {

		Collection<File> files = FileUtils.getDescendantFilesInFolder("C:/Backup/Data/ted/03.b", "*");
		CosineDocumentSimilarityResult result = new CosineDocumentSimilarityServiceImpl().process(files, Codepage.UTF_8);

		FileUtils.toFile(CosineDocumentSimilarityResultAdapter.toString(result), "c:/temp/cluster.dat", Codepage.UTF_8);
		FileUtils.toFile(GsonUtils.toJsonFormatted(result.getNodeLinks()), "c:/temp/cluster.json", Codepage.UTF_8);
	}
}
