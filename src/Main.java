//comment

import java.util.Scanner;

import javax.swing.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	
	private static JFrame frame;
	private static JPanel inputPanel;
	private static JTextField tfSource;
	private static JTextField tfComparing;
	private static JLabel lblTitle;
	private static JLabel lblSource;
	private static JLabel lblComparing;
	private static JButton btnStart;
	private static JLabel lblError;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 467, 164);
		frame.setResizable(false);
		
		inputPanel = new JPanel();
		inputPanel.setBackground(new Color(250, 250, 210));
		inputPanel.setLayout(null);
		frame.getContentPane().add(inputPanel);
		
		lblTitle = new JLabel("Damerau-Levenshtein Edit Distance");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(199, 21, 133));
		lblTitle.setBounds(10, 11, 432, 14);
		inputPanel.add(lblTitle);
		
		lblSource = new JLabel("Source String");
		lblSource.setBounds(10, 47, 81, 14);
		inputPanel.add(lblSource);
		
		lblComparing = new JLabel("Comparing String");
		lblComparing.setBounds(10, 72, 107, 14);
		inputPanel.add(lblComparing);
		
		tfSource = new JTextField();
		tfSource.setBounds(113, 43, 329, 20);
		inputPanel.add(tfSource);
		tfSource.setColumns(10);
		
		tfComparing = new JTextField();
		tfComparing.setColumns(10);
		tfComparing.setBounds(113, 66, 329, 20);
		inputPanel.add(tfComparing);
		
		btnStart = new JButton("START");
		btnStart.setBackground(new Color(255, 192, 203));
		btnStart.setBounds(353, 98, 89, 23);
		inputPanel.add(btnStart);
		
		lblError = new JLabel("Both fields must be filled out.");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(10, 97, 333, 14);
		inputPanel.add(lblError);
		lblError.setVisible(false);
		
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("PRESSED");
				if(!tfSource.getText().isEmpty() && !tfComparing.getText().isEmpty()) {
					String first = tfSource.getText();
					String second = tfComparing.getText();
					first = first.toUpperCase();
					second = second.toUpperCase();
					Damerau damerauThread = new Damerau(first, second);
					damerauThread.tryThread();
					lblError.setVisible(false);
				}
				else{ lblError.setVisible(true);
				System.out.println("NOT WORKING");
				}
			}
			
		});
	}
	
	public Main() {
		initialize();
	}
	
	public static void main(String[] args){
		
		System.out.println("RUNNING");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
				
	}
}
