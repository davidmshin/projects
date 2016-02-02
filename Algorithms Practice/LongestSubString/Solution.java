package algopractice;

import java.util.HashMap;

public class Solution {

    public static int lengthOfLongestSubstring(String s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int count = 0;
        String a = "";
        
        // make string into an array
        String[] array = new String[s.length()];
        for(int i =0; i< s.length(); i++) {
            array[i] = Character.toString(s.charAt(i));
        }
        
       
        
       // Simple concept: if the HashMap already contains the string(an alphabet), return the string,
       // If the HashMap does not have the string, add it to the HashMap
       for(int i=0; i<array.length; i++){
        if(map.containsKey(array[i])){
            
            for(int j=0; j < count; j++) {
                a+= array[i];
            }
            
            
            System.out.println(a.length());
            return count;
        } 
        
        else {
        	System.out.println("put");
        	map.put((array[i]), i);
        	count++;
        }
       }
       
       System.out.println(count);
       return count;
    }
    
    public static void main(String[]args) {
    	String a = "abcdabcd";
    	
    	lengthOfLongestSubstring(a);
    	
    }

}
