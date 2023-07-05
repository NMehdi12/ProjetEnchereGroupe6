package fr.eni.enchere.groupe6.bll;

import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Enchere;

@Service
public class EnchereServiceImpl implements EnchereService {

	@Override
	public void enregistrerEnchere(ArticleVendu articleVendu, Enchere enchere) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enchere afficherMeilleureEnchereParNoArticle(Integer noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

}
