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
	
	/*
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
//	      
//	      System.out.println("insert: " + d[i][j-1] + 1);
//	      System.out.println("delete: " + d[i-1][j] + 1);
//	      System.out.println("sub: " + d[i-1][j-1] + cost);
	      
	      insertionScore = d[i][j-1] + 1;
	      deletionScore = d[i-1][j] + 1;
	      substitutionScore = d[i-1][j-1] + cost;
	      
	      
	      Damerau.updateD(i, j, (Math.min(insertionScore, Math.min(deletionScore, substitutionScore))));
    	  d[i][j] = Math.min(insertionScore,        // insertion
                    Math.min(deletionScore,        // deletion
                           substitutionScore));   // substitution
    	  
	      if(i1 > 0 && j1 > 0) {
	    	  System.out.println("trans: " + d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1);
	    	  transpositionScore = d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1;
	    	  
	    	  Damerau.updateD(i, j, (Math.min(d[i][j], d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1))); //transposition
	    	  d[i][j] = Math.min(d[i][j], d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1); //transposition
	      }
	      
	      //for backtracking hopefully
//	      if( d[i][j] == insertionScore ) isInsertion = true;
//	      else if( d[i][j] == deletionScore ) isDeletion = true;
//	      else if( d[i][j] == substitutionScore ) isSubstitution = true;
//	      else isTransposition = true;
	      
	      //write the result onto the output box
	      
	    }
	    
	    Damerau.setAlphaNum(Damerau.alphabet.indexOf(Damerau.first.charAt(i-1)), i);
	    //alphaNum.set(Damerau.alphabet.indexOf(Damerau.first.charAt(i-1)), i);
	    System.out.println("Done " + name);
	    //Damerau.displayMatrixD();
//	    Damerau.displayMatrixD(name);
	    System.out.println("Done print " + name);
	}
	*/
	
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
//	      
	      
	      int tempi1 = i1;
	      int tempj1 = j1;
//	      System.out.println("i1: " + i1);
	      insertionScore = d[i][j-1] + 1;
	      deletionScore = d[i-1][j] + 1;
	      substitutionScore = d[i-1][j-1] + cost;
//	      if a[i] == b[j-1] && a[i-1] == b[j]
//	      System.out.println("i : "  + i + " \t j : " + j);
//	      
//	      
//	      System.out.println("insert: " + insertionScore);
//	      System.out.println("delete: " + deletionScore);
//	      System.out.println("sub: " + substitutionScore);
	      
	      
		      if(i<Damerau.m && j<Damerau.n && first.charAt(i) == second.charAt(j-1) && first.charAt(i-1) == second.charAt(j)){
//		    	  System.out.println("same: " + first.charAt(i));
		    		  transpositionScore = d[tempi1][tempj1] + (i-tempi1-1) + (j-tempj1-1) + 1;
		    		  Damerau.updateD(i, j, Math.min(transpositionScore, (Math.min(insertionScore, Math.min(deletionScore, substitutionScore)))));
		        	  d[i][j] = Math.min(transpositionScore, (Math.min(insertionScore, Math.min(deletionScore, substitutionScore))));
		      }
		      else{
//		    	  System.out.println("not same " + j + " : " + insertionScore + "\t" + deletionScore + "\t" + substitutionScore);
		    	  Damerau.updateD(i, j, (Math.min(insertionScore, Math.min(deletionScore, substitutionScore))));
		    	  d[i][j] = Math.min(insertionScore,        // insertion
		                    Math.min(deletionScore,        // deletion
		                           substitutionScore));   // substitution
		      }
		      
	      
	      
	      
	      
    	  /*
	      if(tempi1 > 0 && tempj1 > 0) {
//	    	  System.out.println("trans: " + d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1);
//	    	  transpositionScore = d[tempi1-1][tempj1-1] + (i-tempi1-1) + (j-tempj1-1) + 1;
	    	  orig = d[i][j];
	    	  Damerau.updateD(i, j, (Math.min(orig, transpositionScore))); //transposition
	    	  d[i][j] = Math.min(orig, transpositionScore); //transposition
	    	  
	    	  
//	    	  Damerau.updateD(i, j, (Math.min(d[i][j], d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1))); //transposition
//	    	  d[i][j] = Math.min(d[i][j], d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1); //transposition
	      }
	      */
	      
	      //for backtracking hopefully
//	      if( d[i][j] == insertionScore ) isInsertion = true;
//	      else if( d[i][j] == deletionScore ) isDeletion = true;
//	      else if( d[i][j] == substitutionScore ) isSubstitution = true;
//	      else isTransposition = true;
	      
	      //write the result onto the output box
	      
	    }
	    
	    Damerau.setAlphaNum(Damerau.alphabet.indexOf(Damerau.first.charAt(i-1)), i);
	    //alphaNum.set(Damerau.alphabet.indexOf(Damerau.first.charAt(i-1)), i);
//	    System.out.println("Done " + name);
	    //Damerau.displayMatrixD();
//	    Damerau.displayMatrixD(name);
//	    System.out.println("Done print " + name);
	}
	
	
	
	
	public void updateD(int i, int j, int val){
		Damerau.d[i][j] = val;
	}

}
