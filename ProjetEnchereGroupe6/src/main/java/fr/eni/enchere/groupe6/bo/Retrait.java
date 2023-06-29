package fr.eni.enchere.groupe6.bo;

public class Retrait {
	private String rue;
	private String ville;
	private String codePostal;
	private ArticleVendu articleVendu;
	
	
	
	public Retrait() {
		super();
	}
	
	
	//Constructeur sans article car rapport 0..1
	public Retrait(String rue, String ville, String codePostal) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		
	}
	
	public Retrait(String rue, String ville, String codePostal,ArticleVendu articleVendu) {
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
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}


	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", ville=" + ville + ", codePostal=" + codePostal + ", articleVendu="
				+ articleVendu + "]";
	}
	
	
	
}
