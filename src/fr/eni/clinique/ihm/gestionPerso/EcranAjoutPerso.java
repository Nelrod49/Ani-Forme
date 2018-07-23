package fr.eni.clinique.ihm.gestionPerso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.PanelPeer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.LoginManager;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;

public class EcranAjoutPerso extends JFrame {

	private JLabel txtNom, txtMdp, txtConfMdp;
	private JTextField nom, mdp, confMdp;
	private JComboBox<String> role;
	private JButton valider, annuler;
	JOptionPane display = new JOptionPane();
	JPanel panelPrincipal = new JPanel();

	public EcranAjoutPerso() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(400, 250, 500, 200);
		this.setTitle("Ajout Personnel");
		this.initIhm();
	}

	public void initIhm() {
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		panelPrincipal.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);

		gbc.gridy = 0;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		panelPrincipal.add(getNom(), gbc);
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		panelPrincipal.add(getMdp(), gbc);
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		panelPrincipal.add(getConfMdp(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		panelPrincipal.add(getTxtNom(), gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelPrincipal.add(getTxtMdp(), gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		panelPrincipal.add(getTxtConfMdp(), gbc);

		gbc.gridy = 3;
		gbc.gridx = 1;
		
		panelPrincipal.add(getRole(), gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		panelPrincipal.add(getAnnuler(), gbc);
		gbc.gridy = 5;
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		panelPrincipal.add(getValider(), gbc);

		this.setContentPane(panelPrincipal);
	}

	// Texte
	public JLabel getTxtNom() {
		if (txtNom == null) {
			txtNom = new JLabel("Nom : ");
		}
		return txtNom;
	}

	public JLabel getTxtMdp() {
		if (txtMdp == null) {
			txtMdp = new JLabel("Mot de passe : ");
		}
		return txtMdp;
	}

	public JLabel getTxtConfMdp() {
		if (txtConfMdp == null) {
			txtConfMdp = new JLabel("Confirmer mot de passe : ");
		}
		return txtConfMdp;
	}

	// Saisie
	private JTextField getNom() {
		if (nom == null) {
			nom = new JTextField(30);
		}
		return nom;
	}

	private JTextField getMdp() {
		if (mdp == null) {
			mdp = new JTextField(30);
		}
		return mdp;
	}

	private JTextField getConfMdp() {
		if (confMdp == null) {
			confMdp = new JTextField(30);
		}
		return confMdp;
	}

	// Bande déroulante
	private JComboBox<String> getRole() {
		if (role == null) {
			String[] choixRole = { "Choissiez un rôle", "vet", "sec", "adm" };
			role = new JComboBox<String>(choixRole);
			role.setPreferredSize(new Dimension(130, 20));
		}
		return role;
	}

	// Verif de la confirmation du MDP
	private int verifMdp() {
		// si verif est à 0, le mot de passe n'est pas identique dans les deux
		// cases de saisie
		int verif = 0;
		if (mdp.getText().trim().length() >= 5) {
			if (mdp.getText().equals(confMdp.getText())) {
				// si verif est egale à 1, le mot de passe est valide
				verif = 1;
			}
			// si verif est à 2, le mot de passe ne contient pas au moin 5
			// caracteres
		} else
			verif = 2;
		return verif;
	}

	// Bouton Valider
	private JButton getValider() {
		if (valider == null) {
			valider = new JButton("Valider");

			valider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					try {
						LoginManager control = new LoginManager();
						Personnels p = new Personnels(nom.getText(), mdp.getText(), role.getSelectedItem().toString());
						try {
							switch (verifMdp()) {
							case 0:
								display.showMessageDialog(panelPrincipal, "Mot de passe non identique", "Attention",
										JOptionPane.WARNING_MESSAGE);
								break;
							case 1:
								if (role.getSelectedItem().toString().equals("vet") || role.getSelectedItem().toString().equals("sec") || role.getSelectedItem().toString().equals("adm")){
								if (!control.validerConnection(p)) {
									PersonnelsDAO personnelDAO = DAOFactory.getPersonnelsDAO();
									personnelDAO.insert(p);
									display.showMessageDialog(panelPrincipal, "Personnel ajouté");
								} else {
									display.showMessageDialog(panelPrincipal, "Un personnel indentique existe déjà",
											"Attention", JOptionPane.WARNING_MESSAGE);
								}
								} else {
									display.showMessageDialog(panelPrincipal, "Veuillez choisir un rôle",
											"Attention", JOptionPane.WARNING_MESSAGE);
								}
								break;
							case 2:
								display.showMessageDialog(panelPrincipal,
										"Le mot de passe doit contenir au moins 5 caractères", "Attention",
										JOptionPane.WARNING_MESSAGE);
								break;
							}

						} catch (BLLException e2) {
							e2.printStackTrace();
						} catch (DALException e1) {
							e1.printStackTrace();
						}

					} catch (BLLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return valider;

	}

	// Appel de la fenetre PrincipalGestion
	public void fenetreRetourGestion() {
		EcranPrincipalGestion retourGestion = new EcranPrincipalGestion();
		retourGestion.setVisible(true);
		EcranAjoutPerso.this.dispose();
	}

	// Bouton Annuler
	private JButton getAnnuler() {
		if (annuler == null) {
			annuler = new JButton("Annuler");

			annuler.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					EcranAjoutPerso.this.fenetreRetourGestion();
				}
			});
		}
		return annuler;
	}

}
