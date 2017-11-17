import java.util.ArrayList;
import java.util.List;

public class Damerau{
	
	public static String 	first;				// source string
	public static  String 	second;				// comparing string
	public static  int[][] damerauMatrix; 		
	public static  Boolean isChecked = false;	// for monitoring threads
	
	public static  int m, n;
	public static  ArrayList<Character> alphabet = new ArrayList<Character>();
	public static ArrayList<Integer> alphaNum = new ArrayList<Integer>();
	
	public static  int [][] d;
	
	
	public Damerau(String first, String second){
		if ((first.length() > 0 || !first.isEmpty())  || 
			(second.length() > 0 || !second.isEmpty()))
	        {
				this.first = first;
				this.second = second;
				this.m = first.length();
				this.n = second.length();
	        }
		
		prepare();
		
		
	}
	
	public void prepare(){
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
		
		System.out.println("alphanum size: " + alphaNum.size());
		
		for(int i=0; i<alphaNum.size(); i++)
			alphaNum.set(i, 0);
		
		
		d = new int [m+1][n+1];
		
		for (int i = 0; i <= m; i++) 
		    d[i][0] = i;
		
		for (int i = 0; i <= n; i++) 
		    d[0][i] = i;
		
		displayMatrixD();
		System.out.println("\n");
		printAlphabet();
		printAlphabetNum();
	}
	
	public void printAlphabet(){
		System.out.println("printing alphabet");
		for(int i=0; i<alphabet.size(); i++)
			System.out.print(alphabet.get(i) + "\t");
	}
	
	public void printAlphabetNum(){
		System.out.println("printing alphanum");
		for(int i=0; i<alphaNum.size(); i++)
			System.out.println(alphaNum.get(i));
	}
	
	private void displayMatrix()
    {
        System.out.println("  " + first);
        for (int y = 0; y <= n; y++){
            if (y-1 < 0) System.out.print(" "); 
            else System.out.print(second.charAt(y-1));
            
            for (int x = 0; x <= m; x++){
                System.out.print(damerauMatrix[x][y]);
            }
            System.out.println();
        }
    }
	
	static void displayMatrixD()
    {
        System.out.println("  " + first);
        for (int y = 0; y <= n; y++){
            if (y-1 < 0) System.out.print(" "); 
            else System.out.print(second.charAt(y-1));
            
            for (int x = 0; x <= m; x++){
                System.out.print(d[x][y]);
            }
            System.out.println();
        }
    }
	
	// to be commented out //
	void OASDamerau()
    {
		/*
        int cost = 1;
        int deletionScore,
        	subtractionScore, 
        	insertionScore,
        	transpositionScore;
         
        damerauMatrix = new int[m+1][n+1];
 
        // initialize d
        for (int i = 0; i <= m; i++){
        	damerauMatrix[i][0] = i;
        }
 
        for (int i = 0; i <= n; i++){
        	damerauMatrix[0][i] = i;
        }
 
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (first.charAt(i-1) == second.charAt(j-1)){
                    cost = 0;
                }
 
                deletionScore = damerauMatrix[i-1][j]+1;
                insertionScore = damerauMatrix[i][j-1]+1;
                subtractionScore = damerauMatrix[i-1][j-1]+cost;
                transpositionScore = damerauMatrix[i-2][j-2]+cost;
 
                damerauMatrix[i][j] = Math.min(deletionScore,
                        				Math.min(insertionScore,
                        						 subtractionScore));
 
                if ((i > 1) && (j > 1) && (first.charAt(i-1) == second.charAt(j-2)) 
                					   && (first.charAt(i-2) == second.charAt(j-1))){
                    damerauMatrix[i][j] = Math.min(damerauMatrix[i][j], transpositionScore);
                }
            }
        }
 
        isChecked = true;
        System.out.println("distance: " + damerauMatrix[m][n]);
        displayMatrix();
        
        */
		
		
		
		
		//initialize d
		
		
		
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
		    
		    System.out.println("i : " + i);
		    displayMatrixD();
		  }
		
		System.out.println("distance: " + d[m][n]);
		System.out.println("done \n\n\n");
		
