package org.swtk.eng.ner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.common.dict.Dictionary;
import org.swtk.common.dict.DictionaryContext;
import org.swtk.common.dict.DictionaryName;
import org.swtk.eng.tokenizer.text.TextTokenizer;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.SetUtils;
import com.trimc.blogger.commons.utils.StringUtils;

public class FindCandidateNames {

	private Dictionary	stopWordsDictionary;

	public FindCandidateNames() {
		this.stopWordsDictionary = DictionaryContext.getInstance().getDictionary(DictionaryName.STOP_WORDS);
	}

	private boolean hasStopword(String name) {
		for (String token : name.split(" "))
			if (SetUtils.memberOf(token, stopWordsDictionary.entries())) return true;
		return false;
	}

	private Set<String> identify(String line) throws BusinessException {

		String[] tokens = new TextTokenizer(line).tokenize().array();
		if (tokens.length < 4) return new HashSet<String>();

		Set<String> candidates = new TreeSet<String>();
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].length() < 2) continue;

			String f = tokens[i].substring(0, 1);
			boolean isUppercase = StringUtils.isAllUpperCase(f);

			if (isUppercase) list.add(tokens[i]);
			if (2 == list.size()) candidates.add(toString(list));
			if (!isUppercase) list = new ArrayList<String>();
		}

		if (2 == list.size()) candidates.add(toString(list));
		return candidates;
	}

	private Set<String> postProcess(Set<String> s1) {
		Set<String> s2 = new TreeSet<String>();

		for (String name : s1) {
			if (hasStopword(name)) continue;

			s2.add(name);
		}

		return s2;
	}

	public Set<String> process(Collection<String> lines) throws BusinessException {
		Set<String> set = new TreeSet<String>();
		for (String line : lines)
			set.addAll(identify(line));

		return postProcess(set);
	}

	private String toString(List<String> list) {
		return SetUtils.toString(list, " ").trim().toLowerCase();
	}
}
