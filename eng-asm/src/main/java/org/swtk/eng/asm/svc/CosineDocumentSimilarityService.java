package org.swtk.eng.asm.svc;

import java.io.File;
import java.util.Collection;

import org.swtk.eng.asm.dto.CosineDocumentSimilarityResult;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;

public interface CosineDocumentSimilarityService {

	CosineDocumentSimilarityResult process(Collection<File> files, Codepage codepage) throws BusinessException;
}
