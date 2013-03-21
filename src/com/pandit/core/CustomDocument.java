/**
 * 
 */
package com.pandit.core;

import java.util.Collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.map.MultiValueMap;

import com.google.gson.Gson;

/**
 * @author Darshan
 * Basic Object used to represent the values held by the document returned by the Lucene Search
 *
 */
public class CustomDocument {
    private MultiValueMap map;

    /**
     * 
     */
    public CustomDocument() {
	super();
	map = new MultiValueMap();
    }

    /**
     * 
     * @see org.apache.commons.collections.map.MultiValueMap#clear()
     */
    public void clear() {
	map.clear();
    }

    /**
     * @param key
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key) {
	return map.containsKey(key);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#containsValue(java.lang.Object, java.lang.Object)
     */
    public boolean containsValue(Object key, Object value) {
	return map.containsValue(key, value);
    }

    /**
     * @param value
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object value) {
	return map.containsValue(value);
    }

    /**
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#entrySet()
     */
    public Set entrySet() {
	return map.entrySet();
    }

    /**
     * @param object
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#equals(java.lang.Object)
     */
    public boolean equals(Object object) {
	return map.equals(object);
    }

    /**
     * @param key
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#get(java.lang.Object)
     */
    public Object get(Object key) {
	return map.get(key);
    }

    /**
     * @param key
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#getCollection(java.lang.Object)
     */
    public Collection getCollection(Object key) {
	return map.getCollection(key);
    }

    /**
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#hashCode()
     */
    public int hashCode() {
	return map.hashCode();
    }

    /**
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#isEmpty()
     */
    public boolean isEmpty() {
	return map.isEmpty();
    }

    /**
     * @param key
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#iterator(java.lang.Object)
     */
    public Iterator iterator(Object key) {
	return map.iterator(key);
    }

    /**
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#keySet()
     */
    public Set keySet() {
	return map.keySet();
    }

    /**
     * @param key
     * @param value
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object key, Object value) {
	return map.put(key, value);
    }

    /**
     * @param map
     * @see org.apache.commons.collections.map.MultiValueMap#putAll(java.util.Map)
     */
    public void putAll(Map map) {
	map.putAll(map);
    }

    /**
     * @param key
     * @param values
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#putAll(java.lang.Object, java.util.Collection)
     */
    public boolean putAll(Object key, Collection values) {
	return map.putAll(key, values);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#remove(java.lang.Object, java.lang.Object)
     */
    public Object remove(Object key, Object value) {
	return map.remove(key, value);
    }

    /**
     * @param key
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#remove(java.lang.Object)
     */
    public Object remove(Object key) {
	return map.remove(key);
    }

    /**
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#size()
     */
    public int size() {
	return map.size();
    }

    /**
     * @param key
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#size(java.lang.Object)
     */
    public int size(Object key) {
	return map.size(key);
    }

    /**
     * @return
     * @see org.apache.commons.collections.map.AbstractMapDecorator#toString()
     */
    public String toString() {
	return map.toString();
    }

    /**
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#totalSize()
     */
    public int totalSize() {
	return map.totalSize();
    }

    /**
     * @return
     * @see org.apache.commons.collections.map.MultiValueMap#values()
     */
    public Collection values() {
	return map.values();
    }

    /**
     * @return the map
     */
    public MultiValueMap getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(MultiValueMap map) {
        this.map = map;
    }
    
    public String toJSON(){
	Gson gson = new Gson();
	String json = gson.toJson(this);  
	return json;
    }

    
    
    
}
