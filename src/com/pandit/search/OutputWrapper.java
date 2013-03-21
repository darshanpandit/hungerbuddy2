/**
 * 
 */
package com.pandit.search;

/**
 * @author Darshan
 *
 */
public class OutputWrapper {
    private String query;
    private String message;
    private SearchResult searchResult;
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the searchResult
     */
    public SearchResult getSearchResult() {
        return searchResult;
    }
    /**
     * @param searchResult the searchResult to set
     */
    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }
    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }
    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }
    /**
     * @param query
     * @param message
     * @param searchResult
     */
    public OutputWrapper(String query, String message, SearchResult searchResult) {
	super();
	this.query = query;
	this.message = message;
	this.searchResult = searchResult;
    }
    
}
