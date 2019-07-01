package org.formation.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.formation.metier.Adherent;
import org.formation.metier.view.JsonViews;
import org.formation.repository.AdherentRepository;
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
@RequestMapping("/rest/adherent")
public class AdherentRestController {

	
	@Autowired
	AdherentRepository adherentRepository;

	@JsonView(JsonViews.Common.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Adherent>> findAll() {
		return list();
	}

	@JsonView(JsonViews.AdherentAvecArticle.class)
	@GetMapping(value = { "/article" })
	public ResponseEntity<List<Adherent>> findAllWithArticle() {
		return list();
	}

	ResponseEntity<List<Adherent>> list() {
		return new ResponseEntity<List<Adherent>>(adherentRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> create(@Valid @RequestBody Adherent adherent, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		adherentRepository.save(adherent);
		HttpHeaders headers = new HttpHeaders();
		URI uri = uCB.path("/rest/adherent/{numero}").buildAndExpand(adherent.getNumero()).toUri();
		headers.setLocation(uri);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(headers,HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping("/{numero}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Adherent> findById(@PathVariable(name="numero") Integer id) {
 return findAdherentById(id);
	}

	@GetMapping("/{numero}/article")
	@JsonView(JsonViews.AdherentAvecArticle.class)
	public ResponseEntity<Adherent> findByIdWithArticle(@PathVariable(name="numero") Integer id) {
		return findAdherentById(id);
	}

	private ResponseEntity<Adherent> findAdherentById(Integer id) {
		Optional<Adherent> opt = adherentRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Adherent>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Adherent>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{numero}")
	public ResponseEntity<Void> Update(@PathVariable (name="numero") Integer id ,@RequestBody Adherent adherent, BindingResult br) {
		Optional<Adherent> opt = adherentRepository.findById(id);
		if (opt.isPresent()) {
			Adherent adherentEnBase=opt.get();
			adherentEnBase.setPrenom((adherent.getPrenom()) !=null ? adherent.getPrenom() : null);
			adherentEnBase.setNom((adherent.getNom()) !=null ? adherent.getNom() : null);
			adherentEnBase.setCivilite((adherent.getCivilite()) !=null ? adherent.getCivilite() : null);
			adherentEnBase.setAdresse((adherent.getAdresse()) !=null ? adherent.getAdresse() : null);
			adherentEnBase.setArticlesEmpruntes(adherent.getArticlesEmpruntes());
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{numero}")
	public ResponseEntity<Void> delete(@PathVariable(name="numero") Integer id){
		Optional<Adherent> opt = adherentRepository.findById(id);
		if (opt.isPresent()) {
			adherentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
