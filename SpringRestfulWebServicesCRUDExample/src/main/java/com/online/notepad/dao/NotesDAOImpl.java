package com.online.notepad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.online.notepad.model.Notes;

/**
 * An implementation of the ContactDAO interface.
 * 
 * @author www.codejava.net
 * 
 */
@Repository
public class NotesDAOImpl implements NotesDAO {

	final static Logger logger = Logger.getLogger(NotesDAOImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	/**
	 * @param dataSource
	 *            the dataSource to set
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public NotesDAOImpl() {
	}

	public NotesDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("\n DataSource  :: " + dataSource
				+ "\n jdbcTemplate   ::  " + jdbcTemplate);
	}

	@Override
	public void saveOrUpdate(Notes notes) {
		if (notes.getId() != null && notes.getId() > 0) {
			// update
			logger.info("\n______________ UPDATE NOTE _____________");
			logger.info("\n______________ NOTE ID_____________" + notes.getId());

			/*
			 * String sql = "UPDATE notes SET name=?, content=?, type=?, " +
			 * "folder_id=? ,user_id=?, status=? WHERE id=?";
			 */
			String sql = "UPDATE notes SET content=? WHERE id=?";
			jdbcTemplate.update(sql, notes.getContent(), notes.getId());

		} else {
			logger.info("\n______________ INSERT NOTE _____________");
			// insert
			String sql = "INSERT INTO notes (name, content, type, folder_id,user_id,status)"
					+ " VALUES (?,?,?,?,?,?)";
			logger.info("\n===SQL  ::  " + sql + "\n===" + notes.getName()
					+ "\n" + notes.getContent() + "\n" + notes.getType() + "\n"
					+ notes.getFolder().getId() + "\n"
					+ notes.getUser().getId());
			jdbcTemplate.update(sql, notes.getName(), notes.getContent(), notes
					.getType(), notes.getFolder().getId(), notes.getUser()
					.getId(), "Active");

		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM notes WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Notes> list() {
		String sql = "SELECT * FROM notes";
		logger.info("\nlist  jdbcTemplate:  " + jdbcTemplate);
		List<Notes> listContact = jdbcTemplate.query(sql,
				new RowMapper<Notes>() {

					@Override
					public Notes mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Notes notes = new Notes();

						notes.setId(rs.getInt("id"));
						notes.setName(rs.getString("name"));
						notes.setContent(rs.getString("content"));
						notes.setType(rs.getString("type"));
						notes.setStatus(rs.getString("status"));

						return notes;
					}

				});

		return listContact;
	}

	@Override
	public Notes getById(int id) {
		String sql = "SELECT * FROM notes WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Notes>() {

			@Override
			public Notes extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Notes notes = new Notes();
					notes.setId(rs.getInt("id"));
					notes.setName(rs.getString("name"));
					notes.setContent(rs.getString("content"));
					notes.setType(rs.getString("type"));
					notes.setStatus(rs.getString("status"));

					return notes;
				}

				return null;
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.dao.NotesDAO#search(java.lang.String)
	 */
	@Override
	public List<Notes> search(String searchString) {
		Pattern pattern = null;
		Matcher matcher = null;
		List<Notes> searchResultList = null;
		logger.info("RegExValidation :: Search  ::-----------------::"
				+ searchString);
		
		if (searchString != null)
			pattern = Pattern.compile(".*?\\b" + searchString + "\\b.*?",Pattern.CASE_INSENSITIVE);
		
		logger.info("\n PATTERN  ::" + pattern.toString());

		for (Notes note : this.list()) {
			logger.info("\n INSIDE SEARCH LOOP  ::" + note);
			matcher = pattern.matcher(note.toString());
			if (matcher.matches()) {
				logger.info("\n MATCHER SEARCH LOOP  ::" + matcher);
				searchResultList = new ArrayList<Notes>();
				searchResultList.add(note);
			}
		}
		logger.info("\nresultList  ::" + searchResultList);
		return searchResultList;
	}

}
