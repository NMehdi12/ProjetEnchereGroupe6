package fr.eni.enchere.groupe6.bll;

import java.util.List;

import org.springframework.security.core.Authentication;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Categorie;

public interface ArticleVenduService {
List<ArticleVendu> afficherArticlesVendus();
List<ArticleVendu> afficherArticlesVendusParEtat();
ArticleVendu afficherDetailParNoArticle(Integer noArticle);
List<ArticleVendu> afficherResultatRecherche(String nomArticle);
void enregistrerArticle(ArticleVendu articleVendu, Authentication authentication);
List<ArticleVendu> afficherResultatParCategorie(Categorie categorie);
void mettreAJourArticle(ArticleVendu articleVendu);
}
