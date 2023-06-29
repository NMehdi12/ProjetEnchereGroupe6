package fr.eni.enchere.groupe6.bll;

import java.util.List;

import fr.eni.enchere.groupe6.bo.ArticleVendu;

public interface ArticleVenduService {
List<ArticleVendu> afficherArticlesVendus();
List<ArticleVendu> afficherArticlesVendusParEtat();
ArticleVendu afficherDetailParNoArticle(Integer noArticle);
List<ArticleVendu> afficherResultatRecherche(String nomArticle);
void enregistrerArticle(ArticleVendu articleVendu);
}
