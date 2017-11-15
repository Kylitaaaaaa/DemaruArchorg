import java.util.ArrayList;

public class Damerau{
	
	private String 	first;				// source string
	private String 	second;				// comparing string
	private int[][] damerauMatrix; 		
	private Boolean isChecked = false;	// for monitoring threads
	
	public Damerau(String first, String second){
		if ((first.length() > 0 || !first.isEmpty())  || 
			(second.length() > 0 || !second.isEmpty()))
	        {
				this.first = first;
				this.second = second;
	        }
	}
	
	private void displayMatrix()
    {
        System.out.println("  "+compOne);
        for (int y = 0; y <= compTwo.length(); y++)
        {
            if (y-1 < 0) System.out.print(" "); else System.out.print(compTwo.charAt(y-1));
            for (int x = 0; x <= compOne.length(); x++)
            {
                System.out.print(matrix[x][y]);
            }
            System.out.println();
        }
    }
	 
	private void OASDamerau()
    {
		int m = first.length();
		int n = second.length();
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
	
	 int res = -1;
     int INF = compOne.length() + compTwo.length();

     matrix = new int[compOne.length()+1][compTwo.length()+1];

     for (int i = 0; i < compOne.length(); i++)
     {
         matrix[i+1][1] = i;
         matrix[i+1][0] = INF;
     }

     for (int i = 0; i < compTwo.length(); i++)
     {
         matrix[1][i+1] = i;
         matrix[0][i+1] = INF;
     }

     int[] DA = new int[24];

     for (int i = 0; i < 24; i++)
     {
         DA[i] = 0;
     }

     for (int i = 1; i < compOne.length(); i++)
     {
         int db = 0;

         for (int j = 1; j < compTwo.length(); j++)
         {

             int i1 = DA[compTwo.indexOf(compTwo.charAt(j-1))];
             int j1 = db;
             int d = ((compOne.charAt(i-1)==compTwo.charAt(j-1))?0:1);
             if (d == 0) db = j;

             matrix[i+1][j+1] = Math.min(Math.min(matrix[i][j]+d, matrix[i+1][j]+1),Math.min(matrix[i][j+1]+1,matrix[i1][j1]+(i - i1-1)+1+(j-j1-1)));
         }
         DA[compOne.indexOf(compOne.charAt(i-1))] = i;
     }
      
     return matrix[compOne.length()][compTwo.length()];
}
