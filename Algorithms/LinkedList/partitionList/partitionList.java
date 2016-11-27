package LinkedList;

public class partitionList{
	//Given a LinkedList and a value x, partition it such that all nodes less than x comes before nodes that are greater than or equal to x
	public static ListNode partition(ListNode head, int x) {
		ListNode dummy1 = new ListNode(0);
		ListNode dummy2 = new ListNode(0);
		
		ListNode runner1 = dummy1;
		ListNode runner2 = dummy2;
		
		while(head!=null) {
			if(head.val<x) {
				runner1.next = head;
				runner1 = head;
			} else {
				runner2.next = head;
				runner2 = head;
			}
			head = head.next;
		}
		runner2.next = null;
		runner1.next = dummy2.next;
		
		return dummy1.next;
	}
	
}

