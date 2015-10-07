package org.swtk.eng.wordnet.synonyms.svc;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TotalHitCountCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.wordnet.Syns2Index;
import org.swtk.eng.wordnet.synonyms.util.Constants;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.SetUtils;

public class QueryIndex {

	public static LogManager	logger	= new LogManager(QueryIndex.class);

	private FSDirectory			directory;

	private IndexSearcher		searcher;

	public QueryIndex() throws BusinessException {
		init();
	}

	@PostConstruct
	public void close() throws BusinessException {
		try {

			if (null != searcher) searcher.close();
			if (null != directory) directory.close();

			this.searcher = null;
			this.directory = null;

		} catch (IOException e) {
			logger.error(e);
			throw new BusinessException("Unable to shutdown Lucene Index");
		}
	}

	private void init() throws BusinessException {
		try {

			directory = FSDirectory.open(Constants.WORDNET_SYNONYMS_DIR);
			searcher = new IndexSearcher(directory);

		} catch (Exception e) {
			logger.error(e);
			throw new BusinessException("Unable to open Lucene Directory (path = %s)", Constants.WORDNET_SYNONYMS_DIR.getAbsolutePath());
		}
	}

	public Collection<String> process(String term) throws BusinessException {
		try {

			if (null == directory || null == searcher) init();
			Query query = new TermQuery(new Term(Syns2Index.F_WORD, term));

			TotalHitCountCollector thcc = new TotalHitCountCollector();
			searcher.search(query, thcc);

			Set<String> results = new TreeSet<String>();
			ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;

			for (ScoreDoc hit : hits) {
				Document doc = searcher.doc(hit.doc);

				for (String value : doc.getValues(Syns2Index.F_SYN))
					results.add(value);
			}

			if (0 == thcc.getTotalHits()) logger.debug("No Results Found (term = %s)", term);
			else logger.info("Synonyms Found (term = %s, total = %s, list = %s)", term, results.size(), SetUtils.toString(results, ", "));

			return results;

		} catch (IOException e) {
			logger.error(e);
			throw new BusinessException("Unable to Execute Query (term = %s)", term);
		}
	}
}
