package fr.eni.enchere.groupe6.bo;

import java.util.List;

import org.hibernate.validator.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Utilisateur {

	private Integer noUtilisateur;
	@NotNull
	@NotBlank (message="Le nom de votre article doit ètre renseigné")
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Numéro de téléphone invalide")
	private String telephone;
	@Pattern(regexp = "^\\d{5}$", message = "Code postal invalide")
	private String codePostal;
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message ="uniquement caractère alphanumériques")
	private String pseudo;
	@NotNull
	@NotBlank (message="Champ requis")
	private String nom;
	@NotNull
	@NotBlank(message="Champ requis")
	private String prenom;
	@NotNull
	@NotBlank(message="Champ requis")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Adresse e-mail invalide")
	private String email;
	@NotNull
	@NotBlank
	private String rue;
	@NotNull
	@NotBlank
	private String ville;
	@Size(min=6, message="Au moins 6 caractères")
	private String motDePasse;
	private String motDePasseConfirm;
	private Integer credit;
	@NotNull
	private List<ArticleVendu> articleVendu; // faire l'injection par constructeur
	@NotNull
	private boolean administrateur;
	@NotNull
	private List<Enchere>enchere;
	
	
	
	public Utilisateur() {
		super();
	}

	//Constructeur avec tous les attributs (List d'article de 0 à * et List d'enchere) 
	public Utilisateur(Integer noUtilisateur, String telephone, String codePostal, String pseudo, String nom, String prenom,
			String email, String rue, String ville, String motDePasse, Integer credit, boolean administrateur,List<ArticleVendu> articleVendu, List<Enchere>enchere) {
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
		this.enchere=enchere;
		
	}
	
	//Constructeur sans la liste d'article ni d'enchere dans le doute)
	public Utilisateur(Integer noUtilisateur, String telephone, String codePostal, String pseudo, String nom, String prenom,
			String email, String rue, String ville, String motDePasse, Integer credit, boolean administrateur) {
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
	
	
	
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
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
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
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

	public List<Enchere> getEnchere() {
		return enchere;
	}
	
	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", telephone=" + telephone + ", codePostal=" + codePostal
				+ ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", rue=" + rue
				+ ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit + ", articleVendu="
				+ articleVendu + ", administrateur=" + administrateur + "]";
	}

	public String getMotDePasseConfirm() {
		return motDePasseConfirm;
	}

	public void setMotDePasseConfirm(String motDePasseConfirm) {
		this.motDePasseConfirm = motDePasseConfirm;
	}


	
	
	
	
	
}
