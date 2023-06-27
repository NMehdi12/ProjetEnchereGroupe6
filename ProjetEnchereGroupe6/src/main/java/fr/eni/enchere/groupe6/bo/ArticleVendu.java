package fr.eni.enchere.groupe6.bo;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ArticleVendu {
	
	private Integer noArticle;
	@NotNull
	@NotBlank (message="Le nom de votre article doit ètre renseigné")
	private String nomArticle;
	@NotBlank (message="Renseignez une description")
	private String description;
	@NotBlank 
	@FutureOrPresent (message = "renseignez une date valide")
	private Date dateDebutEncheres;
	
	private Date dateFinEncheres;
	private Integer miseAPrix;
	private Integer prixVente;
	private int etatVente; //3 etats possible ?? (en cour d'enchere, vendu, en attente)
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Retrait retrait;
	private Enchere enchere;
	
	private static final int EN_ATTENTE = 0;
	private static final int EN_COURS = 1;
	private static final int VENDU = 2;
	
	
	
	public ArticleVendu() {
		super();
		
	}

	public ArticleVendu(Utilisateur utilisateur, Integer noArticle, String nomArticle, String description,
			Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix, int prixVente, int etatVente,Categorie categorie) {
		
		this.utilisateur = utilisateur;
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}
	
	public ArticleVendu(Utilisateur utilisateur, Integer noArticle, String nomArticle, String description,
			Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix, int prixVente, int etatVente,Categorie categorie,Retrait retrait,Enchere enchere) {
		this.utilisateur = utilisateur;
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.retrait=retrait;
		this.categorie=categorie;
		this.enchere=enchere;
	}
	
	
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Integer getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public int getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", categorie=" + categorie + ", retrait=" + retrait + ", enchere=" + enchere + "]";
	}
	
	
	
	
	
	
}
