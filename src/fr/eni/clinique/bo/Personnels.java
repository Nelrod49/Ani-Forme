package fr.eni.clinique.bo;

public class Personnels {
	
	private int codePersonnel;
	private String nom;
	private String mdp;
	private String role;
	private boolean archive;
	
	//Constructeur
	public Personnels(int codePersonnel, String nom, String mdp, String role, boolean archive) {
		super();
		this.codePersonnel = codePersonnel;
		this.nom = nom;
		this.mdp = mdp;
		this.role = role;
		this.archive = archive;
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

	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	
	
	
	
}
