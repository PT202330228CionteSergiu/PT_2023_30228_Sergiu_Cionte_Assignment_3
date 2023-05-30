package Presentation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;




/**
 * @author sergi
 * 
 * clasa pentru interfata principala
 *
 */
public class MainFrame {

	public ClientFrame clientInterfata = new ClientFrame();
	public ProdusFrame produsInterfata=new ProdusFrame();
	public ComandaFrame comandaInterfata=new ComandaFrame();

	/**
	 * creeaza frameul principal
	 */
	public void createFrame() {
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(1920, 1080);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);
		JLabel title = new JLabel("Order Management");
		title.setBounds(700, 200, 300, 100);
		title.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
		JButton client = new JButton("Client");
		client.setBounds(650, 400, 100, 100);
		JButton produs = new JButton("Produs");
		produs.setBounds(850, 400, 100, 100);
		JButton comanda = new JButton("Comanda");
		comanda.setBounds(750, 550, 100, 100);

		client.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				clientInterfata.createClientFrame();
				mainFrame.setVisible(false);
			}
		});
		
		produs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				produsInterfata.createProdusFrame();
				mainFrame.setVisible(false);
			}
		});
		
		comanda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				comandaInterfata.createComandaFrame();
				mainFrame.setVisible(false);
			}
		});

		mainFrame.add(client);
		mainFrame.add(produs);
		mainFrame.add(title);
		mainFrame.add(comanda);
		mainFrame.setVisible(true);
		// mainFrame.add(title);
	}

}
