package fr.eni.clinique.bo;

public class Personnels {
	
	private int codePersonnel;
	private String nom;
	private String mdp;
	private String role;
	private boolean archive = false;

	
	//Constructeur
	public Personnels(int codePersonnel, String nom, String mdp, String role, boolean archive) {
		super();
		this.codePersonnel = codePersonnel;
		this.nom = nom;
		this.mdp = mdp;
		this.role = role;
		this.archive = archive;
	}
	
	public Personnels(String nom, String mdp, String role, boolean archive) {
		super();
		this.nom = nom;
		this.mdp = mdp;
		this.role = role;
		this.archive = archive;
	}
	
	public Personnels(){
		super();
	}

	//Getter et Setter
	public int getCodePersonnel() {
		return codePersonnel;
	}
	public void setCodePersonnel(int codePersonnel) {
		this.codePersonnel = codePersonnel;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public boolean getArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	//Méthodes
	/*public Personnels addPerso(int codePersonnel, String nom, String mdp, String role){
		Personnels newPerso = new Personnels(codePersonnel, nom, mdp, role, true);
		return newPerso;
	}*/

	@Override
	public String toString() {
		return "Personnels [codePersonnel=" + codePersonnel + ", nom=" + nom + ", mdp=" + mdp + ", role=" + role
				+ ", archive=" + archive + "]";
	}
	
	
	
}
