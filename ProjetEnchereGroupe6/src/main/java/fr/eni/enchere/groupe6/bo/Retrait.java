package fr.eni.enchere.groupe6.bo;

public class Retrait {
	private String rue;
	private String ville;
	private int codePostal;
	private ArticleVendu articleVendu;
	
	
	
	public Retrait() {
		super();
	}
	
	
	//Constructeur sans article car rapport 0..1
	public Retrait(String rue, String ville, int codePostal) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		
	}
	
	public Retrait(String rue, String ville, int codePostal,ArticleVendu articleVendu) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.articleVendu=articleVendu;
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
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	
	
}
