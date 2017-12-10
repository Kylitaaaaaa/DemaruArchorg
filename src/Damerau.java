import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;

import com.sun.prism.paint.Color;

public class Damerau{
	
	public static  String 	first;				// source string
	public static  String 	second;				// comparing string
	public static  int[][]  damerauMatrix; 		
	public static  Boolean  isChecked = false;	// for monitoring threads
	
	public static  int m, n;
	public static  ArrayList<Character> alphabet = new ArrayList<Character>();
	public static  ArrayList<Integer> alphaNum = new ArrayList<Integer>();
	
	public static  int [][] d;
	
	// UI Part
		private static JFrame frame;
		private static JPanel inputPanel;
		private static JTextArea textArea;
		private static JLabel lblTitle;
	
	public static void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 349, 326);
		frame.setResizable(false);
		
		inputPanel = new JPanel();
//		inputPanel.setBackground(new Color(250, 250, 210));
		inputPanel.setLayout(null);
		frame.getContentPane().add(inputPanel);
		
		textArea = new JTextArea();
//		textArea.setForeground(new Color(128, 0, 0));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setBounds(10, 36, 321, 253);
		inputPanel.add(textArea);
		
		lblTitle = new JLabel("Matrix Display");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
//		lblTitle.setForeground(new Color(199, 21, 133));
		lblTitle.setBounds(10, 11, 321, 14);
		inputPanel.add(lblTitle);
	}
	
	
	public Damerau(String first, String second){
		if ((first.length() > 0 || !first.isEmpty())  || 
			(second.length() > 0 || !second.isEmpty()))
	        {
				this.first = first;
				this.second = second;
				this.m = first.length();
				this.n = second.length();
	        }
		initialize();
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
		System.out.println("RUNNING");
		frame.setVisible(true);
		String output = new String();
		
        output = output + "   " + first + "\n";
        for (int y = 0; y <= n; y++){
            if (y-1 < 0) output = output + "  "; 
            else output = output + second.charAt(y-1) + " ";
            
            for (int x = 0; x <= m; x++){
            	output = output + d[x][y];
            }
            output = output + "\n";
        }
        
        textArea.setText(output);
        textArea.setEditable(false);
        
        
        
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
				Damerau.displayMatrixD();
//				System.out.println("done : " + i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
		}
		
		
		displayMatrixD("final");
		JOptionPane.showMessageDialog(frame, "Distance: " + d[m][n]);
//		System.out.println("distance: " + d[m][n]);
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