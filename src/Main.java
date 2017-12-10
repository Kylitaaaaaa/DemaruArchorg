import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class Main {
	
	private static JFrame inputFrame;
	private static JTextField tfSourceInput;
	private static JTextField tfCompareInput;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void initialize() {
		inputFrame =  new JFrame();
		inputFrame.getContentPane().setLayout(null);
		inputFrame.setVisible(true);
		JLabel lblError = new JLabel("Both fields must be filled out.");
		JPanel panel = new JPanel();
		JLabel lblTitle = new JLabel("DAMERAU - LEVENSHTEIN SIMULATOR");
		JLabel lblSourceInput = new JLabel("Source String");
		JLabel lblCompareInput = new JLabel("Compare String");
		
		panel.setBackground(new Color(255, 182, 193));
		panel.setBounds(10, 11, 414, 227);
		inputFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		tfSourceInput = new JTextField();
		tfSourceInput.setBounds(105, 84, 271, 20);
		panel.add(tfSourceInput);
		tfSourceInput.setColumns(10);
		
		lblTitle.setForeground(new Color(128, 0, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 23, 414, 14);
		panel.add(lblTitle);
		
		tfCompareInput = new JTextField();
		tfCompareInput.setColumns(10);
		tfCompareInput.setBounds(105, 111, 271, 20);
		panel.add(tfCompareInput);
		
		lblSourceInput.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSourceInput.setBounds(10, 87, 95, 14);
		panel.add(lblSourceInput);
		
		lblCompareInput.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCompareInput.setBounds(10, 113, 95, 14);
		panel.add(lblCompareInput);
		
		JButton btnStart = new JButton("START");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnStart.setForeground(new Color(199, 21, 133));
		btnStart.setBounds(10, 192, 205, 23);
		panel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfSourceInput.getText().isEmpty() || !tfCompareInput.getText().isEmpty()) {
				Damerau damerauThread = new Damerau(tfSourceInput.getText(), 
										tfCompareInput.getText());
				damerauThread.tryThread();
				lblError.setVisible(false);
				}
				else {
					lblError.setVisible(true);
				}
				
			}
			
		});
		
		JButton btnAdd = new JButton("Add Matrix");
		btnAdd.setBackground(new Color(224, 255, 255));
		btnAdd.setBounds(315, 193, 89, 23);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: addMatrix function here.
				
			}
			
		});

		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(105, 142, 212, 14);	
		panel.add(lblError);
		lblError.setVisible(false);
	}
	
	public static void main(String[] args){
	
		Scanner sc=new Scanner(System.in);
//		System.out.print("First word: ");
//		String first = sc.next();
		
//		System.out.print("Second word: ");
//		String second = sc.next();
		
		String first = "mom";
		String second = "dad";
		first = first.toUpperCase();
		second = second.toUpperCase();
		System.out.println("first: " + first);
		System.out.println("second: " + second);
		String temp = first;
		first = second;
		second = temp;
		
		long startTime = System.currentTimeMillis();
		//serial damerau
		/*
		Damerau d2 = new Damerau(first, second);
		d2.OASDamerau();
		*/
		
		//parallel damerau
		Damerau d1 = new Damerau(first, second);
		d1.tryThread();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("time: " + totalTime);
	
//		initialize();
	}
}
