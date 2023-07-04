package fr.eni.enchere.groupe6.bll;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;

import fr.eni.enchere.groupe6.bo.ArticleVendu;

public interface ArticleVenduService {
List<ArticleVendu> afficherArticlesVendus();
List<ArticleVendu> afficherArticlesVendusParEtat();
ArticleVendu afficherDetailParNoArticle(Integer noArticle);
List<ArticleVendu> afficherResultatRecherche(String nomArticle);
void enregistrerArticle(ArticleVendu articleVendu, Authentication authentication);
void mettreAJourArticle(ArticleVendu articleVendu);
}
