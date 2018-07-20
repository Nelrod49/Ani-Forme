package fr.eni.clinique.ihm;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.gestionPerso.EcranPrincipalGestion;
import fr.eni.clinique.ihm.login.EcranLogin;
import fr.eni.clinique.ihm.priseRdv.EcranPriseRendezVous;

public class DemoApp {

	public static void main(String[] args){
		DemoApp demoApp = new DemoApp();
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				EcranPriseRendezVous ecranPrincipalGestion = new EcranPriseRendezVous("Prise Rendez-vous");
				ecranPrincipalGestion.setVisible(true);
			}
		});
	}
}
