package fr.eni.clinique.ihm.priseRdv;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.AgendasDAO;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.ihm.DateLabelFormatter;
import fr.eni.clinique.ihm.gestionPerso.EcranRenitialiser;

public class EcranPriseRendezVous extends JFrame {
	private JPanel panelPriseRendezVous;
	private JComboBox<String> comboBxClients;
	private JComboBox<String> comboBxAnimaux;
	private JButton buttonClients;
	private JButton buttonAnimaux;
	private JComboBox<String> comboBxPersonnels;
	private JDatePickerImpl datePickerRendezVous;
	private JComboBox comboBxHeure;
	private JComboBox comboBxMinute;
	private JTable tableRendezVous;
	private JButton buttonSupprimer;
	private JButton buttonAjouter;
	private ArrayList<Clients> lesClients = new ArrayList<Clients>();
	private ArrayList<Personnels> lesPersonnels = null;
	private ArrayList<ArrayList> lesRendezVous = new ArrayList<ArrayList>();
	private ArrayList<Animaux> lesAnimaux = null;

	public EcranPriseRendezVous(String titre) {
		super(titre);
		this.setSize(new Dimension(1000, 600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initIHM();
	}

	private void initIHM() {
		panelPriseRendezVous = new JPanel();
		panelPriseRendezVous.setLayout(new GridBagLayout());
		panelPriseRendezVous.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();

		// Clients
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPriseRendezVous.add(new JLabel("Le client :"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelPriseRendezVous.add(getComboBxClients(), gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panelPriseRendezVous.add(getButtonClients(), gbc);

		// Animal
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelPriseRendezVous.add(new JLabel("Animal :"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelPriseRendezVous.add(getComboBxAnimaux(), gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panelPriseRendezVous.add(getButtonAnimaux(), gbc);

		// Vétérinaire
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelPriseRendezVous.add(new JLabel("Le vétérinaire :"), gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		panelPriseRendezVous.add(getComboBxPersonnels(), gbc);

		// Les Horaires
		gbc.gridx = 3;
		gbc.gridy = 1;
		panelPriseRendezVous.add(getDatePickerRendezVous(), gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;
		panelPriseRendezVous.add(getComboBxHeure(), gbc);

		gbc.gridx = 4;
		gbc.gridy = 2;
		panelPriseRendezVous.add(getComboBxMinute(), gbc);

		// Table rendez-vous
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 5;
		panelPriseRendezVous.add(getTableRendezVous(), gbc);

		// Les Boutons
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		panelPriseRendezVous.add(getButtonSupprimer(), gbc);

		gbc.gridx = 5;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		panelPriseRendezVous.add(getButtonAjouter(), gbc);

		this.setContentPane(panelPriseRendezVous);

	}

	private JComboBox<String> getComboBxClients() {
		if (comboBxClients == null) {
			comboBxClients = new JComboBox<String>();
			ClientsDAO clientsDAO = DAOFactory.getClientsDAO();

			try {
				lesClients = clientsDAO.allClients();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!lesClients.isEmpty()) {
				int i = 0;
				comboBxClients.addItem("Sélectionnez un client");
				while (i < lesClients.size()) {
					comboBxClients
							.addItem(lesClients.get(i).getNomClient() + " " + lesClients.get(i).getPrenomClient());
					i = i + 1;
				}
			} else {
				comboBxClients.addItem("Aucun clients");
			}
			comboBxClients.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.print(comboBxClients.getSelectedIndex());
					if (comboBxClients.getSelectedIndex() != 0) {
						AnimauxDAO animauxDAO = DAOFactory.getAnimauxDAO();
						try {
							lesAnimaux = animauxDAO.getAnimauxClients(
									lesClients.get(comboBxClients.getSelectedIndex() - 1).getCodeClient());
							if (lesAnimaux.size() > 0) {
								String[] animaux = new String[lesAnimaux.size()];
								for (int i = 0; i < lesAnimaux.size(); i++) {
									animaux[i] = lesAnimaux.get(i).getNomAnimal();
								}
								if (animaux.length == 0) {
								}
								{
									comboBxAnimaux.setModel(new DefaultComboBoxModel(animaux));
								}
							} else {
								String[] animaux = new String[1];
								animaux[0] = "Aucun animal pour ce client";
								comboBxAnimaux.setModel(new DefaultComboBoxModel(animaux));
							}
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						String[] animaux = new String[1];
						animaux[0] = "Choisissez un clients d'abord";
						comboBxAnimaux.setModel(new DefaultComboBoxModel(animaux));
					}
				}
			});
		}
		return comboBxClients;
	}

	private JButton getButtonClients() {
		if (buttonClients == null) {
			buttonClients = new JButton();
			buttonClients.setText("Ajouter Clients");
		}
		return buttonClients;
	}

	private JComboBox<String> getComboBxAnimaux() {
		if (comboBxAnimaux == null) {
			comboBxAnimaux = new JComboBox<String>();
			comboBxAnimaux.addItem("Sélectionnez un clients d'abord");
		}
		return comboBxAnimaux;
	}

	private JButton getButtonAnimaux() {
		if (buttonAnimaux == null) {
			buttonAnimaux = new JButton();
			buttonAnimaux.setText("Ajouter Animal");
		}
		return buttonAnimaux;
	}

	private JComboBox getComboBxPersonnels() {
		if (null == comboBxPersonnels) {
			comboBxPersonnels = new JComboBox<String>();
			PersonnelsDAO personnelsDAO = DAOFactory.getPersonnelsDAO();
			try {
				lesPersonnels = personnelsDAO.allPersonnelsVeterinaire();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			comboBxPersonnels.addItem("Selectionnez un  vétérinaire");
			if (!lesPersonnels.isEmpty()) {
				int i = 0;
				while (i < lesPersonnels.size()) {
					comboBxPersonnels.addItem(lesPersonnels.get(i).getNom());
					i++;
				}
			} else {
				comboBxPersonnels.addItem("Aucun personnels");
			}
			// When we change the vétérinaire, the table change
			comboBxPersonnels.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					refreshTable();
				}
			});
		}
		return comboBxPersonnels;
	}

	private JDatePickerImpl getDatePickerRendezVous() {
		if (null == datePickerRendezVous) {
			UtilDateModel model = new UtilDateModel();
			Properties p = new Properties();
			p.put("text.today", "Ajourd'hui");
			p.put("text.month", "Mois");
			p.put("text.year", "Année");
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			datePickerRendezVous = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		}
		return datePickerRendezVous;
	}

	private JComboBox getComboBxHeure() {
		if (null == comboBxHeure) {
			comboBxHeure = new JComboBox<String>();
			for (int i = 9; i <= 17; i++) {
				comboBxHeure.addItem("" + i);
			}
		}
		return comboBxHeure;
	}

	private JComboBox getComboBxMinute() {
		if (null == comboBxMinute) {
			comboBxMinute = new JComboBox<String>();
			for (int i = 0; i < 60; i = i + 15) {
				comboBxMinute.addItem("" + i);
			}
		}
		return comboBxMinute;
	}

	private JTable getTableRendezVous() {
		if (null == tableRendezVous) {
			String[] entetes = { "Heure", "Nom du Client", "Animal", "Espèce" };
			Object[][] resultat = new Object[1][5];
			resultat[0][0] = "Acune données";
			resultat[0][1] = "Acune données";
			resultat[0][2] = "Acune données";
			resultat[0][3] = "Acune données";
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
		}
		return tableRendezVous;
	}

	private JButton getButtonAjouter(){
		if(null == buttonAjouter){
			buttonAjouter = new JButton();
			buttonAjouter.setText("Ajouter");
			buttonAjouter.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					JOptionPane display = new JOptionPane();
					if(comboBxAnimaux.getSelectedItem() == "Aucun animal pour ce client" || comboBxClients.getSelectedIndex() == 0 ){
						display.showMessageDialog(panelPriseRendezVous,								
								"Veuillez sélectionnez un animal",
								"Attention",
								JOptionPane.WARNING_MESSAGE);
					}else{
						if(comboBxPersonnels.getSelectedIndex() == 0){
							display.showMessageDialog(panelPriseRendezVous,								
									"Veuillez sélectionnez un vétérinaire",
									"Attention",
									JOptionPane.WARNING_MESSAGE);
						}else{
							if(datePickerRendezVous.getJFormattedTextField().getText().isEmpty()){
								display.showMessageDialog(panelPriseRendezVous,								
										"Veuillez sélectionnez une date ",
										"Attention",
										JOptionPane.WARNING_MESSAGE);
							}
							else{
								System.out.println(datePickerRendezVous.getJFormattedTextField().getText());
							//Recréer la date et l'heure 
							String dateRdv = datePickerRendezVous.getJFormattedTextField().getText() + ' ' 
									+comboBxHeure.getSelectedItem() + ':' + comboBxMinute.getSelectedItem();
							AgendasDAO agendasDAO = DAOFactory.getAgendasDAO();
							boolean resultat = false; //Variable pour savoir si l'ajout à fonctionner
							try {
								resultat = agendasDAO.insertAgendas(
										lesPersonnels.get(comboBxPersonnels.getSelectedIndex() - 1).getCodePersonnel(), 
										dateRdv, 
										lesAnimaux.get(comboBxAnimaux.getSelectedIndex()).getCodeAnimal());
							} catch (DALException e1) {
							}
							if(resultat  == true){
								display.showMessageDialog(panelPriseRendezVous,
									    "Ajout réussi.");
								refreshTable();
							}else{
								display.showMessageDialog(panelPriseRendezVous,
										"Ajout échoué (vérifier les disponibilité et autres)",
										"Erreur",								    
										JOptionPane.ERROR_MESSAGE
									);
							}
						}
							
					}
				}
				}
			});
		}		 
		return buttonAjouter;
	}

	private JButton getButtonSupprimer() {
		if (null == buttonSupprimer) {
			buttonSupprimer = new JButton();
			buttonSupprimer.setText("Supprimer");
			buttonSupprimer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (tableRendezVous.getSelectedRow() == -1) {
						JOptionPane d = new JOptionPane();
						d.showMessageDialog(panelPriseRendezVous, "Vous devez selectionnez un rendez-vous.",
								"Attention", JOptionPane.WARNING_MESSAGE);
					} else {
						final String OLD_FORMAT = "yyyy-MM-dd H:m:s";
						final String NEW_FORMAT = "dd-MM-yyyy H:m:s";
						// August 12, 2010
						String oldDateString = (String) lesRendezVous.get(tableRendezVous.getSelectedRow()).get(2);
						String newDateString;
						SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
						java.util.Date d = null;
						try {
							d = sdf.parse(oldDateString);
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						sdf.applyPattern(NEW_FORMAT);
						newDateString = sdf.format(d);
						AgendasDAO agendasDAO = DAOFactory.getAgendasDAO();
						boolean resultat = true; // Variable pour savoir si la
													// suppression à fonctionner
						try {
							agendasDAO.deleteAgendas(
									(String) lesRendezVous.get(tableRendezVous.getSelectedRow()).get(0),
									(String) lesRendezVous.get(tableRendezVous.getSelectedRow()).get(1),
									(String) newDateString);
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							resultat = false;
						}
						JOptionPane display = new JOptionPane();
						if (resultat == true) {
							display.showMessageDialog(panelPriseRendezVous, "Supression réussi.");
							refreshTable();
						} else {
							display.showMessageDialog(panelPriseRendezVous, "Supression échoué.", "Erreur",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				}
			});
		}
		return buttonSupprimer;
	}

	private void refreshTable() {
		if (comboBxPersonnels.getSelectedIndex() != 0) {
			AgendasDAO agendasDAO = DAOFactory.getAgendasDAO();
			try {
				lesRendezVous = agendasDAO
						.getAllRdvVet(lesPersonnels.get(comboBxPersonnels.getSelectedIndex() - 1).getCodePersonnel());
			} catch (DALException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (lesRendezVous.size() > 0) {
				Object[][] resultat = new Object[lesRendezVous.size()][4];
				int i = 0;
				while (i < lesRendezVous.size()) {
					resultat[i][0] = lesRendezVous.get(i).get(2);
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
				resultat[0][0] = "Acune données";
				resultat[0][1] = "Acune données";
				resultat[0][2] = "Acune données";
				resultat[0][3] = "Acune données";
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
			resultat[0][0] = "Acune données";
			resultat[0][1] = "Acune données";
			resultat[0][2] = "Acune données";
			resultat[0][3] = "Acune données";
			String[] entetes = { "Heure", "Nom du Client", "Animal", "Espèce" };
			tableRendezVous.setModel(new DefaultTableModel(resultat, entetes));
			TableColumnModel columnModel = tableRendezVous.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(150);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(150);
			columnModel.getColumn(3).setPreferredWidth(150);
		}
	}
}
