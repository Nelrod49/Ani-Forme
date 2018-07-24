package fr.eni.clinique.ihm.gestionClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.ihm.login.EcranPrincipal;

public class EcranGestionClients extends JFrame {
	
	private JPanel panelGestionClients;
	private Clients leClient;
	private ArrayList<Animaux> lesAnimaux;
	private JButton buttonRechercher;
	private JButton buttonAjouterClient;
	private JButton buttonSupprimerClient;
	private JButton buttonValider;
	private JButton buttonAnnuler;
	private JTextField txtFieldNom;
	private JTextField txtFieldPrenom;
	private JTextField txtFieldAdresse1;
	private JTextField txtFieldAdresse2;
	private JTextField txtFieldCodePostal;
	private JTextField txtFieldVille;
	private JTable tableAnimaux;
	private JButton buttonAjouterAnimaux;
	private JButton buttonSupprimerAnimaux;
	private JButton buttonEditerAnimaux;
	
	public EcranGestionClients(Clients leClient) {
		this.setTitle("Gestion des clients");
		this.setSize(new Dimension(1000, 600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.leClient = leClient;
		this.initIHM();		
	}	
	
	public EcranGestionClients() {
		this.setTitle("Gestion des clients");
		this.setSize(new Dimension(1000, 600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initIHM();
	}
	
	
	private void initIHM() {
		panelGestionClients = new JPanel();
		panelGestionClients.setLayout(new GridBagLayout());
		panelGestionClients.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Top Bar
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelGestionClients.add(getButtonRechercher(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelGestionClients.add(getButtonAjouterClient(),gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		panelGestionClients.add(getButtonSupprimerClient(),gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		panelGestionClients.add(getButtonValider(),gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		panelGestionClients.add(getButtonAnnuler(),gbc);
		
		//Clients
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelGestionClients.add(new JLabel("Code "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		if(null != leClient){
			panelGestionClients.add(new JLabel(leClient.getCodeClient() + ""), gbc);
		}else{
			panelGestionClients.add(new JLabel(""), gbc);
		}
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelGestionClients.add(new JLabel("Nom "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelGestionClients.add(getTxtFieldNom(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelGestionClients.add(new JLabel("Prenom "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelGestionClients.add(getTxtFieldPrenom(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelGestionClients.add(new JLabel("Adresse "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelGestionClients.add(getTxtFieldAdresse1(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		panelGestionClients.add(getTxtFieldAdresse2(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		panelGestionClients.add(new JLabel("Code Postal "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		panelGestionClients.add(getTxtFieldCodePostal(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		panelGestionClients.add(new JLabel("Ville "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		panelGestionClients.add(getTxtFieldVille(), gbc);
		
		//Animaux
		gbc.gridx = 3;
		gbc.gridy = 1;
		panelGestionClients.add(getTableAnimaux(), gbc);		

		gbc.gridx = 3;
		gbc.gridy = 2;
		panelGestionClients.add(getButtonAjouterAnimaux(), gbc);		

		gbc.gridx = 4;
		gbc.gridy = 2;
		panelGestionClients.add(getButtonSupprimerAnimaux(), gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 2;
		panelGestionClients.add(getButtonEditerAnimaux(), gbc);
		
		this.setContentPane(panelGestionClients);
	}
	
	//Top Bar
	private JButton getButtonRechercher(){
		if(null == buttonRechercher){
			buttonRechercher = new JButton();
			buttonRechercher.setText("Rechercher");
			buttonRechercher.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					EcranRechercheClients ecranRechercheClients = new EcranRechercheClients();
					ecranRechercheClients.setVisible(true);
					EcranGestionClients.this.dispose();
					
				}
			});
		}
		return buttonRechercher;
	}
	
	private JButton getButtonAjouterClient(){
		if(null == buttonAjouterClient){
			buttonAjouterClient = new JButton();
			buttonAjouterClient.setText("Ajouter");
		}
		return buttonAjouterClient;
	}
	
	private JButton getButtonSupprimerClient(){
		if(null == buttonSupprimerClient){
			buttonSupprimerClient = new JButton();
			buttonSupprimerClient.setText("Supprimer");
		}
		return buttonSupprimerClient;
	}
	
	private JButton getButtonValider(){
		if(null == buttonValider){
			buttonValider = new JButton();
			buttonValider.setText("Valider");
		}
		return buttonValider;
	}
	
	private JButton getButtonAnnuler(){
		if(null == buttonAnnuler){
			buttonAnnuler = new JButton();
			buttonAnnuler.setText("Annuler");
		}
		return buttonAnnuler;
	}
	//Clients

	private JTextField getTxtFieldNom(){
		if(null == txtFieldNom){
			txtFieldNom = new JTextField(20);
			if(null != leClient){
				txtFieldNom.setText(leClient.getNomClient());
			}
		}
		return txtFieldNom;
	}
	
	private JTextField getTxtFieldPrenom(){
		if(null == txtFieldPrenom){
			txtFieldPrenom = new JTextField(20);
			if(null != leClient){
				txtFieldPrenom.setText(leClient.getPrenomClient());
			}
		}
		return txtFieldPrenom;
	}
	
	private JTextField getTxtFieldAdresse1(){
		if(null == txtFieldAdresse1){
			txtFieldAdresse1 = new JTextField(20);
			if(null != leClient){
				txtFieldAdresse1.setText(leClient.getAdresse1());
			}
		}
		return txtFieldAdresse1;
	}
	
	private JTextField getTxtFieldAdresse2(){
		if(null == txtFieldAdresse2){
			txtFieldAdresse2 = new JTextField(20);
			if(null != leClient){
				txtFieldAdresse2.setText(leClient.getAdresse2());
			}
		}
		return txtFieldAdresse2;
	}
	
	private JTextField getTxtFieldCodePostal(){
		if(null == txtFieldCodePostal){
			txtFieldCodePostal = new JTextField(20);
			if(null != leClient){
				txtFieldCodePostal.setText(leClient.getCodePostal());
			}
		}
		return txtFieldCodePostal;
	}
	
	private JTextField getTxtFieldVille(){
		if(null == txtFieldVille){
			txtFieldVille = new JTextField(20);
			if(null != leClient){
				txtFieldVille.setText(leClient.getVille());
			}
		}
		return txtFieldVille;
	}
	
	//Animaux
	private JTable getTableAnimaux(){
		if(null == tableAnimaux){
			tableAnimaux = new JTable();
		}
		return tableAnimaux;
	}
	
	private JButton getButtonAjouterAnimaux(){
		if(null == buttonAjouterAnimaux){
			buttonAjouterAnimaux = new JButton("Ajouter");
		}
		return buttonAjouterAnimaux;
	}
	
	private JButton getButtonSupprimerAnimaux(){
		if(null == buttonSupprimerAnimaux){
			buttonSupprimerAnimaux = new JButton("Supprimer");
		}
		return buttonSupprimerAnimaux;
	}
	
	private JButton getButtonEditerAnimaux(){
		if(null == buttonEditerAnimaux){
			buttonEditerAnimaux = new JButton("Éditer");
		}
		return buttonEditerAnimaux;
	}
	
	
}
