package com.jpmorgan.techtest.tradereporting.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReportUtils {
	
	private ReportUtils() {
		
	}
	
	// function to reverse-sort hashmap by values 
    public static Map<String, Double> sortDescendingByValue(Map<String, Double> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Double> > list = 
               new LinkedList<>(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
            public int compare(Map.Entry<String, Double> o1,  
                               Map.Entry<String, Double> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Double> temp = new LinkedHashMap<>(); 
        for (Map.Entry<String, Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 

}
