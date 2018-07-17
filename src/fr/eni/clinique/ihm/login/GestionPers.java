package fr.eni.clinique.ihm.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.awt.Dimension;

public class GestionPers extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPers frame = new GestionPers();
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
	public GestionPers() {
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
		

		
	}

}
