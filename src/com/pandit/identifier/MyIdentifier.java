/**
 * 
 */
package com.pandit.identifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Darshan
 * 
 */
public class MyIdentifier {
    private Set<String> set;

    /**
     * @throws IOException
     * 
     */
    public MyIdentifier(String path) throws IOException {
	super();
	BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
	set = new HashSet<String>();
	String temp;
	while ((temp = bufferedReader.readLine()) != null)
	    set.add(temp.toLowerCase());
	bufferedReader.close();
    }

    /**
     * @param o
     * @return
     * @see java.util.Set#contains(java.lang.Object)
     */
    public boolean contains(String o) {
	return set.contains(o.toLowerCase());
    }

    /*
     * @param Set<String>
     * 
     */
    public void removeValues(Set<String> set){
	if(set!=null)
	this.set.removeAll(set);
    }
    
    /**
     * @return the set
     */
    public Set<String> getSet() {
        return set;
    }

    /**
     * @param set the set to set
     */
    public void setSet(Set<String> set) {
        this.set = set;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Identifier [set=" + set + "]";
    }

}
