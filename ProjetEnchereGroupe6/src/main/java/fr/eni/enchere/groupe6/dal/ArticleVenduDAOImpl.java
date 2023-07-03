package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.groupe6.bo.ArticleVendu;
import fr.eni.enchere.groupe6.bo.Categorie;
import fr.eni.enchere.groupe6.bo.Retrait;
import fr.eni.enchere.groupe6.bo.Utilisateur;
@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	
	private final String FIND_ALL = "SELECT * FROM ARTICLES_VENDUS";
	
	private final static String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
	private final static String FIND_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=:no_article ";
	//private final String FIND_ALL = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS";
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	@Autowired
	private CategorieDAO categorieDAO;
	

	private RetraitDAO retraitDAO;
	
	public ArticleVenduDAOImpl (JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void setRetraitDAO (RetraitDAO retraitDAO) {
		this.retraitDAO=retraitDAO;
	}

	@Override
	public List<ArticleVendu> findAll() {
		
		
		return npJdbcTemplate.query(FIND_ALL, new ArticleRowMapper());
		
	}

	@Override
	public ArticleVendu findById(Integer noArticle) {
		MapSqlParameterSource params = new MapSqlParameterSource("no_article", noArticle);
		ArticleVendu articleVendu = npJdbcTemplate.queryForObject(FIND_ID, params, new ArticleRowMapper());
		System.out.println("je passe par find by id de article vendu");
		return articleVendu;
	}

	@Override
	public void save(ArticleVendu articleVendu, Authentication authentication) {
		String nomUtilisateur = authentication.getName();
		Integer noUtilisateur = utilisateurDAO.findNoUtilisateurByPseudo(nomUtilisateur);
		
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("nom_article", articleVendu.getNomArticle());
		paramSrc.addValue("description", articleVendu.getDescription());
		paramSrc.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
		paramSrc.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
		paramSrc.addValue("prix_initial", articleVendu.getMiseAPrix());
		paramSrc.addValue("prix_vente", articleVendu.getPrixVente());
		paramSrc.addValue("no_utilisateur", noUtilisateur);
		paramSrc.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
		
		System.out.println("Enregistrement de l'article");
		
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
		
			Utilisateur utilisateur = new Utilisateur();
			try {
				utilisateur= utilisateurDAO.findById(rs.getInt("no_utilisateur"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			a.setUtilisateur(utilisateur);
			
			
			
			Retrait retrait= new Retrait();
			try {
				retrait = retraitDAO.findById(rs.getInt("no_article"));
				
			} catch (Exception e) {
				
			}
			//retrait.setArticleVendu(a);
			a.setRetrait(retrait);
			
			
			Categorie categorie = new Categorie();
			try {
				categorie = categorieDAO.findById(rs.getInt("no_categorie"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			//System.out.println("alors? " +categorie);
			a.setCategorie(categorie);
			
			
			
	
			
//			Enchere enchere = new Enchere();
//			enchere.setArticleVendu(a);
//			enchere.setUtilisateur(utilisateur);
//			a.setEnchere(enchere);
//			
			System.out.println("le retrait dans article vendu  "+a.getRetrait() );
			return a;
		}
		
		
		
	}


	@Override
	public List<ArticleVendu> findByNom(String nomArticle) {
		String FIND_BY_NOM = "SELECT * FROM  ARTICLES_VENDUS WHERE nom_article LIKE ?";

        String recherche = "%" + nomArticle + "%";

        return jdbcTemplate.query(FIND_BY_NOM, new ArticleRowMapper() ,recherche );
	}

	@Override
	public List<ArticleVendu> findByCategorie(Categorie categorie) {
		String FIN_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie LIKE ?";
		String rechercheParCategorie = "%" + categorie + "%";
		System.out.println("passe par findByCategorie");
		return jdbcTemplate.query(FIN_BY_CATEGORIE, new ArticleRowMapper(), rechercheParCategorie);
		
		
	}
	
	
}
