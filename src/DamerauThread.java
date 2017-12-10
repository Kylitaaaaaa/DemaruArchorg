import java.util.ArrayList;
import java.util.List;

public class DamerauThread implements Runnable{
	
	private int n;
	private ArrayList<Integer> alphaNum;
	private ArrayList<Character> alphabet;
	private String first;
	private String second;
	private int i;
	private int j;
	private int db;
	private int d[][];
	
	//for backtracking hopefully
	private Boolean isDeletion = false;
	private Boolean isInsertion = false;
	private Boolean isSubstitution = false;
	private Boolean isTransposition = false;	
	
	private int deletionScore = 0;
	private int insertionScore = 0;
	private int orig = 0;
	private int substitutionScore = 0;
	private int transpositionScore = 0;
	
	public DamerauThread(int n,
						ArrayList<Integer> alphaNum, 
						ArrayList<Character> alphabet,
						String first,
						String second,
						int i,
						int j,
						int d[][]){
		
		this.n = n;
		this.alphaNum = alphaNum;
		this.alphabet = alphabet;
		this.first = first;
		this.second = second;
		this.i = i;
		this.j = j;
		this.d = d;
		
	}
	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		String name = thread.getName();
		
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
	      
	      int tempi1 = i1;
	      int tempj1 = j1;
	      insertionScore = d[i][j-1] + 1;
	      deletionScore = d[i-1][j] + 1;
	      substitutionScore = d[i-1][j-1] + cost;
	      
		      if(i<Damerau.m && j<Damerau.n && first.charAt(i) == second.charAt(j-1) && first.charAt(i-1) == second.charAt(j)){
		    		  transpositionScore = d[tempi1][tempj1] + (i-tempi1-1) + (j-tempj1-1) + 1;
//		    		  Damerau.updateD(i, j, Math.min(transpositionScore, (Math.min(insertionScore, Math.min(deletionScore, substitutionScore)))));
		        	  d[i][j] = Math.min(transpositionScore, (Math.min(insertionScore, Math.min(deletionScore, substitutionScore))));
		      }
		      else{
//		    	  Damerau.updateD(i, j, (Math.min(insertionScore, Math.min(deletionScore, substitutionScore))));
		    	  d[i][j] = Math.min(insertionScore,        // insertion
		                    Math.min(deletionScore,        // deletion
		                           substitutionScore));   // substitution
		      }
	    }
	    
	    Damerau.setAlphaNum(Damerau.alphabet.indexOf(Damerau.first.charAt(i-1)), i);
	    
	}
	
	public void updateD(int i, int j, int val){
		Damerau.d[i][j] = val;
	}

}
