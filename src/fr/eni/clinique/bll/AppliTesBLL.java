package fr.eni.clinique.bll;

import java.awt.List;
import java.util.ArrayList;
import fr.eni.clinique.dal.*;
import fr.eni.clinique.bo.Personnels;

public class AppliTesBLL {
	public static void main(String[] args) {
		// Instanciation du jeu d'essai
		List<Personnels> Personnels = new ArrayList<>();
		Personnels.add(new Personnels(01, "Nelson", "nelrod", "adm", true));
		Personnels.add(new Personnels(02, "Elie", "Eliegui", "sec", false));
		 
		
		LoginManager manager = null;
		
		try {
			manager = LoginManager.getInstance();
		} catch (BLLException e1) {
			e1.printStackTrace();
		}

		// Ajout d'un Personnels au catalogue
		try {
			for (Personnels p : Personnels) {
				manager.addPersonnels(p);
			}
			System.out.println(manager.getCatalogue());

		} catch (BLLException e) {
			e.printStackTrace();
		}

		// Modification d'un Personnels
		try {
			((Personnels) Personnels).setRole("vet");
			((Personnels) Personnels).setArchive(true);
			manager.updatePersonnels(Personnels);
			System.out.println("Personnels après modification  : " + Personnels.toString());
		} catch (BLLException e) {
			e.printStackTrace();
		}

		// Suppression d'un Personnels
		try {
			manager.removePersonnels(Personnels);
			System.out.println(manager.getCatalogue());
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
