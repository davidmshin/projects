package algopractice;

import java.util.HashMap;

public class Solution {

    public static int lengthOfLongestSubstring(String s) {
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	int length = 0;
    	int startPoint = -1;
    	int endPoint = 0;
    	
    	//Using two pointers, startPoint and endPoint, updating them appropriately, return the length
    	for(endPoint =0; endPoint<s.length(); endPoint++) {
    		char c = s.charAt(endPoint);
    		
    		if(map.containsKey(c)) {
    			int newStartPoint = map.get(c);
    			startPoint = max(startPoint, newStartPoint);
    		}	
    		length = max(length, endPoint-startPoint);
    		map.put(c, endPoint);

    	}
    	return length;
    }
    
    public static int max(int x, int y) {
    	if(x>y) return x;
    	return y;
    }
       
        
        
}
