package fr.eni.clinique.ihm.gestionPerso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JButton valider;

	
	public EcranAjoutPerso() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(400, 250, 500, 300);
		this.setTitle("Ajout Personnel");
		this.initIhm();
	}

	public void initIhm() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		panelPrincipal.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);

		gbc.gridy = 0;
		gbc.gridx = 1;
		panelPrincipal.add(getNom(), gbc);
		gbc.gridy = 1;
		gbc.gridx = 1;
		panelPrincipal.add(getMdp(), gbc);
		gbc.gridy = 2;
		gbc.gridx = 1;
		panelPrincipal.add(getConfMdp(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 0;
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
		gbc.gridwidth = 2;
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
			String[] choixRole = { "vet", "sec", "adm" };
			role = new JComboBox<String>(choixRole);
			role.setPreferredSize(new Dimension(100, 20));
		}
		return role;
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
							if (!control.validerConnection(p)) {
								PersonnelsDAO personnelDAO = DAOFactory.getPersonnelsDAO();
								personnelDAO.insert(p);
								System.out.println(nom.getText() + " ajouté");
							} else {
								System.out.println("Erreur");
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

}
