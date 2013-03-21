/**
 * 
 */
package com.pandit.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Darshan
 *
 */
public class Intent {
    private String quantifier,  requestType, root;
    private List<String> subjects,objects;
    /**
     * @return the quantifier
     */
    public String getQuantifier() {
        return quantifier;
    }
    /**
     * @param quantifier the quantifier to set
     */
    public void setQuantifier(String quantifier) {
        this.quantifier = quantifier;
    }
    /**
     * @return the subject
     */
    public List<String> getSubjects() {
        return subjects;
    }
    /**
     * @param subject the subject to set
     */
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
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
     * @return the objects
     */
    public List<String> getObjects() {
        return objects;
    }
    /**
     * @param objects the objects to set
     */
    public void setObjects(List<String> objects) {
        this.objects = objects;
    }
    
    /**
     * @return the root
     */
    public String getRoot() {
        return root;
    }
    /**
     * @param root the root to set
     */
    public void setRoot(String root) {
        this.root = root;
    }
    /**
     * 
     */
    public Intent() {
	super();
	subjects = new ArrayList<String>();
	objects = new ArrayList<String>();
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Intent [quantifier=" + quantifier + ", requestType="
		+ requestType + ", root=" + root + ", subjects=" + subjects
		+ ", objects=" + objects + "]";
    }
   
    
}
