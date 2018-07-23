package fr.eni.clinique.ihm;

import javax.swing.SwingUtilities;

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
				EcranPrincipalGestion ecranPrincipalGestion = new EcranPrincipalGestion();
=======
				EcranPriseRendezVous ecranPrincipalGestion = new EcranPriseRendezVous("Prise Rendez-vous");
>>>>>>> a8787a87be2150b722cae3e614bb713250287d1b
				ecranPrincipalGestion.setVisible(true);
			}
		});
	}
}
