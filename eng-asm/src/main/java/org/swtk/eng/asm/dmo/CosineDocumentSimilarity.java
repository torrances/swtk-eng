package org.swtk.eng.asm.dmo;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;

/* 	prov:wasQuotedFrom
 * 		<http://stackoverflow.com/questions/1844194/get-cosine-similarity-between-two-documents-in-lucene/14438826#14438826>
 * 		prov:wasQuotedFrom
 * 			<http://sujitpal.blogspot.ch/2011/10/computing-document-similarity-using.html>  */
public class CosineDocumentSimilarity {

	public static final String		CONTENT		= "Content";

	/* Indexed, tokenized, stored. */
	public static final FieldType	TYPE_STORED	= new FieldType();

	static {
		// TYPE_STORED.setIndexed(true);
		TYPE_STORED.setTokenized(true);
		TYPE_STORED.setStored(true);
		TYPE_STORED.setStoreTermVectors(true);
		TYPE_STORED.setStoreTermVectorPositions(true);
		TYPE_STORED.freeze();
	}

	public static double getCosineSimilarity(String s1, String s2) throws IOException {
		return new CosineDocumentSimilarity(s1, s2).getCosineSimilarity();
	}

	private final Set<String>	terms	= new HashSet<>();

	private final RealVector	v1;

	private final RealVector	v2;

	public CosineDocumentSimilarity(String s1, String s2) throws IOException {
		Directory directory = createIndex(s1, s2);
		IndexReader reader = DirectoryReader.open(directory);
		Map<String, Integer> f1 = getTermFrequencies(reader, 0);
		Map<String, Integer> f2 = getTermFrequencies(reader, 1);
		reader.close();
		v1 = toRealVector(f1);
		v2 = toRealVector(f2);
	}

	private void addDocument(IndexWriter writer, String content) throws IOException {
		Document doc = new Document();
		Field field = new Field(CONTENT, content, TYPE_STORED);
		doc.add(field);
		writer.addDocument(doc);
	}

	// @SuppressWarnings("deprecation")
	private Directory createIndex(String s1, String s2) throws IOException {
		Directory directory = new RAMDirectory();
		Analyzer analyzer = new SimpleAnalyzer();//Version.LUCENE_CURRENT);
		// IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(directory, iwc);
		addDocument(writer, s1);
		addDocument(writer, s2);
		writer.close();
		return directory;
	}

	public double getCosineSimilarity() {
		return (v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm());
	}

	private Map<String, Integer> getTermFrequencies(IndexReader reader, int docId) throws IOException {
		Terms vector = reader.getTermVector(docId, CONTENT);
		TermsEnum termsEnum = null;
		termsEnum = vector.iterator(termsEnum);
		Map<String, Integer> frequencies = new HashMap<>();
		BytesRef text = null;
		while ((text = termsEnum.next()) != null) {
			String term = text.utf8ToString();
			int freq = (int) termsEnum.totalTermFreq();
			frequencies.put(term, freq);
			terms.add(term);
		}
		return frequencies;
	}

	private RealVector toRealVector(Map<String, Integer> map) {
		RealVector vector = new ArrayRealVector(terms.size());

		int i = 0;
		for (String term : terms) {
			int value = map.containsKey(term) ? map.get(term) : 0;
			vector.setEntry(i++, value);
		}

		return (RealVector) vector.mapDivide(vector.getL1Norm());
	}
}
