package org.swtk.eng.asm.dto.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.swtk.common.ds.d3js.dto.LinkStructure;
import org.swtk.common.ds.d3js.dto.NodeLinkStructure;
import org.swtk.common.ds.d3js.dto.NodeStructure;
import org.swtk.common.ds.d3js.dto.adapter.LinkStructureAdapter;
import org.swtk.common.ds.d3js.dto.adapter.NodeLinkStructureAdapter;
import org.swtk.common.ds.d3js.dto.adapter.NodeStructureAdapter;
import org.swtk.eng.asm.dto.CosineDocumentSimilarityResult;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.StringUtils;

public final class CosineDocumentSimilarityResultAdapter {

	public static String toString(CosineDocumentSimilarityResult cosineDocumentSimilarityResult) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < cosineDocumentSimilarityResult.getMatrix().length; i++) {
			for (int j = 0; j < cosineDocumentSimilarityResult.getMatrix()[i].length; j++)
				sb.append(StringUtils.format(cosineDocumentSimilarityResult.getMatrix()[i][j], "#.##") + "\t");
			sb.append("\n");
		}

		return sb.toString();
	}

	public static CosineDocumentSimilarityResult transform(double[][] matrix, NodeLinkStructure nodeLinks) throws AdapterValidationException {
		CosineDocumentSimilarityResult cosineDocumentSimilarityResult = new CosineDocumentSimilarityResult();

		cosineDocumentSimilarityResult.setMatrix(matrix);
		cosineDocumentSimilarityResult.setNodeLinks(nodeLinks);

		return cosineDocumentSimilarityResult;
	}

	public static CosineDocumentSimilarityResult transform(final int SIZE, Map<String, Map<String, Double>> map) throws AdapterValidationException {
		double[][] matrix = new double[SIZE][SIZE];

		int x = 0; /* columns */
		int y = 0; /* rows */

		List<NodeStructure> nodes = new ArrayList<NodeStructure>();
		List<LinkStructure> links = new ArrayList<LinkStructure>();

		/* build numerical matrix */
		for (Map.Entry<String, Map<String, Double>> e1 : map.entrySet()) {

			Map<String, Double> inner = e1.getValue();
			for (Map.Entry<String, Double> e2 : inner.entrySet()) {

				matrix[x++][y] = e2.getValue();
				links.add(LinkStructureAdapter.transform(x, y, e2.getValue()));
			}

			nodes.add(NodeStructureAdapter.transform(e1.getKey(), y));

			y++;
			x = 0;
		}

		return transform(matrix, NodeLinkStructureAdapter.transform(nodes, links));
	}
}
