package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

public class GestionAnimauxManager {
	//mise en place du singleton pour faire qu’une seul instance d’une classe.
		private static GestionAnimauxManager INSTANCE = null;
		
		private AnimauxDAO daoAnimaux;
		
		private GestionAnimauxManager() throws BLLException
		{
			//pas de catalogue à mettre en place
			//Obtention du DAO Animaux
			daoAnimaux = DAOFactory.getAnimauxDAO();

		}	
		public static synchronized GestionAnimauxManager getInstance() throws BLLException{	
				if (INSTANCE == null){
					INSTANCE = new GestionAnimauxManager();	
				}
				return INSTANCE;
		}
		
		/*Vérification que toutes les informations sont rentrée correctement*/
		public boolean validerNouveauAnimal (Animaux a) throws BLLException
		{
			boolean valide = false;
			StringBuffer sb = new StringBuffer();
			
			if(a == null){
				throw new BLLException("Animaux deja existant.");
			}
			
				if (a.getNomAnimal() == null || a.getNomAnimal().trim().length() == 0) {
					sb.append("Le nom est obligatoire.\n");
					return valide;
				}
				if (a.getSexe() == null || a.getSexe().trim().length() == 0) {
					sb.append("Le sexe est obligatoire.\n");
					return valide;
				}
				if (a.getCouleur() == null || a.getCouleur().trim().length() == 0) {
					sb.append("La couleur est obligatoire.\n");
					return valide;
				}

				if (a.getTatouage() == null || a.getTatouage().trim().length() == 0) {
					sb.append("Le tatouage  est obligatoire.\n");
					return valide;
				}
				
			return valide;
		
		}
		
		public void ajouterAnimal (Animaux a)throws BLLException{
			
			try {
				validerNouveauAnimal(a);
				daoAnimaux.insertAnimaux(a);
			} catch (DALException e) {
				throw new BLLException("Echec d'ajout d'un animal", e);
			}


		}
		
		public void modifierAnimal (Animaux a) throws BLLException{
			try {
				validerNouveauAnimal(a);
				daoAnimaux.updateAnimaux(a);
			} catch (DALException e) {
				throw new BLLException("Echec de modification d'un animal",e);
			}
		}
}
