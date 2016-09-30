/**
 * 
 */
package com.online.notepad.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.notepad.dao.NotesDAO;
import com.online.notepad.model.Notes;

/**
 * @author Syamu
 *
 */
@Service
public class NotepadServiceImpl implements NotepadService {
	
	@Autowired
	private NotesDAO notesDAO;

	@Override
	public void saveOrUpdate(Notes notes) {
		notesDAO.saveOrUpdate(notes);		
	}

	@Override
	public void delete(int id) {
		 notesDAO.delete(id);
		
	}

	@Override
	public Notes getById(int id) {
		return notesDAO.getById(id);
	}

	@Override
	public List<Notes> list() {
		return notesDAO.list();
	}

	
	@Override
	public List<Notes> search(String searchString) {
		return notesDAO.search(searchString);
	}


}
