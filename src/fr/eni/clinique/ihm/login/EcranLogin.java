package fr.eni.clinique.ihm.login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
		panelPrincipal.add(new JLabel("Nom"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelPrincipal.add(getMotPasse(), gbc);
		
		
		this.setContentPane(panelPrincipal);
		
	}
	
	public JTextField getMotPasse(){
		if(textMotPasse == null){
			textMotPasse = new JTextField(30);
		}
		return textMotPasse;
	}
}