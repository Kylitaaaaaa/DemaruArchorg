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
	
	public DamerauThread(int n,
						ArrayList<Integer> alphaNum, 
						ArrayList<Character> alphabet,
						String first,
						String second,
						int i,
						int d[][]){
		
		this.n = n;
		this.alphaNum = alphaNum;
		this.alphabet = alphabet;
		this.first = first;
		this.second = second;
		this.i = i;
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
	      
	      Damerau.updateD(i, j, (Math.min(d[i][j-1] + 1, Math.min(d[i-1][j] + 1, d[i-1][j-1] + cost))));
    	  d[i][j] = Math.min(d[i][j-1] + 1,                 // insertion
                  Math.min(d[i-1][j] + 1,        // deletion
                           d[i-1][j-1] + cost)); // substitution
    	  
	      if(i1 > 0 && j1 > 0) {
	    	  Damerau.updateD(i, j, (Math.min(d[i][j], d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1))); //transposition
	    	  d[i][j] = Math.min(d[i][j], d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1); //transposition
	      }
	      
	    }
	    
	    Damerau.setAlphaNum(Damerau.alphabet.indexOf(Damerau.first.charAt(i-1)), i);
	    //alphaNum.set(Damerau.alphabet.indexOf(Damerau.first.charAt(i-1)), i);
	    System.out.println("Done " + name);
	    //Damerau.displayMatrixD();
	    Damerau.displayMatrixD(name);
	    System.out.println("Done print " + name);
	}
	
	public void updateD(int i, int j, int val){
		Damerau.d[i][j] = val;
	}

}
