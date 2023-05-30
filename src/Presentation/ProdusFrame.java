package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
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
import BusinessLogic.ProdusBLL;
import Model.Client;
import Model.Produs;





/**
 * @author sergi
 * 
 * clasa pentru generarea interfetei produsului
 *
 */

public class ProdusFrame {
	
	/**
	 * genereaza headerul si populeaza tabelul
	 * @param list
	 * @param table
	 */
	public static void getHeader(ArrayList<Produs> list, JTable table) {
	     Produs p= list.get(0);
	    String[] header = new String[p.getClass().getDeclaredFields().length];
	    int index = 0;
	    for (Field field : p.getClass().getDeclaredFields()) {
	        field.setAccessible(true);
	        header[index] = field.getName();
	        index++;
	    }

	    DefaultTableModel model = new DefaultTableModel(0, header.length);
	    model.setColumnIdentifiers(header);
	    table.setModel(model);

	    for (Produs pr : list) {
	        Object[] rowData = new Object[header.length];
	        int i = 0;
	        for (Field field : pr.getClass().getDeclaredFields()) {
	            field.setAccessible(true);
	            try {
	                rowData[i] = field.get(pr);
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
	 * creeaza frameul pt produs
	 */
	public void createProdusFrame() {
	     JFrame produs = new JFrame();
	        produs.setSize(1920, 1080);
	        produs.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			produs.setLayout(null);
			JButton back = new JButton("back");

			back.setBounds(850, 400, 100, 100);
			
		     JFrame afis = new JFrame();
		   
				JButton insert=new JButton("insert");
				insert.setBounds(200,700,150,100);
				JButton deleteID=new JButton("deleteID");
				deleteID.setBounds(400,700,150,100);
				JButton deleteName=new JButton("deleteName");
				deleteName.setBounds(600,700,150,100);
				JButton updateName=new JButton("updateName");
				updateName.setBounds(10,300,150,40);
				JButton updateCantitate=new JButton("updateCantitate");
				updateCantitate.setBounds(10,400,150,40);
				JButton updatePret=new JButton("updatePret");
				updatePret.setBounds(10,500,150,40);
				
				JLabel idp = new JLabel("idp produs");
				idp.setBounds(200, 145, 300, 150);
				JTextField idprodus = new JTextField();
				idprodus.setBounds(500, 200, 300, 40);
				JLabel name = new JLabel("nume produs");
				name.setBounds(200, 245, 300, 150);
				JTextField nameProdus = new JTextField();
				nameProdus.setBounds(500, 300, 300, 40);
				JLabel cantitate = new JLabel("cantitate produs");
				cantitate.setBounds(200, 345, 300, 150);
				JTextField cantitateProdus = new JTextField();
				cantitateProdus.setBounds(500, 400, 300, 40);
				JLabel pret = new JLabel("pret produs");
				pret.setBounds(200, 445, 300, 150);
				JTextField pretProdus = new JTextField();
				pretProdus.setBounds(500, 500, 300, 40);
			
				JTable table=new JTable();
				table.setBounds(1000,200,600,500);
				JButton refresh=new JButton("refresh");
				refresh.setBounds(200,100,100,100);
				

			back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainFrame main = new MainFrame();
					main.createFrame();// .createClientFrame();
					produs.setVisible(false);
				}
			});
			insert.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Produs p = new Produs(Integer.parseInt(idprodus.getText()), nameProdus.getText(),
						Integer.parseInt(cantitateProdus.getText()), Double.parseDouble(pretProdus.getText()));
					try {
						ProdusBLL.insert(p);
					} catch (InvalidDataException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}

				}
			});
			
			deleteID.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the product you want to move:");
					int id=Integer.parseInt(idStr);
					//ClientDAO.deleteID(id);
					ProdusBLL.deleteID(id);
				}
			});
			
			deleteName.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nameStr = JOptionPane.showInputDialog(null,"Please enter the id of the product you want to move:");
					//int id=Integer.parseInt(idStr);
					//ClientDAO.deleteName(nameStr);
					ProdusBLL.deleteName(nameStr);
				}
			});
			
			updateName.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the product you want to move:");
					String nameStr=JOptionPane.showInputDialog(null,"Please enter the name of the product you want to move:");
					//int id=Integer.parseInt(idStr);
					ProdusBLL.updateName(Integer.parseInt(idStr), nameStr);
					
				}
			});
		
			
			updateCantitate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the product you want to move:");
					String cantitateStr=JOptionPane.showInputDialog(null,"Please enter the cantitate of the product you want to move:");
					//int id=Integer.parseInt(idStr);
					ProdusBLL.updateCantitate(Integer.parseInt(idStr), Integer.parseInt(cantitateStr));
					
				}
			});
			
			updatePret.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = JOptionPane.showInputDialog(null,"Please enter the id of the product you want to move:");
					String pretStr=JOptionPane.showInputDialog(null,"Please enter the pret of the product you want to move:");
					//int id=Integer.parseInt(idStr);
					ProdusBLL.updatePret(Integer.parseInt(idStr), Double.parseDouble(pretStr));
					
				}
			});
			
			refresh.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				ArrayList<Produs> lista=new ArrayList<Produs>();
				lista=ProdusBLL.createList();
				getHeader(lista,table);
					
				}
			});
			
			
			//int selID=extractClient(table);
			//System.out.println(selID);
	        
			produs.add(back);
		
			produs.add(idp);
			produs.add(idprodus);
			produs.add(nameProdus);
			produs.add(name);
			produs.add(cantitateProdus);
			produs.add(cantitate);
			produs.add(pretProdus);
			produs.add(pret);
		
			produs.add(insert);
			produs.add(deleteID);
			produs.add(deleteName);
			produs.add(updateName);
			produs.add(updateCantitate);
			produs.add(updatePret);
			//produs.add(updateAdress);
			//client.add(table);
			produs.add(refresh);
			
	    
	        produs.setVisible(true);
	}

}
