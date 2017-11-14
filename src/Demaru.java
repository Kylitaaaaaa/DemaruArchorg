import java.util.ArrayList;

public class Demaru {
	public Demaru(){
		
	}
	
	public void doDemaru(String first, String second){
		int m = first.length();
		int n = second.length();
		
		
		//initialize d
		int [][] d = new int [m+1][n+1];
		for (int i = 0; i <= m; i++) 
		    d[i][0] = i;
		
		for (int i = 0; i <= n; i++) 
		    d[0][i] = i;
		
		//initialize alphabet
		ArrayList<Character> alphabet = new ArrayList<Character>();
		ArrayList<Integer> alphaNum = new ArrayList<Integer>();
		
		for (int i = 0; i < m; i++) 
			if(! alphabet.contains(first.charAt(i))){
				alphabet.add(first.charAt(i));
				alphaNum.add(0);
			}
		
		for (int i = 0; i < n; i++) 
			if(!alphabet.contains(second.charAt(i))){
				alphabet.add(second.charAt(i));
				alphaNum.add(0);
			}
		
		
		for (int i = 1; i <= m; i++) {
			int db = 0;
		    for (int j = 1; j <= n; j++) {
		      int i1 = alphaNum.get(alphabet.indexOf(second.charAt(j-1)));
		      
		      int j1 = db;
		      int cost = 0;
		      
		      if (first.charAt(i-1) == second.charAt(j-1)) { // Subtract one to start at strings' index zero instead of index one
		        db = j;
		      } else {
		        cost = 1;
		      }
		      d[i][j] = Math.min(d[i][j-1] + 1,                 // insertion
		                         Math.min(d[i-1][j] + 1,        // deletion
		                                  d[i-1][j-1] + cost)); // substitution
		      if(i1 > 0 && j1 > 0) {
		        d[i][j] = Math.min(d[i][j], d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1); //transposition
		      }
		    }
		    alphaNum.set(alphabet.indexOf(first.charAt(i-1)), i);
		  }
		
		System.out.println("distance: " + d[m][n]);
	
	}
	
	
}
