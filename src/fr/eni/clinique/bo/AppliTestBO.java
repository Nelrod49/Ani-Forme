package fr.eni.clinique.bo;

public class AppliTestBO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	GestionPerso perso = new GestionPerso();
		
		perso.addVet("Jean", "JeanMDP");
		System.out.println(perso.getPerso());
	}

}
