package fr.eni.clinique.ihm;

import javax.swing.SwingUtilities;

import fr.eni.clinique.ihm.agendas.EcranAgendas;
<<<<<<< HEAD
import fr.eni.clinique.ihm.agendas.EcranDossierMedical;
=======
import fr.eni.clinique.ihm.gestionClient.EcranGestionClients;
>>>>>>> 74333e0770e11029869a3a2fa4d21c19ada6fc20
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

<<<<<<< HEAD
				EcranDossierMedical ecranLogin = new EcranDossierMedical();
				ecranLogin.setVisible(true);
=======
				EcranGestionClients ecranPrincipalGestion = new EcranGestionClients();
				ecranPrincipalGestion.setVisible(true);

>>>>>>> 74333e0770e11029869a3a2fa4d21c19ada6fc20
			}
		});
	}
}
