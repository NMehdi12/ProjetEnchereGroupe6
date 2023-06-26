package fr.eni.enchere.groupe6.bo;

import java.util.Date;
import java.util.List;

public class Enchere {
	private Date dateEnchere;
	private int montantEnchere;
	private List<ArticleVendu> articleVendu;
	private List<Utilisateur> utilisateur;
	

	
	
	

	public Enchere() {
		super();
	}
	
	public Enchere(Date dateEnchere, int montantEnchere, List<ArticleVendu> articleVendu,
			List<Utilisateur> utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.utilisateur = utilisateur;
	}
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	public List<ArticleVendu> getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(List<ArticleVendu> articleVendu) {
		this.articleVendu = articleVendu;
	}


	public List<Utilisateur> getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(List<Utilisateur> utilisateur) {
		this.utilisateur = utilisateur;
	}
	

	@Override
	public String toString() {
		return "Enchere [articleVendu=" + articleVendu + ", dateEnchere=" + dateEnchere + ", montantEnchere="
				+ montantEnchere + ", utilisateur=" + utilisateur + "]";
	}
	
	
}
