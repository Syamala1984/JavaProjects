package com.online.notepad.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.online.notepad.model.Notes;

/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
@Repository
public interface NotesDAO {
	
	public void saveOrUpdate(Notes notes);
	
	public void delete(int id);
	
	public Notes getById(int id);
	
	public List<Notes> list();

	public List<Notes> search(String searchString);
}
