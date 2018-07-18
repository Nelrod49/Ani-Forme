package fr.eni.clinique.ihm.gestionPerso;

import java.awt.Dimension;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class EcranPrincipalGestion extends JFrame {

	private JButton buttonAjouter;
	private JButton buttonSupprimer;
	private JButton buttonReinitialiser;
	private JTable tablePersonnels;
	
	public EcranPrincipalGestion(String titre){
		super(titre);
		this.setSize(new Dimension(600,400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initIHM();
	}
	
	private void initIHM(){
		JPanel panelPrincipal = new JPanel();
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

		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelPrincipal.add(getPersonnels(), gbc);
		
		this.setContentPane(panelPrincipal);
	}
	private JButton getAjouter(){
		if(buttonAjouter == null){
			buttonAjouter = new JButton();
			buttonAjouter.setText("Ajouter");
		}
		return buttonAjouter;
	}
	
	private JButton getSupprimer(){
		if(buttonSupprimer == null){
			buttonSupprimer = new JButton();
			buttonSupprimer.setText("Supprimer");
		}
		return buttonSupprimer;
	}
	
	private JButton getReinitiliser(){
		if(buttonReinitialiser == null){
			buttonReinitialiser = new JButton();
			buttonReinitialiser.setText("Réinitialiser");
		}
		return buttonReinitialiser;
	}
	
	private JTable getPersonnels(){
		if(tablePersonnels == null){
			tablePersonnels = new JTable();
		}
		return tablePersonnels;
	}
	
}
