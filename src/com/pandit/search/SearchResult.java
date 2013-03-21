/**
 * 
 */
package com.pandit.search;
import java.util.Set;

import org.apache.lucene.document.Document;

/**
 * @author Darshan
 *
 */
public class SearchResult {
    private Set<Document> documentSet;
    private Integer totalHits;
    
    /**
     * @return the documentSet
     */
    public Set<Document> getDocumentSet() {
        return documentSet;
    }
    /**
     * @param documentSet the documentSet to set
     */
    public void setDocumentSet(Set<Document> documentSet) {
        this.documentSet = documentSet;
    }
    /**
     * @return the totalHits
     */
    public Integer getTotalHits() {
        return totalHits;
    }
    /**
     * @param totalHits the totalHits to set
     */
    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }
    /**
     * @param documentSet
     * @param totalHits
     */
    public SearchResult(Set<Document> documentSet, Integer totalHits) {
	super();
	this.documentSet = documentSet;
	this.totalHits = totalHits;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SearchResult [documentSet=" + documentSet + ", totalHits="
		+ totalHits + "]";
    }
    
}
