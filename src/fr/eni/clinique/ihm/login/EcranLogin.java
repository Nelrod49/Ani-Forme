package fr.eni.clinique.ihm.login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.LoginManager;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;

public class EcranLogin extends JFrame{
	/**
	 * 
	 */
	private JPasswordField textMotPasse;
	private JTextField textNom;
	private JButton buttonLogin;
	private JPanel panelPrincipal;
	
	public EcranLogin(){
		this.setSize(new Dimension(600,400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login");
		this.initIHM();
	}
	
	private void initIHM(){
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		panelPrincipal.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPrincipal.add(new JLabel("Nom : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelPrincipal.add(getNom(), gbc);
		

		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelPrincipal.add(new JLabel("Mot de Passe : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelPrincipal.add(getMotPasse(), gbc);
		

		
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelPrincipal.add(getButtonLogin(), gbc);
		
		this.setContentPane(panelPrincipal);
		
	}

	
	private JTextField getNom(){
		if(textNom == null){
			textNom = new JTextField(20);
		}
		return textNom;
	}
	
	private JTextField getMotPasse(){
		if(textMotPasse == null){
			textMotPasse = new JPasswordField(20);
		}
		return textMotPasse;
	}
	
	private JButton getButtonLogin(){
		if(buttonLogin == null){
			buttonLogin = new JButton();	
			buttonLogin.setText("Login");
			buttonLogin.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0){
					try {
						LoginManager lManager = new LoginManager();
						String passText = new String(textMotPasse.getPassword());
						Personnels p1 = new Personnels(textNom.getText(),passText);
						passText = null;
						try {
							if(lManager.validerConnection(p1)){
								//TODO Redirect to 
								PersonnelsDAO personnelDAO = DAOFactory.getPersonnelsDAO();
								p1 = personnelDAO.getAllData(p1);
								//Renvoie vers fenetre GestionPers	
								
								EcranPrincipal ecranPrincipal = new EcranPrincipal(p1);
								ecranPrincipal.setVisible(true);
								
								//ferme la fen�tre EcranLogin
								EcranLogin.this.dispose();
								
								//TODO fermer le JFrame EcranLogin.
							
								

							
							}else{
								JOptionPane d = new JOptionPane();
								d.showMessageDialog(panelPrincipal,
									    "Connexion refuser.",
									    "Refuser",
									    JOptionPane.ERROR_MESSAGE);
								System.out.println("Connection �chou�");
							}
						} catch (BLLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (BLLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			});
		}
		return buttonLogin;
	}
}