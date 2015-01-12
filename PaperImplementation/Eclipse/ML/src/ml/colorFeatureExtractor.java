package ml;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class colorFeatureExtractor extends JPanel implements ActionListener {
	static private final String newline = "\n";
	JButton openTrainingFolderButton, saveInputListButton, openTestFolderButton, saveButton, extractTrainingFeatures;
	JTextArea log;
	JFileChooser fc;
	File trainData = null,testData =null,inputFileList=null,extractedFeatures=null;

	public colorFeatureExtractor() {
		super(new BorderLayout());

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		
		
		// Create a file chooser
		fc = new JFileChooser();
		
		// Uncomment one of the following lines to try a different
		// file selection mode. The first allows just directories
		// to be selected (and, at least in the Java look and feel,
		// shown). The second allows both files and directories
		// to be selected. If you leave these lines commented out,
		// then the default mode (FILES_ONLY) will be used.
		//
		 fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Create the open button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		openTrainingFolderButton = new JButton("Open training folder...");
		openTrainingFolderButton.addActionListener(this);
		
		 openTestFolderButton = new JButton("Open test folder...");
		 openTestFolderButton.addActionListener(this);

		// Create the save button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		saveInputListButton = new JButton("Select file list location");
		saveInputListButton.addActionListener(this);
		
		  saveButton = new JButton("Select feature file location");
		  saveButton.addActionListener(this);
		  
		  extractTrainingFeatures = new JButton("Run on Training Data");
		  extractTrainingFeatures.addActionListener(this);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(openTrainingFolderButton);
		buttonPanel.add(openTestFolderButton);
		buttonPanel.add(saveInputListButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(extractTrainingFeatures);


		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) 
	{
		// Handle open button action.
		if (e.getSource() == openTrainingFolderButton) {
			int returnVal = fc.showOpenDialog(colorFeatureExtractor.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				trainData = fc.getSelectedFile();
				// This is where a real application would open the file.
				log.append("Folder selected for training : " + trainData.toString() + "." + newline);
				
				/*try
				{
					ML.batchColorFeatureBuilder(file.toString(), 10, 8, 6, 6,new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/input/continuousTest2.train"),new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/output/filesInputTest2.txt"));
				}
				catch(IOException k)
				{
					
				}
				log.append("fdd\n");*/
			} else {
				log.append("Open command cancelled by user." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());

			
			
		} 
		
		else if(e.getSource()==openTestFolderButton)
		{
			// Handle open button action.
			 if (e.getSource() == openTestFolderButton) {
		            int returnVal = fc.showOpenDialog(colorFeatureExtractor.this);

		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		                testData = fc.getSelectedFile();
		                //This is where a real application would open the file.
		                log.append("Folder seelcted for testing : " + testData.getName() + "." + newline);
		          
		            } else {
		                log.append("Open command cancelled by user." + newline);
		            }
		            log.setCaretPosition(log.getDocument().getLength());
		}
		}
		
		// Handle save button action.
		
		else if (e.getSource() == saveInputListButton) {
			int returnVal = fc.showSaveDialog(colorFeatureExtractor.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				inputFileList = fc.getSelectedFile();
				// This is where a real application would save the file.
				log.append("File selected for storing input file list : " + inputFileList.getName() + "." + newline);
			} else {
				log.append("Save command cancelled by user." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
		
		else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(colorFeatureExtractor.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                extractedFeatures = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("File selected for storing extracted features : " + extractedFeatures.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
		
		else if (e.getSource() == extractTrainingFeatures) 
		{
			try
			{
				ML.batchColorFeatureBuilder(trainData.toString(), 10, 8, 6, 6,extractedFeatures,inputFileList);
			}
			catch(IOException k)
			{
				
			}
            log.setCaretPosition(log.getDocument().getLength());
        }
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = colorFeatureExtractor.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("ColorFeaturesExtractor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new colorFeatureExtractor());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
