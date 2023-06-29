package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.bo.Retrait;
import fr.eni.enchere.groupe6.bo.Utilisateur;
@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	
	private final String FIND_ALL = "SELECT * FROM ARTICLES_VENDUS";
	
	private final static String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
	
	//private final String FIND_ALL = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS";
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	@Autowired
	private CategorieDAO categorieDAO;
	
//	@Autowired
//	private RetraitDAO retraitDAO;
	

	@Override
	public List<ArticleVendu> findAll() {
		
		
		return npJdbcTemplate.query(FIND_ALL, new ArticleRowMapper());
		
	}

	@Override
	public ArticleVendu findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ArticleVendu articleVendu) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("nom_article", articleVendu.getNomArticle());
		paramSrc.addValue("description", articleVendu.getDescription());
		paramSrc.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
		paramSrc.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
		paramSrc.addValue("prix_initial", articleVendu.getMiseAPrix());
		paramSrc.addValue("prix_vente", articleVendu.getPrixVente());
		paramSrc.addValue("no_utilisateur", articleVendu.getUtilisateur().getNoUtilisateur());
		paramSrc.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
		
		npJdbcTemplate.update(INSERT, paramSrc);
		
		
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
			
			Utilisateur utilisateur = null;
			try {
				utilisateur= utilisateurDAO.findById(rs.getInt("no_utilisateur"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			a.setUtilisateur(utilisateur);
			
			
			
			Categorie categorie = null;
			try {
				categorie = categorieDAO.findById(rs.getInt("no_categorie"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			//System.out.println("alors? " +categorie);
			a.setCategorie(categorie);
			
			
			
//			Retrait retrait= new Retrait();
//			try {
//				retrait = retraitDAO.findById(rs.getInt("no_article"));
//			} catch (Exception e) {
//				
//			}
//			//retrait.setArticleVendu(a);
//			a.setRetrait(retrait);
			
//			Enchere enchere = new Enchere();
//			enchere.setArticleVendu(a);
//			enchere.setUtilisateur(utilisateur);
//			a.setEnchere(enchere);
			
			System.out.println("la categorie "+a.getCategorie() );
			return a;
		}
		
		
		
	}
	
	
}
