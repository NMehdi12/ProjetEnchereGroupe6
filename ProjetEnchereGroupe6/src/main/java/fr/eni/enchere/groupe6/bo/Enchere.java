package fr.eni.enchere.groupe6.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Enchere {
	private Timestamp dateEnchere;
	private Integer montantEnchere;
	private ArticleVendu articleVendu;
	private Utilisateur utilisateur;
	

	
	
	

	public Enchere() {
		super();
	}
	
	public Enchere(Timestamp dateEnchere, Integer montantEnchere, ArticleVendu articleVendu,
			Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.utilisateur = utilisateur;
	}
	public Timestamp getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Timestamp dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public Integer getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	

	@Override
	public String toString() {
		return "Enchere [articleVendu=" + articleVendu + ", dateEnchere=" + dateEnchere + ", montantEnchere="
				+ montantEnchere + ", utilisateur=" + utilisateur + "]";
	}
	
	
}
