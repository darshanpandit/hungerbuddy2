/**
 * 
 */
package com.pandit.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.pandit.search.IntentTranslatorSingleton;

import edu.stanford.nlp.trees.TreeGraphNode;
import edu.stanford.nlp.trees.TypedDependency;

/**
 * @author Darshan
 * 
 */
public class UtilFunctions {

    public static Intent analyse_Step1(
	    Collection<TypedDependency> typedDependencyCollection,
	    TreeGraphNode root) throws IOException {
	Intent intent = new Intent();
	intent.setRoot(root.nodeString());
	for (TypedDependency typedDependency : UtilFunctions.getChildNodes(
		typedDependencyCollection, root)) {
	    if (typedDependency.reln().toString().equals("nsubj")) {
		intent.getSubjects().addAll(
			getDescriptor(typedDependencyCollection,
				typedDependency.dep()));

		// To find request type
		Collection<TypedDependency> collection = getChildNodes(
			getDependency(typedDependencyCollection, "det"), root);
		if (collection != null && collection.size() > 0)
		    intent.setRequestType(collection.iterator().next().dep()
			    .toString("value"));

		// To find quantifier for request
		collection = getChildNodes(
			getDependency(typedDependencyCollection, "num"),
			typedDependency.dep());
		if (collection != null && collection.size() > 0)
		    intent.setQuantifier(collection.iterator().next().dep()
			    .toString("value"));

		// To get Objects
		for (TypedDependency dependency : getDependency(
			getChildNodes(typedDependencyCollection, root), "dobj")) {
		    intent.getObjects().addAll(
			    getDescriptor(typedDependencyCollection,
				    dependency.dep()));
		}

		/*
		 * //gets properties from dobj of rcmod for(TypedDependency
		 * dependency: getDependency(typedDependencyCollection,
		 * "rcmod")){
		 * if(dependency.gov().toString("value").equals(typedDependency
		 * .dep().toString("value"))){ for(TypedDependency inner:
		 * getChildNodes(typedDependencyCollection, dependency.dep())){
		 * if(inner.reln().toString().equals("dobj") ){
		 * intent.getObjects
		 * ().addAll(getDescriptor(typedDependencyCollection,
		 * inner.dep())); } } }
		 * 
		 * }
		 */
		// Here we add all known entites that are followed by known
		// entities eg: in Brooklyn is recognized, in Mumbai is ignored.
		Set<String> prep_relations = new TreeSet<String>();
		prep_relations.add("prep");
		// prep_relations.add("prep_in");
		// prep_relations.add("prep_during");

		IntentTranslatorSingleton intentTranslatorSingleton = IntentTranslatorSingleton.intentTranslatorSingleton;
		for (String prep : prep_relations) {
		    for (TypedDependency inner : getDependency(
			    typedDependencyCollection, prep)) {
			// System.out.println(inner);
			List<String> tempSet = getDescriptor(
				typedDependencyCollection, inner.dep());
			for (String temp : tempSet) {
			    if (intentTranslatorSingleton.identifyType(temp) != null)
				intent.getObjects().add(temp);
			}

		    }
		}

		/*
		 * for(TypedDependency inner:
		 * getDependency(typedDependencyCollection, "xcomp")){
		 * if(inner.gov
		 * ().toString().equals(getRoot(typedDependencyCollection
		 * ).toString())){ //System.out.println(inner);
		 * intent.getObjects
		 * ().addAll(getDescriptor(typedDependencyCollection,
		 * inner.dep())); } }
		 */
		return intent;
	    }

	}

	return null;
    }

    public static List<String> getDescriptor(
	    Collection<TypedDependency> typedDependencyCollection,
	    TreeGraphNode word) {
	List<String> list = new ArrayList<String>();
	boolean conjunct = false;
	StringBuffer buffer = new StringBuffer();

	for (TypedDependency typedDependency : UtilFunctions.getChildNodes(
		typedDependencyCollection, word)) {
	    // Check for Conjunctions and add them to the descriptor list.
	    if (typedDependency.reln().toString().equals("conj_and")) {
		typedDependencyCollection.remove(typedDependency);
		list.addAll(getDescriptor(typedDependencyCollection,
			typedDependency.gov()));
		list.addAll(getDescriptor(typedDependencyCollection,
			typedDependency.dep()));
		conjunct = true;
	    }

	    else {
		String relationString = typedDependency.reln().toString();
		if (relationString.equals("nn")
			|| relationString.equals("amod")) {
		    buffer.append(typedDependency.dep().toString("value") + " ");
		}
	    }
	}
	if (conjunct == false) {
	    list.add(buffer.toString() + word.toString("value"));
	}
	return list;
    }

    public static Collection<TypedDependency> getChildNodes(
	    Collection<TypedDependency> typedDependencyCollection,
	    TreeGraphNode treeGraphNode) {

	List<TypedDependency> list = new ArrayList<TypedDependency>();
	if ((typedDependencyCollection == null)
		|| (!(typedDependencyCollection.size() > 0)))
	    return list;
	for (TypedDependency typedDependency : typedDependencyCollection) {
	    if (typedDependency.gov().toString("value")
		    .equalsIgnoreCase(treeGraphNode.toString("value"))) {
		list.add(typedDependency);
	    }
	}
	return list;
    }

    public static Collection<TypedDependency> getDependency(
	    Collection<TypedDependency> typedDependencies, String relation) {
	List<TypedDependency> collection = new ArrayList<TypedDependency>();
	if ((typedDependencies == null) || (!(typedDependencies.size() > 0)))
	    return collection;
	for (TypedDependency typedDependency : typedDependencies) {
	    // System.out.println(typedDependency.reln().getShortName());
	    if (typedDependency.reln().getShortName().equals(relation)) {
		collection.add(typedDependency);
	    }
	}
	return collection;
    }

    public static TreeGraphNode getRoot(
	    Collection<TypedDependency> typedDependencies) {
	for (TypedDependency dependency : typedDependencies) {
	    if (dependency.reln().toString().equals("root"))

		return dependency.dep();
	}
	return null;
    }
}
