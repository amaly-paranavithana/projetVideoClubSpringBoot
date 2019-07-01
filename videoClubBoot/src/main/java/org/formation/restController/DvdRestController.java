package org.formation.restController;

import java.net.URI;
import java.util.*;

import javax.validation.Valid;

import org.formation.metier.Article;
import org.formation.metier.Dvd;
import org.formation.metier.view.JsonViews;
import org.formation.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/rest/dvd")
public class DvdRestController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value = {"", "/"})
	public ResponseEntity<List<Article>> findAll() {
		return list();
	}
	
	private ResponseEntity<List<Article>> list() {
		return new ResponseEntity<List<Article>>(articleRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value= {"", "/"})
	public ResponseEntity<Void> create(@Valid @RequestBody Dvd dvd, BindingResult br, UriComponentsBuilder ucb) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		articleRepository.save(dvd);
		HttpHeaders headers = new HttpHeaders();
		URI uri = ucb.path("/rest/article/{id}").buildAndExpand(dvd.getNumeroArticle()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<> (headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Dvd> findById(@PathVariable(name="numero_article") Integer id) {
		return findById(id);
	}

	
	@JsonView(JsonViews.ArticleAvecBluRay.class)
	public ResponseEntity<Article> findArticleById(Integer id) {
		Optional<Article> opt = articleRepository.findById(id);
		if(opt.isPresent()) {
			return new ResponseEntity<Article>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name="numero_article") Integer id, @Valid @RequestBody Dvd dvd, BindingResult br) {
		Optional<Article> opt = articleRepository.findById(id);
		if (opt.isPresent()) {
			Article articleEnBase = opt.get();
			articleEnBase.setNbDisques((dvd.getNbDisques() != null) ? dvd.getNbDisques():articleEnBase.getNbDisques());
			articleEnBase.setFilm((dvd.getFilm() != null) ? dvd.getFilm():articleEnBase.getFilm());
			articleEnBase.setEmprunteur((dvd.getEmprunteur() != null) ? dvd.getEmprunteur(): articleEnBase.getEmprunteur());
			articleRepository.save(articleEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name="numero_article") Integer id) {
		Optional<Article> opt = articleRepository.findById(id);
		if(opt.isPresent()) {
			articleRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
