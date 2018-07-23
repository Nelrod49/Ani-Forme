package fr.eni.clinique.ihm;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.agendas.EcranAgendas;
import fr.eni.clinique.ihm.gestionPerso.EcranAjoutPerso;
import fr.eni.clinique.ihm.gestionPerso.EcranPrincipalGestion;
import fr.eni.clinique.ihm.login.EcranLogin;
import fr.eni.clinique.ihm.priseRdv.EcranPriseRendezVous;

public class DemoApp {

	public static void main(String[] args){
		DemoApp demoApp = new DemoApp();
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
<<<<<<< HEAD
				EcranAgendas ecranPrincipalGestion = new EcranAgendas("Les Rendez-vous");
				ecranPrincipalGestion.setVisible(true);
=======

				EcranLogin ecranLogin = new EcranLogin();
				ecranLogin.setVisible(true);
>>>>>>> 3fa305174d3975339018ca1276ea7b15d1af5e59
			}
		});
	}
}
