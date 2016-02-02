package algopractice;

public class LinkedList {
	public int data;
	public LinkedList next;
	public LinkedList prev;
	
	// reverse Singly LinkedList
	public LinkedList reverseSingly(LinkedList head) {
		if( head!=null ){
			LinkedList prev = null;
			while( head.next != null) {
				head.next = prev;
				prev = head;
				head = next;
			}
			head.next = prev;
		}
		return head;
	}

	// reverse Doubly LinkedList
	public LinkedList reverseDoubly(LinkedList head) {
		while(head!=null) {
			LinkedList temp = head.next;
			head.next = head.prev;
			head.prev = temp;
			if( temp==null ) {
				break;
			}
			head = temp;
		}
		return head;
		
	}
}
