package org.swtk.eng.asm.dto.adapter;

import org.swtk.eng.asm.dto.EncodedResult;

public final class EncodedResultAdapter {

	public static String toTsv(EncodedResult result) {
		StringBuilder sb = new StringBuilder();

		sb.append(result.getValue());
		sb.append("\t");

		sb.append(result.getCaverphone1());
		sb.append("\t");

		sb.append(result.getCaverphone2());
		sb.append("\t");

		sb.append(result.getColognePhonetic());
		sb.append("\t");

		sb.append(result.getDoubleMetaphone());
		sb.append("\t");

		sb.append(result.getMetaphone());
		sb.append("\t");

		sb.append(result.getRefinedSoundex());
		sb.append("\t");

		sb.append(result.getSoundex());
		return sb.toString();
	}
}
