package fr.eni.clinique.ihm.agendas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bo.Agendas;
import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.bo.Races;
import fr.eni.clinique.dal.AgendasDAO;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.dal.RacesDAO;
import fr.eni.clinique.ihm.DateLabelFormatter;
import fr.eni.clinique.ihm.login.EcranPrincipal;
import fr.eni.clinique.ihm.priseRdv.EcranPriseRendezVous;

public class EcranAgendas extends JFrame {
	private JPanel panelAgendas;
	private JComboBox<String> comboBxPersonnels;
	private JDatePickerImpl datePickerRendezVous;
	private JTable tableRendezVous;
	private JButton dossierMedic;

	private ArrayList<Personnels> lesPersonnels;
	private Personnels leVeterinaire;
	private Clients cli;
	private Animaux ani;
	private Races espece;

	public EcranAgendas() {
		this.setBounds(400, 250, 1000, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Agendas");
		this.initIHM();
		// leVeterinaire
	}

	public EcranAgendas(Personnels perso) {
		this.setBounds(400, 250, 1000, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		leVeterinaire = perso;
		this.setTitle("Agendas");
		this.initIHM();
		
	}

	private void initIHM() {
		panelAgendas = new JPanel();
		panelAgendas.setLayout(new GridBagLayout());
		panelAgendas.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();

		// Vétérinaire
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelAgendas.add(new JLabel("Vétérinaire :"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelAgendas.add(getComboBxPersonnels(), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelAgendas.add(new JLabel("Date :"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panelAgendas.add(getDatePickerRendezVous(), gbc);

		// Table rendez-vous
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 5;
		panelAgendas.add(getTableRendezVous(), gbc);

		// Bouton vers Dossier Medic
		gbc.gridx = 1;
		gbc.gridy = 5;
		panelAgendas.add(getDossierMedic(), gbc);

		this.setContentPane(panelAgendas);
	}

	private JComboBox<String> getComboBxPersonnels() {
		if (null == comboBxPersonnels) {
			comboBxPersonnels = new JComboBox<String>();
			PersonnelsDAO personnelsDAO = DAOFactory.getPersonnelsDAO();
			int leConnecter = -1;
			try {
				lesPersonnels = personnelsDAO.allPersonnelsVeterinaire();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!lesPersonnels.isEmpty()) {
				int i = 0;
				comboBxPersonnels.addItem("Sélectionnez un vétérinaire");
				while (i < lesPersonnels.size()) {
					if (null != leVeterinaire) {
						if (leVeterinaire.getNom().equals(lesPersonnels.get(i).getNom())) {
							comboBxPersonnels.addItem(lesPersonnels.get(i).getNom());
							leConnecter = i;
						}else{
							comboBxPersonnels.addItem(lesPersonnels.get(i).getNom());
						}
					} else {
						comboBxPersonnels.addItem(lesPersonnels.get(i).getNom());
					}
					i++;
				}
			} else {
				comboBxPersonnels.addItem("Aucun vétérinaire");
			}
			if (leConnecter != -1) {
				comboBxPersonnels.setSelectedItem(lesPersonnels.get(leConnecter).getNom());
			}
			comboBxPersonnels.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					refreshTable();
				}
			});
		}
		return comboBxPersonnels;
	}

	public JDatePickerImpl getDatePickerRendezVous() {
		if (null == datePickerRendezVous) {
			UtilDateModel model = new UtilDateModel();
			Properties p = new Properties();
			p.put("text.today", "Ajourd'hui");
			p.put("text.month", "Mois");
			p.put("text.year", "Année");
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			datePickerRendezVous = new JDatePickerImpl(datePanel, new DateLabelFormatter());
			datePickerRendezVous.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					refreshTable();
				}
			});
		}
		return datePickerRendezVous;
	}

	private JTable getTableRendezVous() {
		if (null == tableRendezVous) {
			String[] entetes = { "Heure", "Nom du Client", "Animal", "Espèce" };
			//if (null == leVeterinaire) {
				Object[][] resultat = new Object[1][5];
				resultat[0][0] = "Aucune données";
				resultat[0][1] = "Aucune données";
				resultat[0][2] = "Aucune données";
				resultat[0][3] = "Aucune données";

				tableRendezVous = new JTable(resultat, entetes);
				TableColumnModel columnModel = tableRendezVous.getColumnModel();
				tableRendezVous.setPreferredSize(new Dimension(600, 200));
				columnModel.getColumn(0).setPreferredWidth(200);
				columnModel.getColumn(1).setPreferredWidth(150);
				columnModel.getColumn(2).setPreferredWidth(150);
				columnModel.getColumn(3).setPreferredWidth(150);
				tableRendezVous.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tableRendezVous.setFillsViewportHeight(true);
				JScrollPane js = new JScrollPane(tableRendezVous);
				js.setVisible(true);
				add(js);

			//} else {
			//	refreshTable();
			//}

		}
		return tableRendezVous;
	}

	private void refreshTable() {
		if (comboBxPersonnels.getSelectedIndex() != 0) {
			AgendasDAO agendasDAO = DAOFactory.getAgendasDAO();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.now();
			String date = dtf.format(localDate);
			if (!datePickerRendezVous.getJFormattedTextField().getText().isEmpty()) {
				date = datePickerRendezVous.getJFormattedTextField().getText();
			}
			ArrayList<ArrayList> lesRendezVous = null;
			try {
				lesRendezVous = agendasDAO.getAgendasVeterinaire(
						lesPersonnels.get(comboBxPersonnels.getSelectedIndex() - 1).getCodePersonnel(), date);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated catch block

			if (lesRendezVous.size() > 0) {
				Object[][] resultat = new Object[lesRendezVous.size()][4];
				int i = 0;

				final String OLD_FORMAT = "yyyy-MM-dd HH:mm:ss";
				final String NEW_FORMAT = "HH:mm";
				while (i < lesRendezVous.size()) {
					// Get just hours
					String oldDateString = (String) lesRendezVous.get(i).get(2);
					String newDateString;
					SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
					java.util.Date d = null;
					try {
						d = sdf.parse(oldDateString);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					sdf.applyPattern(NEW_FORMAT);
					newDateString = sdf.format(d);

					resultat[i][0] = newDateString;
					resultat[i][1] = lesRendezVous.get(i).get(3) + " " + lesRendezVous.get(i).get(4);
					resultat[i][2] = lesRendezVous.get(i).get(5);
					resultat[i][3] = lesRendezVous.get(i).get(6);
					i++;
				}
				String[] entetes = { "Heure", "Nom du Client", "Animal", "Espèce" };
				tableRendezVous.setModel(new DefaultTableModel(resultat, entetes));
				TableColumnModel columnModel = tableRendezVous.getColumnModel();
				columnModel.getColumn(0).setPreferredWidth(150);
				columnModel.getColumn(1).setPreferredWidth(150);
				columnModel.getColumn(2).setPreferredWidth(150);
				columnModel.getColumn(3).setPreferredWidth(150);
			} else {
				Object[][] resultat = new Object[1][5];
				resultat[0][0] = "Aucune données";
				resultat[0][1] = "Aucune données";
				resultat[0][2] = "Aucune données";
				resultat[0][3] = "Aucune données";
				String[] entetes = { "Heure", "Nom du Client", "Animal", "Espèce" };
				tableRendezVous.setModel(new DefaultTableModel(resultat, entetes));
				TableColumnModel columnModel = tableRendezVous.getColumnModel();
				columnModel.getColumn(0).setPreferredWidth(150);
				columnModel.getColumn(1).setPreferredWidth(150);
				columnModel.getColumn(2).setPreferredWidth(150);
				columnModel.getColumn(3).setPreferredWidth(150);
			}
		} else {
			Object[][] resultat = new Object[1][5];
			resultat[0][0] = "Aucune données";
			resultat[0][1] = "Aucune données";
			resultat[0][2] = "Aucune données";
			resultat[0][3] = "Aucune données";
			String[] entetes = { "Heure", "Nom du Client", "Animal", "Espèce" };
			tableRendezVous.setModel(new DefaultTableModel(resultat, entetes));
			TableColumnModel columnModel = tableRendezVous.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(150);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(150);
			columnModel.getColumn(3).setPreferredWidth(150);
		}
	}

	// Bouton Dossier Medic
	public JButton getDossierMedic() {
		if (dossierMedic == null) {
			dossierMedic = new JButton("Dossier Médical");
		}
		dossierMedic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				// getSelectedRow()
				// EcranPriseRendezVous rdv = new EcranPriseRendezVous();
				ClientsDAO clientsDAO = DAOFactory.getClientsDAO();
				AnimauxDAO animauxDAO = DAOFactory.getAnimauxDAO();
				RacesDAO racesDAO = new DAOFactory().getRaceDAO();
				JOptionPane display = new JOptionPane();
				AgendasDAO agendasDAO = DAOFactory.getAgendasDAO();

				if (comboBxPersonnels.getSelectedIndex() == 0) {
					display.showMessageDialog(panelAgendas, "Veuillez sélectionnez un vétérinaire", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					if (datePickerRendezVous.getJFormattedTextField().getText().isEmpty()) {
						display.showMessageDialog(panelAgendas, "Veuillez sélectionnez une date ", "Attention",
								JOptionPane.WARNING_MESSAGE);
					} else {
						int ligne = tableRendezVous.getSelectedRow();
						int colonne = tableRendezVous.getSelectedColumn();
						if (!tableRendezVous.getValueAt(ligne, colonne).equals("Aucune données")) {
							System.out.println("valide");
							try {
								cli = clientsDAO.getClient(tableRendezVous.getValueAt(ligne, 1).toString());
								System.out.println(cli);
							} catch (DALException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								ani = animauxDAO.getAnimauxClients(cli.getCodeClient()).get(0);
							} catch (DALException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								espece = racesDAO.allRaces().get(0);
							} catch (DALException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							EcranAgendas.this.fntrDossierMedic();
						} else {
							display.showMessageDialog(panelAgendas, "Veuillez sélectionner un rendez-vous valide",
									"Attention", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});
		return dossierMedic;

	}

	// Envoie à l'écran Dossier Medic
	public void fntrDossierMedic() {
		EcranDossierMedical goToDossierMedic = new EcranDossierMedical(cli, ani, espece);
		goToDossierMedic.setVisible(true);
		EcranAgendas.this.dispose();
	}

}
