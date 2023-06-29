package fr.eni.enchere.groupe6.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.groupe6.bo.Categorie;


@Repository
public class CategorieDAOImpl implements CategorieDAO {
	
	private static final String FIND_ALL = "SELECT * FROM CATEGORIES";
	private static final String FIND_BY_ID = "select * from categories where id=:id";
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	@Override
	public List<Categorie> findAll() {
		List<Categorie> categories = npJdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Categorie.class));
		return categories;
	}

	@Override
	public Categorie findById (Integer noCategorie) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", noCategorie);
		
		Categorie categorie = null;
		try {
			categorie = npJdbcTemplate.queryForObject(FIND_ALL, params,new CetegorieRowMapper());
		} catch (Exception e) {
			
		}
			return categorie;
	}

	@Override
	public void save(Categorie categorie) {
		
		
	}

	@Override
	public void delete(Categorie categorie) {
		
		
	}
	
	class CetegorieRowMapper implements RowMapper<Categorie>{

		@Override
		public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Categorie categorie = new Categorie();
			
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			
			return categorie;
		}
		
	}
	

}
