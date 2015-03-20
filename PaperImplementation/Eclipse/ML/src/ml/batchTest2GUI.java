package ml;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	private JTextField textField_5;
	private JLabel lblImageDimension;
	private JTextField textField_6;
	private JButton btnRun;
	private JButton btnForceStop;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSele = new JButton("Select Training Folder");
		btnSele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSele.setBounds(84, 12, 226, 25);
		contentPane.add(btnSele);
		
		JButton btnSelectTestFolder = new JButton("Select Test Folder");
		btnSelectTestFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		nEnd.setText("10");
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
		C_hEnd.setText("10");
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
		C_sEnd.setText("10");
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
		
		textField_5 = new JTextField();
		textField_5.setText("10");
		textField_5.setColumns(10);
		textField_5.setBounds(265, 231, 45, 19);
		contentPane.add(textField_5);
		
		lblImageDimension = new JLabel("Image Dimension");
		lblImageDimension.setBounds(86, 286, 128, 15);
		contentPane.add(lblImageDimension);
		
		textField_6 = new JTextField();
		textField_6.setText("250");
		textField_6.setBounds(224, 286, 53, 17);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		btnRun = new JButton("Run");
		btnRun.setBounds(53, 329, 110, 25);
		contentPane.add(btnRun);
		
		btnForceStop = new JButton("Force Stop");
		btnForceStop.setBounds(213, 329, 110, 25);
		contentPane.add(btnForceStop);
	}
}
