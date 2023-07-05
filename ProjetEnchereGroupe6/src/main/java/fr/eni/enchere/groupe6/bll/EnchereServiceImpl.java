package fr.eni.enchere.groupe6.bll;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Enchere;
import fr.eni.enchere.groupe6.dal.EnchereDAO;

@Service
public class EnchereServiceImpl implements EnchereService {
	
	private EnchereDAO enchereDAO;
	
	public EnchereServiceImpl(EnchereDAO enchereDAO) {
		this.enchereDAO = enchereDAO;
	}

	@Override
	public void enregistrerEnchere(ArticleVendu articleVendu, Authentication authentication) {
		enchereDAO.save(articleVendu, authentication);
		
	}

	@Override
	public Enchere afficherMeilleureEnchereParNoArticle(Integer noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void encherir(ArticleVendu articleVendu, Authentication authentication, Integer nouvelleEnchere) {
		enchereDAO.update(articleVendu, authentication, nouvelleEnchere);
		
	}

}
