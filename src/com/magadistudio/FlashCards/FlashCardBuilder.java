package com.magadistudio.FlashCards;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class FlashCardBuilder {
	
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<FlashCard> cardList;
	private JFrame frame;
	
	public FlashCardBuilder() {
		// Build the user interface
		frame = new JFrame("Flash Card");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		//Create a JPanel to hold everything 
		JPanel mainPanel = new JPanel();
		
		//Create a font
		Font greatFont = new Font("Helvectica Neue",Font.BOLD, 21);
		
		question = new JTextArea(6, 20);	//6 rows and 20 columns
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(greatFont);
		
		//Question area
		JScrollPane qJScrollPane = new JScrollPane(question);
		qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		// Answer area
		answer = new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(greatFont);
		
		//JScrollPane
		JScrollPane aJScrollPane = new JScrollPane(answer);
		aJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JButton nextButton = new JButton("Next card");
		
		cardList = new ArrayList<FlashCard>();
		System.out.println("Size of ArrayList" + cardList.size());

		
		//Create a few labels
		JLabel qJLabel = new JLabel("Question");
		JLabel aJLabel = new JLabel("Answer");
		
		
		//add components to mainPanel
		mainPanel.add(qJLabel);
		mainPanel.add(qJScrollPane);
		mainPanel.add(aJLabel);
		mainPanel.add(aJScrollPane);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		
		//MenuBar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem NewMenuItem = new JMenuItem("New");
		JMenuItem SaveMenuItem = new JMenuItem("Save");
		
		fileMenu.add(NewMenuItem);
		fileMenu.add(SaveMenuItem);
		
		menuBar.add(fileMenu);
		
		//add eventListeners
		NewMenuItem.addActionListener(new NewMenuItemListener());
		SaveMenuItem.addActionListener(new SaveMenuItemListener());
		
		frame.setJMenuBar(menuBar);
		
		//add to the Frame
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {
				new FlashCardBuilder();
			}
			
		});
	}
	class NextCardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Create a flash card object
			FlashCard card = new FlashCard(question.getText(), answer.getText() );
			cardList.add(card);
			clearCard();
		}


		
	}
	
	class NewMenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("New was clicked");
		}
		
	}
	
	class SaveMenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			FlashCard card = new FlashCard(question.getText(), answer.getText() );
			cardList.add(card);
			
			//Create a file dialog with file chooser
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame); //needs to show from the main frame
			saveFile(fileSave.getSelectedFile());
		}


		
	}
	
	private void clearCard() {
		question.setText(" ");
		answer.setText(" ");
		question.requestFocus(); 	//puts the focus back to question
	}
	
	private void saveFile(File selectedFile) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
			Iterator<FlashCard> cardIterator = cardList.iterator();
			while (cardIterator.hasNext()) {
				FlashCard card = (FlashCard) cardIterator.next();
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
				//Format to be like this: Where's Mozambique/Africa\n
				
			}
			writer.close();
			
//			//Another way to do this
//			for (FlashCard card : cardList) {
//				writer.write(card.getQuestion() + "/");
//				writer.write(card.getAnswer() + "\n");
//			}
			
			
		} catch (Exception e) {
			System.out.println("Couldn't write to file");
			e.printStackTrace();
			
		}
		
		
	}

	

}
