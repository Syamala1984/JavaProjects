package com.online.notepad.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.online.notepad.model.Notes;
import com.online.notepad.service.NotepadService;
import com.online.notepad.validator.NoteFormValidator;

/**
 * 
 * @author Syamu
 * 
 */
@Controller
public class NotepadController {

	final static Logger logger = Logger.getLogger(NotepadController.class);
	
	@Autowired
	private NotepadService notepadService;
	
	@Autowired
	NoteFormValidator noteFormValidator;

	//Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(noteFormValidator);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveNote(@ModelAttribute("notes") @Validated Notes notes,
			BindingResult result,final RedirectAttributes redirectAttributes) {
		logger.info("\n NotepadController save  ::::::::::::::  ");
		logger.info("\n========  " + notes + " \n Note Id:" + notes.getId());

		if (result.hasErrors()) {
			return new ModelAndView("addNote");
		} else {
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("css", "success");
			if (notes.getId() == null) {
				redirectAttributes.addFlashAttribute("msg",
						"Note added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg",
						"Notes updated successfully!");
			}

			notepadService.saveOrUpdate(notes);
			logger.info("====END OF SAVE====\n");
			return new ModelAndView("redirect:/list.do");
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listNotes() {
		logger.info("\n==============allNotes=====================");
		ModelAndView modelAndView = new ModelAndView("notesList", "allNotes",
				notepadService.list());
		logger.info("\n==============allNotes \n============"
				+ modelAndView.toString());
		logger.info("\n==============END OF listNotes=====================");
		return modelAndView;

	}

	@RequestMapping(value = "/addNote", method = RequestMethod.GET)
	public ModelAndView addNote(@ModelAttribute("command") Notes notes,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("addNote", "notes", new Notes());
		return  modelAndView;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteNotes(@ModelAttribute Notes notes) {
		logger.info("\nDELETE :::::::::::::::  "+notes.getId());
		notepadService.delete(notes.getId());
		return new ModelAndView("redirect:/list.do");
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateNotes(@ModelAttribute("command") Notes notes,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("addNote", "notes",
				notepadService.getById(notes.getId()));
		logger.info("\n UPDATE BY ID :: \n" + modelAndView.toString());
		return modelAndView;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@ModelAttribute("command")Notes notes,BindingResult result) {
			return new ModelAndView("search");
	}
	
	@RequestMapping(value = "/doSearch", method = RequestMethod.POST)
	public ModelAndView searchResults(@ModelAttribute("command")Notes notes,BindingResult result) {
			
		return new ModelAndView("search", "allNotes",notepadService.search(notes.getSearchString()));

	}
	/*
	 * @RequestMapping(value = "/search", method = RequestMethod.GET) public
	 * ModelAndView search(@RequestParam(value="searchString") String
	 * searchString,BindingResult result) {
	 * logger.info("SEARCH  ::  "+searchString); if(searchString == null ||
	 * searchString.equals("")){ return new ModelAndView("search"); } else{
	 * return new
	 * ModelAndView("search","allNotes",notepadService.search(searchString)); }
	 */
}
