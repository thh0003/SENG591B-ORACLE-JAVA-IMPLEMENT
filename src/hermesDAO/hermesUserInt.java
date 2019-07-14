package hermesDAO;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;

import helpers.Wizard;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import static helpers.Configuration.*;

public class hermesUserInt extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Connection dbConn;
	protected String[][] queryArray = new String[3][21]; 
	protected Wizard wiz = new Wizard();
	
	protected JComboBox<String> cbTable = new JComboBox<String>();
	protected JComboBox<String> cbCol = new JComboBox<String>();
	protected JComboBox<String> cbSelectData = new JComboBox<String>();
	protected JComboBox<String> cbMutationType = new JComboBox<String>();
	protected JComboBox<String> cbMutationTable  = new JComboBox<String>();
	protected JComboBox<String> cbMutationSetCol  = new JComboBox<String>();
	protected JComboBox<String> cbMutationSetValue  = new JComboBox<String>();
	protected JComboBox<String> cbMutationWhereCol  = new JComboBox<String>();
	protected JComboBox<String> cbMutationWhereCond  = new JComboBox<String>();
	protected JComboBox<String> cbMutationWhereData  = new JComboBox<String>();
	
	protected JButton hsGetDataButton = new JButton("Show Data");
	protected JTabbedPane hsInterface = new JTabbedPane();
	protected JPanel hsDataViewer = new JPanel();
	protected JPanel hsInsertData = new JPanel();
	
	private Integer UIState = 0;
	private Integer QueryFilterType[][] = new Integer[3][20];
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
	}
	
    private void initUI() {
        
    	cbTable.setName("cbTable");
    	cbTable.setActionCommand("TableChange");
    	
    	cbMutationTable.setActionCommand("TableChange");
    	cbMutationSetCol.setActionCommand("ColumnChange");
    	cbMutationWhereCol.setActionCommand("ColumnChange"); 
    	
    	cbCol.setName("cbCol");
    	cbCol.setActionCommand("ColumnChange");
    	
    	cbSelectData.setName("cbSelectData");
    	cbSelectData.setActionCommand("DataChange");

    	ImageIcon icon = new ImageIcon("src/img/hermes.jpg");
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbcClean = new GridBagConstraints();
		
		
		// Create Data Select Tab
		
		hsDataViewer = createInnerPanel("Select Data to View",0,0);
		hsInterface.addTab("Data Viewer", icon, hsDataViewer, "Data Viewer");
		hsInterface.setSelectedIndex(0);
			
		JLabel hslabel1 = new JLabel("Table");
		hslabel1.setToolTipText("Please select which Table you want to view data from.");
		gbc.gridx = 0;
		gbc.gridy = 1;
		hslabel1.setForeground(Color.GREEN);
		hsDataViewer.add(hslabel1,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		
		JLabel hslabel2 = new JLabel("Where Column");
		hslabel2.setToolTipText("Please select which Column you would like to filter data against.");
		hslabel2.setForeground(Color.GREEN);
		gbc.gridx = 1;
		gbc.gridy = 1;
		hsDataViewer.add(hslabel2,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();

		JLabel hslabel3 = new JLabel("Condition");
		hslabel3.setToolTipText("Please select the comparison operator");
		hslabel3.setForeground(Color.GREEN);
		gbc.gridx = 2;
		gbc.gridy = 1;
		hsDataViewer.add(hslabel3,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		
		JLabel hslabel4 = new JLabel("Comparison Value");
		hslabel4.setForeground(Color.GREEN);
		hslabel4.setToolTipText("Please select the data to compare");
		gbc.gridx = 3;
		gbc.gridy = 1;
		hsDataViewer.add(hslabel4,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();

		loadComboBox(cbTable, "_metaTables", "", 3);
		cbTable.setToolTipText("Please select which Table you want to view data from.");
		cbTable.setBackground(Color.GREEN);
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
		gbc.gridy = 7;
		gbc.weighty = 1;
		gbc.weightx = 1;
	    pane.setContentType( "text/html" );
	    pane.setEditable(false);
	    hsDataViewer.add(jsp,gbc);
	    gbc = (GridBagConstraints) gbcClean.clone();

	    
	    cbCol.addItem("Loading...");
	    cbCol.setToolTipText("Please select the Table first.  Selection Columns will then be loaded...");
	    cbCol.setBackground(Color.GREEN);
		gbc.gridx = 1;
		gbc.gridy = 2;
		hsDataViewer.add(cbCol, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();

	    JComboBox<String> cbCond = new JComboBox<String>();
	    cbCond.addItem("=");
	    cbCond.addItem(">");
	    cbCond.addItem("<");
	    cbCond.addItem("Like");
	    cbCond.setBackground(Color.GREEN);
		gbc.gridx = 2;
		gbc.gridy = 2;
		hsDataViewer.add(cbCond, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();	
		
	    
	    cbSelectData.addItem("Loading...");
	    cbSelectData.setBackground(Color.GREEN);
		gbc.gridx = 3;
		gbc.gridy = 2;
		hsDataViewer.add(cbSelectData, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();		

		
		
		gbc.gridx = 4;
		gbc.gridy = 2;
		hsDataViewer.add(hsGetDataButton,gbc);
		hsGetDataButton.setBackground(Color.GREEN);
		gbc = (GridBagConstraints) gbcClean.clone();
		
		cbTable.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				Integer UILock = getUIState(); 
				
				if (event.getActionCommand() == "TableChange" && UILock == 0) {
					setUIState(1);
					cbSelectData.removeAllItems();
					cbCol.removeAllItems();
					setQueryFilterType(0,0,0);
					String sqlQuery = "SELECT * FROM "+ cbTable.getSelectedItem().toString();
					loadComboBox(cbCol, "_metaColumns", sqlQuery, 3);
					setUIState(0);
					
				}
			}

		});
		
		cbCol.addActionListener(new ActionListener() {
			
			@Override
		    public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				Integer UILock = getUIState();
				
				if (event.getActionCommand() == "ColumnChange" && UILock == 0) {
					setUIState(1);
					cbSelectData.removeAllItems();
					setQueryFilterType(0,0,0);
					String sqlQuery = "SELECT DISTINCT "+ cbCol.getSelectedItem().toString() + " FROM "+ cbTable.getSelectedItem().toString() + " ORDER BY "+cbCol.getSelectedItem().toString()+" ASC";
					loadComboBox(cbSelectData, "_selectData", sqlQuery, 1);
					setUIState(0);
					
					
				}
			}

		});					

		hsGetDataButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e)
	        {
	          // Display Data when button is pressed
			    String sqlQuery = "SELECT * FROM "+ cbTable.getSelectedItem().toString();
			    Integer filterType = getQueryFilterType(0,cbCol.getSelectedIndex());
			    
	        	if(cbSelectData.getSelectedItem().toString() != "" && cbCol.getSelectedItem().toString() != "*") {
	        		
	        		sqlQuery = sqlQuery + " WHERE "+ cbCol.getSelectedItem().toString() + " " + cbCond.getSelectedItem().toString() +" ";
	        		
	        		if (filterType < 0) {
	        			sqlQuery = sqlQuery + cbSelectData.getSelectedItem().toString();
	        		} else {
	        			sqlQuery = sqlQuery + "'"+ cbSelectData.getSelectedItem().toString() + "'";
	        		} 	        		
	        	}
	        	updateDataPane(pane, sqlQuery, "SELECT");
	        }
	      });

		JLabel hsSelectLabel = new JLabel("Select Query to Display:");
		gbc.gridwidth = 5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		hsDataViewer.add(hsSelectLabel,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
	    JComboBox<String> queryComboBox = new JComboBox<String>();
	    for(int x=0; x<this.queryArray[0].length; x++) {
	    	queryComboBox.addItem(this.queryArray[0][x]);
	    	
	    }
		gbc.gridx = 0;
		gbc.gridy = 4;
		queryComboBox.setBackground(Color.YELLOW);
		hsDataViewer.add(queryComboBox, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();

		JButton hsQuery = new JButton("View Query");
		gbc.gridx = 1;
		gbc.gridy = 4;
		hsQuery.setBackground(Color.YELLOW);
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
		hsCommitBut.setBackground(Color.RED);
		gbc.gridx = 0;
		gbc.gridy = 0;
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

		JLabel hsDMLabel = new JLabel("Data Mutation:");
		hsDMLabel.setForeground(Color.blue);
		gbc.gridwidth = 5;
		gbc.gridx = 0;
		gbc.gridy = 5;
		hsDataViewer.add(hsDMLabel,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();

		
		gbc.gridx = 0;
		gbc.gridy = 6;
		cbMutationType.setBackground(Color.blue);
		hsDataViewer.add(cbMutationType, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		cbMutationType.addItem("Mutation");
		cbMutationType.addItem("UPDATE");
		cbMutationType.addItem("DELETE");
		cbMutationType.setActionCommand("ChangeMutType");

		cbMutationType.addActionListener(new ActionListener() {
			
			@Override
		    public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				Integer UILock = getUIState(); 
				
				if (event.getActionCommand() == "ChangeMutType" && UILock == 0) {
					setUIState(1);

					cbMutationSetCol.removeAllItems();
					cbMutationSetValue.removeAllItems();
					setQueryFilterType(1,0,0);
					setQueryFilterType(2,0,0);
					
					if(cbMutationType.getSelectedItem()=="DELETE") {
						cbMutationSetCol.setEnabled(false);
						cbMutationSetValue.setEnabled(false);
					} else {
						cbMutationSetCol.setEnabled(true);
						cbMutationSetValue.setEnabled(true);
					}

					loadComboBox(cbMutationTable, "_metaTables", "", 3);
					setUIState(0);
					
				}
			}

		});
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		cbMutationTable.setBackground(Color.blue);
		hsDataViewer.add(cbMutationTable, gbc);
		loadComboBox(cbMutationTable, "_metaMutTables", "", 3);
		gbc = (GridBagConstraints) gbcClean.clone();
		/*
			cbMutationType cbMutationTable cbMutationSetCol	cbMutationSetValue cbMutationWhereCol cbMutationWhereCond cbMutationWhereData 
		*/
		cbMutationTable.addActionListener(new ActionListener() {
			
			@Override
		    public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> sourceCB = (JComboBox<String>) event.getSource();
				System.out.println("cbTable check: Name of ComboBox: "+ sourceCB.getName());
				System.out.println("cbTable check: getID: "+ event.getID());
				System.out.println("cbTable check: ActionCommand: "+ event.getActionCommand());
				System.out.println("cbTable check: paramString: "+ event.paramString());
				System.out.println("cbTable check: hasCode: "+ event.hashCode());
				System.out.println("cbTable check: toString: "+ event.toString());
				Integer UILock = getUIState(); 
				
				if (event.getActionCommand() == "TableChange" && UILock == 0) {
					setUIState(1);

					cbMutationSetCol.removeAllItems();
					cbMutationSetValue.removeAllItems();
					cbMutationWhereCol.removeAllItems();
					cbMutationWhereData.removeAllItems();
					setQueryFilterType(1,0,0);
					setQueryFilterType(2,0,0);
					String sqlQuery = "SELECT * FROM "+ cbMutationTable.getSelectedItem().toString();
					loadComboBox(cbMutationSetCol, "_mutSetColumns", sqlQuery, 3);
					loadComboBox(cbMutationWhereCol, "_mutWhereColumns", sqlQuery, 3);
					setUIState(0);
					
				}
			}

		});
		
		gbc.gridx = 2;
		gbc.gridy = 6;
		cbMutationSetCol.setBackground(Color.blue);
		hsDataViewer.add(cbMutationSetCol, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		cbMutationSetCol.addActionListener(new ActionListener() {
			
			@Override
		    public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> sourceCB = (JComboBox<String>) event.getSource();
				System.out.println("cbTable check: Name of ComboBox: "+ sourceCB.getName());
				System.out.println("cbTable check: getID: "+ event.getID());
				System.out.println("cbTable check: ActionCommand: "+ event.getActionCommand());
				System.out.println("cbTable check: paramString: "+ event.paramString());
				System.out.println("cbTable check: hasCode: "+ event.hashCode());
				System.out.println("cbTable check: toString: "+ event.toString());
				Integer UILock = getUIState(); 
				
				if (event.getActionCommand() == "ColumnChange" && UILock == 0) {
					setUIState(1);
					cbMutationSetValue.removeAllItems();
					setQueryFilterType(1,0,0);
					String sqlQuery = "SELECT DISTINCT "+ cbMutationSetCol.getSelectedItem().toString() + " FROM "+ cbMutationTable.getSelectedItem().toString() + " ORDER BY "+cbMutationSetCol.getSelectedItem().toString()+" ASC";
					loadComboBox(cbMutationSetValue, "_mutSetValue", sqlQuery, 1);
					setUIState(0);
					
				}
			}

		});

		gbc.gridx = 3;
		gbc.gridy = 6;
		cbMutationSetValue.setBackground(Color.blue);
		hsDataViewer.add(cbMutationSetValue, gbc);
		gbc = (GridBagConstraints) gbcClean.clone();
		
		
		JButton hsMutationQuery = new JButton("Show Mutation");
		gbc.gridx = 4;
		gbc.gridy = 6;
		hsMutationQuery.setBackground(Color.blue);
		hsDataViewer.add(hsMutationQuery,gbc);
		gbc = (GridBagConstraints) gbcClean.clone();

		hsMutationQuery.addActionListener(new ActionListener(){
	    	
	    	
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
	    
	    setLayout(new GridLayout(1, 1));
		add(hsInterface);
    }
    
    private void setQueryFilterType(Integer intQueryType, Integer index,  Integer intColType ) {
    	if (index == 0 && intColType == 0 ) {
    		this.QueryFilterType[intQueryType] = new Integer[20];
    	}else {
    		this.QueryFilterType[intQueryType][index] = intColType;
    	}
    	
    }

    private Integer getQueryFilterType(Integer intQueryType, Integer index ) {
    	return this.QueryFilterType[intQueryType][index];
    }

    private Integer getUIState() {
    	return this.UIState;
    }
    
    private void setUIState(Integer intState) {
    	this.UIState = intState;
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

	protected Boolean loadComboBox(JComboBox<String> loadBox, String dataSelect, String sqlQuery, Integer colIndex) {
		try {
			
				createDBConnection();
				ResultSet rs;
				DatabaseMetaData md;
				ResultSetMetaData rsmd;
				
				loadBox = new JComboBox<String>();
				
				if (dataSelect == "_metaTables") {
					
					md = this.dbConn.getMetaData();
					this.cbTable.addItem("");
					rs = md.getTables(null, "THH0003", "%", null);
					while (rs.next()) {

						this.cbTable.addItem(rs.getString(colIndex));
					}
					
				} else if (dataSelect == "_metaMutTables") {
						
						md = this.dbConn.getMetaData();
						this.cbMutationTable.addItem("");
						rs = md.getTables(null, "THH0003", "%", null);
						while (rs.next()) {

							this.cbMutationTable.addItem(rs.getString(colIndex));
						}
						
				} else if (dataSelect == "_metaColumns"){
					
					Statement stmt = this.dbConn.createStatement();
					this.cbCol.addItem("");
					rs = stmt.executeQuery(sqlQuery);
					rsmd=rs.getMetaData();
					
					for (int i=1; i <= rsmd.getColumnCount();i++){
						
						System.out.println("Column Data Type: "+ rsmd.getColumnType(i) );
						this.cbCol.addItem(rsmd.getColumnName(i));
						setQueryFilterType(0,i, rsmd.getColumnType(i));
						
						
					}
					
				} else if (dataSelect == "_selectData"){
					Statement stmt = this.dbConn.createStatement();
//					System.out.println("Loading Table List: "+ dataSelect );
					rs = stmt.executeQuery(sqlQuery);
					while (rs.next()){
	//					System.out.println("Data: "+ rs.getString(colIndex) );
						this.cbSelectData.addItem(rs.getString(colIndex));
					}
					
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		} {
			closeDBConnection();
			return true;
			
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