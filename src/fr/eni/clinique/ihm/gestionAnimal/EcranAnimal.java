package fr.eni.clinique.ihm.gestionAnimal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.GestionClientManager;
import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Especes;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.bo.Races;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.EspecesDAO;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.dal.RacesDAO;

import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class EcranAnimal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldClient;
	private JTextField textFieldCode;
	private JTextField textFieldNom;
	private JTextField textFieldCouleur;
	private JTextField textFieldTatouage;
	
	private JComboBox<String> comboBoxEspeces;
	private ArrayList<Especes> lesEspeces = new ArrayList<Especes>();
	
	private JComboBox<String> comboBoxRace;
	private ArrayList<Races>lesRaces = new ArrayList<Races>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranAnimal frame = new EcranAnimal();
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
	public EcranAnimal() {
		setTitle("Animaux");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{20, 66, 0, 0, 0, 97, 0, 0};
		gbl_panel.rowHeights = new int[]{68, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnValider = new JButton("Valider\r\n");
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.insets = new Insets(0, 0, 5, 5);
		gbc_btnValider.gridx = 5;
		gbc_btnValider.gridy = 0;
		panel.add(btnValider, gbc_btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		GridBagConstraints gbc_btnAnnuler = new GridBagConstraints();
		gbc_btnAnnuler.insets = new Insets(0, 0, 5, 0);
		gbc_btnAnnuler.gridx = 6;
		gbc_btnAnnuler.gridy = 0;
		panel.add(btnAnnuler, gbc_btnAnnuler);
		
		JLabel lblClient = new JLabel("Client");
		GridBagConstraints gbc_lblClient = new GridBagConstraints();
		gbc_lblClient.insets = new Insets(0, 0, 5, 5);
		gbc_lblClient.gridx = 1;
		gbc_lblClient.gridy = 1;
		panel.add(lblClient, gbc_lblClient);
		
		textFieldClient = new JTextField();
		GridBagConstraints gbc_textFieldClient = new GridBagConstraints();
		gbc_textFieldClient.gridwidth = 3;
		gbc_textFieldClient.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldClient.gridx = 3;
		gbc_textFieldClient.gridy = 1;
		panel.add(textFieldClient, gbc_textFieldClient);
		textFieldClient.setColumns(10);
		
		JLabel lblCode = new JLabel("Code");
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 3;
		panel.add(lblCode, gbc_lblCode);
		
		textFieldCode = new JTextField();
		GridBagConstraints gbc_textFieldCode = new GridBagConstraints();
		gbc_textFieldCode.gridwidth = 3;
		gbc_textFieldCode.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCode.gridx = 3;
		gbc_textFieldCode.gridy = 3;
		panel.add(textFieldCode, gbc_textFieldCode);
		textFieldCode.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 1;
		gbc_lblNom.gridy = 4;
		panel.add(lblNom, gbc_lblNom);
		
		textFieldNom = new JTextField();
		GridBagConstraints gbc_textFieldNom = new GridBagConstraints();
		gbc_textFieldNom.gridwidth = 3;
		gbc_textFieldNom.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNom.gridx = 3;
		gbc_textFieldNom.gridy = 4;
		panel.add(textFieldNom, gbc_textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblCouleur = new JLabel("Couleur");
		GridBagConstraints gbc_lblCouleur = new GridBagConstraints();
		gbc_lblCouleur.insets = new Insets(0, 0, 5, 5);
		gbc_lblCouleur.gridx = 1;
		gbc_lblCouleur.gridy = 5;
		panel.add(lblCouleur, gbc_lblCouleur);
		
		textFieldCouleur = new JTextField();
		GridBagConstraints gbc_textFieldCouleur = new GridBagConstraints();
		gbc_textFieldCouleur.gridwidth = 3;
		gbc_textFieldCouleur.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCouleur.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCouleur.gridx = 3;
		gbc_textFieldCouleur.gridy = 5;
		panel.add(textFieldCouleur, gbc_textFieldCouleur);
		textFieldCouleur.setColumns(10);
		
		JLabel lblSexe = new JLabel("Sexe");
		GridBagConstraints gbc_lblSexe = new GridBagConstraints();
		gbc_lblSexe.insets = new Insets(0, 0, 5, 5);
		gbc_lblSexe.gridx = 1;
		gbc_lblSexe.gridy = 6;
		panel.add(lblSexe, gbc_lblSexe);
		
		
		//Creation Combobox avec insertion de données
		String[] sexeStrings = { "Male", "Femelle", "Hermaphrodite" };
		JComboBox comboBoxSexe = new JComboBox(sexeStrings);
		GridBagConstraints gbc_comboBoxSexe = new GridBagConstraints();
		gbc_comboBoxSexe.gridwidth = 3;
		gbc_comboBoxSexe.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSexe.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSexe.gridx = 3;
		gbc_comboBoxSexe.gridy = 6;
		panel.add(comboBoxSexe, gbc_comboBoxSexe);
		
		comboBoxSexe.setSelectedIndex(1);
		comboBoxSexe.addActionListener(comboBoxSexe);
		
		JLabel lblRace = new JLabel("Race");
		GridBagConstraints gbc_lblRace = new GridBagConstraints();
		gbc_lblRace.insets = new Insets(0, 0, 5, 5);
		gbc_lblRace.gridx = 1;
		gbc_lblRace.gridy = 7;
		panel.add(lblRace, gbc_lblRace);
		
		//JComboBox comboBoxRace = new JComboBox();
		GridBagConstraints gbc_comboBoxRace = new GridBagConstraints();
		gbc_comboBoxRace.gridwidth = 3;
		gbc_comboBoxRace.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRace.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRace.gridx = 3;
		gbc_comboBoxRace.gridy = 7;
		panel.add(getComboBoxRace(), gbc_comboBoxRace);
		
		JLabel lblEspece = new JLabel("Espece");
		GridBagConstraints gbc_lblEspece = new GridBagConstraints();
		gbc_lblEspece.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspece.gridx = 1;
		gbc_lblEspece.gridy = 8;
		panel.add(lblEspece, gbc_lblEspece);
		
		//JComboBox comboBoxEspece = new JComboBox();
		GridBagConstraints gbc_comboBoxEspece = new GridBagConstraints();
		gbc_comboBoxEspece.gridwidth = 3;
		gbc_comboBoxEspece.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEspece.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEspece.gridx = 3;
		gbc_comboBoxEspece.gridy = 8;
		panel.add(getComboBoxEspece(), gbc_comboBoxEspece);
		
		
		
		JLabel lblTatouage = new JLabel("Tatouage");
		GridBagConstraints gbc_lblTatouage = new GridBagConstraints();
		gbc_lblTatouage.insets = new Insets(0, 0, 5, 5);
		gbc_lblTatouage.gridx = 1;
		gbc_lblTatouage.gridy = 9;
		panel.add(lblTatouage, gbc_lblTatouage);
		
		textFieldTatouage = new JTextField();
		GridBagConstraints gbc_textFieldTatouage = new GridBagConstraints();
		gbc_textFieldTatouage.gridwidth = 3;
		gbc_textFieldTatouage.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTatouage.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTatouage.gridx = 3;
		gbc_textFieldTatouage.gridy = 9;
		panel.add(textFieldTatouage, gbc_textFieldTatouage);
		textFieldTatouage.setColumns(10);
	}

	
	//méthode de la comboboxRace pour aller chercher toutes les espèces
	private JComboBox getComboBoxEspece() {
		if (null == comboBoxEspeces) {
			comboBoxEspeces = new JComboBox<String>();
			EspecesDAO especeDAO = DAOFactory.getEspeceDAO();
			try {
				lesEspeces = especeDAO.allEspeces();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			comboBoxEspeces.addItem("Selectionnez une Espece");
			if (!lesEspeces.isEmpty()) {
				int i = 0;
				while (i < lesEspeces.size()) {
					comboBoxEspeces.addItem(lesEspeces.get(i).getEspece());
					i++;
				}
			} else {
				comboBoxEspeces.addItem("Aucune especes");
			}
			// When we change the espece, the table change
			comboBoxEspeces.addActionListener(new ActionListener() {
				@Override
				
				//méthode pour modifier la combobox race en fonction de l'espèce
				public void actionPerformed(ActionEvent e) {
				
					if (comboBoxEspeces.getSelectedIndex() != 0) {
						RacesDAO racesDAO = DAOFactory.getRaceDAO();
						try {
							lesRaces = racesDAO.getRaceEspeces(
									lesEspeces.get(comboBoxEspeces.getSelectedIndex()-1).getCodeEspece());
							//TODO debug
							System.out.println(comboBoxEspeces.getSelectedIndex());
							System.out.println(lesEspeces.get(comboBoxEspeces.getSelectedIndex()-1).getCodeEspece());
									

							
							if (lesRaces.size() > 0) {
								String[] races = new String[lesRaces.size()];
								for (int i = 0; i < lesRaces.size(); i++) {
									races[i] = lesRaces.get(i).getRace();
								}
								if (races.length == 0) {
								}
								{
									comboBoxRace.setModel(new DefaultComboBoxModel(races));
								}
							} else {
								String[] races = new String[1];
								races[0] = "Aucune race pour cet espece";
								comboBoxRace.setModel(new DefaultComboBoxModel(races));
							}
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						String[] races = new String[1];
						races[0] = "Choisissez une espece d'abord";
						comboBoxRace.setModel(new DefaultComboBoxModel(races));
					}
				}
			});
		}
		return comboBoxEspeces;
	}
	private JComboBox<String> getComboBoxRace() {
		if (comboBoxRace == null) {
			comboBoxRace = new JComboBox<String>();
			comboBoxRace.addItem("Sélectionnez une espece d'abord");
		}
		return comboBoxRace;
	}
	
}
