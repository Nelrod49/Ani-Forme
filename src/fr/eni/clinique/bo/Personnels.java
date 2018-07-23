package fr.eni.clinique.bo;

public class Personnels {
	
	private int codePersonnel;
	private String nom;
	private String mdp;
	private String role;
	private boolean archive = false;

	
	//Constructeur
	public Personnels(int codePersonnel, String nom, String mdp, String role) {
		super();
		this.codePersonnel = codePersonnel;
		this.nom = nom;
		this.mdp = mdp;
		this.role = role;
	}

	public Personnels(String nom, String mdp, String role) {
		super();
		this.nom = nom;
		this.mdp = mdp;
		this.role = role;
	}
	
	public Personnels(String nom, String mdp) {
		super();
		this.nom = nom;
		this.mdp = mdp;
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


	@Override
	public String toString() {
		return "Personnels [codePersonnel=" + codePersonnel + ", nom=" + nom + ", mdp=" + mdp + ", role=" + role
				+ ", archive=" + archive + "]";
	}
	
	
	
}
