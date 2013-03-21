/**
 * 
 */
package com.pandit.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




import com.pandit.core.Intent;
import com.pandit.core.IntentFiller;
import com.pandit.identifier.MyIdentifier;

/**
 * @author Darshan
 * 
 */
public class IntentTranslatorSingleton {
    private MyIdentifier foodIdentifier, locationIdentifier,
	    restaurantIdentifier;
    public static IntentTranslatorSingleton intentTranslatorSingleton = new IntentTranslatorSingleton();

    private IntentTranslatorSingleton() {
	try {
	    foodIdentifier = new MyIdentifier(HungerConfig.foodList);

	    locationIdentifier = new MyIdentifier(HungerConfig.locationList);
	    restaurantIdentifier = new MyIdentifier(HungerConfig.restaurantList);

	    // Removes values reflected in multiple sets
	    removeIntersection(foodIdentifier, restaurantIdentifier);
	    removeIntersection(foodIdentifier, locationIdentifier);
	    removeIntersection(restaurantIdentifier, locationIdentifier);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    public IntentFiller fillIntent(Intent intent){
	 IntentFiller intentFiller = new IntentFiller();
	       intentFiller.setRequestType(intent.getRequestType().toLowerCase());
	       
	       if(foodIdentifier.contains(intent.getRoot().toLowerCase()))
		   intentFiller.setRootType("food");
	       if(locationIdentifier.contains(intent.getRoot().toLowerCase()))
		   intentFiller.setRootType("location");
	       if(restaurantIdentifier.contains(intent.getRoot().toLowerCase()))
		   intentFiller.setRootType("restaurant");
	       else
		   intentFiller.setRootType(intent.getRoot());
	       
	       if(intentFiller.getRequestType().equalsIgnoreCase("who")){
		   if(foodIdentifier.contains(intent.getObjects().iterator().next().toLowerCase()))
			   intentFiller.setSubjectType("food");
		       if(locationIdentifier.contains(intent.getObjects().iterator().next().toLowerCase()))
			   intentFiller.setSubjectType("location");
		       if(restaurantIdentifier.contains(intent.getObjects().iterator().next().toLowerCase()))
			   intentFiller.setSubjectType("restaurant");
		       else
			   intentFiller.setSubjectType(intent.getObjects().iterator().next().toLowerCase());
		       
	       }
	       else{
	       if(foodIdentifier.contains(intent.getSubjects().iterator().next().toLowerCase()))
		   intentFiller.setSubjectType("food");
	       if(locationIdentifier.contains(intent.getSubjects().iterator().next().toLowerCase()))
		   intentFiller.setSubjectType("location");
	       if(restaurantIdentifier.contains(intent.getSubjects().iterator().next().toLowerCase()))
		   intentFiller.setSubjectType("restaurant");
	       else
		   intentFiller.setSubjectType(intent.getSubjects().iterator().next().toLowerCase());
	       }  
	      return intentFiller;
   }
    
    private void removeIntersection(MyIdentifier base, MyIdentifier reference) {
	base.removeValues(reference.getSet());
    }

    public String identifyType(String text) {
	if (foodIdentifier.contains(text))
	    return HungerConfig.field_food;
	if (locationIdentifier.contains(text))
	    return HungerConfig.field_location;
	if (restaurantIdentifier.contains(text))
	    return HungerConfig.field_name;
	else
	    return null;
    }
    
    /*
     * @return List<SearchParam>
     * @param Intent
     * This core function is responsible to translate the intent query into a SearchParams which can then be filled as PigeonHoles
     */
    public List<SearchParam> translate(Intent intent) {
	List<SearchParam> list = new ArrayList<SearchParam>();
	if (intent.getRoot() != null) {
	    if (identifyType(intent.getRoot()) != null)
		list.add(new SearchParam(identifyType(intent.getRoot()), intent
			.getRoot()));
		
	   
	}
	for(String temp: intent.getObjects()){
	    if (temp != null) {
		    if (identifyType(temp) != null)
			list.add(new SearchParam(identifyType(temp), temp));
		    else
			list.add(new SearchParam(null, temp));
		}
	}
	for(String temp: intent.getSubjects()){
	    if (temp != null) {
		    if (identifyType(temp) != null)
			list.add(new SearchParam(identifyType(temp), temp));
		    else
			list.add(new SearchParam(null, temp));
		}
	}
	
	return list;

    }

}
