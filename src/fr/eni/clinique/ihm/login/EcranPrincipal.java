package fr.eni.clinique.ihm.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.awt.Dimension;

import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.dal.jdbc.*;

public class EcranPrincipal extends JFrame {

	private JPanel contentPane;
	private Personnels pers;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranPrincipal frame = new EcranPrincipal();
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
	public EcranPrincipal(Personnels p) {
		pers = p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFichiers = new JMenu("Fichier(s)");
		mnFichiers.setAutoscrolls(true);
		menuBar.add(mnFichiers);
		
		JMenuItem mntmDeconnexion = new JMenuItem("Deconnexion");
		mnFichiers.add(mntmDeconnexion);
		
		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mnFichiers.add(mntmFermer);
		
		JMenu mnGestionDesRendezvous = new JMenu("Gestion des Rendez-vous");
		menuBar.add(mnGestionDesRendezvous);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Prise de rendez-vous");
		mnGestionDesRendezvous.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Gestion des clients");
		mnGestionDesRendezvous.add(mntmNewMenuItem_1);
		
		JMenu mnAgenda = new JMenu("Agenda");
		menuBar.add(mnAgenda);
		
		JMenu mnGestionDuPersonnels = new JMenu("Gestion du Personnels");
		menuBar.add(mnGestionDuPersonnels);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0};
		gbl_panel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		//Vérifier les rôles et mettre en disable les itemMenu de mon 
		//EcranPrincipal enfonction du rôle.
		//Instancie toujours une classe pour la reutiliser par la suite
		PersonnelsDAOJdbcImpl p1 = new PersonnelsDAOJdbcImpl();
		//instancie la classe personne pour la reutiliser en paramètre de ma méthode
		//getAllData
		
		
		if( pers.equals("adm")){
			mnAgenda.setEnabled(false);
			mnGestionDesRendezvous.setEnabled(false);
		}
		if( pers.equals("sec")){
			mnAgenda.setEnabled(false);
			mnGestionDuPersonnels.setEnabled(false);
		}
		if( pers.equals("vet")){
			mnGestionDesRendezvous.setEnabled(false);
			mnGestionDuPersonnels.setEnabled(false);
		}
		

		
	}


}
