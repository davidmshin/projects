//
// RECORD.JAVA
// Record type for string hash table
//
// A record associates a certain string (the key value) with 
// a list of sequence positions at which that string occurs.
//

import java.util.*;

public class Record {
    public String key;
    public ArrayList<Integer> positions;
    public int intHashKey;
    public int pos;
    
    public Record(String s) {
        key = s;
	   intHashKey = StringTable.toHashKey(s);
	   positions = new ArrayList<Integer>(1);
    }
}
