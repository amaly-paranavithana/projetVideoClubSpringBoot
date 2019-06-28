package org.formation.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.formation.metier.*;


@Controller
@RequestMapping("/adherents")
public class AdherentController {

	@Autowired
	AdherentRepository adherentRepository;

	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("adherents/list", "adherents", adherentRepository.findAll());
		// mav.addObject("articlesEmpruntes", adherentRepository.findAllWithArticles());
		return mav;
	}

	@GetMapping("/add")
	public ModelAndView add() {
		return goEdit(new Adherent());
	}

	private ModelAndView goEdit(Adherent adherent) {
		return new ModelAndView("adherents/edit", "adherents", adherent);
	}

	@GetMapping("/save")
	private ModelAndView save(@Valid @ModelAttribute("adherents") Adherent a, BindingResult br) {
		if (br.hasErrors())
			return new ModelAndView("adherents/edit");
		adherentRepository.save(a);
		return new ModelAndView("redirect:/adherents/list");
	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(name = "numero") Integer id) {
		Optional<Adherent> opt = adherentRepository.findById(id);
		if (opt.isPresent()) {
			return goEdit(opt.get());
		} else {
			return new ModelAndView("redirect:/adherents/list");
		}
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "numero") int id) {
		Optional<Adherent> opt = adherentRepository.findById(id);
		if (opt.isPresent()) {
			adherentRepository.deleteById(id);
		}
		return "redirect:/adherents/list";
	}
}
