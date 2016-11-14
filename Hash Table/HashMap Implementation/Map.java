package HashMaps;

import java.util.ArrayList;

public class Map<K, V> {
	// chainArray is used to store array of chains
	private ArrayList<HashNode<K,V>> chainArray;
	
	// Current capacity of array list and its current size
	private int numChain;
	private int size;
	
	// Constructor
	public Map() {
		chainArray = new ArrayList<>();
		numChain = 10;
		size = 0;
		
		//Create empty chains
		for(int i=0; i<numChain; i++) {
			chainArray.add(null);
		}
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size()==0;
	}
	
	// Hash function to find index for a key
	private int getChainIndex(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % numChain;
		return index;
	}
	
	// Method to remove a given key
	public V remove(K key) {
		int chainIndex = getChainIndex(key);
		
		HashNode<K,V> head = chainArray.get(chainIndex);
		
		HashNode<K,V> prev = null;
		while(head!=null){
			if(head.key.equals(key)) {
				break;
			}
			// Else keep moving in chain
			prev = head;
			head = head.next;
		}
		
		if(head==null) {
			return null;
		}
		
		size--;
		
		if(prev!=null) {
			prev.next = head.next;
		} else {
			chainArray.set(chainIndex, head.next);
		}
		
		return head.value;
	}
	
	public V get(K key) {
		int chainIndex =  getChainIndex(key);
		HashNode<K, V> head = chainArray.get(chainIndex);
		
		while(head!=null) {
			if(head.key.equals(key)) {
				return head.value;
			}
			head = head.next;
		}
		
		// If key not found
		return null;
	}
	
	public void add(K key, V value) {
		int chainIndex = getChainIndex(key);
		HashNode<K, V> head =  chainArray.get(chainIndex);
		
		//Check if key is already existing
		while(head!=null) {
			if(head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}
		
		// If it doesn't, insert key in chain
		size++;
		head = chainArray.get(chainIndex);
		HashNode<K, V> newNode = new HashNode<K,V>(key, value);
		newNode.next = head;
		chainArray.set(chainIndex, newNode);
		
		// Check the Load Factor, if it's beyond threshold, double the hash table size
		if((1.0*size)/numChain >= 0.65) {
			ArrayList<HashNode<K,V>> temp = chainArray;
			chainArray = new ArrayList<>();
			numChain = 2*numChain;
			size = 0;
			for(int i=0; i<numChain; i++) {
				chainArray.add(null);
			}
			
			for(HashNode<K,V> headNode: temp) {
				while(headNode!=null) {
					add(headNode.key, headNode.value);
					headNode = headNode.next;
				}
			}
		}
		
	}

}
