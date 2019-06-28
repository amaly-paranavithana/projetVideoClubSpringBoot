package org.formation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.formation.metier.*;

import org.formation.repository.*;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private AdherentRepository adhrentRepository;

	@GetMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("articles/list", "articles", articleRepository.findAll());
	}

	@GetMapping("/addDvd")
	public ModelAndView addDvd() {
		return goEdit(new Dvd());
	}

	@GetMapping("/addBluray")
	public ModelAndView addBlueray() {
		return goEdit(new BluRay());
	}

	private ModelAndView goEdit(Article article) {
		ModelAndView mav = new ModelAndView("articles/edit", "article", article);
		mav.addObject("films", filmRepository.findAll());
		mav.addObject("adherents", adhrentRepository.findAll());
		return mav;
	}

	@GetMapping("/saveDvd")
	public ModelAndView saveDvd(@ModelAttribute("article") Dvd d) {
		return save(d);
	}

	@GetMapping("/saveBlueray")
	public ModelAndView saveBlueray(@ModelAttribute("article") BluRay b) {
		return save(b);
	}

	private ModelAndView save(Article article) {

		// if need si pas de converteur
		if (article.getEmprunteur() != null && article.getEmprunteur().getNumero() == null) {
			article.setEmprunteur(null);
		}
		articleRepository.save(article);
		return new ModelAndView("redirect:/articles/list");
	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(name = "numeroArticle") Integer id) {
		Optional<Article> opt = articleRepository.findById(id);
		if (opt.isPresent()) {
			return goEdit(opt.get());
		} else {
			return new ModelAndView("redirect:/articles/list");
		}
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "numeroArticle") int id) {
		Optional<Article> opt = articleRepository.findById(id);
		if (opt.isPresent()) {
			articleRepository.deleteById(id);
		}
		return "redirect:/articles/list";
	}

}
