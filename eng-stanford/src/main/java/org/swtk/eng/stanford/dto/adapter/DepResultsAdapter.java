package org.swtk.eng.stanford.dto.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.eng.stanford.dto.DepNode;
import org.swtk.eng.stanford.dto.DepResult;
import org.swtk.eng.stanford.dto.DepResults;
import org.swtk.eng.stanford.dto.TreeNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.xml.DomIterator;
import com.trimc.blogger.commons.xml.DomUtils;

public final class DepResultsAdapter {

	/*<results>
		 <line><![CDATA[A_crystal_structure consists of atoms arranged in a_pattern that repeats periodically in a_three-dimensional_geometric_lattice.]]></line>
		 <node object="A_crystal_structure" object-sequence="1" predicate="nsubj" subject="consists" subject-sequence="2"/>
		 <node object="consists" object-sequence="2" predicate="root" subject="ROOT" subject-sequence="0"/>
		 <node object="atoms" object-sequence="4" predicate="prep_of" subject="consists" subject-sequence="2"/>
		 <node object="arranged" object-sequence="5" predicate="vmod" subject="atoms" subject-sequence="4"/>
		 <node object="a_pattern" object-sequence="7" predicate="prep_in" subject="arranged" subject-sequence="5"/>
		 <node object="a_pattern" object-sequence="7" predicate="nsubj" subject="repeats" subject-sequence="9"/>
		 <node object="repeats" object-sequence="9" predicate="rcmod" subject="a_pattern" subject-sequence="7"/>
		 <node object="periodically" object-sequence="10" predicate="advmod" subject="repeats" subject-sequence="9"/>
		 <node object="a_three-dimensional_geometric_lattice" object-sequence="12" predicate="prep_in" subject="repeats" subject-sequence="9"/>
	</results>*/

	public static LogManager	logger	= new LogManager(DepResultsAdapter.class);

	public static void enhance(DepResults results, TreeNode flattened) throws AdapterValidationException {
		Map<String, TreeNode> index = TreeNodeAdapter.index(flattened);

		for (DepResult result : results.getResults()) {

			String s = result.getSubject().getName();
			if (index.containsKey(s)) result.getSubject().setPos(index.get(s).getPos());

			String o = result.getObject().getName();
			if (index.containsKey(o)) result.getObject().setPos(index.get(o).getPos());
		}
	}

	public static Integer getMaxSequence(DepResults results) throws AdapterValidationException {
		int max = 0;

		for (DepResult result : results) {
			if (result.getObject().getSequence() > max) max = result.getObject().getSequence();
			if (result.getSubject().getSequence() > max) max = result.getSubject().getSequence();
		}

		return max;
	}

	public static Map<Integer, DepNode> hash(DepResults results) throws AdapterValidationException {
		Map<Integer, DepNode> map = new TreeMap<Integer, DepNode>();

		for (DepResult result : results) {
			map.put(result.getSubject().hashCode(), result.getSubject());
			map.put(result.getObject().hashCode(), result.getObject());
		}

		return map;
	}

	public static DepResults transform(String original, Document dom) throws AdapterValidationException {
		String modified = null;
		for (DomIterator iter = new DomIterator(dom); iter.hasNext();) {
			Element el = iter.next();
			if ("line".equals(el.getNodeName())) modified = DomUtils.getTextValue(el);
		}

		return transform(original, modified, dom);
	}

	public static DepResults transform(String original, String modified, Document dependencyDom) throws AdapterValidationException {
		DepResults results = new DepResults();

		Map<Integer, Map<Integer, DepResult>> map = new TreeMap<Integer, Map<Integer, DepResult>>();

		for (DomIterator iter = new DomIterator(dependencyDom); iter.hasNext();) {
			Element el = iter.next();

			if ("node".equals(el.getNodeName())) {
				DepResult result = DepResultAdapter.transform(el);

				Integer seq = result.getSubject().getSequence();
				Map<Integer, DepResult> innerMap = (map.containsKey(seq)) ? map.get(seq) : new TreeMap<Integer, DepResult>();
				innerMap.put(result.getObject().getSequence(), result);

				map.put(seq, innerMap);
			}
		}

		List<DepResult> master = new ArrayList<DepResult>();
		for (Map<Integer, DepResult> innerMap : map.values()) {
			master.addAll(innerMap.values());
		}

		results.setResults(master);

		results.setLine(LineAdapter.transform(original, modified));

		if (!results.hasResults()) throw new AdapterValidationException("Results Transformation Failiure");

		logger.debug("Transformed Dom to Dependency Results Object (results = %s)", results.getResults().size());
		return results;
	}
}
