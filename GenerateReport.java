import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

import java.awt.Color;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GenerateReport extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDate;
	private JTextField tfMiles;
	private JTextField tfTravelTime;
	private JTextField tfMPG;
	private JTextField tfGasPrice;
	private JTextField tfNumPassengers;
	private JTextField tfGallons;
	private JTextField tfGasCost;
	private JTextField tfPassengerPrice;
	private JEditorPane editorPassengers;

	/**
	 * Create the dialog.
	 */
	public GenerateReport(double miles, double MPG, double gasPrice, int numPassengers, double gallons, double gasCost, double passengerPrice) {
		DecimalFormat dblVar = new DecimalFormat("#.###");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		setLocationRelativeTo(null);
		setBounds(100, 100, 379, 471);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Generate a report...");
		setVisible(true);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 379, 410);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Date of trip:<br><br>\nMiles to travel:<br><br>\nEstimated travel time:<br><br>\nMiles per gallon:<br><br>\nAverage gas price:<br><br>\nNumber of passengers:<br><br>\nTotal gallons needed:<br><br>\nTotal gas cost:<br><br>\nPrice per passenger:<br><br>\nPassengers:<br><br><br><br><br><br></html>");
		lblNewLabel.setBounds(6, 6, 146, 398);
		contentPanel.add(lblNewLabel);
		
		Date d = new Date();
		tfDate = new JTextField(dateFormat.format(d));
		tfDate.setBounds(164, 6, 204, 28);
		contentPanel.add(tfDate);
		tfDate.setColumns(10);
		
		tfMiles = new JTextField("" + dblVar.format(miles) + " round trip");
		tfMiles.setColumns(10);
		tfMiles.setBounds(164, 38, 204, 28);
		contentPanel.add(tfMiles);
		
		Time t = new Time((miles/2) * 0.01538461538);
		tfTravelTime = new JTextField("" + t.toString() + " one way.");
		tfTravelTime.setBounds(164, 70, 204, 28);
		contentPanel.add(tfTravelTime);
		tfTravelTime.setColumns(10);
		
		tfMPG = new JTextField(""+ dblVar.format(MPG));
		tfMPG.setColumns(10);
		tfMPG.setBounds(164, 102, 204, 28);
		contentPanel.add(tfMPG);
		
		tfGasPrice = new JTextField(""+ dblVar.format(gasPrice));
		tfGasPrice.setColumns(10);
		tfGasPrice.setBounds(164, 134, 204, 28);
		contentPanel.add(tfGasPrice);
		
		tfNumPassengers = new JTextField(""+ numPassengers);
		tfNumPassengers.setColumns(10);
		tfNumPassengers.setBounds(164, 166, 204, 28);
		contentPanel.add(tfNumPassengers);
		
		tfGallons = new JTextField(""+ dblVar.format(gallons));
		tfGallons.setColumns(10);
		tfGallons.setBounds(164, 198, 204, 28);
		contentPanel.add(tfGallons);
		
		tfGasCost = new JTextField(""+ dblVar.format(gasCost));
		tfGasCost.setColumns(10);
		tfGasCost.setBounds(164, 230, 204, 28);
		contentPanel.add(tfGasCost);
		
		tfPassengerPrice = new JTextField(""+ dblVar.format(passengerPrice));
		tfPassengerPrice.setColumns(10);
		tfPassengerPrice.setBounds(164, 262, 204, 28);
		contentPanel.add(tfPassengerPrice);
		
		editorPassengers = new JEditorPane();
		editorPassengers.setBackground(new Color(255, 255, 255));
		editorPassengers.setBounds(169, 294, 194, 109);
		contentPanel.add(editorPassengers);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 410, 379, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						//opens dialog box for save location
						String s;
						JFileChooser fc = new JFileChooser();
					    fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

					    if( fc.showOpenDialog( getParent() ) == JFileChooser.APPROVE_OPTION )
					    {
					        s = fc.getSelectedFile().getAbsolutePath();
					    }
					    else
					    	s = null;
				
					    if(s != null){
					    	
					    	
					    	try {								
								File file = new File(s + "/report.txt");
								// if file doesn't exists, then create it
								if (!file.exists()) {
									file.createNewFile();
								}
								FileWriter fw = new FileWriter(file.getAbsoluteFile());
								BufferedWriter bw = new BufferedWriter(fw);								
								bw.write("Date of trip: " + tfDate.getText());
								bw.write("\nMiles to travel: " + tfMiles.getText());
								bw.write("\nEstimated travel time: " + tfTravelTime.getText());
								bw.write("\n\nMiles per gallon: " + tfMPG.getText());
								bw.write("\nAverage gas price: " + tfGasPrice.getText());
								bw.write("\nTotal gallons needed: "+ tfGallons.getText());
								bw.write("\nTotal cost of gas: " + tfGasCost.getText());
								bw.write("\n\nNumber of passengers: " + tfNumPassengers.getText());
								bw.write("\nPrice per passenger: " + tfPassengerPrice.getText());
								bw.write("\n\nPassengers:\n\n" + editorPassengers.getText());
								
								
								bw.close();
						 
								System.out.println(s);
						 
								} catch (IOException exception) {
									exception.printStackTrace();
								}
					    	
					    	dispose();
					    }
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
