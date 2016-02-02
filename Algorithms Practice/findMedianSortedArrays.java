package algopractice;

public class findMedianSortedArrays {
	
	public static double findMedianSortedArrays(int[]nums1 , int[]nums2) {
		int []answer = new int[nums1.length+nums2.length];
		int i = nums1.length-1;
		int j = nums2.length-1;
		int k = answer.length;

		while(k>0) {
			answer[--k] = (j<0 || (i>=0 && nums1[i] >= nums2[i])) ? nums1[i--] : nums2[j--];
		}
		double median = 0.0;
		
		k = answer.length;
		if(answer.length%2 ==0){
			median = (answer[k/2-1]+answer[k/2])/2.0;
		} else {
			median = answer[k/2];
		}
		System.out.println(median);
		return median;
		
	}	
	
	public static void main(String[]args) {
		int[] nums1 = {1,2,3,4,5};
		int[] nums2 = {6,7,8,9,10};
		findMedianSortedArrays(nums1, nums2);
	}

}
