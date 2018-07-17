package fr.eni.clinique.ihm.login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EcranLogin extends JFrame{
	private JTextField textMotPasse;
	private JTextField textNom;
	private JButton buttonLogin;
	
	public EcranLogin(String titre){
		super(titre);
		this.setSize(new Dimension(600,400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initIHM();
	}
	
	private void initIHM(){
		JPanel panelPrincipal = new JPanel();
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
			textMotPasse = new JTextField(20);
		}
		return textMotPasse;
	}
	
	private JButton getButtonLogin(){
		if(buttonLogin == null){
			buttonLogin = new JButton();	
			buttonLogin.setText("Login");
		}
		return buttonLogin;
	}
}