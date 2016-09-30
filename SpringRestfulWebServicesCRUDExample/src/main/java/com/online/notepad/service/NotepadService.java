/**
 * 
 */
package com.online.notepad.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.online.notepad.model.Notes;

/**
 * @author Syamu
 * 
 */
@Service
public interface NotepadService {

	public void saveOrUpdate(Notes notes);
	
	public void delete(int id);
	
	public Notes getById(int id);
	
	public List<Notes> list();

	public List<Notes> search(String searchString);
}
