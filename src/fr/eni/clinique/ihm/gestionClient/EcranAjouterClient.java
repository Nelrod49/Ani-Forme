package fr.eni.clinique.ihm.gestionClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.GestionClientManager;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DAOFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class EcranAjouterClient extends JFrame {
	
	/* Formulaire de saisie*/
	private JPanel contentPane;
	private JTextField textFieldPrenomClient;
	private JTextField textFieldNomClient;
	private JTextField textFieldAdresseClient_1;
	private JTextField textFieldCodePostalClient;
	private JTextField textFieldVilleClient;
	private JTextField textFieldAdresseClient_2;
	private JTextField textFieldNumTel;
	private JTextField textFieldAssurance;
	private JTextField textFieldEmail;
	private JTextField textFieldArchive;
	
	/*Bouttons*/
	private JButton btnAjouter;
	private JButton btnAnnuler;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranAjouterClient frame = new EcranAjouterClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EcranAjouterClient() {
		setTitle("Ajouter un client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{424, 0};
		gbl_contentPane.rowHeights = new int[]{33, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelBtn = new JPanel();
		GridBagConstraints gbc_panelBtn = new GridBagConstraints();
		gbc_panelBtn.insets = new Insets(0, 0, 5, 0);
		gbc_panelBtn.anchor = GridBagConstraints.NORTH;
		gbc_panelBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBtn.gridx = 0;
		gbc_panelBtn.gridy = 0;
		contentPane.add(panelBtn, gbc_panelBtn);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Dois-je crée un nouveau client oui je pense sinon ajouter client n'aura rien en paramère 
				 * et devrait renvoyer un nullpointer exception*/
				Clients c = new Clients(textFieldNomClient.getText(), textFieldPrenomClient.getText(),textFieldAdresseClient_1.getText(), textFieldCodePostalClient.getText(), textFieldVilleClient.getText());
				System.out.println(c);
				
				try {
					GestionClientManager.getInstance().ajouterClient(c);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelBtn.add(btnAjouter);
		
		JButton btnAnnuler = new JButton("Annuler");
		panelBtn.add(btnAnnuler);
		
		JPanel panelForm = new JPanel();
		GridBagConstraints gbc_panelForm = new GridBagConstraints();
		gbc_panelForm.fill = GridBagConstraints.BOTH;
		gbc_panelForm.gridx = 0;
		gbc_panelForm.gridy = 1;
		contentPane.add(panelForm, gbc_panelForm);
		GridBagLayout gbl_panelForm = new GridBagLayout();
		gbl_panelForm.columnWidths = new int[]{156, 21, 86, 0};
		gbl_panelForm.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelForm.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelForm.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelForm.setLayout(gbl_panelForm);
		
		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 0;
		panelForm.add(lblNom, gbc_lblNom);
		
		textFieldNomClient = new JTextField();
		GridBagConstraints gbc_textFieldNomClient = new GridBagConstraints();
		gbc_textFieldNomClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNomClient.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNomClient.gridx = 2;
		gbc_textFieldNomClient.gridy = 0;
		panelForm.add(textFieldNomClient, gbc_textFieldNomClient);
		textFieldNomClient.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom");
		GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
		gbc_lblPrenom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrenom.gridx = 0;
		gbc_lblPrenom.gridy = 1;
		panelForm.add(lblPrenom, gbc_lblPrenom);
		
		textFieldPrenomClient = new JTextField();
		GridBagConstraints gbc_textFieldPrenomClient = new GridBagConstraints();
		gbc_textFieldPrenomClient.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPrenomClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrenomClient.gridx = 2;
		gbc_textFieldPrenomClient.gridy = 1;
		panelForm.add(textFieldPrenomClient, gbc_textFieldPrenomClient);
		textFieldPrenomClient.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse");
		GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
		gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdresse.gridx = 0;
		gbc_lblAdresse.gridy = 2;
		panelForm.add(lblAdresse, gbc_lblAdresse);
		
		textFieldAdresseClient_1 = new JTextField();
		GridBagConstraints gbc_textFieldAdresseClient_1 = new GridBagConstraints();
		gbc_textFieldAdresseClient_1.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAdresseClient_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdresseClient_1.gridx = 2;
		gbc_textFieldAdresseClient_1.gridy = 2;
		panelForm.add(textFieldAdresseClient_1, gbc_textFieldAdresseClient_1);
		textFieldAdresseClient_1.setColumns(10);
		
		textFieldAdresseClient_2 = new JTextField();
		GridBagConstraints gbc_textFieldAdresseClient_2 = new GridBagConstraints();
		gbc_textFieldAdresseClient_2.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAdresseClient_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdresseClient_2.gridx = 2;
		gbc_textFieldAdresseClient_2.gridy = 3;
		panelForm.add(textFieldAdresseClient_2, gbc_textFieldAdresseClient_2);
		textFieldAdresseClient_2.setColumns(10);
		
		JLabel lblCodePostal = new JLabel("Code Postal");
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 0;
		gbc_lblCodePostal.gridy = 4;
		panelForm.add(lblCodePostal, gbc_lblCodePostal);
		
		textFieldCodePostalClient = new JTextField();
		GridBagConstraints gbc_textFieldCodePostalClient = new GridBagConstraints();
		gbc_textFieldCodePostalClient.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCodePostalClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCodePostalClient.gridx = 2;
		gbc_textFieldCodePostalClient.gridy = 4;
		panelForm.add(textFieldCodePostalClient, gbc_textFieldCodePostalClient);
		textFieldCodePostalClient.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 0;
		gbc_lblVille.gridy = 5;
		panelForm.add(lblVille, gbc_lblVille);
		
		textFieldVilleClient = new JTextField();
		GridBagConstraints gbc_textFieldVilleClient = new GridBagConstraints();
		gbc_textFieldVilleClient.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldVilleClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVilleClient.gridx = 2;
		gbc_textFieldVilleClient.gridy = 5;
		panelForm.add(textFieldVilleClient, gbc_textFieldVilleClient);
		textFieldVilleClient.setColumns(10);
		
		JLabel lblNumTel = new JLabel("Num Tel");
		GridBagConstraints gbc_lblNumTel = new GridBagConstraints();
		gbc_lblNumTel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumTel.gridx = 0;
		gbc_lblNumTel.gridy = 6;
		panelForm.add(lblNumTel, gbc_lblNumTel);
		
		textFieldNumTel = new JTextField();
		GridBagConstraints gbc_textFieldNumTel = new GridBagConstraints();
		gbc_textFieldNumTel.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNumTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNumTel.gridx = 2;
		gbc_textFieldNumTel.gridy = 6;
		panelForm.add(textFieldNumTel, gbc_textFieldNumTel);
		textFieldNumTel.setColumns(10);
		
		JLabel lblAssurance = new JLabel("Assurance");
		GridBagConstraints gbc_lblAssurance = new GridBagConstraints();
		gbc_lblAssurance.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssurance.gridx = 0;
		gbc_lblAssurance.gridy = 7;
		panelForm.add(lblAssurance, gbc_lblAssurance);
		
		textFieldAssurance = new JTextField();
		GridBagConstraints gbc_textFieldAssurance = new GridBagConstraints();
		gbc_textFieldAssurance.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAssurance.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAssurance.gridx = 2;
		gbc_textFieldAssurance.gridy = 7;
		panelForm.add(textFieldAssurance, gbc_textFieldAssurance);
		textFieldAssurance.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 8;
		panelForm.add(lblEmail, gbc_lblEmail);
		
		textFieldEmail = new JTextField();
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 2;
		gbc_textFieldEmail.gridy = 8;
		panelForm.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblRemarque = new JLabel("Remarque");
		GridBagConstraints gbc_lblRemarque = new GridBagConstraints();
		gbc_lblRemarque.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemarque.gridx = 0;
		gbc_lblRemarque.gridy = 9;
		panelForm.add(lblRemarque, gbc_lblRemarque);
		
		JTextArea textAreaRemarque = new JTextArea();
		GridBagConstraints gbc_textAreaRemarque = new GridBagConstraints();
		gbc_textAreaRemarque.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaRemarque.fill = GridBagConstraints.BOTH;
		gbc_textAreaRemarque.gridx = 2;
		gbc_textAreaRemarque.gridy = 9;
		panelForm.add(textAreaRemarque, gbc_textAreaRemarque);
		
		JLabel lblArchive = new JLabel("Archive");
		GridBagConstraints gbc_lblArchive = new GridBagConstraints();
		gbc_lblArchive.insets = new Insets(0, 0, 0, 5);
		gbc_lblArchive.gridx = 0;
		gbc_lblArchive.gridy = 10;
		panelForm.add(lblArchive, gbc_lblArchive);
		
		textFieldArchive = new JTextField();
		GridBagConstraints gbc_textFieldArchive = new GridBagConstraints();
		gbc_textFieldArchive.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldArchive.gridx = 2;
		gbc_textFieldArchive.gridy = 10;
		panelForm.add(textFieldArchive, gbc_textFieldArchive);
		textFieldArchive.setColumns(10);
	}
	
	

//	private JButton getBtnAjouter() {
//		if (btnAjouter == null) {
//			btnAjouter = new JButton();
//			
//			// définition du comportement du bouton
//			btnAjouter.addActionListener( new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					/*Dois-je crée un nouveau client oui je pense sinon ajouter client n'aura rien en paramère 
//					 * et devrait renvoyer un nullpointer exception*/
//					Clients c = new Clients(textFieldNomClient.getText(), textFieldPrenomClient.getText(),textFieldAdresseClient_1.getText(), textFieldCodePostalClient.getText(), textFieldVilleClient.getText());
//					System.out.println(c);
////					ClientsDAO clientDAO = DAOFactory.getClientsDAO();
////					clientDAO.insertClient(cli);
//					try {
//						GestionClientManager.getInstance().ajouterClient(c);
//					} catch (BLLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//				}
//			});
//			
//		}
//		return btnAjouter;
//	}
	

}
