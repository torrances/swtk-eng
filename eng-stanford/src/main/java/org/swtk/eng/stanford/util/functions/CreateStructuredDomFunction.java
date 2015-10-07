package org.swtk.eng.stanford.util.functions;

import java.util.Collection;

import org.swtk.eng.stanford.dto.DependencyParseContract;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.xml.DomUtils;
import com.trimc.blogger.commons.xml.Xpath;

/**
 * 	Purpose:
 * 	Turn a flattened dependency parse into a structured dependency parse DOM
 */
public final class CreateStructuredDomFunction {

	/*
	 * 	
	 * 	Example:
	 * 
	 * 		<node object="Ammonium_chloride_salt_deposition" object-sequence="1" predicate="nsubj" subject="likely" subject-sequence="4"/>
	 * 		<node object="Ammonium_chloride_salt_deposition" object-sequence="1" predicate="xsubj" subject="occur" subject-sequence="6"/>
	 * 		<node object="is" object-sequence="2" predicate="cop" subject="likely" subject-sequence="4"/>
	 * 		<node object="most" object-sequence="3" predicate="advmod" subject="likely" subject-sequence="4"/>=
	 * 		<node object="likely" object-sequence="4" predicate="root" subject="ROOT" subject-sequence="0"/>
	 * 		<node object="to" object-sequence="5" predicate="aux" subject="occur" subject-sequence="6"/>
	 * 		<node object="occur" object-sequence="6" predicate="xcomp" subject="likely" subject-sequence="4"/>
	 * 		<node object="heat_exchangers" object-sequence="8" predicate="prep_in" subject="occur" subject-sequence="6"/>
	 * 		<node object="where" object-sequence="9" predicate="advmod" subject="cooled" subject-sequence="12"/>
	 * 		<node object="the_process_stream" object-sequence="10" predicate="nsubjpass" subject="cooled" subject-sequence="12"/>
	 * 		<node object="is" object-sequence="11" predicate="auxpass" subject="cooled" subject-sequence="12"/>
	 * 		<node object="cooled" object-sequence="12" predicate="advcl" subject="occur" subject-sequence="6"/>
	 * 		<node object="the_NH4C1_salt_deposition_temperature" object-sequence="14" predicate="prep_below" subject="cooled" subject-sequence="12"/>
	 * 	
	 * 	Structured:
	 * 
	 * 		<node object="likely" object-sequence="4" predicate="root" subject="ROOT" subject-sequence="0">
	 * 			<node object="Ammonium_chloride_salt_deposition" object-sequence="1" predicate="nsubj" subject="likely" subject-sequence="4"/>
	 * 			<node object="is" object-sequence="2" predicate="cop" subject="likely" subject-sequence="4"/>
	 * 			<node object="most" object-sequence="3" predicate="advmod" subject="likely" subject-sequence="4"/>
	 * 			<node object="occur" object-sequence="6" predicate="xcomp" subject="likely" subject-sequence="4">
	 * 				<node object="Ammonium_chloride_salt_deposition" object-sequence="1" predicate="xsubj" subject="occur" subject-sequence="6"/>
	 * 				<node object="to" object-sequence="5" predicate="aux" subject="occur" subject-sequence="6"/>
	 * 				<node object="heat_exchangers" object-sequence="8" predicate="prep_in" subject="occur" subject-sequence="6"/>
	 * 				<node object="cooled" object-sequence="12" predicate="advcl" subject="occur" subject-sequence="6">
	 * 					<node object="where" object-sequence="9" predicate="advmod" subject="cooled" subject-sequence="12"/>
	 * 					<node object="the_process_stream" object-sequence="10" predicate="nsubjpass" subject="cooled" subject-sequence="12"/>
	 * 					<node object="is" object-sequence="11" predicate="auxpass" subject="cooled" subject-sequence="12"/>
	 * 					<node object="the_NH4C1_salt_deposition_temperature" object-sequence="14" predicate="prep_below" subject="cooled" subject-sequence="12"/>
	 * 				</node>
	 * 			</node>
	 * 		</node>
	 */

	public static LogManager	logger	= new LogManager(CreateStructuredDomFunction.class);

	private static Element copy(Document _dom, Element el) {
		Element node = _dom.createElement("node");

		node.setAttribute("object", el.getAttribute("object"));
		node.setAttribute("object-sequence", el.getAttribute("object-sequence"));
		node.setAttribute("predicate", el.getAttribute("predicate"));
		node.setAttribute("subject", el.getAttribute("subject"));
		node.setAttribute("subject-sequence", el.getAttribute("subject-sequence"));

		return node;
	}

	private static Collection<Element> findSubjects(Document dom, String subject) throws BusinessException {
		String expr = String.format("descendant-or-self::node[@subject='%s']", subject);
		logger.trace("Created Xpath Expression to find Subjects (%s)", expr);

		return new Xpath(dom).evaluate(expr).elements();
	}

	private static Element iterate(Document _dom, Document dom, Element el) throws BusinessException {
		String o = el.getAttribute("object");

		Element _el = copy(_dom, el);
		for (Element s : findSubjects(dom, o))
			_el.appendChild(iterate(_dom, dom, s));

		return _el;
	}

	public static Document process(DependencyParseContract contract) throws BusinessException {
		return process(contract.getDocument());
	}

	private static Document process(Document dom) throws BusinessException {
		try {

			Document _dom = DomUtils.createDOM();

			Element root = findSubjects(dom, "ROOT").iterator().next();
			Element _root = iterate(_dom, dom, root);

			_dom.appendChild(_root);
			return _dom;

		} catch (BusinessException e) {
			throw new BusinessException(e, "Unable to create structured DOM");
		}
	}
}
