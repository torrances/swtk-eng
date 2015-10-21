package org.swtk.eng.stanford.dto.adapter;

import org.swtk.eng.stanford.dto.StanfordDependencyTypeSer;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.type.StanfordDependencyType;

public final class StanfordDependencyTypeSerAdapter {

	public static StanfordDependencyTypeSer transform(StanfordDependencyType type) throws AdapterValidationException {
		StanfordDependencyTypeSer ser = new StanfordDependencyTypeSer();

		ser.setCollapsed(type.isCollapsed());
		ser.setDescription(type.getDescription());
		ser.setLongName(type.getLongName());
		ser.setShortName(type.getShortName());

		return ser;
	}
}
