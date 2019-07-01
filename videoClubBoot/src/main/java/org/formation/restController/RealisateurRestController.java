package org.formation.restController;

import java.net.URI;
import java.util.*;

import javax.validation.Valid;

import org.formation.metier.*;
import org.formation.metier.view.*;
import org.formation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping("rest/realisateur")
public class RealisateurRestController {
	
	@Autowired
	private RealisateurRepository realisateurRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Realisateur>> findAll() {
		return list();
	}
	
	ResponseEntity<List<Realisateur>> list() {
		return new ResponseEntity<List<Realisateur>>(realisateurRepository.findAll(), HttpStatus.OK);
	}
	

	@PostMapping(value = { "", "/" })
	public ResponseEntity<Void> create(@Valid @RequestBody Realisateur realisateur, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		realisateurRepository.save(realisateur);
		HttpHeaders headers = new HttpHeaders();
		// on obtient le chemin complet vers le soldat
		URI uri = uCB.path("/rest/realisateur/{id}").buildAndExpand(realisateur.getId()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Realisateur> findById(@PathVariable(name = "id") Integer id) {
		return findRealisateurById(id);
	}
	
	public ResponseEntity<Realisateur> findRealisateurById(Integer id) {
		Optional<Realisateur> opt = realisateurRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Realisateur>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable(name = "id") Integer id, @RequestBody Realisateur realisateur) {
		Optional<Realisateur> opt = realisateurRepository.findById(id);
		if (opt.isPresent()) {
			Realisateur realisateurEnBase = opt.get();
			realisateurEnBase.setPrenom((realisateur.getPrenom() != null) ? realisateur.getPrenom() : realisateurEnBase.getPrenom());

			realisateurEnBase.setNom((realisateur.getNom() != null) ? realisateur.getNom() : realisateurEnBase.getNom());
			

			realisateurEnBase.setFilms(realisateur.getFilms());

			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	
}
