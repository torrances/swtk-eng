package org.swtk.eng.stanford.dmo.base.frame;

import org.swtk.eng.stanford.dmo.base.state.DependencyParseInternalsState;

public abstract class DependencyParseInternalsFrame extends DependencyParseInternalsState {

	public String text() {
		return getInput();
	}
}
