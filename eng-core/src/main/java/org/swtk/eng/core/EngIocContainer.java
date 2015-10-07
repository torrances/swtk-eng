package org.swtk.eng.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.swtk.common.core.CommonsIocContainer;

public class EngIocContainer {

	public static final String[]	CONTEXTS	= { "config/eng-ner-config.xml", "config/eng-grammar-config.xml", "config/eng-wordnet-config.xml" };

	public static ApplicationContext getContext() {
		return new ClassPathXmlApplicationContext(CONTEXTS, CommonsIocContainer.getContext());
	}
}
