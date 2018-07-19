package fr.eni.clinique.ihm.gestionPerso;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelsBLL;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;

public class EcranRenitialiser extends JFrame {

	private JButton buttonModifier;
	private JPasswordField password1;
	private JPasswordField password2;
	private JPanel panelPrincipal;
	private Personnels per;
	
	public EcranRenitialiser(String titre, Personnels pers){
		super(titre);
		per = pers;
		this.setSize(new Dimension(600,400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initIHM();
	}
	
	private void initIHM(){
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridBagLayout());
		panelPrincipal.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPrincipal.add(new JLabel("Nouveau mot de passe : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelPrincipal.add(getPassword1(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelPrincipal.add(new JLabel("Retapez votre mots de passe : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelPrincipal.add(getPassword2(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelPrincipal.add(getModifier(), gbc);
		
		this.setContentPane(panelPrincipal);
	}
	
	private JPasswordField getPassword1(){
		if(password1 == null){
			password1 = new JPasswordField(20);
		}
		return password1;
	}

	
	private JPasswordField getPassword2(){
		if(password2 == null){
			password2 = new JPasswordField(20);
		}
		return password2;
	}
	
	private JButton getModifier(){
		if(buttonModifier == null){
			buttonModifier = new JButton();
			buttonModifier.setText("Modifier");
			buttonModifier.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					if(Arrays.equals(password1.getPassword(), password2.getPassword())){
						System.out.println(password1.getPassword());
						PersonnelsBLL personnelsBLL;
						try {
							personnelsBLL = new PersonnelsBLL();
							String passText = new String(password1.getPassword());
							per.setMdp(passText);
							passText = null; //Pour pas laisser le mot de passe en base mémoire
							if(personnelsBLL.validerNouveauMotPasse(per)){
								JComponent comp = (JComponent) e.getSource();
					        Window win = SwingUtilities.getWindowAncestor(comp);
					        win.dispose(); //On ferme l'écran actuel
							new EcranPrincipalGestion("Gestion Personnel").setVisible(true);
							JOptionPane d = new JOptionPane();
							d.showMessageDialog(panelPrincipal,
								    "Mot de passe modifier.");
							}else{
								JOptionPane d = new JOptionPane();
								d.showMessageDialog(panelPrincipal,
									    "Erreur de modification du mot de passe.");
							}
							
						} catch (BLLException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						} catch (DALException err) {
							// TODO Auto-generated catch block
							err.printStackTrace();
						}
					}else{
						JOptionPane d = new JOptionPane();
						d.showMessageDialog(panelPrincipal,
							    "Vous devez avoir deux mots de passe égaux.",
							    "Attention",
							    JOptionPane.WARNING_MESSAGE);
					}

				}
			});
		}
		return buttonModifier;
	}
}
