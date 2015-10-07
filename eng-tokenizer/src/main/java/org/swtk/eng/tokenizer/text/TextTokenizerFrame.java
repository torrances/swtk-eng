package org.swtk.eng.tokenizer.text;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.trimc.blogger.commons.utils.TextUtils;

public abstract class TextTokenizerFrame extends TextTokenizerState {

	protected void addToken(String text) {
		if (!TextUtils.isEmpty(text)) getTokens().add(text);
	}

	protected void addToken(StringBuilder buffer) {
		if (null != buffer && 0 != buffer.length()) addToken(buffer.toString().trim());
	}

	public String[] array() {
		return (String[]) getTokens().toArray(new String[getTokens().size()]);
	}

	public Collection<String> collection() {
		return getTokens();
	}

	protected boolean isFwdSlashDelimited(String token) {
		return getFwdSlashDelimited().contains(token.toLowerCase());
	}

	public List<String> list() {
		return getTokens();
	}

	public Set<String> set() {
		Set<String> set = new TreeSet<String>();
		set.addAll(getTokens());
		return set;
	}
}
