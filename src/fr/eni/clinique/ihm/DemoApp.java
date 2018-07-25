package fr.eni.clinique.ihm;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.agendas.EcranAgendas;
import fr.eni.clinique.ihm.agendas.EcranDossierMedical;
import fr.eni.clinique.ihm.gestionAnimal.EcranAnimal;
import fr.eni.clinique.ihm.gestionClient.EcranGestionClients;
import fr.eni.clinique.ihm.gestionPerso.EcranAjoutPerso;
import fr.eni.clinique.ihm.gestionPerso.EcranPrincipalGestion;
import fr.eni.clinique.ihm.login.EcranGestionPersonnel;
import fr.eni.clinique.ihm.login.EcranLogin;
import fr.eni.clinique.ihm.priseRdv.EcranPriseRendezVous;

public class DemoApp {

	public static void main(String[] args){
		DemoApp demoApp = new DemoApp();
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){

				EcranAgendas ecranLogin = new EcranAgendas();
				ecranLogin.setVisible(true);

			}
		});
	}
}
