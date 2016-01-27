package org.swtk.eng.stanford;

public class SimpleCoreNLPDemo {
	
	// requires 3.6.0; not available on maven central yet
	// <http://stanfordnlp.github.io/CoreNLP/history.html>
	// We'll be releasing 3.6.0 on Maven in early January, the OpenIE stuff is included in the main release.
	// <https://github.com/stanfordnlp/CoreNLP/issues/117>

	//	public static void main(String[] args) {
	//		Document doc = new Document("add your text here! It can contain multiple sentences.");
	//		for (Sentence sent : doc.sentences()) { // Will iterate over two sentences
	//			// We're only asking for words -- no need to load any models yet
	//			System.out.println("The second word of the sentence '" + sent + "' is " + sent.word(1));
	//			// When we ask for the lemma, it will load and run the part of speech tagger
	//			System.out.println("The third lemma of the sentence '" + sent + "' is " + sent.lemma(2));
	//			// When we ask for the parse, it will load and run the parser
	//			System.out.println("The parse of the sentence '" + sent + "' is " + sent.parse());
	//			// ...
	//		}
	//	}
}
