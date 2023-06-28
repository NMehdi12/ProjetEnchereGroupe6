package fr.eni.enchere.groupe6.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.dal.ArticleVenduDAO;


public class ArticleVenduServiceImpl implements ArticleVenduService {

	private ArticleVenduDAO articleVenduDao;
	
	@Autowired
	public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDao) {
		this.articleVenduDao = articleVenduDao;
	}
	
	@Override
	public List<ArticleVendu> afficherArticlesVendus() {
		return articleVenduDao.findAll();
	}

	@Override
	public List<ArticleVendu> afficherArticlesVendusParEtat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu afficherDetailParNoArticle(Integer noArticle) {
		return articleVenduDao.findById(noArticle);
	}

	@Override
	public List<ArticleVendu> afficherResultatRecherche(String nomArticle) {
		// TODO Auto-generated method stub
		return null;
	}

}
