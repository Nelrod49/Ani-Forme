package fr.eni.clinique.ihm.gestionClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.AgendasDAO;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.ihm.gestionAnimal.EcranAnimal;
import fr.eni.clinique.ihm.login.EcranPrincipal;
import fr.eni.clinique.ihm.priseRdv.EcranPriseRendezVous;

import java.awt.Insets;

public class EcranGestionClients extends JFrame {

	private JPanel panelGestionClients;
	private Clients leClient;
	private ArrayList<ArrayList> lesAnimaux;
	private JButton buttonRechercher;
	private JButton buttonAjouterClient;
	private JButton buttonSupprimerClient;
	private JButton buttonValider;
	private JButton buttonAnnuler;
	private JButton buttonAjouterAnimaux;
	private JButton buttonSupprimerAnimaux;
	private JButton buttonEditerAnimaux;
	private JButton btnRetour;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldVille;
	private JTextField textFieldAdresse2;
	private JTextField textFieldAdresse1;
	private JTable tableAnimaux;
	private JTextField textFieldCodePostal;
	private Personnels pers;

	/**
	 * @wbp.parser.constructor
	 */
	public EcranGestionClients(Clients leClient) {
		this.setTitle("Gestion des clients");
		this.setSize(new Dimension(1000, 600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.leClient = leClient;
		this.initIHM();
	}

	public EcranGestionClients(Personnels pers) {
		this.setTitle("Gestion des clients");
		this.setSize(new Dimension(1000, 600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initIHM();
		this.pers = pers;
	}

	private void initIHM() {
		panelGestionClients = new JPanel();
		panelGestionClients.setOpaque(true);
		GridBagLayout gbl_panelGestionClients = new GridBagLayout();
		gbl_panelGestionClients.columnWidths = new int[] { 96, 105, 84, 128, 122, 210, 0, 0, 0, 0, 0 };
		gbl_panelGestionClients.rowHeights = new int[] { 0, 31, 30, 32, 28, 28, 27, 0, 29, 32, 0, 0 };
		gbl_panelGestionClients.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelGestionClients.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelGestionClients.setLayout(gbl_panelGestionClients);
		this.setContentPane(panelGestionClients);

		// Boutton Rechercher
		JButton btnRechercher = new JButton("Rechercher");
		GridBagConstraints gbc_btnRechercher = new GridBagConstraints();
		gbc_btnRechercher.insets = new Insets(0, 0, 5, 5);
		gbc_btnRechercher.gridx = 1;
		gbc_btnRechercher.gridy = 1;
		btnRechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EcranRechercheClients ecranRechercheClients = new EcranRechercheClients();
				ecranRechercheClients.setVisible(true);
				EcranGestionClients.this.dispose();

			}
		});
		panelGestionClients.add(btnRechercher, gbc_btnRechercher);

		JButton buttonAjouterClient = new JButton("Ajouter");
		buttonAjouterClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EcranAjouterClient ecranAjouterClient = new EcranAjouterClient();
				ecranAjouterClient.setVisible(true);
				EcranGestionClients.this.dispose();

			}
		});
		GridBagConstraints gbc_btnAjouter = new GridBagConstraints();
		gbc_btnAjouter.insets = new Insets(0, 0, 5, 5);
		gbc_btnAjouter.gridx = 3;
		gbc_btnAjouter.gridy = 1;
		panelGestionClients.add(buttonAjouterClient, gbc_btnAjouter);

		JButton buttonSupprimerClient = new JButton("Supprimer");
		buttonSupprimerClient.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientsDAO clientsDAO = new DAOFactory().getClientsDAO();
				try {
					clientsDAO.delete(leClient.getCodeClient());
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EcranGestionClients.this.dispose();
				EcranGestionClients ecranGestionClients = new EcranGestionClients(pers);
				ecranGestionClients.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
		gbc_btnSupprimer.insets = new Insets(0, 0, 5, 5);
		gbc_btnSupprimer.gridx = 4;
		gbc_btnSupprimer.gridy = 1;
		panelGestionClients.add(buttonSupprimerClient, gbc_btnSupprimer);

		GridBagConstraints gbc_btnRetour_1 = new GridBagConstraints();
		gbc_btnRetour_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnRetour_1.gridx = 5;
		gbc_btnRetour_1.gridy = 1;
		panelGestionClients.add(getBtnRetour(), gbc_btnRetour_1);

		JLabel lblCode = new JLabel("Code");
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.EAST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 0;
		gbc_lblCode.gridy = 3;
		panelGestionClients.add(lblCode, gbc_lblCode);

		JLabel lblCodeResultat;
		if (null != leClient) {
			lblCodeResultat = new JLabel(leClient.getCodeClient() + "");
		} else {
			lblCodeResultat = new JLabel("");
		}
		GridBagConstraints gbc_lblCodeResultat = new GridBagConstraints();
		gbc_lblCodeResultat.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodeResultat.gridx = 1;
		gbc_lblCodeResultat.gridy = 3;
		panelGestionClients.add(lblCodeResultat, gbc_lblCodeResultat);

		tableAnimaux = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		refreshTable();
		GridBagConstraints gbc_tableAnimaux = new GridBagConstraints();
		gbc_tableAnimaux.insets = new Insets(0, 0, 5, 5);
		gbc_tableAnimaux.gridwidth = 6;
		gbc_tableAnimaux.gridheight = 7;
		gbc_tableAnimaux.fill = GridBagConstraints.BOTH;
		gbc_tableAnimaux.gridx = 3;
		gbc_tableAnimaux.gridy = 3;
		panelGestionClients.add(tableAnimaux, gbc_tableAnimaux);

		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 4;
		panelGestionClients.add(lblNom, gbc_lblNom);

		textFieldNom = new JTextField();
		GridBagConstraints gbc_textFieldNom = new GridBagConstraints();
		gbc_textFieldNom.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNom.gridx = 1;
		gbc_textFieldNom.gridy = 4;
		if (null != leClient) {
			textFieldNom.setText(leClient.getNomClient());
		}
		panelGestionClients.add(textFieldNom, gbc_textFieldNom);
		textFieldNom.setColumns(1);

		JLabel lblNewLabel = new JLabel("Prénom");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		panelGestionClients.add(lblNewLabel, gbc_lblNewLabel);

		textFieldPrenom = new JTextField();
		GridBagConstraints gbc_textFieldPrenom = new GridBagConstraints();
		gbc_textFieldPrenom.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrenom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrenom.gridx = 1;
		gbc_textFieldPrenom.gridy = 5;
		if (null != leClient) {
			textFieldPrenom.setText(leClient.getPrenomClient());
		}
		panelGestionClients.add(textFieldPrenom, gbc_textFieldPrenom);
		textFieldPrenom.setColumns(10);

		JLabel lblAdresse = new JLabel("Adresse");
		GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
		gbc_lblAdresse.anchor = GridBagConstraints.EAST;
		gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdresse.gridx = 0;
		gbc_lblAdresse.gridy = 6;
		panelGestionClients.add(lblAdresse, gbc_lblAdresse);

		textFieldAdresse1 = new JTextField();
		GridBagConstraints gbc_textFieldAdresse1 = new GridBagConstraints();
		gbc_textFieldAdresse1.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAdresse1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdresse1.gridx = 1;
		gbc_textFieldAdresse1.gridy = 6;
		if (null != leClient) {
			textFieldAdresse1.setText(leClient.getAdresse1());
		}
		panelGestionClients.add(textFieldAdresse1, gbc_textFieldAdresse1);
		textFieldAdresse1.setColumns(5);

		textFieldAdresse2 = new JTextField();
		GridBagConstraints gbc_textFieldAdresse2 = new GridBagConstraints();
		gbc_textFieldAdresse2.weightx = 20.0;
		gbc_textFieldAdresse2.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAdresse2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdresse2.gridx = 1;
		gbc_textFieldAdresse2.gridy = 7;
		if (null != leClient) {
			textFieldAdresse2.setText(leClient.getAdresse2());
		}
		panelGestionClients.add(textFieldAdresse2, gbc_textFieldAdresse2);
		textFieldAdresse2.setColumns(10);

		JLabel lblCodePostal = new JLabel("Code Postal");
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.anchor = GridBagConstraints.EAST;
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 0;
		gbc_lblCodePostal.gridy = 8;
		panelGestionClients.add(lblCodePostal, gbc_lblCodePostal);

		textFieldCodePostal = new JTextField();
		GridBagConstraints gbc_textFieldCodePostal = new GridBagConstraints();
		gbc_textFieldCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCodePostal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCodePostal.gridx = 1;
		gbc_textFieldCodePostal.gridy = 8;
		if (null != leClient) {
			textFieldCodePostal.setText(leClient.getCodePostal());
		}
		panelGestionClients.add(textFieldCodePostal, gbc_textFieldCodePostal);
		textFieldCodePostal.setColumns(10);

		JLabel lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.EAST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 0;
		gbc_lblVille.gridy = 9;
		panelGestionClients.add(lblVille, gbc_lblVille);

		textFieldVille = new JTextField();
		GridBagConstraints gbc_textFieldVille = new GridBagConstraints();
		gbc_textFieldVille.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVille.gridx = 1;
		gbc_textFieldVille.gridy = 9;
		if (null != leClient) {
			textFieldVille.setText(leClient.getVille());
		}
		panelGestionClients.add(textFieldVille, gbc_textFieldVille);
		textFieldVille.setColumns(10);

		JButton btnAjouterAnimaux = new JButton("Ajouter");
		// TODO Relier à l'écran animal
		/*
		 * btnAjouterAnimaux.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { EcranAnimal
		 * ecranAnimal = new EcranAnimal(); ecranAnimal.setVisible(true);
		 * EcranGestionClients.this.dispose();
		 * 
		 * } });
		 */
		GridBagConstraints gbc_btnAjouter_1 = new GridBagConstraints();
		gbc_btnAjouter_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnAjouter_1.gridx = 6;
		gbc_btnAjouter_1.gridy = 10;
		panelGestionClients.add(btnAjouterAnimaux, gbc_btnAjouter_1);

		JButton btnSupprimerAnimaux = new JButton("Supprimer");
		btnSupprimerAnimaux.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {

				if (tableAnimaux.getSelectedRow() == -1) {
					JOptionPane d = new JOptionPane();
					d.showMessageDialog(panelGestionClients, "Vous devez selectionnez un animal.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					AnimauxDAO animauxDAO = new DAOFactory().getAnimauxDAO();
					try {
						animauxDAO.delete(
								Integer.parseInt((String) lesAnimaux.get(tableAnimaux.getSelectedRow()).get(0)));
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					refreshTable();
				}

			}
		});
		GridBagConstraints gbc_btnSupprimer_1 = new GridBagConstraints();
		gbc_btnSupprimer_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnSupprimer_1.gridx = 7;
		gbc_btnSupprimer_1.gridy = 10;
		panelGestionClients.add(btnSupprimerAnimaux, gbc_btnSupprimer_1);

		JButton btnEditerAnimaux = new JButton("\u00C9diter");
		// TODO Relier à l'écran animal
		/*
		 * btnEditerAnimaux.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { EcranAnimal
		 * ecranAnimal = new EcranAnimal(); ecranAnimal.setVisible(true);
		 * EcranGestionClients.this.dispose();
		 * 
		 * } });
		 */
		GridBagConstraints gbc_btnditer = new GridBagConstraints();
		gbc_btnditer.insets = new Insets(0, 0, 0, 5);
		gbc_btnditer.gridx = 8;
		gbc_btnditer.gridy = 10;
		panelGestionClients.add(btnEditerAnimaux, gbc_btnditer);
	}

	private void refreshTable() {

		Object[][] resultat;
		if (null == leClient) {
			resultat = new Object[1][7];
			resultat[0][0] = "Acune données";
			resultat[0][1] = "Acune données";
			resultat[0][2] = "Acune données";
			resultat[0][3] = "Acune données";
			resultat[0][4] = "Acune données";
			resultat[0][5] = "Acune données";
			resultat[0][6] = "Acune données";
		} else {
			AnimauxDAO animauxDAO = new DAOFactory().getAnimauxDAO();
			try {
				lesAnimaux = animauxDAO.getAnimauxClientsRaces(leClient.getCodeClient());
			} catch (DALException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (!lesAnimaux.isEmpty()) {
				int i = 0;
				resultat = new Object[lesAnimaux.size()][7];
				while (i < lesAnimaux.size()) {
					resultat[i][0] = lesAnimaux.get(i).get(0);
					resultat[i][1] = lesAnimaux.get(i).get(1);
					resultat[i][2] = lesAnimaux.get(i).get(2);
					resultat[i][3] = lesAnimaux.get(i).get(3);
					resultat[i][4] = lesAnimaux.get(i).get(4);
					resultat[i][5] = lesAnimaux.get(i).get(5);
					resultat[i][6] = lesAnimaux.get(i).get(6);
					i++;
				}
			} else {
				resultat = new Object[1][7];
				resultat[0][0] = "Acune données";
				resultat[0][1] = "Acune données";
				resultat[0][2] = "Acune données";
				resultat[0][3] = "Acune données";
				resultat[0][4] = "Acune données";
				resultat[0][5] = "Acune données";
				resultat[0][6] = "Acune données";
			}

		}
		String[] entetes = { "Numéro", "Nom", "Sexe", "Couleur", "Race", "Espèce", "Tatouage" };
		tableAnimaux.setModel(new DefaultTableModel(resultat, entetes));

	}

	// Bouton Retour ecran principal
	private JButton getBtnRetour() {
		if (btnRetour == null) {
			btnRetour = new JButton("Retour");
			btnRetour.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					EcranGestionClients.this.fenetreGestionCli();

				}
			});
		}
		return btnRetour;
	}

	// Renvoie à l'écran Principal
	public void fenetreGestionCli() {
		EcranPrincipal goToPrincipal = new EcranPrincipal(pers);
		goToPrincipal.setVisible(true);
		EcranGestionClients.this.dispose();
	}

}
