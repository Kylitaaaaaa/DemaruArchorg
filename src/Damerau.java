import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Damerau{
	
	public static  String 	first;				// source string
	public static  String 	second;				// comparing string
	public static  int[][]  damerauMatrix; 		
	public static  Boolean  isChecked = false;	// for monitoring threads
	
	public static  int m, n;
	public static  ArrayList<Character> alphabet = new ArrayList<Character>();
	public static  ArrayList<Integer> alphaNum = new ArrayList<Integer>();
	
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
		
//		System.out.println("alphanum size: " + alphaNum.size());
		
		for(int i=0; i<alphaNum.size(); i++)
			alphaNum.set(i, 0);
		
		
		d = new int [m+1][n+1];
		
		for (int i = 0; i <= m; i++) 
		    d[i][0] = i;
		
		for (int i = 0; i <= n; i++) 
		    d[0][i] = i;
		
		System.out.println("\n");
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
	
	static void displayMatrixD(String name)
    {
		String temp = "";
		
		temp = temp + "\t" + first;
		
        for (int y = 0; y <= n; y++){
            if (y-1 < 0) 
            	temp = temp + "\t";
            else 
            	temp = temp + second.charAt(y-1) + "";
            
            for (int x = 0; x <= m; x++){
            	temp = temp + d[x][y] + "";
            }
            temp = temp + "\n";
        }
        
        JFrame parent = new JFrame();
        parent.setVisible(true);
        
        JTextArea area=new JTextArea(temp);  
        area.setBounds(10,30, 200,200);  
        area.setEditable(false);
        
        
        parent.add(area);  
        parent.setSize(300,300);  
        parent.setLayout(null);  
        parent.setVisible(true);  
        
        
        
//        JOptionPane.showMessageDialog(parent, temp, name,  JOptionPane.PLAIN_MESSAGE);
    }
	
	// to be commented out //
	void OASDamerau()
    {
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
		
		
		System.out.println("done \n\n\n");
		
		displayMatrixD();
		System.out.println("distance: " + d[m][n]);
    }
	
	public void tryThread(){
		for(int i = 1; i <= m; i++){
			ExecutorService es = Executors.newCachedThreadPool();
			for (int j = 1; j <= n; j++) {
				es.execute(new Thread(new DamerauThread(n, alphaNum, alphabet, first, second, i, j, d), "i : " + i));
			}
			es.shutdown();
			try {
				boolean finshed = es.awaitTermination(1, TimeUnit.MINUTES);
				Damerau.displayMatrixD(i+"");
//				System.out.println("done : " + i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
		}
		displayMatrixD();
		System.out.println("distance: " + d[m][n]);
	}
	
	
	public static void updateD(int i, int j, int val){
		d[i][j] = val;
	}
	
	public static void setAlphaNum(int index, int i){
		alphaNum.set(index, i);
	}
    
	public static int[][] getDamerauMatrix() {
		return damerauMatrix;
	}

	public static void setDamerauMatrix(int[][] damerauMatrix) {
		Damerau.damerauMatrix = damerauMatrix;
	}
	
	
	
	
}