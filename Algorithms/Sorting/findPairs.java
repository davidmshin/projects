package algopractice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class findPairs {
	
	//Find the pairs, which sum to the integer N
	public static void findPairs(int[] a, int N) {
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		
		for(int n: a) {
			if(set.contains(N-n)) System.out.println(N-n+","+n);
			else set.add(n);
		}
		
	}
	
	public static void main(String[]args) {
		int[]a = {3, 7, 2, 5, 6, 4};
		int N = 9;
		findPairs f = new findPairs();
		f.findPairs(a, N);
	}

}
