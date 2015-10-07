package org.swtk.eng.stanford.dto.adapter;

import org.swtk.eng.stanford.dto.Line;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class LineAdapter {

	public static Line transform(String original, String modified) throws AdapterValidationException {
		Line line = new Line();

		line.setOriginal(original);
		line.setModified(modified);

		return line;
	}
}
