package hermesDAO;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import java.net.URLConnection;

import helpers.Wizard;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static helpers.Configuration.*;

public class hermesUserInt extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Connection dbConn;
	protected String[][] queryArray = new String[3][28]; 
	protected Wizard wiz = new Wizard();
	private String username;
	private String password;
	
	public hermesUserInt() {
		// build query list
		acquireUNPW();
		loadQuery();
        initUI();
    }
	
	private void acquireUNPW() {
		this.username = this.wiz.getUsername(); 
		this.password = this.wiz.getPassword();		
	}
	
	private void loadQuery() {
		this.queryArray[0][0] = "Update Query 1";
		this.queryArray[0][1] = "Update Query 2";
		this.queryArray[0][2] = "Update Query 3";
		this.queryArray[0][3] = "Update Query 4";
		this.queryArray[0][4] = "Update Query 5";
		this.queryArray[0][5] = "Update Query 6";
		this.queryArray[0][6] = "Update Query 7";
		this.queryArray[0][7] = "Delete Query 1";
		this.queryArray[0][8] = "Delete Query 2";
		this.queryArray[0][9] = "Delete Query 3";
		this.queryArray[0][10] = "Delete Query 4";
		this.queryArray[0][11] = "Delete Query 5";
		this.queryArray[0][12] = "Select Query 1";
		this.queryArray[0][13] = "Select Query 2";
		this.queryArray[0][14] = "Select Query 3";
		this.queryArray[0][15] = "Select Query 4";
		this.queryArray[0][16] = "Aggregate Select Query 1";
		this.queryArray[0][17] = "Aggregate Select Query 2";
		this.queryArray[0][18] = "Aggregate Select Query 3";
		this.queryArray[0][19] = "Aggregate Select Query 4";
		this.queryArray[0][20] = "Load Customer Data";
		
		
		this.queryArray[2][0] = "UPDATE";
		this.queryArray[2][1] = "UPDATE";
		this.queryArray[2][2] = "UPDATE";
		this.queryArray[2][3] = "UPDATE";
		this.queryArray[2][4] = "UPDATE";
		this.queryArray[2][5] = "UPDATE";
		this.queryArray[2][6] = "UPDATE";
		this.queryArray[2][7] = "DELETE";
		this.queryArray[2][8] = "DELETE";
		this.queryArray[2][9] = "DELETE";
		this.queryArray[2][10] = "DELETE";
		this.queryArray[2][11] = "DELETE";
		this.queryArray[2][12] = "SELECT";
		this.queryArray[2][13] = "SELECT";
		this.queryArray[2][14] = "SELECT";
		this.queryArray[2][15] = "SELECT";
		this.queryArray[2][16] = "SELECT";
		this.queryArray[2][17] = "SELECT";
		this.queryArray[2][18] = "SELECT";
		this.queryArray[2][19] = "SELECT";
		this.queryArray[2][19] = "INSERT";
		
		this.queryArray[1][0] = "UPDATE Customer SET First_Name = 'Trevor', Middle_Name = 'Hunt', Last_Name = 'Holmes', Email = 'tholmes4005@gmail.com', Phone = '309-648-3968' WHERE Useridentifier = '11E99838993835F4B77F42010A80016C'";
		this.queryArray[1][1] = "UPDATE Customer SET Active = 0 WHERE Useridentifier = '11E99838993835F4B77F42010A80016C'";
		this.queryArray[1][2] = "UPDATE Address SET Address_Number = '401', Address_Name = 'Main St', Address_Unit = 'Suite 110', State = 'IL', Zip = '61602', Address_Type = 4, FedEx_Verified = 1, City = 'Peoria' WHERE addressidentifier = '11E99845B0C4849FB77F42010A80016C'";
		this.queryArray[1][3] = "UPDATE Address SET Active = 0 WHERE addressidentifier = '11E99845B0C4849FB77F42010A80016C'";
		this.queryArray[1][4] = "UPDATE Address SET Active = 0 WHERE Useridentifier = '11E9983899384B05B77F42010A80016C'";
		this.queryArray[1][5] = "UPDATE Shipment SET Shipping_Status = 4, Delivered_Date = TO_DATE('2019-06-28 12:00:00','YYYY-MM-DD HH:MI:SS') WHERE FedEx_Shipping_ID = '00F53825140706R15K3635943GN'";
		this.queryArray[1][6] = "UPDATE User_Package SET Width = 563, Length = 102, Height = 12, Weight = 1535.1 WHERE Package_ID = '11E999D0035FE047B77F42010A80016C'";
		this.queryArray[1][7] = "DELETE FROM User_Package WHERE Package_ID = '11E999D0035FE047B77F42010A80016C'";
		this.queryArray[1][8] = "DELETE FROM User_Package WHERE Useridentifier = '11E99838993873DAB77F42010A80016C'";
		this.queryArray[1][9] = "DELETE FROM Address WHERE Useridentifier = '11E99838993873DAB77F42010A80016C' and Active = 0";
		this.queryArray[1][10] = "DELETE FROM Customer WHERE Active = 0";
		this.queryArray[1][11] = "DELETE FROM Customer WHERE Useridentifier = '11E99838993873DAB77F42010A80016C'";
		this.queryArray[1][12] = "SELECT DISTINCT Customer.Useridentifier FROM Customer, Shipment WHERE Customer.Useridentifier = Shipment.Useridentifier and Customer.Last_Name='HOLMES' AND EXTRACT(YEAR FROM Shipment.Shipped_Date)=2017";
		this.queryArray[1][13] = "SELECT User_Package.Package_ID FROM User_Package INNER JOIN Shipment ON User_Package.FedEx_Shipping_ID = Shipment.FedEx_Shipping_ID WHERE EXTRACT(YEAR FROM Shipment.Delivered_Date) = 2018";
		this.queryArray[1][14] = "SELECT Shipment.Useridentifier FROM Shipment INNER JOIN Payment_Method ON Payment_Method.Useridentifier = Shipment.Useridentifier AND Payment_Method.Account_Number = Shipment.Account_Number WHERE EXTRACT(YEAR FROM Shipment.Delivered_Date) = 2017 and Payment_Method.Payment_Type = 1";
		this.queryArray[1][15] = "SELECT Shipment.Useridentifier FROM Shipment INNER JOIN User_Package ON User_Package.FedEx_Shipping_ID = Shipment.FedEx_Shipping_ID WHERE User_Package.Weight > 1000 AND EXTRACT(YEAR FROM Shipment.Shipped_Date) = 2018";
		this.queryArray[1][16] = "SELECT Count(Customer.Useridentifier) FROM Customer INNER JOIN Address ON Customer.Useridentifier = Address.Useridentifier WHERE Address.Address_Type = 4 and Address.State = 'WV'";
		this.queryArray[1][17] = "SELECT AVG(Shipment.Actual_Shipping_Cost), Min(Shipment.Actual_Shipping_Cost), MAX(Shipment.Actual_Shipping_Cost), Count(Shipment.Actual_Shipping_Cost) FROM Shipment WHERE EXTRACT(YEAR FROM Shipment.Delivered_Date) = 2018";
		this.queryArray[1][18] = "SELECT Shipment.Useridentifier FROM Shipment INNER JOIN User_Package ON User_Package.FedEx_Shipping_ID = Shipment.FedEx_Shipping_ID WHERE User_Package.Weight > 10 AND EXTRACT(YEAR FROM Shipment.Shipped_Date) = 2018 and Shipment.Useridentifier IN (SELECT Address.Useridentifier FROM Address WHERE Address.addressidentifier = Shipment.To_Addressidentifier and Address.State = 'WV')";
		this.queryArray[1][19] = "SELECT Customer.Useridentifier FROM Customer INNER JOIN Address ON Address.Useridentifier = Customer.Useridentifier WHERE Address.Address_Type = 4 AND Address.State='IL' and Customer.Useridentifier IN (SELECT Address.Useridentifier FROM Address INNER JOIN Shipment ON Address.Useridentifier = Shipment.Useridentifier WHERE Address.addressidentifier = Shipment.From_Addressidentifier and Address.State = 'WV')";

		try{
			
	         DataInputStream dis = new DataInputStream (new FileInputStream ("https://www.scoutprop.com/SENG591B/ORACLE-DATA/Customers-ORACLE.sql"));
	         String out = new Scanner(new URL("https://www.scoutprop.com/SENG591B/ORACLE-DATA/Customers-ORACLE.sql").openStream(), "UTF-8").useDelimiter("\\A").next();
		 byte[] datainBytes = new byte[dis.available()];
		 dis.readFully(datainBytes);
		 dis.close();
		       
		 String content = new String(datainBytes, 0, datainBytes.length);
		 this.queryArray[1][20] = content;
		 
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
    private void initUI() {
        
    	
		ImageIcon icon = new ImageIcon("src/img/hermes.jpg");
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbcClean = new GridBagConstraints();
		JComboBox<String> cbTable = null;
		// Create Data Select Tab
		JTabbedPane hsInterface = new JTabbedPane();
		JPanel hsDataViewer = createInnerPanel("Select Data to View",0,0);
		JPanel hsInsertData = createInnerPanel("Insert Data",0,0);
		
		hsInterface.addTab("Data Viewer", icon, hsDataViewer, "Data Viewer");
		hsInterface.addTab("Insert Data", icon, hsInsertData, "Insert Data");
		hsInterface.setSelectedIndex(0);
			
		JLabel hslabel1 = new JLabel("Table");
		gbc.gridx = 0;
		gbc.gridy = 1;
		hsDataViewer.add(hslabel1,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		JLabel hslabel2 = new JLabel("Where Column");
		gbc.gridx = 1;
		gbc.gridy = 1;
		hsDataViewer.add(hslabel2,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();

		JLabel hslabel3 = new JLabel("Where Value");

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 1;
		hsDataViewer.add(hslabel3,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		
		cbTable = createComboBox2(cbTable, null, 3);
		gbc.gridx = 0;
		gbc.gridy = 2;
		hsDataViewer.add(cbTable, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
	    HTMLDocument doc = new HTMLDocument();
		JTextPane pane = new JTextPane(doc);
		JScrollPane jsp = new JScrollPane(pane);
		// Create the StyleContext, the document and the pane
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 1;
		gbc.weightx = 1;
	    pane.setContentType( "text/html" );
	    pane.setEditable(false);
	    hsDataViewer.remove(jsp);
	    hsDataViewer.add(jsp,gbc);
	    gbc = (GridBagConstraints) gbcClean.clone();
		
		cbTable.addActionListener(new ActionListener() {
			JComboBox<String> cbCol = null;
			JTextField hsText1 = new JTextField(1);
			JButton hsGetDataButton = new JButton("Show Data");

		    GridBagConstraints gbcT = new GridBagConstraints();
			@Override
		    public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == "comboBoxChanged") {
					@SuppressWarnings("unchecked")
					JComboBox<String> combo = (JComboBox<String>) event.getSource();
										
					cbCol = createComboBox2(combo, cbCol,3);
					gbcT.gridx = 1;
					gbcT.gridy = 2;
					hsDataViewer.remove(cbCol);
					hsDataViewer.add(cbCol, gbcT);
					gbcT = (GridBagConstraints) gbcClean.clone();
					
					gbcT.fill = GridBagConstraints.HORIZONTAL;
					gbcT.gridx = 2;
					gbcT.gridy = 2;
					hsDataViewer.remove(hsText1);
					hsDataViewer.add(hsText1,gbcT);
					gbcT = (GridBagConstraints) gbcClean.clone();
					
					gbcT.gridx = 3;
					gbcT.gridy = 2;
					hsDataViewer.remove(hsGetDataButton);
					hsDataViewer.add(hsGetDataButton,gbcT);
					gbcT = (GridBagConstraints) gbcClean.clone();

				    hsGetDataButton.addActionListener(new ActionListener(){
				        public void actionPerformed(ActionEvent e)
				        {
				          // Display Data when button is pressed
						    String sqlQuery = "SELECT * FROM "+ combo.getSelectedItem().toString();

				        	if(hsText1.getText() != null && cbCol.getSelectedItem().toString() != "*") {
				        		sqlQuery = sqlQuery + " WHERE "+ cbCol.getSelectedItem().toString() + " = "+ hsText1.getText() ;
				        	}
				        	updateDataPane(pane, sqlQuery, "SELECT");
				        }
				      });
				    hsDataViewer.validate();
					}
				}

			});					

		JLabel hsSelectLabel = new JLabel("Select Query to Display:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		hsDataViewer.add(hsSelectLabel,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
	    JComboBox<String> queryComboBox = new JComboBox<String>();
	    for(int x=0; x<this.queryArray[0].length; x++) {
	    	queryComboBox.addItem(this.queryArray[0][x]);
	    	
	    }
		gbc.gridx = 1;
		gbc.gridy = 3;
		hsDataViewer.add(queryComboBox, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();

		JButton hsQuery = new JButton("View Query");
		gbc.gridx = 2;
		gbc.gridy = 3;
		hsDataViewer.add(hsQuery,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		String[][] tempArray = this.queryArray;
	    hsQuery.addActionListener(new ActionListener(){
	    	
	    	
	        public void actionPerformed(ActionEvent e)
	        {
	          // Display Data when button is pressed
	        	Integer index = queryComboBox.getSelectedIndex();
	        	System.out.println("Index: "+ index);
	        	String Query = tempArray[1][index];
	        	String QueryType = tempArray[2][index];
	        	
	        	updateDataPane(pane, Query, QueryType);
	        }
	      });
	    
		JButton hsCommitBut = new JButton("Commit");
		gbc.gridx = 3;
		gbc.gridy = 3;
		hsDataViewer.add(hsCommitBut,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		
		hsCommitBut.addActionListener(new ActionListener(){
			
	        public void actionPerformed(ActionEvent e)
	        {
	          // Commit Mutations
	        	Integer index = queryComboBox.getSelectedIndex();
	        	System.out.println("Index: "+ index);
	        	String Query = tempArray[1][index];
	        	
        		createDBConnection();
        		runQuery(Query);
        		committDBTransactions();
				closeDBConnection();
	        }
	      });

	    setLayout(new GridLayout(1, 1));
		add(hsInterface);
    }
    
    protected Number runQuery(String Query) {
    		
		Number count = -1;
		try {
			PreparedStatement prep = dbConn.prepareStatement(Query);
			count = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}{
			return count;
		}
    }
	protected void updateDataPane(JTextPane pane, String query, String queryType) {
		
		Statement stmt;
		HTMLDocument doc = (HTMLDocument)pane.getDocument();
				
		try {
			createDBConnection();
			stmt = this.dbConn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			System.out.println("SQL STATEMENT: "+ query);
			
			final ResultSetMetaData meta = rs.getMetaData();
			String tvOutput = "<!DOCTYPE html><html><head><title>SENG 591B - IMPLEMENTATION 1</title>"+
							  "</head><BODY style='font-size: 10px;'><H1>SENG 591B - IMPLEMENTATION 1 - DATA VIEW</H1>"+
							  "<p>SQL STATEMENT: "+ query +"</p>";
			
			if (queryType == "SELECT") {
				tvOutput = tvOutput + "<TABLE style='padding: 5px; text-align: left;'><TR>";
				final int columns = meta.getColumnCount();
				for (int i = 1; i <= columns; i++) {

					tvOutput = tvOutput + "<TH style='padding: 5px; text-align: left;'>" + meta.getColumnName(i) + "</TH>";
				}
				tvOutput = tvOutput + "</TR>";
				
				while (rs.next()) {
					tvOutput = tvOutput + "<TR style='background-color: #f2f2f2;'>";
					for (int i = 1; i <= columns; i++) {

						tvOutput = tvOutput + "<TD style='padding: 5px; text-align: left;'>" + rs.getString(i) + "</TD>"; 
					}
					tvOutput = tvOutput + "</TR>";
				}
				tvOutput = tvOutput + "</body></html>";
				
			} else {
				
				int count = stmt.getUpdateCount();
				tvOutput = tvOutput + "<P>queryType: "+ queryType +" Rows Affected: "+ count + "</P>"+
									  "<p style='color: red;font-weight: bold'>NOTE: SELECT 'COMMIT' BUTTON TO COMMIT MUTATIONS.  OTHERWISE ALL MUTATIONS ARE ROLLEDBACK</p>"; 
			}
			
			doc.setInnerHTML(doc.getDefaultRootElement(), tvOutput);
			pane.setCaretPosition(0);

		} catch (SQLException | BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} {
			rollbackDBTransactions();
			closeDBConnection();
		}

	}

	protected JComboBox<String> createComboBox2(JComboBox<String> tableComboBox, JComboBox<String> colComboBox, Integer colIndex ) {
		JComboBox<String> ComboBox = new JComboBox<String>();

		try {
		
				createDBConnection();
				ResultSet rs;
				DatabaseMetaData md;
				ResultSetMetaData rsmd;
				
				if (tableComboBox == null) {
					tableComboBox = new JComboBox<String>();
					md = this.dbConn.getMetaData();
					tableComboBox.addItem("");
					rs = md.getTables(null, "THH0003", "%", null);
					while (rs.next()) {
						tableComboBox.addItem(rs.getString(colIndex));
					}
					ComboBox = tableComboBox;
				} else{
					if (colComboBox == null) {
						colComboBox = new JComboBox<String>();
					}
					final String query = "SELECT * FROM " + tableComboBox.getSelectedItem().toString();
					Statement stmt = this.dbConn.createStatement(); 
					rs = stmt.executeQuery(query);
					rsmd=rs.getMetaData();
					colComboBox.removeAllItems();
					colComboBox.addItem("*");
					for (int i=1; i <= rsmd.getColumnCount();i++){

						colComboBox.addItem(rsmd.getColumnName(i));
					}
					ComboBox = colComboBox;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} {
			closeDBConnection();
		}
		return ComboBox;
	}

	protected void createDBConnection() {

		try {
			this.dbConn = DriverManager.getConnection(
				JDBC_URL,
				this.username, 
				this.password
			);
			this.dbConn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}{
			System.out.println("Connected Successfully.\n");
		}
	}
	
	protected void closeDBConnection(){
		try {
			this.dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}{
			System.out.println("Connection Closed.\n");
		}
	}
	
	private void rollbackDBTransactions() {
		try {
			this.dbConn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void committDBTransactions() {
		try {
			this.dbConn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected JPanel createInnerPanel(String text, Integer pRows, Integer pCols) {
		JPanel jplPanel = new JPanel();
		JLabel jlbDisplay = new JLabel(text);
		jlbDisplay.setHorizontalAlignment(JLabel.CENTER);
		jlbDisplay.setVerticalAlignment(JLabel.TOP);
		jplPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 8;
		gbc.gridx = pCols;
		gbc.gridy = pRows;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		jplPanel.add(jlbDisplay,gbc);
		return jplPanel;
	}
	
    public static void main(String[] args) {

    	JFrame frame = new JFrame("Hermes Shipping - Data Administration");
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(new hermesUserInt(), BorderLayout.CENTER);
		frame.setSize(1024, 768);
		frame.setVisible(true);
    	
    }
}