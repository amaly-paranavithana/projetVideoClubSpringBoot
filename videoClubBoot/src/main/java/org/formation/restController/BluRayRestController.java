package org.formation.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.formation.metier.Adherent;
import org.formation.metier.Article;
import org.formation.metier.BluRay;
import org.formation.metier.view.JsonViews;
import org.formation.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/rest/bluRay")
public class BluRayRestController {

	@Autowired
	ArticleRepository articleRepository;

//	@JsonView(JsonViews.Common.class)
//	@GetMapping(value = { "", "/" })
//	public ResponseEntity<List<BluRay>> findAll() {
//		return list();
//	}

//	@JsonView(JsonViews.AdherentAvecArticle.class)
//	@GetMapping(value = { "/article" })
//	public ResponseEntity<List<Adherent>> findAllWithArticle() {
//		return list();
//	}

	
//	ResponseEntity<List<Adherent>> list() {
//		return new ResponseEntity<List<Adherent>>(adherentRepository.findAll(), HttpStatus.OK);
//	}

	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> create(@Valid @RequestBody BluRay bluRay, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		articleRepository.save(bluRay);
		HttpHeaders headers = new HttpHeaders();
		URI uri = uCB.path("/rest/bluRay/{numeroArticle}").buildAndExpand(bluRay.getNumeroArticle()).toUri();
		headers.setLocation(uri);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping("/{numeroArticle}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<BluRay> findById(@PathVariable(name = "numeroArticle") Integer id) {
		return findArticleById(id);
	}

	@GetMapping("/{numero}/article")
	@JsonView(JsonViews.AdherentAvecArticle.class)
	public ResponseEntity<Adherent> findByIdWithArticle(@PathVariable(name = "numero") Integer id) {
		return findAdherentById(id);
	}

	private ResponseEntity<Article> findArticleById(Integer id) {
		Optional<Article> opt = articleRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Article>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{numeroArticle}")
	public ResponseEntity<Void> Update(@PathVariable(name = "numeroArticle") Integer id, @RequestBody BluRay bluRay,
			BindingResult br) {
		Optional<BluRay> opt = articleRepository.findById(id);
		if (opt.isPresent()) {
			BluRay bluRayEnBase = opt.get();
			bluRayEnBase.setFilm((bluRay.getFilm()) != null ? bluRay.getFilm() : null);
			bluRayEnBase.setNbDisques((bluRay.getNbDisques()) != null ? bluRay.getNbDisques() : null);
			bluRayEnBase.setTroisD((bluRay.getTroisD()) != null ? bluRay.getTroisD() : null);
			bluRayEnBase.setEmprunteur((bluRay.getEmprunteur()) != null ? bluRay.getEmprunteur() : null);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{numeroArticle}")
	public ResponseEntity<Void> delete(@PathVariable(name = "numeroArticle") Integer id) {
		Optional<BluRay> opt = articleRepository.findById(id);
		if (opt.isPresent()) {
			adherentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
