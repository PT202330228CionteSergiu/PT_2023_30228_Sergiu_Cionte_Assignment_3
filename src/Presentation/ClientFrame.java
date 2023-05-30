package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.ClientBLL;
import BusinessLogic.InvalidDataException;
import Connection.ConnectionFactory;
import DataAccess.ClientDAO;
import DataAccess.GenericDAO;
import Model.Client;




/**
 * @author sergi
 * 
 * clasa care genereaza interfata pentru client
 *
 */
public class ClientFrame {
	//public MainFrame main=new MainFrame();
	/**
	 * genereaza header si populeaza tabelul
	 * @param list
	 * @param table
	 */
	public static void getHeader(ArrayList<Client> list, JTable table) {
	    Client c = list.get(0);
	    String[] header = new String[c.getClass().getDeclaredFields().length];
	    int index = 0;
	    for (Field field : c.getClass().getDeclaredFields()) {
	        field.setAccessible(true);
	        header[index] = field.getName();
	        index++;
	    }

	    DefaultTableModel model = new DefaultTableModel(0, header.length);
	    model.setColumnIdentifiers(header);
	    table.setModel(model);

	    for (Client cl : list) {
	        Object[] rowData = new Object[header.length];
	        int i = 0;
	        for (Field field : cl.getClass().getDeclaredFields()) {
	            field.setAccessible(true);
	            try {
	                rowData[i] = field.get(cl);
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
	 * creeaza frame pt client
	 */
	public void createClientFrame() {
		 Connection conn = ConnectionFactory.getConnection();
	     JFrame client = new JFrame();
	        client.setSize(1920, 1080);
	        client.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        client.setLayout(null);
	    	JButton back = new JButton("back");
			back.setBounds(850, 400, 150, 100);
			JButton insert=new JButton("insert");
			insert.setBounds(200,700,150,100);
			JButton deleteID=new JButton("deleteID");
			deleteID.setBounds(400,700,150,100);
			JButton deleteName=new JButton("deleteName");
			deleteName.setBounds(600,700,150,100);
			JButton updateName=new JButton("updateName");
			updateName.setBounds(10,300,150,40);
			JButton updateAdress=new JButton("updateAdress");
			updateAdress.setBounds(10,400,150,40);
			JButton updateEmail=new JButton("updateEmail");
			updateEmail.setBounds(10,500,150,40);
			JButton updateAge=new JButton("updateAge");
			updateAge.setBounds(10,600,150,40);
			JLabel idc = new JLabel("id client");
			idc.setBounds(200, 145, 300, 150);
			JTextField idclient = new JTextField();
			idclient.setBounds(500, 200, 300, 40);
			JLabel name = new JLabel("nume client");
			name.setBounds(200, 245, 300, 150);
			JTextField nameClient = new JTextField();
			nameClient.setBounds(500, 300, 300, 40);
			JLabel address = new JLabel("adresa client");
			address.setBounds(200, 345, 300, 150);
			JTextField addressClient = new JTextField();
			addressClient.setBounds(500, 400, 300, 40);
			JLabel email = new JLabel("email client");
			email.setBounds(200, 445, 300, 150);
			JTextField emailClient = new JTextField();
			emailClient.setBounds(500, 500, 300, 40);
			JLabel age = new JLabel("varsta client");
			age.setBounds(200, 545, 300, 150);
			JTextField ageClient = new JTextField();
			ageClient.setBounds(500, 600, 300, 40);
			JTable table=new JTable();
			table.setBounds(1000,200,600,500);
			JButton refresh=new JButton("refresh");
			refresh.setBounds(200,100,100,100);
			GenericDAO<Client> clientDAOO = new GenericDAO<>(conn);
			
			

			back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainFrame main = new MainFrame();
					main.createFrame();// .createClientFrame();
					client.setVisible(false);
				}
			});

			insert.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Client c = new Client(Integer.parseInt(idclient.getText()), nameClient.getText(),
							addressClient.getText(), emailClient.getText(), Integer.parseInt(ageClient.getText()));
					GenericDAO<Client> clientDAO = new GenericDAO<>(conn); 

					try {
						ClientBLL.insert(c, clientDAO);
					} catch (InvalidDataException ex) {
						ex.printStackTrace();
						// Handle the InvalidDataException
					}

				}
			});
			
			deleteID.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the client you want to move:");
					int id=Integer.parseInt(idStr);
					//ClientDAO.deleteID(id);
					ClientBLL.deleteID(id);
				}
			});
			
			deleteName.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nameStr = JOptionPane.showInputDialog(null,"Please enter the id of the client you want to move:");
					//int id=Integer.parseInt(idStr);
					//ClientDAO.deleteName(nameStr);
					ClientBLL.deleteName(nameStr);
				}
			});
			
			updateName.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the client you want to move:");
					String nameStr=JOptionPane.showInputDialog(null,"Please enter the name of the client you want to move:");
					//int id=Integer.parseInt(idStr);
					ClientBLL.updateName(Integer.parseInt(idStr), nameStr);
					
				}
			});
			
			updateEmail.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the client you want to move:");
					String emailStr=JOptionPane.showInputDialog(null,"Please enter the email of the client you want to move:");
					//int id=Integer.parseInt(idStr);
					ClientBLL.updateEmail(Integer.parseInt(idStr), emailStr);
					
				}
			});
			
			updateAdress.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the client you want to move:");
					String addressStr=JOptionPane.showInputDialog(null,"Please enter the address of the client you want to move:");
					//int id=Integer.parseInt(idStr);
					ClientBLL.updateAddress(Integer.parseInt(idStr), addressStr);
					
				}
			});
			
			updateAge.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the client you want to move:");
					String ageStr=JOptionPane.showInputDialog(null,"Please enter the age of the client you want to move:");
					//int id=Integer.parseInt(idStr);
					ClientBLL.updateAge(Integer.parseInt(idStr), Integer.parseInt(ageStr));
					
				}
			});
			
			refresh.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				ArrayList<Client> lista=new ArrayList<Client>();
				lista=ClientBLL.createList();
				getHeader(lista,table);
					
				}
			});
			
			
			

			client.add(back);
			client.add(idc);
			client.add(idclient);
			client.add(nameClient);
			client.add(name);
			client.add(addressClient);
			client.add(address);
			client.add(emailClient);
			client.add(email);
			client.add(ageClient);
			client.add(age);
			client.add(insert);
			client.add(deleteID);
			client.add(deleteName);
			client.add(updateName);
			client.add(updateAge);
			client.add(updateEmail);
			client.add(updateAdress);
			client.add(table);
			client.add(refresh);
			
	        client.setVisible(true);
	}

}
