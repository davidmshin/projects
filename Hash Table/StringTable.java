import java.util.ArrayList;
import java.util.Hashtable;

// STRINGTABLE.JAVA
// A hash table mapping Strings to their positions in the the pattern sequence
// You get to fill in the methods for this part.
//


public class StringTable {
    //
    // Create an empty table big enough to hold maxSize records.
    //
	public int m;                // size of the hash table
	public Record table[];
    public int recordCounter;   // number of records inserted

	
	
    public StringTable(int maxSize) 
    {
    	double fixed;
        double a;
        m = maxSize;
        recordCounter = 0;
        a = Math.log(maxSize) / Math.log(2);
        if (a % 1 == 0){
            fixed = Math.pow(2,a);
        }
        else {
            fixed = Math.pow(2, a+1);
        }
        table = new Record[(int)fixed];
    }
    
    //Constructor for the doubled table
    public StringTable() {
    	//Give it a table size of 2
    	recordCounter = 0;
    	m = 2;
    	table = new Record[2];
    }
    
    
    //Double the size of the hash table
    public void hashDoubling(){
        
        //Create new table with doubled m(hash table size)
    	StringTable doubleTable = new StringTable(this.m*2);
        
    	for(Record r: table) {
    		if(r!=null) {
                //insert each record into doubled table
    			doubleTable.insert(r);
    		}
    	}
        //Update the table
    	this.table = doubleTable.table;
		this.m = doubleTable.m;
    	
        //After making completing making a new hash table
        //Set the number of inserted records back to zero
    	recordCounter = 0;
    	
    }
    
    
    //
    // Insert a Record r into the table.  Return true if
    // successful, false if the table is full.  You shouldn't ever
    // get two insertions with the same key value, but you may
    // simply return false if this happens.
    //
    public boolean insert(Record r) 
    {
    	//Double the table if the record is fourth full
    	//Load factor α = 1/4
        //Check the Load Factor first s.t the table is not initially full before insertion
    	if((double)recordCounter/m >= 0.25) {
    		hashDoubling();
    	}
    	
    	int hashKey = r.intHashKey;
    	int h1 = baseHash(hashKey);
    	int h2 = stepHash(hashKey);
    	
    	int a = h1;
    	int counter = 0;
    	
    	
    	while(counter<m && table[a] != null) {
    		
    		if(table[a].intHashKey == r.intHashKey) {
    			if(table[a].key.equals(r.key)) {
    				return false;	// The Record Already Exists!
    			}
    		}
    		//Try with h2
    		a = (a+h2)%m;
    		counter++;
    	}
    	
    	// If the slot is empty, insert r into that slot
    	if(table[a] == null) {
    		table[a] = r;
    		r.pos = a;

    	} else {
    		return false;
    	}
    	
        //Since r is inserted, increment the recordCounter
    	recordCounter++;
    	
    	
    //Insertion is successful
	return true;
    }
    
    
    //
    // Delete a Record r from the table.  Note that you'll have to
    // find the record first unless you keep some extra information
    // in the Record structure.
    //
    public void remove(Record r) 
    {
        //Deletion is as simple as updating Record's position and hashKey to -1, wile decrementing the recordCounter
        if(r.pos != -1)
    	{
    		r.pos = -1;
    		r.intHashKey = -1;
    		recordCounter--;
    	}
    	
    }
    
    
    //
    // Find a record with a key matching the input.  Return the
    // record if it exists, or null if no matching record is found.
    //
    public Record find(String key) 
    {
    	int hashKey = toHashKey(key);
    	int h1 = baseHash(hashKey);
    	int h2 = stepHash(hashKey);
    	
    	
    	
    	// First probe with h1
    	int a = h1;
    	int counter = 0;
    	
    	
    	while(counter<m && table[a] != null) {
    		if(table[a].intHashKey == hashKey) {
    			if(table[a].key.equals(key)) {
    				return table[a];
    			}
    		}
    		
    		//Else probe with h2
    		a = (a+h2)%m;
    		counter++;
    	}
    	
    	//Then check that table is full or doesn't have the value
    	if(counter >= m || table[a] == null) {
    		return null;
    	} else {
    		return table[a];
    	}
    }
    
    
    ///////////////////////////////////////////////////////////////////////
    
    
    // Convert a String key into an integer that serves as input to hash
    // functions.  This mapping is based on the idea of a linear-congruential
    // pesudorandom number generator, in which successive values r_i are 
    // generated by computing
    //    r_i = ( A * r_(i-1) + B ) mod M
    // A is a large prime number, while B is a small increment thrown in
    // so that we don't just compute successive powers of A mod M.
    //
    // We modify the above generator by perturbing each r_i, adding in
    // the ith character of the string and its offset, to alter the
    // pseudorandom sequence.
    //
    
    
    // Made it static s.t I could use intHashKey in Record class
    public static int toHashKey(String s)
    {
	int A = 1952786893;
	int B = 367257;
	int v = B;
	
	for (int j = 0; j < s.length(); j++)
	    {
		char c = s.charAt(j);
		v = A * (v + (int) c + j) + B;
	    }
	
	if (v < 0) v = -v;
	return v;
    }
    
    
    // Computes the base hash of the hashKey
    int baseHash(int hashKey)
    {
    	double a = Math.sqrt(5)-2;
    	int h1 = (int)Math.floor(m*(a*hashKey - Math.floor(a*hashKey)));
    	return h1;
    }
    
    // Computes the step hash of the hashKey
    int stepHash(int hashKey)
    {
    	double a = (Math.sqrt(6)-1)/2;
		int h2 = (int)Math.floor(m*(a*hashKey - Math.floor(a*hashKey)));
    	if(h2%2==0) {
    		h2 = h2+1;
    	}
    	
    	return h2;
    }
}
