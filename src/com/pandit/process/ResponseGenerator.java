/**
 * 
 */
package com.pandit.process;

import java.util.NoSuchElementException;

import com.pandit.core.Intent;
import com.pandit.core.IntentFiller;
import com.pandit.search.SearchResult;

/**
 * @author Darshan
 * 
 */
public class ResponseGenerator {
    public String generateResponse(IntentFiller filler, Intent intent,
	    SearchResult searchResult) {
	String message = "";
	System.out.println(filler.toString() + intent.toString()
		+ searchResult.toString());
	boolean negative = false;
	if (searchResult.getTotalHits() == 0)
	    negative = true;

	// Does
	if (filler.getRequestType().equalsIgnoreCase("do")
		|| filler.getRequestType().toLowerCase().equalsIgnoreCase("does")) {
	    if (negative)
		message = "No, " + intent.getSubjects() + " "+filler.getRequestType().toLowerCase()+" not "
			+ filler.getRootType() + " "
			+ intent.getObjects().iterator().next()
			+ ". Not to my knowledge.";
	    else if (filler.getSubjectType() != null) {
		message = "Yes, " + intent.getSubjects()+ " "+filler.getRequestType().toLowerCase()+" "	+ filler.getRootType() + " "
			+ intent.getObjects().iterator().next();
	    }

	}

	//What
	if (filler.getRequestType().equalsIgnoreCase("What")) {
	    if (negative)
		message = "I am wrong many times, But we all are. I need to improve";
	    else{
		message=intent.getSubjects().iterator().next()+" "+ filler.getRootType()+ " the following:";
	    }
	}
		
	// How
	if (filler.getRequestType().toLowerCase().equals("how")) {
	    if (negative)
		message = "Sorry, Couldn't find any places matching your criteria. Ask me something general.";
	    else {
		message = "Please read the ";
		if (filler.getSubjectType() != null) {
		    if (filler.getSubjectType().equalsIgnoreCase("restaurants")
			    || filler.getSubjectType().equalsIgnoreCase(
				    "restaurant")
			    || filler.getSubjectType().equalsIgnoreCase("food")
			    || filler.getSubjectType()
				    .equalsIgnoreCase("foods"))
			message += "reviews to find more about ";
		    if (filler.getSubjectType().equalsIgnoreCase("cost")
			    || filler.getSubjectType()
				    .equalsIgnoreCase("price")
			    || filler.getSubjectType().equalsIgnoreCase(
				    "prices")
			    || filler.getSubjectType().equalsIgnoreCase(
				    "serves")) {
			message += "menus to find more about ";
		    }
		    message += intent.getSubjects().iterator().next() + ".";
		}
	    }

	}

	// Who
	if (filler.getRequestType().toLowerCase().equals("who")
		|| filler.getRequestType().toLowerCase().equals("whom")) {
	    if (negative)
		message = "Sorry, Couldn't find any places matching your criteria. I am unaware of it.";
	    else {
		if (filler.getSubjectType() != null) {
		    message = "Following places " + intent.getRoot() + " "
			    + filler.getSubjectType();
		}
	    }

	}

	// Which
	if (filler.getRequestType().toLowerCase().equals("which")) {
	    if (negative)
		message = "Sorry, Couldn't find any places matching your criteria. I am unaware of it.";
	    else {
		if (intent.getObjects().iterator().next() != null) {
		    message = "Following places " + intent.getRoot() + " "
			    + intent.getObjects().iterator().next();
		}
	    }

	}

	// Where
	if (filler.getRequestType().toLowerCase().equals("where")) {
	    if (negative)
		message = "Sorry, Couldn't find any places matching your criteria. Try searching something else.";
	    else {
		if (intent.getSubjects().iterator().next()
			.equalsIgnoreCase("I"))
		    message += "You ";
		else
		    message += "One ";
		try {
		    if (intent.getObjects().iterator().next() == null)
			message += " can " + intent.getRoot()
				+ " at following places : ";
		    else
			message += " can " + intent.getRoot() + " "
				+ intent.getObjects().iterator().next()
				+ " at following places : ";
		} catch (NoSuchElementException e) {
		    message += " can find it at following places : ";
		}

	    }

	}

	// Inrecognized stuff
	if (filler.getRootType().equalsIgnoreCase("tonight")
		|| filler.getRootType().equalsIgnoreCase("open")
		|| filler.getRootType().equalsIgnoreCase("weekends")
		|| filler.getRootType().equalsIgnoreCase("weekend")
		|| filler.getRootType().equalsIgnoreCase("weekdays")
		|| filler.getRootType().equalsIgnoreCase("weekdays")
		|| filler.getRootType().equalsIgnoreCase("now")
		|| filler.getRootType().equalsIgnoreCase("nearby")
		|| filler.getRootType().equalsIgnoreCase("closes")
		|| filler.getRootType().equalsIgnoreCase("closed"))
	    message += "I am currently unable to understand time references, nor do I have data to answer your question. Please aske me something else. Thank You.";
	if (message.equals(""))
	    return new String("Unable to generate response.");
	else
	    return message;
    }
}
