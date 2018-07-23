package fr.eni.clinique.ihm.gestionPerso;

import java.awt.Dimension;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;

public class EcranPrincipalGestion extends JFrame {

	private JButton buttonAjouter;
	private JButton buttonSupprimer;
	private JButton buttonReinitialiser;
	private JTable tablePersonnels;
	private JPanel panelPrincipal;
	private ArrayList<Personnels> personnels = new ArrayList<Personnels>();

	public EcranPrincipalGestion() {
		this.setSize(new Dimension(600, 400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Gestion personnel");
		this.initIHM();
	}

	private void initIHM() {
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		panelPrincipal.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPrincipal.add(getAjouter(), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelPrincipal.add(getSupprimer(), gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		panelPrincipal.add(getReinitiliser(), gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panelPrincipal.add(getPersonnels(), gbc);

		this.setContentPane(panelPrincipal);
	}

	//appel de la fenetre AjoutPerso
	public void fenetreAjoutPerso() {	
		EcranAjoutPerso addPerso = new EcranAjoutPerso();
		addPerso.setVisible(true);
		EcranPrincipalGestion.this.dispose();
	}

	private JButton getAjouter(){
		if(buttonAjouter == null){
			buttonAjouter = new JButton();
			buttonAjouter.setText("Ajouter");
			buttonAjouter.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e1){
					EcranPrincipalGestion.this.fenetreAjoutPerso();
					
				}
			});
		}
		return buttonAjouter;
	}

	private JButton getSupprimer() {
		if (buttonSupprimer == null) {
			buttonSupprimer = new JButton();
			buttonSupprimer.setText("Supprimer");
			buttonSupprimer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (tablePersonnels.getSelectedRow() == -1) {
						JOptionPane d = new JOptionPane();
						d.showMessageDialog(panelPrincipal, "Vous devez selectionnez quelqu'un.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					} else {
						PersonnelsDAO personnelsDAO = DAOFactory.getPersonnelsDAO();
						try {
							personnelsDAO.delete(personnels.get(tablePersonnels.getSelectedRow()));
						} catch (DALException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						}

						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose(); // On ferme l'écran actuel
						new EcranPrincipalGestion().setVisible(true);
						JOptionPane d = new JOptionPane();
						d.showMessageDialog(panelPrincipal, "Personnels archiver.");
					}

				}
			});
		}
		return buttonSupprimer;
	}

	private JButton getReinitiliser() {
		if (buttonReinitialiser == null) {
			buttonReinitialiser = new JButton();
			buttonReinitialiser.setText("Réinitialiser");
			buttonReinitialiser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (tablePersonnels.getSelectedRow() == -1) {
						JOptionPane d = new JOptionPane();
						d.showMessageDialog(panelPrincipal, "Vous devez selectionnez quelqu'un.", "Attention",
								JOptionPane.WARNING_MESSAGE);
					} else {
						PersonnelsDAO personnelsDAO = DAOFactory.getPersonnelsDAO();
						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose(); // On ferme l'écran actuel
						new EcranRenitialiser("Réinitialiser mot de passe",
								personnels.get(tablePersonnels.getSelectedRow())).setVisible(true);
					}

				}
			});
		}
		return buttonReinitialiser;
	}

	private JTable getPersonnels() {
		if (tablePersonnels == null) {
			String[] entetes = { "Code Personnel", "Prénom Nom", "Rôle" };
			PersonnelsDAO personnelsDAO = DAOFactory.getPersonnelsDAO();
			try {
				personnels = personnelsDAO.allPersonnels();
				Object[][] data = new Object[personnels.size()][3];
				int i = 0;
				while (i < personnels.size()) {
					data[i][0] = personnels.get(i).getCodePersonnel();
					data[i][1] = personnels.get(i).getNom();
					data[i][2] = personnels.get(i).getRole();
					i = i + 1;
				}
				tablePersonnels = new JTable(data, entetes);
				TableColumnModel columnModel = tablePersonnels.getColumnModel();
				columnModel.getColumn(0).setPreferredWidth(50);
				columnModel.getColumn(1).setPreferredWidth(150);
				columnModel.getColumn(2).setPreferredWidth(50);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return tablePersonnels;
	}

}
