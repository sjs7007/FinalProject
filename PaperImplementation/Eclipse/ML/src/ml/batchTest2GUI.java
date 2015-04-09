package ml;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class batchTest2GUI extends JFrame {

	private JPanel contentPane;
	private JTextField nStart;
	private JTextField nEnd;
	private JTextField C_hStart;
	private JTextField C_hEnd;
	private JTextField C_sStart;
	private JTextField C_sEnd;
	private JTextField C_vStart;
	private JTextField C_vEnd;
	private JLabel lblImageDimension;
	private JTextField imageDimension;
	private JButton btnRun;
	
	JFileChooser fc;
	File trainData = new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/input/trainingImageResized"),testData =null,inputFileList=null,extractedFeatures=null,trainFeatures=null, testFeatures=null, modelFile=null;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					batchTest2GUI frame = new batchTest2GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public batchTest2GUI() {
		
		// Create a file chooser
		//user.dir is to get defualt directory
		fc =  new JFileChooser(new File(System.getProperty("user.dir")));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelectTrainingFolder = new JButton("Select Training Folder");
		btnSelectTrainingFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				int returnVal = fc.showOpenDialog(batchTest2GUI.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					trainData = fc.getSelectedFile();
					// This is where a real application would open the file.
					System.out.print("Folder selected for training : " + trainData.toString() + ".\n");				
				} else {
					System.out.print("Open command cancelled by user.\n");
				}
				//log.setCaretPosition(log.getDocument().getLength());
			}
		});
		btnSelectTrainingFolder.setBounds(84, 12, 226, 25);
		contentPane.add(btnSelectTrainingFolder);
		
		JButton btnSelectTestFolder = new JButton("Select Test Folder");
		btnSelectTestFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int returnVal = fc.showOpenDialog(batchTest2GUI.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					testData = fc.getSelectedFile();
					//This is where a real application would open the file.
					System.out.print("Folder selected for testing : " + testData.getName() + ".\n");

				} else {
					System.out.print("Open command cancelled by user.\n" );
				}
			}
		});
		btnSelectTestFolder.setBounds(84, 54, 226, 25);
		contentPane.add(btnSelectTestFolder);
		
		nStart = new JTextField();
		nStart.setText("5");
		nStart.setBounds(118, 132, 45, 19);
		contentPane.add(nStart);
		nStart.setColumns(10);
		
		nEnd = new JTextField();
		nEnd.setText("5");
		nEnd.setColumns(10);
		nEnd.setBounds(265, 132, 45, 19);
		contentPane.add(nEnd);
		
		JLabel lblStar = new JLabel("Range Start");
		lblStar.setBounds(95, 105, 91, 15);
		contentPane.add(lblStar);
		
		JLabel lblRangeEnd = new JLabel("Range End");
		lblRangeEnd.setBounds(251, 105, 91, 15);
		contentPane.add(lblRangeEnd);
		
		JLabel lblN = new JLabel("N");
		lblN.setBounds(69, 132, 31, 19);
		contentPane.add(lblN);
		
		JLabel lblCh = new JLabel("C_h");
		lblCh.setBounds(69, 169, 31, 19);
		contentPane.add(lblCh);
		
		C_hStart = new JTextField();
		C_hStart.setText("5");
		C_hStart.setColumns(10);
		C_hStart.setBounds(118, 169, 45, 19);
		contentPane.add(C_hStart);
		
		C_hEnd = new JTextField();
		C_hEnd.setText("5");
		C_hEnd.setColumns(10);
		C_hEnd.setBounds(265, 169, 45, 19);
		contentPane.add(C_hEnd);
		
		JLabel lblCs = new JLabel("C_s");
		lblCs.setBounds(69, 200, 31, 19);
		contentPane.add(lblCs);
		
		C_sStart = new JTextField();
		C_sStart.setText("5");
		C_sStart.setColumns(10);
		C_sStart.setBounds(118, 200, 45, 19);
		contentPane.add(C_sStart);
		
		C_sEnd = new JTextField();
		C_sEnd.setText("5");
		C_sEnd.setColumns(10);
		C_sEnd.setBounds(265, 200, 45, 19);
		contentPane.add(C_sEnd);
		
		JLabel label = new JLabel("C_v");
		label.setBounds(69, 231, 31, 19);
		contentPane.add(label);
		
		C_vStart = new JTextField();
		C_vStart.setText("5");
		C_vStart.setColumns(10);
		C_vStart.setBounds(118, 231, 45, 19);
		contentPane.add(C_vStart);
		
		C_vEnd = new JTextField();
		C_vEnd.setText("5");
		C_vEnd.setColumns(10);
		C_vEnd.setBounds(265, 231, 45, 19);
		contentPane.add(C_vEnd);
		
		lblImageDimension = new JLabel("Image Dimension");
		lblImageDimension.setBounds(86, 286, 128, 15);
		contentPane.add(lblImageDimension);
		
		imageDimension = new JTextField();
		imageDimension.setText("250");
		imageDimension.setBounds(224, 286, 53, 17);
		contentPane.add(imageDimension);
		imageDimension.setColumns(10);
		
		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				int count=1;
				
				for(int n=Integer.parseInt(nStart.getText());n<=Integer.parseInt(nEnd.getText());n=n+1)
				{
					for(int h=Integer.parseInt(C_hStart.getText());h<=Integer.parseInt(C_hEnd.getText());h=h+1)
					{
						for(int s=Integer.parseInt(C_sStart.getText());s<=Integer.parseInt(C_sEnd.getText());s=s+1)
						{
							for(int v=Integer.parseInt(C_vStart.getText());v<=Integer.parseInt(C_vEnd.getText());v=v+1)
							//int h=10,s=10,v=10;
							{
								try {
									//System.out.print(imageDimension.getText());
									batchTest2_gui_mod.predictM(count,n,h,s,v,Integer.parseInt(imageDimension.getText()), trainData.toString(), testData.toString());
								} catch (NumberFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								count++;
							}
						}                                                                                                     
					}
					
				}
			}
		});
		btnRun.setBounds(129, 332, 110, 25);
		contentPane.add(btnRun);
	}
}
