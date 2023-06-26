package fr.eni.enchere.groupe6.bo;

import java.util.List;

public class Utilisateur {

	private int noUtilisateur;
	private int telephone;
	private int codePostal;
	private String pseudo;
	private String nom;
	private String prenom;	
	private String email;
	private String rue;
	private String ville;
	private String motDePasse;
	private int credit;
	private List<ArticleVendu> articleVendu; // faire l'injection par constructeur
	private boolean administrateur;
	private List<Enchere>enchere;
	
	
	
	public Utilisateur() {
		super();
	}

	//Constructeur avec tous les attributs (List d'article de 0 à * et List d'enchere) 
	public Utilisateur(int noUtilisateur, int telephone, int codePostal, String pseudo, String nom, String prenom,
			String email, String rue, String ville, String motDePasse, int credit, boolean administrateur,List<ArticleVendu> articleVendu, List<Enchere>enchere) {
		this.noUtilisateur = noUtilisateur;
		this.telephone = telephone;
		this.codePostal = codePostal;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.articleVendu= articleVendu;
	}
	
	//Constructeur sans la liste d'article ni d'enchere dans le doute)
	public Utilisateur(int noUtilisateur, int telephone, int codePostal, String pseudo, String nom, String prenom,
			String email, String rue, String ville, String motDePasse, int credit, boolean administrateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.telephone = telephone;
		this.codePostal = codePostal;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public List<ArticleVendu> getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(List<ArticleVendu> articleVendu) {
		this.articleVendu = articleVendu;
	}
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", telephone=" + telephone + ", codePostal=" + codePostal
				+ ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", rue=" + rue
				+ ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit + ", articleVendu="
				+ articleVendu + ", administrateur=" + administrateur + "]";
	}
	
	
	
	
	
}
