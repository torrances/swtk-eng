package org.swtk.eng.asm.dto;

import org.swtk.common.ds.d3js.dto.NodeLinkStructure;

public class CosineDocumentSimilarityResult {

	private double[][]			matrix;

	private NodeLinkStructure	nodeLinks;

	public double[][] getMatrix() {
		return matrix;
	}

	public NodeLinkStructure getNodeLinks() {
		return nodeLinks;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public void setNodeLinks(NodeLinkStructure nodeLinks) {
		this.nodeLinks = nodeLinks;
	}
}
