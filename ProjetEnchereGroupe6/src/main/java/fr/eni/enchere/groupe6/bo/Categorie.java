package fr.eni.enchere.groupe6.bo;

import java.util.List;

public class Categorie {
	private int noCategorie;
	private String libelle;
	private List <ArticleVendu> articleVendu;
	
	
	
	
	
	public Categorie() {
		super();
	}

	public Categorie(int noCategorie, String libelle, List<ArticleVendu> articleVendu) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.articleVendu = articleVendu;
	}
	
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<ArticleVendu> getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(List<ArticleVendu> articleVendu) {
		this.articleVendu = articleVendu;
	}
	@Override
	public String toString() {
		return "categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", articleVendu=" + articleVendu
				+ "]";
	} 
	
	
	
	
	
}