		displayMatrixD();
    }
	
	public void tryThread(){
//		Thread t1 = new Thread(new DamerauThread(), "t1");
//		Thread t2 = new Thread(new DamerauThread(), "t2");
//		Thread t3 = new Thread(new DamerauThread(), "t3");
//		
//		t1.start();
//		t2.start();
//		t3.start();
		
		List <Thread> tList = new ArrayList <Thread> ();
		for (int i = 1; i <= m; i++) {
			//Thread t = new Thread(new DamerauThread(i), "i : " + i);
			Thread t = new Thread(new DamerauThread(n, alphaNum, alphabet, first, second, i, d), "i : " + i);
			tList.add(t);
			t.start();
			
		}
		
		
		System.out.println("distance: " + d[m][n]);
		
		while(tList.size() != 0){
			for(int i=0; i<tList.size(); i++)
				if(!tList.get(i).isAlive()){
					System.out.println("\nTarget : " + i);
					tList.remove(i);
					displayMatrixD();
				}
		}
		if(tList.size() ==0){
			displayMatrixD();
		}
		
	}
	
	public void tryThread2(){
		List <Thread> tList = new ArrayList <Thread> ();
		
		for (int i = 1; i <= m; i++) {
			int db = 0;
		    for (int j = 1; j <= n; j++) {
		    	Thread t = new Thread(new TrialThread2(n, alphaNum, alphabet, first, second, i, j, d), "i : " + i);
				tList.add(t);
				t.start();
		    }
		    alphaNum.set(alphabet.indexOf(first.charAt(i-1)), i);
		  }
		
		System.out.println("distance: " + d[m][n]);
		
		damerauMatrix = d;
		displayMatrix();
		
		
		
		while(tList.size() != 0){
			for(int i=0; i<tList.size(); i++)
				if(!tList.get(i).isAlive()){
					System.out.println("\nTarget : " + i);
					tList.remove(i);
					damerauMatrix = d;
					displayMatrix();
				}
		}
		if(tList.size() ==0){
			damerauMatrix = d;
			displayMatrix();
		}
	}
	
	public static void updateD(int i, int j, int val){
		d[i][j] = val;
	}
	
	public static void setAlphaNum(int index, int i){
		alphaNum.set(index, i);
	}
    

	public int DHDamerau()
	{
		 int INF = m+n;
		 int deletionScore,
			 	subtractionScore, 
			 	insertionScore,
			 	transpositionScore;
		
		 damerauMatrix = new int[m+1][n+1];
		
		 // initialize d
		 for (int i = 0; i < m; i++){
			 damerauMatrix[i+1][1] = i;
			 damerauMatrix[i+1][0] = INF;
		 }
		
		 for (int i = 0; i < n; i++){
			 damerauMatrix[1][i+1] = i;
			 damerauMatrix[0][i+1] = INF;
		 }
		
		 int[] DA = new int[24];
		
		 for (int i = 0; i < 24; i++){
		     DA[i] = 0;
		 }
		
		 for (int i = 1; i < m; i++){
		     int db = 0;
		
		     for (int j = 1; j < n; j++)
		     {
		
		         int i1 = DA[second.indexOf(second.charAt(j-1))];
		         int j1 = db;
		         
		         int d = ((first.charAt(i-1) == first.charAt(j-1)) ? 0 : 1);
		         if (d == 0) db = j;
		         
		         deletionScore = damerauMatrix[i][j]+d;
		         insertionScore = damerauMatrix[i+1][j]+1;
		         subtractionScore = damerauMatrix[i][j+1]+1;
		         transpositionScore = damerauMatrix[i1][j1]+(i-i1-1)+1+(j-j1-1);
		
		         damerauMatrix[i+1][j+1] = Math.min(Math.min(deletionScore, 
		        		 									 insertionScore),
		        		 					   		Math.min(subtractionScore,
		        		 					   				 transpositionScore));
		     }
		     DA[first.indexOf(first.charAt(i-1))] = i;
		 }
		  
		 isChecked = true;
		 displayMatrix();
		 
	     return damerauMatrix[m][n];
	}
	
	
}