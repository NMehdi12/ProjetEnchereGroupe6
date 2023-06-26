package fr.eni.enchere.groupe6.bo;

import java.util.Date;

public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente; //3 etats possible ?? (en cour d'enchere, vendu, en attente)
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Retrait retrait;
	private Enchere enchere;
	
	
	
	public ArticleVendu() {
		super();
		
	}

	public ArticleVendu(Utilisateur utilisateur, int noArticle, String nomArticle, String description,
			Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix, int prixVente, String etatVente,Categorie categorie) {
		
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
	
	public ArticleVendu(Utilisateur utilisateur, int noArticle, String nomArticle, String description,
			Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix, int prixVente, String etatVente,Categorie categorie,Retrait retrait,Enchere enchere) {
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
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
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
	public String getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}
	
	
	
	
}
