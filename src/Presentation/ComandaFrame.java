package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.ClientBLL;
import BusinessLogic.ComandaBLL;
import BusinessLogic.InvalidDataException;
import BusinessLogic.ProdusBLL;
import Connection.ConnectionFactory;
import DataAccess.BillDAO;
import Model.Bill;
import Model.Client;
import Model.Comanda;
import Model.Produs;





/**
 * @author sergi
 * 
 * clasa care geneareza interfata pentru comanda
 *
 */
public class ComandaFrame {
	
	
	private static final String afisStringClient="SELECT * from client WHERE idc = ?";
	private static final String afisStringProdus="SELECT * from produs WHERE idp = ?";
	//private static final String afisStringComanda="SELECT * from produs WHERE idp = ?";
	
	/**
	 * genereaza headerul si populeaza tabelul
	 * @param list
	 * @param table
	 */
	public static void getHeader(ArrayList<Comanda> list, JTable table) {
	     Comanda co= list.get(0);
	    String[] header = new String[co.getClass().getDeclaredFields().length];
	    int index = 0;
	    for (Field field : co.getClass().getDeclaredFields()) {
	        field.setAccessible(true);
	        header[index] = field.getName();
	        index++;
	    }

	    DefaultTableModel model = new DefaultTableModel(0, header.length);
	    model.setColumnIdentifiers(header);
	    table.setModel(model);

	    for (Comanda com : list) {
	        Object[] rowData = new Object[header.length];
	        int i = 0;
	        for (Field field : com.getClass().getDeclaredFields()) {
	            field.setAccessible(true);
	            try {
	                rowData[i] = field.get(com);
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	            i++;
	        }
	        model.addRow(rowData);
	    }

	    JScrollPane scrollPane = new JScrollPane(table);
	    JFrame frame = new JFrame();
	    frame.add(scrollPane);
	   // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	/**
	 * genereaza un obiect client din un client din baza de date
	 * @param id
	 * @return
	 */
	public static Client generateClient(int id) {
	    Connection conn = ConnectionFactory.getConnection();
	    PreparedStatement stat = null;
	    ResultSet rs = null;
	    Client c = null; // Initialize c to null

	    try {
	        stat = conn.prepareStatement(afisStringClient);
	       
			stat.setInt(1, id);
	        rs = stat.executeQuery();
	        if (rs.next()) { 
	            try {
	                c = ClientBLL.generateClient(rs);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionFactory.closeResultSet(rs);
	        ConnectionFactory.closeStatement(stat);
	        ConnectionFactory.closeConnection(conn);
	    }
	    System.out.println(c.toString());
	    return c; // Return the generated client object
	    //System.out.println(c.toString());
	}
	

	
	/**
	 * genereaza un obiect produs din un produs din baza de date
	 * @param id
	 * @return
	 */
	public static Produs generateProdus(int id) {
	    Connection conn = ConnectionFactory.getConnection();
	    PreparedStatement stat = null;
	    ResultSet rs = null;
	    Produs p = null; // Initialize c to null

	    try {
	        stat = conn.prepareStatement(afisStringProdus);
	       
			stat.setInt(1, id);
	        rs = stat.executeQuery();
	        if (rs.next()) { 
	            try {
	                p = ProdusBLL.generateProdus(rs);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionFactory.closeResultSet(rs);
	        ConnectionFactory.closeStatement(stat);
	        ConnectionFactory.closeConnection(conn);
	    }
	    System.out.println(p.toString());
	    return p; // Return the generated client object
	    //System.out.println(c.toString());
	}

	
	/**
	 * creeaza frame-ul
	 */
	public void createComandaFrame() {
		
		   JFrame comanda = new JFrame();
	        comanda.setSize(1920, 1080);
	        comanda.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        comanda.setLayout(null);
	       // JFrame afisClient=new JFrame();
	        //JFrame afisProdus=new JFrame();
			JTable table = new JTable();
			table.setBounds(1000, 200, 600, 500);
			JButton refreshClient = new JButton("afisClient");
			refreshClient.setBounds(200, 100, 150, 150);
			JButton refreshProdus = new JButton("afisProdus");
			refreshProdus.setBounds(200, 300, 150, 150);
			JButton createCommand = new JButton("createCommand");
			createCommand.setBounds(200, 500, 150, 150);
			JButton refresh = new JButton("refresh");
			refresh.setBounds(200, 700, 150, 150);
			JButton generateBill = new JButton("generateBill");
			generateBill.setBounds(400, 400, 150, 150);

			refreshClient.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<Client> lista = new ArrayList<Client>();
					lista = ClientBLL.createList();
					ClientFrame.getHeader(lista, table);

				}
			});
	    	
			refreshProdus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				ArrayList<Produs> lista=new ArrayList<Produs>();
				lista=ProdusBLL.createList();
				ProdusFrame.getHeader(lista,table);
					
				}
			});
			
			refresh.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				ArrayList<Comanda> lista=new ArrayList<Comanda>();
				lista=ComandaBLL.createList();
				getHeader(lista,table);
					
				}
			});
			
			
			createCommand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// String idcoStr = JOptionPane.showInputDialog(null,"Please enter the id of the
					// comanda you want to move:");
					// int idco=Integer.parseInt(idcoStr);
					String idStr = JOptionPane.showInputDialog(null,
							"Please enter the id of the client you want to move:");
					int id = Integer.parseInt(idStr);
					Client c = generateClient(id);

					String idStr1 = JOptionPane.showInputDialog(null,
							"Please enter the id of the produs you want to move:");
					int id1 = Integer.parseInt(idStr1);
					Produs p = generateProdus(id1);
					String idcoStr = JOptionPane.showInputDialog(null,
							"Please enter the id of the comanda you want to move:");
					int idco = Integer.parseInt(idcoStr);
					String cantitateStr = JOptionPane.showInputDialog(null,
							"Please enter the id of the comanda you want to move:");
					int cantitate = Integer.parseInt(cantitateStr);
					try {
						ComandaBLL.createComanda(c, p, cantitate, idco);
					
					
					} catch (InvalidDataException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					
						
					
				}
				
				
			});
			
			generateBill.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				ArrayList<Bill> lista=new ArrayList<Bill>();
				lista=BillDAO.generateBills();
				BillDAO.insertBills(lista);
					
				}
			});
	    	
			comanda.add(createCommand);
	    	comanda.add(refreshClient);
	    	comanda.add(refreshProdus);
	    	comanda.add(refresh);
	    	comanda.add(generateBill);
			
	        comanda.setVisible(true);
		
	}

}
