/**
 * 
 */
package com.pandit.core;


/**
 * @author Darshan
 *
 */
public class IntentFiller {
    
    String requestType, subjectType, rootType;

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the subjectType
     */
    public String getSubjectType() {
        return subjectType;
    }

    /**
     * @param subjectType the subjectType to set
     */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * @return the rootType
     */
    public String getRootType() {
        return rootType;
    }

    /**
     * @param rootType the rootType to set
     */
    public void setRootType(String rootType) {
        this.rootType = rootType;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "IntentFiller [requestType=" + requestType + ", subjectType="
		+ subjectType + ", rootType=" + rootType + "]";
    }

    /**
     * 
     */
    public IntentFiller() {
	super();
	
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((requestType == null) ? 0 : requestType.hashCode());
	result = prime * result
		+ ((rootType == null) ? 0 : rootType.hashCode());
	result = prime * result
		+ ((subjectType == null) ? 0 : subjectType.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	IntentFiller other = (IntentFiller) obj;
	if (requestType == null) {
	    if (other.requestType != null)
		return false;
	} else if (!requestType.equals(other.requestType))
	    return false;
	if (rootType == null) {
	    if (other.rootType != null)
		return false;
	} else if (!rootType.equals(other.rootType))
	    return false;
	if (subjectType == null) {
	    if (other.subjectType != null)
		return false;
	} else if (!subjectType.equals(other.subjectType))
	    return false;
	return true;
    }
    
}
