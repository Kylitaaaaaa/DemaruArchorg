import java.util.ArrayList;

public class Damerau{
	
	private String 	first;				// source string
	private String 	second;				// comparing string
	private int[][] damerauMatrix; 		
	private Boolean isChecked = false;	// for monitoring threads
	
	private int m, n;
	
	public Damerau(String first, String second){
		if ((first.length() > 0 || !first.isEmpty())  || 
			(second.length() > 0 || !second.isEmpty()))
	        {
				this.first = first;
				this.second = second;
				this.m = first.length();
				this.n = second.length();
	        }
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
	
	// to be commented out //
	private void OASDamerau()
    {
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