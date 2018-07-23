package fr.eni.clinique.ihm.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.awt.Dimension;

import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.dal.jdbc.*;
import fr.eni.clinique.ihm.gestionPerso.EcranAjoutPerso;
import fr.eni.clinique.ihm.gestionPerso.EcranPrincipalGestion;
import fr.eni.clinique.ihm.gestionPerso.EcranRenitialiser;

public class EcranPrincipal extends JFrame {

	private JPanel contentPane;
	private Personnels pers;

	/**
	 * Create the frame.
	 */
	public EcranPrincipal(Personnels p) {
		pers = p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFichiers = new JMenu("Fichier(s)");
		mnFichiers.setAutoscrolls(true);
		menuBar.add(mnFichiers);
		
		//Déconnexion
		JMenuItem mntmDeconnexion = new JMenuItem("Deconnexion");
		mntmDeconnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				EcranPrincipal.this.fenetreLogin();
			}
		});
		mnFichiers.add(mntmDeconnexion);
		
		//Fermer
		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mntmFermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent b) {
				EcranPrincipal.this.dispose();
			}
		});
		mnFichiers.add(mntmFermer);
		
		
		if (pers.equals("sec")) { // If sec then display all management
									// appointment
			JMenu mnGestionDesRendezvous = new JMenu("Gestion des Rendez-vous");
			menuBar.add(mnGestionDesRendezvous);

			JMenuItem mntmNewMenuItem = new JMenuItem("Prise de rendez-vous");
			mnGestionDesRendezvous.add(mntmNewMenuItem);

			JMenuItem mntmNewMenuItem_1 = new JMenuItem("Gestion des clients");
			mnGestionDesRendezvous.add(mntmNewMenuItem_1);
		}
		if (pers.getRole().equals("vet")) { // If vet then display agenda
			JMenu mnAgenda = new JMenu("Agenda");
			menuBar.add(mnAgenda);
		}
		if (pers.getRole().equals("adm")) { // If adm then display the
											// management of the user
			JMenu mnGestionDuPersonnels = new JMenu("Gestion du Personnels");
			mnGestionDuPersonnels.addMenuListener((new MenuListener() {
				@Override
				public void menuSelected(MenuEvent e) {
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose(); // On ferme l'écran actuel
					new EcranPrincipalGestion().setVisible(true);

				}

				@Override
				public void menuCanceled(MenuEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void menuDeselected(MenuEvent arg0) {
					// TODO Auto-generated method stub

				}
			}));
			menuBar.add(mnGestionDuPersonnels);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Vérifier les rôles et mettre en disable les itemMenu de mon
		// EcranPrincipal enfonction du rôle.
		// Instancie toujours une classe pour la reutiliser par la suite
		PersonnelsDAOJdbcImpl p1 = new PersonnelsDAOJdbcImpl();

	}
	
	// Retour à l'écran de login
	public void fenetreLogin() {
		EcranLogin retourLogin = new EcranLogin();
		retourLogin.setVisible(true);
		EcranPrincipal.this.dispose();
	}

}
