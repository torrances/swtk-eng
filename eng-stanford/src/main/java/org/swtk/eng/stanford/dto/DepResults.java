package org.swtk.eng.stanford.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.annotations.Expose;

public class DepResults implements Iterable<DepResult> {

	@Expose private Line			line;

	@Expose private List<DepResult>	results;

	public void addResults(DepResult... results) {
		for (DepResult result : results)
			getResults().add(result);
	}

	public Line getLine() {
		return line;
	}

	public List<DepResult> getResults() {
		if (null == results) setResults(new ArrayList<DepResult>());
		return results;
	}

	public boolean hasResults() {
		return !getResults().isEmpty();
	}

	@Override public Iterator<DepResult> iterator() {
		return getResults().iterator();
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public void setResults(List<DepResult> results) {
		this.results = results;
	}

	public int size() {
		return getResults().size();
	}
}
