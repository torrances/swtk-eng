package org.swtk.eng.rollinghash.svc;

import java.util.HashSet;
import java.util.Set;

import org.swtk.eng.rollinghash.dmo.CyclicHash;

public class CyclicHashService {

	public Set<Integer> process(String input, int k) {
		Set<Integer> set = new HashSet<Integer>();

		CyclicHash ch = new CyclicHash(k);

		int i = 0;
		for (; i < k - 1; ++i)
			ch.eat(input.charAt(i));

		int rollinghash = ch.eat(input.charAt(i)); // the first or last 32-(n-1) bits are 
		set.add(rollinghash);

		for (; i < input.length(); ++i) {
			if ((i - k) < 0) continue;
			rollinghash = ch.update(input.charAt(i - k), input.charAt(i));
			set.add(rollinghash);
		}

		return set;
	}
}
