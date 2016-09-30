/**
 * 
 */
package com.online.notepad.test.main;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.online.notepad.dao.NotesDAOImpl;
import com.online.notepad.model.Folder;
import com.online.notepad.model.Notes;
import com.online.notepad.model.User;

/**
 * @author Syamu
 * 
 */
public class NotepadMainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				"springrest-servlet.xml");

		NotesDAOImpl notesDAOImpl = (NotesDAOImpl) context
				.getBean(NotesDAOImpl.class);
*/
		NotesDAOImpl notesDAOImpl= new NotesDAOImpl();
		
		DataSource dataSource=null;
		try {
			dataSource = DataSourceConfig.getDataSource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    notesDAOImpl = new NotesDAOImpl(dataSource);
		Notes notes = new Notes();
		notes.setName("Sample CV");
		notes.setContent("Skils Education Experience Summary");
		notes.setType(".txt");
		notes.setStatus("Active");
		notes.setFolder(new Folder());
		notes.getFolder().setId(1);
		notes.setUser(new User());
		notes.getUser().setId(1);

		System.out.println("------Records Creation--------");
		
		System.out.println("------Records Creation--------"+notes);
		//notesDAOImpl.saveOrUpdate(notes);

		System.out.println("------Listing Multiple Records--------");
		List<Notes> notesList = notesDAOImpl.list();
		for (Notes record : notesList) {
			System.out.print("\n =============\n ID : " + record.getId());
			System.out.print(", Name : " + record.getName()+"\n =============\n");
		}
		
		System.out.println("------getById --------");
		notes = new Notes();
		notes = notesDAOImpl.getById(1);
		System.out.print("\n =============\n ID : " + notes.toString());

		System.out.println("----Updating Record with ID = 2 -----");
		/*notes.setId(1);
		notesDAOImpl.saveOrUpdate(notes);
*/
	}

}
