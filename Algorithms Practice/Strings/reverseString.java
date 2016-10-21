package algopractice;

public class reverseString {
	
	
	public static String reverseString(String str) {
		if(str ==null || str.isEmpty()) throw new IllegalArgumentException("empty string");
		boolean hasPeriod = str.contains(".");
		if(hasPeriod) str = str.substring(0, str.length()-1);
		String [] array = str.split("");
		int bound = array.length -1;
		StringBuilder sb = new StringBuilder("");
		for (int i= bound; i>0; i--){
			sb.append(array[i]+ "");
		}
		sb.append(array[0]);
		
		return (hasPeriod) ? sb.append(".").toString() : sb.toString(); 
	}

	
	public static void main(String[]args) {
		
		String a = "STRING IS REVERSED";
		System.out.println(new reverseString().reverseString(a));
		
		
	}
}
