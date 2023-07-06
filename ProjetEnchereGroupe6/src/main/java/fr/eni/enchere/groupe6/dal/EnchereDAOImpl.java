package fr.eni.enchere.groupe6.dal;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Enchere;

@Repository
public class EnchereDAOImpl implements EnchereDAO{
	
	@Autowired
	UtilisateurDAO utilisateurDAO;
	
	@Autowired
	ArticleVenduDAO articleVenduDAO;
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	private final static String INSERT = "insert into ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (:no_utilisateur, (select max(no_article) from ARTICLES_VENDUS), :date_enchere, :montant_enchere)";
	
	private final static String UPDATE = "update ENCHERES set no_utilisateur = :no_utilisateur, date_enchere = :date_enchere, montant_enchere = :montant_enchere where no_article = :no_article";
	
	@Override
	public List<Enchere> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Enchere enchere) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(ArticleVendu articleVendu, Authentication authentication) {
		String nomUtilisateur = authentication.getName();
		Integer noUtilisateur = utilisateurDAO.findNoUtilisateurByPseudo(nomUtilisateur);
		
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_utilisateur", noUtilisateur);
		paramSrc.addValue("date_enchere", articleVendu.getDateDebutEncheres());
		paramSrc.addValue("montant_enchere", articleVendu.getMiseAPrix());
		
		System.out.println("Enregistrement de la première enchère ! Les valeurs entrées en base de données : " + paramSrc.toString());
		
		npJdbcTemplate.update(INSERT, paramSrc);
		
	}

	@Override
	public void update(ArticleVendu articleVendu, Authentication authentication, Integer nouvelleEnchere) {
		String pseudo = authentication.getName();
		Integer noUtilisateur = utilisateurDAO.findNoUtilisateurByPseudo(pseudo);
		LocalDateTime dateTime = LocalDateTime.now();
		
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_utilisateur", noUtilisateur);
		paramSrc.addValue("date_enchere", dateTime);
		paramSrc.addValue("montant_enchere", nouvelleEnchere);
		paramSrc.addValue("no_article", articleVendu.getNoArticle());
		
		System.out.println("Mise à jour de l'enchère avec les valeurs suivantes : " + paramSrc.toString());
		
		npJdbcTemplate.update(UPDATE, paramSrc);
		
		articleVenduDAO.updatePrixVente(articleVendu, nouvelleEnchere);
	}


	

}
