package org.swtk.eng.asm.svc;

import com.trimc.blogger.commons.exception.BusinessException;

/**	
 * 	One of the first derived match codes schemes is called the Soundex algorithm. 
 * 
 * 	It was first patented in 1918 ( Odell, Russell, 1918 ) and was used in the 1930s as a manual process to match records in the Social Security Administration ( Herzog, et al., 2007 ). 
 * 	The name Soundex comes from the combination of the words sound and indexing because it attempts to recognize both the syntactic and phonetic similarity between two names. 
 * 	As with most approximate matching, there are many variations resulting from the adaptation of the algorithm to different applications. 
 * 
 * 	The algorithm presented here is from Herzog, et al. (2007) using the name �Checker�: 
 * 		1. Capitalize all letters and drop punctuation -> CHECKER. 
 * 		2. Remove the letters A, E, I, O, U, H, W, and Y after the first letter -> CCKR. 
 * 		3. Keep first letter but replace the other letters by digits according to the coding 
 * 			{B, F, P, V} replace with 1, 
 * 			{C, G, J, K, Q, S, X, Z} replace with 2, 
 * 			{D, T} replace with 3, {L} replace with 4, 
 * 			{M, N} replace with 5, and {R} replace with 6 -> C226. 
 * 		4. Replace consecutive sequences of the same digit with a single digit if the letters they represent were originally next together in the name 
 * 			or separated by H or W -> C26 (because the 22 comes from letters CK that were next together). 
 * 		5. If the result is longer than 4 characters total, drop digits at the end to make it 4 characters long. 
 * 			If the result is fewer than 4 characters, add zeros at the end to make it 4 characters long -> C260. 
 * 
 * 	Using this same algorithm, the name �John� produces the Soundex match code value J500. 
 * 	By using these match codes as proxies for the attribute values, the name �John Checker� would be matched to any other names that produce the same match codes, such as �Jon Cecker.� 
 * 	
 * 	Talburt, John R.; Talburt, John R. (2011-01-14). Entity Resolution and Information Quality (Kindle Locations 609-625). Elsevier Science. Kindle Edition.
 */
public interface SoundexService {

	int difference(String s1, String s2) throws BusinessException;

	String encode(String s1) throws BusinessException;

	boolean isEqual(String s1, String s2) throws BusinessException;
}
