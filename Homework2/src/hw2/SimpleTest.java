package hw2;

public class SimpleTest {

	public static void main(String[] args) {
		System.out.print(countOccurrences("aa", "aaaaaa", true));
		
	}
	public static int countOccurrences(String t, String s, boolean allowOverlap)
	  {
		
		int count=0;
	    for(int i =0; i< s.length() ; i++) {
	    	if (allowOverlap) {
	    		if(t.charAt(i) == s.charAt(i) && s.charAt(i+1) ==t.charAt(i+1 )) {
	    				
	    				count++;
	    		}
	    		if(t.charAt(i) != s.charAt(i) || s.charAt(i+1) !=t.charAt(i+1 )) {
	    			
	    		}
	    	}
	    		
	    }
	    return count;
	  }

}
