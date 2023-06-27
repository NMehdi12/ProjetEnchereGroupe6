package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.bo.Enchere;
import fr.eni.enchere.groupe6.bo.Retrait;
import fr.eni.enchere.groupe6.bo.Utilisateur;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	private final String FIND_ALL = "SELECT * FROM ARTICLES_VENDUS";
	
	
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<ArticleVendu> findAll() {
		
		return jdbcTemplate.query(FIND_ALL, new ArticleRowMapper());
	}

	@Override
	public ArticleVendu findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ArticleVendu articleVendu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ArticleVendu articleVendu) {
		// TODO Auto-generated method stub
		
	}

	
	class ArticleRowMapper implements RowMapper<ArticleVendu>{

		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticleVendu a = new ArticleVendu();
			a.setNoArticle(rs.getInt("no_article"));
			a.setNomArticle(rs.getString("nom_article"));
			a.setDescription(rs.getString("description"));
			a.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
			a.setDateFinEncheres(rs.getDate("date_fin_encheres"));
			a.setMiseAPrix(rs.getInt("prix_initial"));
			a.setPrixVente(rs.getInt("prix_vente"));
			
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			a.setUtilisateur(utilisateur);
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			a.setCategorie(categorie);
			
			Retrait retrait= new Retrait();
			retrait.setArticleVendu(a);
			a.setRetrait(retrait);
			
			Enchere enchere = new Enchere();
			enchere.setArticleVendu(a);
			enchere.setUtilisateur(utilisateur);
			a.setEnchere(enchere);
			
			return a;
		}
		
		
		
	}
	
	
}
