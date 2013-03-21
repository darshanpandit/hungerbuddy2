/**
 * 
 */
package com.pandit.search;

/**
 * @author Darshan
 *
 */
public class SearchParam {
    private String key, value;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SearchParam [key=" + key + ", value=" + value + "]";
    }

    /**
     * @param key
     * @param value
     */
    public SearchParam(String key, String value) {
	super();
	this.key = key;
	this.value = value;
    }
    
}
