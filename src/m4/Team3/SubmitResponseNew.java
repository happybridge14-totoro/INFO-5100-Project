//package FinalProject;
package m4.Team3;
//package FinalProject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import utils.JDBC;

public class SubmitResponseNew extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6375909820518138040L;
	public JFrame frame;
	private JTextField carBrandTextField, carTypeTextField, carYearTextField, carModelTextField, customerFirstNameTextField, customerLastNameTextField, customerEmailTextField, customerPhoneNumberTextField;
	private JTextArea customerQueryTextArea, dealerResponseTextArea ;
	private JLabel carInfoLabel, customerInfoLabel, customerQueryLabel, dealerResponseLabel, carBrand, carType, carYear, carModel, customerFirstName, customerLastName, customerEmailId, customerPhoneNo;
	private JButton submitButton, resetButton;
	private JPanel panel, panel_1;
	JTextArea textAreas[] = {customerQueryTextArea, dealerResponseTextArea};
	JPanel panels[] = {panel, panel_1};
	JTextField textFields[] = {carBrandTextField, carTypeTextField, carYearTextField, carModelTextField, customerFirstNameTextField, customerLastNameTextField, customerEmailTextField, customerPhoneNumberTextField};
	JLabel labels[] = {carInfoLabel, carBrand, carModel, carYear, carType, customerInfoLabel, customerFirstName, customerLastName, customerEmailId, customerPhoneNo, customerQueryLabel, dealerResponseLabel};
	String str[] = {"Car Information","Car Brand", "Car Model", "Car Year", "Car Type", "Customer Information", "First Name", "Last Name","Email ID", "Phone No", "Query from customer:", "Reply to customer:"};   

	private String leadId;
	
	public SubmitResponseNew() {
		initializeAndAddComponents();
		setLayout();
	}
	
	public SubmitResponseNew(String leadId) {
		this();
		this.leadId = leadId;
		fillData();
	}

	private void fillData() {
		try {
			String leadSql = String.format("select * from dbo.CustomerRequest where leadId = '%s'", leadId);
			ResultSet leadInfo = JDBC.getInstance().getResults(leadSql);
			if(!leadInfo.next()) {
				System.out.println("customerrequest cannot be found in database: " + leadId);
				return;
			}
			
			textFields[4].setText(leadInfo.getString("firstName"));
			textFields[5].setText(leadInfo.getString("lastName"));
			textFields[6].setText(leadInfo.getString("email"));
			textFields[7].setText(leadInfo.getString("contactNo"));
			textAreas[0].setText(leadInfo.getString("comment"));
			textAreas[1].setText(leadInfo.getString("dealerComment"));
			
			
			String vehicleId = leadInfo.getString("vehicleId");
			String vehicleSql = String.format("select * from dbo.CarInventory where vechileId = '%s'", vehicleId);
			ResultSet vehicleInfo = JDBC.getInstance().getResults(vehicleSql);
			if(!vehicleInfo.next()) {
				System.out.println("vehicle cannot be found in database: " + vehicleId);
				return;
			}
			textFields[0].setText(vehicleInfo.getString("brand"));
			textFields[1].setText(vehicleInfo.getString("type"));
			textFields[2].setText(vehicleInfo.getString("dateofmanufacturing"));
			textFields[3].setText(vehicleInfo.getString("model"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void initializeAndAddComponents() {
		frame = new JFrame();
		frame.setBounds(70, 70, 782, 778);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font defaultFont = new Font("Tahoma", Font.PLAIN, 20);

		//initializing text areas
		for(int i = 0; i < textAreas.length; i++) {
			textAreas[i] = new JTextArea();
			textAreas[i].setFont(new Font("Monospaced", Font.PLAIN, 16));
			if(i == 0) {
				textAreas[i].setEditable(false);
				textAreas[i].setText("Hi, I'd like to ****");
			}
		}

		//initializing panels
		for(int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			panels[i].setLayout(null);
		}

		//initializing text fields
		for (int i = 0; i < textFields.length; i++) {
			textFields[i] = new JTextField(10);
			if( i < 4) {
				if( i == 0) {
					textFields[i].setBounds(133, 53, 96, 20);
				} else if( i == 1) {
					textFields[i].setBounds(399, 53, 96, 20);
				} else if( i == 2) {
					textFields[i].setBounds(133, 81, 96, 20);
				} else if( i == 3) {
					textFields[i].setBounds(399, 81, 96, 20);
				} 
				panels[0].add(textFields[i]);
			} else {			
				if( i == 4) {
					textFields[i].setBounds(133, 52, 96, 20);
				} else if( i == 5) {
					textFields[i].setBounds(399, 52, 96, 20);
				} else if( i == 6) {
					textFields[i].setBounds(133, 83, 169, 20);
				}else {
					textFields[i].setBounds(133, 119, 169, 20);
				}
				panels[1].add(textFields[i]);
			}
		}

		//initializing labels
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(str[i]);
			if( i < 5) {
				if( i == 0) {
					labels[i].setFont(defaultFont);
					labels[i].setBounds(10, 11, 219, 20);
				} else if( i == 1) {
					labels[i].setBounds(25, 53, 69, 20);
				} else if( i == 2) {
					labels[i].setBounds(311, 81, 69, 20);
				}else if( i == 3){
					labels[i].setBounds(25, 81, 69, 20);
				}else {
					labels[i].setBounds(311, 52, 69, 20);
				}		
				panels[0].add(labels[i]);
			} else if( i > 4 & i < 10) {
				if( i == 5) {
					labels[i].setFont(defaultFont);
					labels[i].setBounds(15, 16, 242, 20);
				} else if( i == 6) {
					labels[i].setBounds(25, 52, 69, 20);
				} else if( i == 7) {
					labels[i].setBounds(311, 52, 69, 20);
				}else if( i == 8){
					labels[i].setBounds(25, 83, 69, 20);
				}else {
					labels[i].setBounds(25, 119, 69, 20);
				}					
				panels[1].add(labels[i]);
			}else {
				labels[i].setFont(defaultFont);
			}
		}

		submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textAreas[1].getText().isEmpty()) {					
					JOptionPane.showMessageDialog(frame, "Response field is empty");
				}else {
					int result = JOptionPane.showConfirmDialog(frame, "are you sure you want to send response?",
							"alert", JOptionPane.OK_CANCEL_OPTION);
					if (result == 0) {
						//JOptionPane.showMessageDialog(null, "Response has been sent to customer");
						try {
							// can add auto response string to response when sending email:
//							SendEmail.gmailSender(user.getEmail(), "You got a response from dealer!", HTMLGenerator());
							String updateSql = String.format("update dbo.CustomerRequest set dealerComment = '%s' where leadId = '%s'", textAreas[1].getText(), leadId);
							JDBC.getInstance().update(updateSql);
							
							SendEmailTest2.gmailSender(textFields[6].getText(), "You got a response from dealer!", HTMLGenerator());
							JOptionPane.showMessageDialog(frame, "Response sent! An email also sent to customer.");
							//如果需要关闭原窗口
							//frame.setVisible(false);
						} catch (Exception exp) {
							exp.printStackTrace();
							JOptionPane.showMessageDialog(null, exp.getMessage());
						}
					}
				}
			}
		});
		

		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreas[1].setText("");
			}
		});

	}
	
	private void setLayout() {
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(130).addComponent(submitButton).addPreferredGap(ComponentPlacement.RELATED, 381, Short.MAX_VALUE).addComponent(resetButton)
						.addGap(103)).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(textAreas[1], GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(labels[10], GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE).addContainerGap(469, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(16).addComponent(panels[0], GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addGap(16).addComponent(panels[1], GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE).addContainerGap()).addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addContainerGap().addComponent(labels[11], GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE).addContainerGap(491, Short.MAX_VALUE)).addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addContainerGap().addComponent(textAreas[0], GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panels[0], GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE).addGap(33)
						.addComponent(panels[1], GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE).addGap(18).addComponent(labels[10]).addGap(18)
						.addComponent(textAreas[0], GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE).addGap(32).addComponent(labels[11]).addGap(18)
						.addComponent(textAreas[1], GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE).addGap(29).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(resetButton).addComponent(submitButton)).addGap(87)));
		frame.getContentPane().setLayout(groupLayout);
	}


	private String HTMLGenerator() {
		SAXReader reader = new SAXReader();
		org.dom4j.Document htmlDocument = null;
		String path = "src/m4/Team3/src/emailTemplate.html";
		File file = new File(path);
		String emailContent = "";
		try {
			htmlDocument = reader.read(file);
			Element root = htmlDocument.getRootElement();
			Element name = null; //SendEmail.getNodes(root, "id", "name");
			Element dealermessage = null; //SendEmail.getNodes(root, "id", "dealermessage");
//----------------------insert auto generated message here-------------------------
//          Element automessage = SendEmail.getNodes(root, "id", "automessage");
//          automessage.setText(autoResponseGenerator());
//			name.setText(user.getFirstName());
			name.setText(customerFirstNameTextField.getText());
//			dealermessage.setText(dResponseField.getText());
			dealermessage.setText(dealerResponseTextArea.getText());
			FileWriter fwriter = new FileWriter("src/temp.html");
			XMLWriter writer = new XMLWriter(fwriter);
			writer.write(htmlDocument);
			writer.flush();
			FileReader in = new FileReader("src/temp.html");
			char[] buff = new char[1024 * 10];
			in.read(buff);
			emailContent = new String(buff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailContent;
	}
}