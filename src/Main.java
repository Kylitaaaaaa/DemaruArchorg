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
		System.out.print("First word: ");
//		String first = sc.next();
		
		System.out.print("Second word: ");
//		String second = sc.next();
		
//		String first = "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";
//		String second = "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";
		String first = "EVvCLeIA76NuS85ZyaEgPhh9Of4PtfncAI2cznZjH8ghXuLlzDVSDmbQXeq1L7HGfGi9gOE0PSYm5PDQ12fnqzVyxROniv0SVO0FA5Px1HBOCGZxck6dD5cgoNS4WBXQYkn9R2hZQDZB5vh2s1nkJWW1LgcTyVGgMU70d3XrHTBtXzL9AbplUtYncez8xm3pNIYFcsu0ecmG4sA6Vk5KJoBKbVjeykqXE5DRXQfHI5Wc6XB6hDJ4E6iObJcExRZiGTsUzNFdlJW85YndIjgvMeaQGwGRsZZLMWqlo5dFrXaMliGls423fErvl6LH4W0XFaMZdOuOqD7gc49txZrBmh2ID6l63IfHPJrervOW6RaupARRYxByqpaBM9Ipxq3zvPVsJ4Ot1Cel4BuCO4Ci61Qo1rgJlwalrt8LcOV49NSPRGjM5fzyXXhsK347QQlkfIupafqEGCcYljqVAN1SyygxjZCRoqHo5tDa0x0iylMicDBCXk3B36m6XWhYQb2npb6CZUn062G76bkWDHXh5qin2wBug9uzhH8mR5re4Vla1eQMNRvwgtd1QMaeDEkDkIVtYQoEj3Osq8dYqJuIs1fh0hPX07vWSRtfimbrDIl1S0LyVcXd5g5fQK6MIQkDA5aBZQg4xFhMBVuI4gZIgoMDWr4cb4Ttp5M2hxP32s46nIXiUXaMNqXDwuqNo6A9gg2G2KslIMxFYP4bcUWNAUgLQBPczydY6Vq7oQmPlDoZm7JXDcwJAwQHtOKcyO4jhaDK9sbSwWHPMt3BRLEf6KUU7e4yGPAPZCeasLpzEaMmTuFDD9azItXpH0Q99n7FKalVV3b33zuPwjAlnv8yeAC432IjbcR9dQblLf4gB8DXRXd1EnY1s1B0IaLvIfbPQsK3JYirQM8zyJpFhaYsJIlOnEgpats5dE5C3ns6DUZ3UCs1CeEDInbW5iIFrLn1lKFvf6eCC7hi28DdvNlYoJrv6BXnzc2SL6XLLya2";
		String second = "OTEi6knZ7UZb72V01jvWKKViyaOlwHKfl81a72TmIxvRFGvocxqz4v5UIw6Sjs2TNMOMydzczBjHztxKcXzC5c9B6bYmEeHk6YFCND2fCVWNhgkFRC3ywANjZHXQ3TaDBgrrZ7xu4NDVcvXkioz3NA5WZF3TMtakcrhN764K6wetp6e0XygFVC8jZD2WZSxqC5zNMpooJWvM9b5IGNUr3F55OmX7XqnwnfnxbdzMm9lmID6n4IxU18DwoFdA6ZOw9s4UdmPEkbRe2jBEFDcRx7yVIAw0RnXPI89gnGHuYYgzgzYqeGHZHvHFcADmZpFGNGA7OcmXXVJHa25gWdPi3iuVh4RCwZV4wNx7XXzADHeDRGfG99r7p4tUwQjRrdqg8P54je1KglBORmkxFC3B5AtAxb5Dpc9UcKqkByi8LFJqq4QEmdHp14mLH32D7RlXLi4QXufAGbQXWDIIy9J5LrfKKP1XGlzC4Kv4DK1X9i3EUZ5MFqYH7pv0j0SGc1GSaA7aKPgxmCmRgjfJ1q9ye1qa6gsD6WnoGnVEX8AXoqRmbWKfm0DztbFUyA9eufj2TWgEqutBt5TbdrOun2r5DMuez0t66mDwzeIkORqY0G2fuRU5bN1UNcJDMi33RtMsB8pEu7pm3TRRAouEVIafFZg3fHV8JK4ulewna5NiMuEb4vGNvYP8GCIR0xUn45EduwSRtBYHKyELaIVXDR7TT9hq5x5OENfBXibPEVpdNkrvoNr89BNAcs2eEqjjHjbKQNxEfzjYJqxPKpOx6mWPL1LcgUDgWBYWFxhugrm4ZzYTIVAdQeFmRZBryiw5kUCRWnt9Y5BbXoWC0coOHAWBIj8HUcp4IMTlzJ9ajNALzPOdLtXDsBGx6XvHMdi9HJpRLzQ4DqBwu4vUtBniDwHsFjmPn3MMEg2acr9lgqjeFn2N8MqowLFX3cLJ4HLU6NI4ik3RUh17clHlgrdBYJEjwB6j1vSHXO7E2L4OxjjY";
		first = first.toUpperCase();
		second = second.toUpperCase();
		System.out.println("fIRST: " + first);
		System.out.println("second: " + second);
		String temp = first;
		first = second;
		second = temp;
		
		long startTime = System.currentTimeMillis();
		//serial damerau
//		Damerau d2 = new Damerau(first, second);
//		d2.OASDamerau();
		
		//parallel damerau
		Damerau d1 = new Damerau(first, second);
		d1.tryThread();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("time: " + totalTime);
	
//		initialize();
	}
}
