package revisited;

public class quickSort {
	
	public static void quickSort(int[] arr, int low, int high) {
		if(arr.length==0 || arr==null) return;
		if(low>=high) return;
		
		int mid = low+(high-low)/2;
		int pivot = arr[mid];
		
		int i = low, j=high;
		while(i<=j) {
			while(arr[i]<pivot) {
				i++;
			}
			while(arr[j]>pivot) {
				j--;
			}
			
			if(i<=j) {
				// swap elements
				swap(i, j, arr);
				i++;
				j--;
			}
		}
		
		//Recursively call quickSort
		if(low<j) {
			quickSort(arr, low, j);
		}
		if(high>i) {
			quickSort(arr, i, high);
		}
	}
	
	public static void swap(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]input = {1,2,45,5,53,2,342,6};
		
		quickSort(input, 0, input.length-1);
		for(int i=0; i<input.length; i++) {
			System.out.print(" "+input[i]);
		}

	}

}
