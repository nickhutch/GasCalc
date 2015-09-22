import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GasCalc {

	private JFrame frame;
	private JTextField tfMiles;
	private JTextField tfMPG;
	private JTextField tfGasPrice;
	private JTextField tfNumPassengers;
	private JCheckBox chckbxCountDriver;
	private JLabel labelCalculatedInformation;
	private JLabel lblCalcualtedTitles;
	
	private double miles;
	private double milesPerGallon;
	private double gasPrice;
	private int numPassengers;
	
	private double gasCost;
	private double gasPerPassenger;
	private double gallons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GasCalc window = new GasCalc();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GasCalc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame = new JFrame();
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		frame.setTitle("Gas Calcualtor");
		frame.setBounds(100, 100, 709, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfMiles = new JTextField();
		tfMiles.setBounds(185, 26, 134, 28);
		frame.getContentPane().add(tfMiles);
		tfMiles.setColumns(10);
		
		tfMPG = new JTextField();
		tfMPG.setBounds(185, 66, 134, 28);
		frame.getContentPane().add(tfMPG);
		tfMPG.setColumns(10);
		
		tfGasPrice = new JTextField();
		tfGasPrice.setBounds(185, 106, 134, 28);
		frame.getContentPane().add(tfGasPrice);
		tfGasPrice.setColumns(10);
		
		JLabel lblMilesToTravel = new JLabel("Miles to travel:");
		lblMilesToTravel.setBounds(35, 26, 134, 28);
		frame.getContentPane().add(lblMilesToTravel);
		
		JLabel lblMilesPerGallon = new JLabel("Miles per gallon:");
		lblMilesPerGallon.setBounds(35, 66, 134, 28);
		frame.getContentPane().add(lblMilesPerGallon);
		
		JLabel lblCurrentGasPrice = new JLabel("Current gas price:");
		lblCurrentGasPrice.setBounds(35, 112, 112, 16);
		frame.getContentPane().add(lblCurrentGasPrice);
		
		chckbxCountDriver = new JCheckBox("Count driver in total?");
		chckbxCountDriver.setBounds(95, 169, 164, 23);
		frame.getContentPane().add(chckbxCountDriver);
		
		tfNumPassengers = new JTextField();
		tfNumPassengers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
			    if (key == KeyEvent.VK_ENTER){

					if(!tfMiles.getText().isEmpty() && !tfMPG.getText().isEmpty() && !tfGasPrice.getText().isEmpty() && !tfNumPassengers.getText().isEmpty()){

						miles = Double.parseDouble(tfMiles.getText()) *2;
						milesPerGallon = Double.parseDouble(tfMPG.getText());
						gasPrice = Double.parseDouble(tfGasPrice.getText());
						numPassengers = Integer.parseInt(tfNumPassengers.getText());
						
						labelCalculatedInformation.setText(calcualte());
						lblCalcualtedTitles.setEnabled(true);
					}
			    }				
			}
		});
		tfNumPassengers.setBounds(185, 206, 134, 28);
		frame.getContentPane().add(tfNumPassengers);
		tfNumPassengers.setColumns(10);
		
		JLabel lblNumberOfPassengers = new JLabel("Number of passengers:");
		lblNumberOfPassengers.setBounds(35, 212, 146, 16);
		frame.getContentPane().add(lblNumberOfPassengers);
		
		JButton btnCalculate = new JButton("Calculate!");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!tfMiles.getText().isEmpty() && !tfMPG.getText().isEmpty() && !tfGasPrice.getText().isEmpty() && !tfNumPassengers.getText().isEmpty()){

					miles = Double.parseDouble(tfMiles.getText()) *2;
					milesPerGallon = Double.parseDouble(tfMPG.getText());
					gasPrice = Double.parseDouble(tfGasPrice.getText());
					numPassengers = Integer.parseInt(tfNumPassengers.getText());
					
					labelCalculatedInformation.setText(calcualte());
					lblCalcualtedTitles.setEnabled(true);
				}
			}
		});
		btnCalculate.setBounds(35, 242, 284, 38);
		frame.getContentPane().add(btnCalculate);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(348, 9, 5, 280);
		frame.getContentPane().add(separator);
		
		lblCalcualtedTitles = new JLabel("<html>Miles to travel:<br><br>\nMiles per gallon:<br><br>\nAverage gas price:<br><br>\nNumber of passengers:<br><br>\n<br><br><br>\nTotal gallons needed:<br><br>\nTotal gas cost:<br><br>\nPrice per passenger:</html>");
		lblCalcualtedTitles.setEnabled(false);
		lblCalcualtedTitles.setBounds(390, 22, 153, 254);
		frame.getContentPane().add(lblCalcualtedTitles);
		
		labelCalculatedInformation = new JLabel("");
		labelCalculatedInformation.setBounds(550, 17, 153, 254);
		frame.getContentPane().add(labelCalculatedInformation);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmReport = new JMenuItem("Generate a report - ⌘G");
		mntmReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateReport r = new GenerateReport(miles, milesPerGallon, gasPrice, numPassengers, gallons, gasCost, gasPerPassenger);
			}
		});
		mnFile.add(mntmReport);
	}
	private String calcualte(){
		
		gallons = miles * Math.pow(milesPerGallon, -1);
		gasCost = gallons * gasPrice;
		if(chckbxCountDriver.isSelected())
			numPassengers++;
		gasPerPassenger = gasCost / numPassengers;
		
		DecimalFormat dblVar = new DecimalFormat("#.###");
		
		String s = ("<html>"
				+ miles
				+ " round trip.<br><br>"
				+ milesPerGallon
				+ "<br><br>"
				+ gasPrice
				+ "<br><br>"
				+ numPassengers
				+ "<br><br><br><br><br>"
				+ dblVar.format(gallons)
				+ "<br><br>"
				+ dblVar.format(gasCost)
				+ "<br><br>"
				+ dblVar.format(gasPerPassenger)
				+ "</html>");
		
		return s;
		
		
	}
}























