/**
 * 
 */
package com.pandit.search;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;



/**
 * @author Darshan
 *
 */
public class SearchHandlerSingleton {
    public static SearchHandlerSingleton searchHandlerSingleton = new SearchHandlerSingleton();
    private IndexReader indexReader;
    private IndexSearcher indexSearcher;
    private SearchHandlerSingleton() {
	try {
	    indexReader = IndexReader.open(FSDirectory.open(new File(HungerConfig.IndexPath)));
	    indexSearcher = new IndexSearcher(indexReader);
	} catch (CorruptIndexException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	   e.printStackTrace();
	}
	
    }
    
    public static SearchHandlerSingleton getInstance(){
	return searchHandlerSingleton;
    }
    
    /*
     *  @return SearchResult
     * 
     */
    public SearchResult search(List<SearchParam> map, Integer docCount) throws CorruptIndexException, IOException, ParseException{
	BooleanQuery query = new BooleanQuery();
	for(SearchParam temp:map){
	    //TODO Replace this with a Phrase Query
	    PhraseQuery pq= new PhraseQuery();
	    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
	   /* for(String team:temp.getValue().split(" ")){
		pq.add(new Term(temp.getKey(), team.toLowerCase()));
	    }
	    query.add(pq, Occur.MUST);
	    
	   */QueryParser parser;
	    if(temp.getKey()==null)
		parser=new QueryParser(Version.LUCENE_36, HungerConfig.field_commment, new StandardAnalyzer(Version.LUCENE_36));
	    else
		parser = new QueryParser(Version.LUCENE_36, temp.getKey(), new StandardAnalyzer(Version.LUCENE_36));
	    //parser.setPhraseSlop(0);
	   query.add(parser.parse("\""+temp.getValue()+"\""), Occur.MUST);
	}
	System.out.println(query);
	if(docCount==0)
	    docCount=indexSearcher.search(query, docCount).totalHits;
	TopDocs topDocs = indexSearcher.search(query, docCount);
	Set<Document> set = new HashSet<Document>();
	for(ScoreDoc scoreDoc:topDocs.scoreDocs){
	    set.add(indexReader.document(scoreDoc.doc));
	}
	return new SearchResult(set, topDocs.totalHits);
	
    }
}
