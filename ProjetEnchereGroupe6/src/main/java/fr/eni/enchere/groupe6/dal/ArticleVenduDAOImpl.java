package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private final String FIND_ALL = "SELECT * FROM ARTICLES_VENDUS WHERE date_debut_encheres <= GETDATE() AND date_fin_encheres >= GETDATE()";
	
	private final static String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
	private final static String FIND_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=:no_article ";
	//private final String FIND_ALL = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS";
	private final static String UPDATE = "update ARTICLES_VENDUS set nom_article = :nom_article, description = :description, date_debut_encheres = :date_debut_encheres, date_fin_encheres = :date_fin_encheres, prix_initial = :prix_initial, no_categorie = :no_categorie where no_article = :no_article";
	private final static String UPDATE_PRIX_VENTE = "update ARTICLES_VENDUS set prix_vente = :prix_vente where no_article = :no_article";
	
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
		paramSrc.addValue("prix_vente", articleVendu.getMiseAPrix());
		paramSrc.addValue("no_utilisateur", noUtilisateur);
		paramSrc.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
		
		System.out.println("Enregistrement de l'article");
		
		int result =npJdbcTemplate.update(INSERT, paramSrc);
		System.out.println("voici ce qu'il faut regarder:"  + result);
	
		
		
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
		String FIN_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie=:no_categorie AND ";
		
		Map<String, Object> params1 = new HashMap<>();
		params1.put("no_categorie", categorie.getNoCategorie());
		params1.put("libelle", categorie.getLibelle());
		List<ArticleVendu>coucou =  npJdbcTemplate.query(FIN_BY_CATEGORIE, params1, new ArticleRowMapper());
		System.out.println("categoerie : " + categorie.getNoCategorie());
		
		return coucou;
	}

	@Override
	public List<ArticleVendu> findByNoUtilisateur(Utilisateur utilisateur) {
		String FIND_BY_NO_UTILISATEUR= "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=:no_utilisateur AND date_debut_encheres <= GETDATE() AND date_fin_encheres >= GETDATE()";
		Map<String, Object>params = new HashMap<>();
		params.put("no_utilisateur", utilisateur.getNoUtilisateur());
		
		return npJdbcTemplate.query(FIND_BY_NO_UTILISATEUR, params, new ArticleRowMapper());
	}
	
	@Override
	public List<ArticleVendu> findByVentesNonCommencees(Utilisateur utilisateur) {
		String FIND_BY_NON_COMMENCE= "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=:no_utilisateur AND date_debut_encheres > GETDATE() AND date_fin_encheres > GETDATE()";
		Map<String, Object>params = new HashMap<>();
		params.put("no_utilisateur", utilisateur.getNoUtilisateur());
		return npJdbcTemplate.query(FIND_BY_NON_COMMENCE, params, new ArticleRowMapper());
	}
	
	
	@Override
	public List<ArticleVendu> findByVentesTerminees(Utilisateur utilisateur) {
		String FIND_BY_VENTES_TERMINE= "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=:no_utilisateur AND date_debut_encheres < GETDATE() AND date_fin_encheres <= GETDATE()";
		Map<String, Object>params = new HashMap<>();
		params.put("no_utilisateur", utilisateur.getNoUtilisateur());
		return npJdbcTemplate.query(FIND_BY_VENTES_TERMINE, params, new ArticleRowMapper());
	}
	
	@Override
	public List<ArticleVendu> findByMesEncheresEnCours(Utilisateur utilisateur) {
		String FIND_BY_ENCHERES_EN_COURS= 	"SELECT av. * FROM ARTICLES_VENDUS av INNER JOIN ENCHERES e ON av.no_article = e.no_article AND"
				+ " av.no_utilisateur != e.no_utilisateur WHERE e.no_utilisateur =:no_utilisateur AND date_debut_encheres <= GETDATE() AND date_fin_encheres >= GETDATE()";
		Map<String, Object>params = new HashMap<>();
		params.put("no_utilisateur", utilisateur.getNoUtilisateur());
		return npJdbcTemplate.query(FIND_BY_ENCHERES_EN_COURS, params, new ArticleRowMapper());
	}
	
	@Override
	public List<ArticleVendu> findByMesEncheresTerminees(Utilisateur utilisateur) {
		String FIND_BY_ENCHERES_EN_COURS = "SELECT av. * FROM ARTICLES_VENDUS av INNER JOIN ENCHERES e ON av.no_article = e.no_article AND "
				+ "av.no_utilisateur != e.no_utilisateur  INNER JOIN UTILISATEURS u ON av.no_utilisateur =:no_utilisateur "
				+ "WHERE e.no_utilisateur = 2 AND av.date_debut_encheres < GETDATE() AND av.date_fin_encheres <= GETDATE()";
		Map<String, Object>params = new HashMap<>();
		params.put("no_utilisateur", utilisateur.getNoUtilisateur());
		return npJdbcTemplate.query(FIND_BY_ENCHERES_EN_COURS, params, new ArticleRowMapper());
	}
	
	
	
	@Override
	public void update(ArticleVendu articleVendu) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("nom_article", articleVendu.getNomArticle());
        paramSrc.addValue("description", articleVendu.getDescription());
        paramSrc.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
        paramSrc.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
        paramSrc.addValue("prix_initial", articleVendu.getMiseAPrix());
        paramSrc.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
        paramSrc.addValue("no_article", articleVendu.getNoArticle());
        
        System.out.println("Mise à jour de l'article");
        System.out.println("Valeurs de paramSrc : " + paramSrc.toString());
        
        npJdbcTemplate.update(UPDATE, paramSrc);
	}

	@Override
	public void updatePrixVente(ArticleVendu articleVendu, Integer nouveauPrixVente) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("prix_vente", nouveauPrixVente);
		paramSrc.addValue("no_article", articleVendu.getNoArticle());
		
		System.out.println("Mise à jour de prixVente dans la table ARTICLES_VENDUS en base de données");
		npJdbcTemplate.update(UPDATE_PRIX_VENTE, paramSrc);
		
	}

	

	

	




	
	
}
