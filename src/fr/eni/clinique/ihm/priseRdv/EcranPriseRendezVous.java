package fr.eni.clinique.ihm.priseRdv;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jdatepicker.JDatePicker;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;

public class EcranPriseRendezVous extends JFrame{
	private JPanel panelPriseRendezVous;
	private JComboBox<String> comboBxClients;
	private JComboBox<String> comboBxAnimaux;
	private JButton buttonClients;
	private JButton buttonAnimaux;
	private JComboBox<String> comboBxPersonnels;
	private JDatePicker datePickerRendezVous;
	private JComboBox comboBxHeure;
	private JComboBox comboBxMinute;
	private JTable tableRendezVous;
	private JButton buttonSupprimer;
	private JButton buttonAjouter;
	private ArrayList<Clients>lesClients = new ArrayList<Clients>();
	
	public EcranPriseRendezVous(String titre){
		super(titre);
		this.setSize(new Dimension(600,400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initIHM();
	}
	
	private void initIHM(){
		panelPriseRendezVous = new JPanel();
		panelPriseRendezVous.setLayout(new GridBagLayout());
		panelPriseRendezVous.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Clients
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPriseRendezVous.add(new JLabel("Le client :"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelPriseRendezVous.add(getComboBxClients(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelPriseRendezVous.add(getButtonClients(), gbc);
		
		//Animal
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelPriseRendezVous.add(new JLabel("Animal :"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelPriseRendezVous.add(getComboBxAnimaux(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelPriseRendezVous.add(getButtonAnimaux(), gbc);
		
		//Vétérinaire
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelPriseRendezVous.add(new JLabel("Le vétérinaire :"), gbc);		

		gbc.gridx = 2;
		gbc.gridy = 1;
		panelPriseRendezVous.add(getComboBxPersonnels(), gbc);
		
		this.setContentPane(panelPriseRendezVous);
		
	}
	
	private JComboBox<String> getComboBxClients(){
		if(comboBxClients == null){
			comboBxClients = new JComboBox<String>();
			ClientsDAO clientsDAO = DAOFactory.getClientsDAO();
			
			try {
				lesClients = clientsDAO.allClients() ;
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!lesClients.isEmpty()){
				int i = 0;
				comboBxClients.addItem("Sélectionnez un client");
				while (i < lesClients.size()){
					comboBxClients.addItem(
							lesClients.get(i).getNomClient() + " " + 
					lesClients.get(i).getPrenomClient());
		            i=i+1;		 
		        }
			}else{
				comboBxClients.addItem("Aucun clients");
			}
			comboBxClients.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					if(comboBxClients.getSelectedIndex() != -1){
						AnimauxDAO animauxDAO = DAOFactory.getAnimauxDAO();
						try {
							ArrayList<Animaux> lesAnimaux = animauxDAO.getAnimauxClients(
									lesClients.get(comboBxClients.getSelectedIndex() - 1).getCodeClient());
							if(lesAnimaux.size() > 0){
							String[] animaux = new String[lesAnimaux.size()];
							System.out.println(lesAnimaux);
							for (int i=0; i<lesAnimaux.size(); i++) {
								animaux[i] = lesAnimaux.get(i).getNomAnimal();
							}
							System.out.println(animaux);
							if(animaux.length == 0){
							}{
								comboBxAnimaux.setModel(new DefaultComboBoxModel(animaux));
							}
							}else{
								String[] animaux = new String[1];
								animaux[0] = "Aucun animal pour ce client";
								comboBxAnimaux.setModel(new DefaultComboBoxModel(animaux));
							}
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					}
				}
			});
		}
		return comboBxClients;
	}
	
	private JButton getButtonClients(){
		if(buttonClients == null){
			buttonClients = new JButton();
			buttonClients.setText("Ajouter Clients");
		}
		return buttonClients;
	}
	
	private JComboBox<String> getComboBxAnimaux(){
		if(comboBxAnimaux == null){
			comboBxAnimaux = new JComboBox<String>();
			comboBxAnimaux.addItem("Sélectionnez un clients d'abord");			
		}
		return comboBxAnimaux;
	}
	
	private JButton getButtonAnimaux(){
		if(buttonAnimaux == null){
			buttonAnimaux = new JButton();
			buttonAnimaux.setText("Ajouter Animal");
		}
		return buttonAnimaux;
	}
	
	private JComboBox getComboBxPersonnels(){
		if(null == comboBxPersonnels){
			comboBxPersonnels = new JComboBox<String>();
			PersonnelsDAO personnelsDAO = DAOFactory.getPersonnelsDAO();
			ArrayList<Personnels> lesPersonnels = null;
			try {
				lesPersonnels = personnelsDAO.allPersonnelsVeterinaire();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!lesPersonnels.isEmpty()){
				int i = 0;
				while (i < lesPersonnels.size()){
					comboBxPersonnels.addItem(lesPersonnels.get(i).getNom()); 	
					i ++;
		        }
			}else{
				comboBxPersonnels.addItem("Aucun personnels");
			}
		}
		return comboBxPersonnels;
	}
}
