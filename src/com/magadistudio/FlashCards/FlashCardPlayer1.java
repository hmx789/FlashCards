package com.magadistudio.FlashCards;

import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;

public class FlashCardPlayer1 {

	private JFrame frame;
	private JTextArea prompt;
	private ArrayList<FlashCard> cardList;
	
	
	FlashCardPlayer1() {
		frame = new JFrame("Flash Card Player");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		frame.setSize(500, 400);
		
		//Creating a jpanel to hold everything
		JPanel mainPanel = new JPanel();
		
		//Using a previously made font
		Font greatFont = new Font("Helvectica Neue",Font.BOLD, 21);
		
		//Creating the area where question and answer will be displayed
		prompt = new JTextArea(6,20);
		prompt.setLineWrap(true);
		prompt.setWrapStyleWord(true);
		prompt.setFont(greatFont);
		
		//Creating the Scroll Panes for the Text area
		JScrollPane vertical = new JScrollPane(prompt);
		vertical.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane horizontal = new JScrollPane(prompt);
		horizontal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		//Creating the button for displaying answer or next question
		JButton nextButton = new JButton ("Next");
		
		//Adding stuff to the Panel and the frame
		mainPanel.add(prompt);
		mainPanel.add(nextButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		
		frame.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub'
				new FlashCardPlayer1();
				
			}
			
		
		});
	}

}

