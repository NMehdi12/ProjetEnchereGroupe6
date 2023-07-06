package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.groupe6.bo.Utilisateur;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	private final static String SELECT_ALL = "select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from UTILISATEURS";
	private static final String FIND_BY_NO_USER = "select * from UTILISATEURS where no_utilisateur=:no_utilisateur";
	private static final String FIND_BY_PSEUDO = "select * from UTILISATEURS where pseudo=:pseudo";
	private static final String INSERT = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur)";

	private static final String UPDATE = "update UTILISATEURS set pseudo=:pseudo, nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, code_postal=:code_postal, ville=:ville, mot_de_passe=:mot_de_passe where no_utilisateur=:no_utilisateur";
//  private final static String DELETE = "delete from UTILISATEURS where no_utilisateur= :noUtilisateur";
    private final static String DELETE = "delete from UTILISATEURS where no_utilisateur= ?";
	private final static String FIND_NO_USER_BY_PSEUDO = "select no_utilisateur from UTILISATEURS where pseudo = :pseudo";
	private final static String FIND_NO_USER_BY_EMAIL = "select * from UTILISATEURS where email = :email";
	private final static String CREDITER = "update UTILISATEURS set credit = (select credit from UTILISATEURS where no_utilisateur = (select no_utilisateur from ENCHERES where no_article = :no_article)) + :credit where no_utilisateur = (select no_utilisateur from ENCHERES where no_article = :no_article)";
	private final static String DEBITER = "BEGIN TRANSACTION; update UTILISATEURS set credit = (select credit from UTILISATEURS where no_utilisateur = :no_utilisateur) - :credit where no_utilisateur = :no_utilisateur";




	private NamedParameterJdbcTemplate njt;

	public UtilisateurDAOImpl(NamedParameterJdbcTemplate njt) {
		this.njt = njt;
	}

	class UtilisateurRowMapper implements RowMapper<Utilisateur> {

		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
			utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

			return utilisateur;
		}
	}

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs = njt.query(SELECT_ALL, new UtilisateurRowMapper());
		System.out.println("Liste des utilisateurs :  " + utilisateurs);
		return utilisateurs;

	}

	@Override
	public Utilisateur findById(Integer noUtilisateur) {

		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_utilisateur", noUtilisateur);
		Utilisateur utilisateur = njt.queryForObject(FIND_BY_NO_USER, paramSrc, new UtilisateurRowMapper());
		System.out.println("passe par findByNoUtilisateur");
		return utilisateur;
	}

	@Override
	public Utilisateur findByPseudo(String pseudo) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", pseudo);
		List<Utilisateur> utilisateurs = njt.query(FIND_BY_PSEUDO, paramSrc, new UtilisateurRowMapper());

		if (utilisateurs.isEmpty()) {
			System.out.println("passe par findbyPseudo utilisateur n'existe pas");
			return null;
		} else {
			System.out.println("passe par findbyPseudo l'utilisateur existe");
			return utilisateurs.get(0);
		}
	}

	@Override
	public void save(Utilisateur utilisateur) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", utilisateur.getPseudo());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("prenom", utilisateur.getPrenom());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("email", utilisateur.getEmail());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("telephone", utilisateur.getTelephone());
		paramSrc.addValue("rue", utilisateur.getRue());
		paramSrc.addValue("code_postal", utilisateur.getCodePostal());
		paramSrc.addValue("ville", utilisateur.getVille());
		paramSrc.addValue("mot_de_passe", utilisateur.getMotDePasse());
		paramSrc.addValue("credit", 1000);
		paramSrc.addValue("administrateur", false);
		njt.update(INSERT, paramSrc);
		System.out.println("passe par le save UtilisateurDAOimpl");
	}

	@Override
	public void update(Utilisateur utilisateur) {
		System.out.println("passe par le update UtilisateurDAOimpl");
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", utilisateur.getPseudo());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("prenom", utilisateur.getPrenom());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("email", utilisateur.getEmail());
		paramSrc.addValue("nom", utilisateur.getNom());
		paramSrc.addValue("telephone", utilisateur.getTelephone());
		paramSrc.addValue("rue", utilisateur.getRue());
		paramSrc.addValue("code_postal", utilisateur.getCodePostal());
		paramSrc.addValue("ville", utilisateur.getVille());
		paramSrc.addValue("mot_de_passe", utilisateur.getMotDePasse());
		paramSrc.addValue("no_utilisateur", utilisateur.getNoUtilisateur());
		// paramSrc.addValue("credit", 0);
		// paramSrc.addValue("administrateur", false);
		njt.update(UPDATE, paramSrc);
		System.out.println("passe par le update UtilisateurDAOimpl");
		System.out.println("Valeurs de paramSrc : " + paramSrc.toString());
		System.out.println(utilisateur);
	}

	@Override
    public void delete(Integer noUtilisateur) {
//      Utilisateur utilisateur = new Utilisateur();
//      utilisateur.setNoUtilisateur(noUtilisateur);
//      njt.update(DELETE, new BeanPropertySqlParameterSource(utilisateur));
        njt.getJdbcOperations().update(DELETE, noUtilisateur);
    }

	@Override
	public Integer findNoUtilisateurByPseudo(String pseudo) {
		Integer noUtilisateur;
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("pseudo", pseudo);

		noUtilisateur = njt.queryForObject(FIND_NO_USER_BY_PSEUDO, paramSrc, Integer.class);
		return noUtilisateur;
	}

	@Override
	public Utilisateur findByEmail(String email) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("email", email);
		List<Utilisateur> utilisateurs = njt.query(FIND_NO_USER_BY_EMAIL, paramSrc, new UtilisateurRowMapper());

		if (utilisateurs.isEmpty()) {
			System.out.println("passe par findbyEmail utilisateur n'existe pas");
			return null;
		} else {
			System.out.println("passe par findbyEmail l'utilisateur existe");
			return utilisateurs.get(0);
		}
	}

	@Override
	public void crediter(Integer nouveauMontant, Integer noArticle) {
		
		
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("credit", nouveauMontant);
		paramSrc.addValue("no_article", noArticle);
		
		System.out.println("Je passe par la méthode crediter() de UtilisateurDAOImpl");
		
		njt.update(CREDITER, paramSrc);
	}

	@Override
	public void debiter(Integer nouveauMontant, Authentication authentication) {
		String pseudo = authentication.getName();
		Integer noUtilisateur = findNoUtilisateurByPseudo(pseudo);
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("credit", nouveauMontant);
		paramSrc.addValue("no_utilisateur", noUtilisateur);
		
		System.out.println("Je passe par la méthode debiter() de UtilisateurDAOImpl");
		
		njt.update(DEBITER, paramSrc);
		
	}
}
