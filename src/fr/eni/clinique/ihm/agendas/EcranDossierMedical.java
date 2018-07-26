/**
 * 
 */
package fr.eni.clinique.ihm.agendas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Races;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.ihm.login.EcranPrincipal;
import fr.eni.clinique.ihm.priseRdv.EcranPriseRendezVous;

/**
 * Classe en charge de
 * 
 * @author eguillard2018
 * @date 24 juil. 2018 - 09:41:07 Ani-Forme - Version 1.0
 */
public class EcranDossierMedical extends JFrame {

	private JTextArea textAreaNote;
	private JButton btnValider, btnAnnuler;
	private JLabel lblAnt;
	private JLabel lblClient, lblNomCli;
	private JLabel lblAnimal, lblNomAni, lblCodeAni, lblCouleurSexe, lblEspece, lblTatou;
	JPanel panelPrincipal = new JPanel();
	private Clients cli;
	private Animaux ani;
	private Races espece;

	public EcranDossierMedical() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(400, 250, 500, 300);
		this.setTitle("Dosssier Médical");
		this.initIhm();
	}

	/*public EcranDossierMedical(Clients cli, Animaux ani, Races espece) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(400, 250, 500, 300);
		this.setTitle("Dosssier Médical");
		this.initIhm();
		this.cli = cli;
		this.ani = ani;
		this.espece = espece;
	}*/

	public void initIhm() {
		panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		this.setContentPane(panelPrincipal);
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[] { 63, 120, 0, 0, 17, 0 };
		gbl_panelPrincipal.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0, 0, 10 };
		gbl_panelPrincipal.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelPrincipal.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0 };
		panelPrincipal.setLayout(gbl_panelPrincipal);

		// BOUTONS
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.insets = new Insets(0, 0, 5, 5);
		gbc_btnValider.gridx = 2;
		gbc_btnValider.gridy = 0;
		panelPrincipal.add(getValider(), gbc_btnValider);

		GridBagConstraints gbc_btnAnnuler = new GridBagConstraints();
		gbc_btnAnnuler.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnnuler.gridx = 3;
		gbc_btnAnnuler.gridy = 0;
		panelPrincipal.add(getAnnuler(), gbc_btnAnnuler);

		// TEXTE
		lblClient = new JLabel("Client :");
		GridBagConstraints gbc_lblClient = new GridBagConstraints();
		gbc_lblClient.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblClient.insets = new Insets(0, 0, 5, 5);
		gbc_lblClient.gridx = 0;
		gbc_lblClient.gridy = 0;
		panelPrincipal.add(lblClient, gbc_lblClient);

		lblNomCli = new JLabel("NomClient");
		GridBagConstraints gbc_lblNomcli = new GridBagConstraints();
		gbc_lblNomcli.gridwidth = 2;
		gbc_lblNomcli.anchor = GridBagConstraints.NORTH;
		gbc_lblNomcli.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomcli.gridx = 0;
		gbc_lblNomcli.gridy = 1;
		panelPrincipal.add(lblNomCli, gbc_lblNomcli);

		lblAnt = new JLabel("Antécédent consultations");
		GridBagConstraints gbc_lblNote = new GridBagConstraints();
		gbc_lblNote.gridwidth = 2;
		gbc_lblNote.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNote.insets = new Insets(0, 0, 5, 0);
		gbc_lblNote.gridx = 2;
		gbc_lblNote.gridy = 1;
		panelPrincipal.add(lblAnt, gbc_lblNote);

		lblAnimal = new JLabel("Animal :");
		GridBagConstraints gbc_lblAnimalCode = new GridBagConstraints();
		gbc_lblAnimalCode.anchor = GridBagConstraints.EAST;
		gbc_lblAnimalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnimalCode.gridx = 0;
		gbc_lblAnimalCode.gridy = 2;
		panelPrincipal.add(lblAnimal, gbc_lblAnimalCode);

		lblCodeAni = new JLabel("CodeAnimal");
		GridBagConstraints gbc_lblCodeAnimal = new GridBagConstraints();
		gbc_lblCodeAnimal.anchor = GridBagConstraints.WEST;
		gbc_lblCodeAnimal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodeAnimal.gridx = 1;
		gbc_lblCodeAnimal.gridy = 2;
		panelPrincipal.add(lblCodeAni, gbc_lblCodeAnimal);

		lblNomAni = new JLabel("NonAnimal");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panelPrincipal.add(lblNomAni, gbc_lblNewLabel);

		lblCouleurSexe = new JLabel("Couleur  Sexe");
		GridBagConstraints gbc_lblCouleurSexe = new GridBagConstraints();
		gbc_lblCouleurSexe.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblCouleurSexe.insets = new Insets(0, 0, 5, 5);
		gbc_lblCouleurSexe.gridx = 1;
		gbc_lblCouleurSexe.gridy = 4;
		panelPrincipal.add(lblCouleurSexe, gbc_lblCouleurSexe);

		lblEspece = new JLabel("Espece");
		GridBagConstraints gbc_lblEspece = new GridBagConstraints();
		gbc_lblEspece.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEspece.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspece.gridx = 1;
		gbc_lblEspece.gridy = 5;
		panelPrincipal.add(lblEspece, gbc_lblEspece);

		lblTatou = new JLabel("Tatouage");
		GridBagConstraints gbc_lblTatouage = new GridBagConstraints();
		gbc_lblTatouage.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTatouage.insets = new Insets(0, 0, 5, 5);
		gbc_lblTatouage.gridx = 1;
		gbc_lblTatouage.gridy = 6;
		panelPrincipal.add(lblTatou, gbc_lblTatouage);
		
		//ZONE TEXTE
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.gridwidth = 2;
		gbc_textArea.gridheight = 7;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 2;
		panelPrincipal.add(getNote(), gbc_textArea);
	}

	// ZONE TEXTE
	public JTextArea getNote() {
		if (textAreaNote == null){
			textAreaNote = new JTextArea();
		}
		return textAreaNote;
	}

	// BOUTONS
	public JButton getValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Animaux ani = new Animaux();
					AnimauxDAO aniDAO = new DAOFactory().getAnimauxDAO();
					ani.setAntecedents(textAreaNote.getText());
					try {
						aniDAO.updateAnimaux(ani);
						System.out.println("Passage");
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					EcranDossierMedical.this.fenetreAgendas();

				}
			});
		}
		return btnValider;
	}

	public JButton getAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					EcranDossierMedical.this.fenetreAgendas();

				}
			});
		}
		return btnAnnuler;
	}

	// Retour Agendas
	public void fenetreAgendas() {
		EcranAgendas goToAgendas = new EcranAgendas();
		goToAgendas.setVisible(true);
		EcranDossierMedical.this.dispose();
	}
}
