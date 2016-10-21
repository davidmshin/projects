package revisited;

public class mergeSort {
	
	public static void mergeSort(int[] input) {
		int size = input.length;
		if(size<2) return;
		int mid = size/2;
		int[] left = new int[mid];
		int[] right = new int[size-mid];
		
		for(int i=0; i<mid; i++) {
			left[i] = input[i];
		}
		for(int i=0; i<size-mid; i++) {
			right[i] = input[mid+i];
		}
		
		mergeSort(left);
		mergeSort(right);
		// merge!
		merge(left, right, input);
	}
	
	public static void merge(int[]left, int[]right, int[] res) {
		int leftSize = left.length;
		int rightSize = right.length;
		int i=0, j=0, k=0; // i = left array pointer, j = right array pointer, k = result array pointer
		
		while(i<leftSize && j<rightSize) {
			if(left[i]<=right[j]) {
				res[k] = left[i];
				k++;
				i++;
			} else {
				res[k] = right[j];
				k++;
				j++;
			}
		}
		
		// What if any of the left/right array has some elements left?
		while(i<leftSize) {
			res[k] = left[i];
			k++;
			i++;
		}
		while(j<rightSize) {
			res[k] = right[j];
			k++;
			j++;
		}
	}
	
	public static void main(String[]args) {
		int[]input = {2,4,5,52,14,63,7};
		mergeSort(input);
		for(int i=0; i<input.length; i++) {
		System.out.print(" "+input[i]);
		}
	}

}
