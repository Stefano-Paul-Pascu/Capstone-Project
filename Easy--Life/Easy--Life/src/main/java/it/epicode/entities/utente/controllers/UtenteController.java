package it.epicode.entities.utente.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.payloads.UtenteUpdatePayload;
import it.epicode.entities.utente.service.UtenteService;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	@Autowired
	UtenteService utenteService;

	@GetMapping("")
	public List<Utente> findAll(@RequestParam(defaultValue = "id") String order) {
		return utenteService.find(order);
	}

	@GetMapping("/{id}")
	public Utente findById(@PathVariable UUID id) {
		return utenteService.findById(id);
	}

	@PutMapping("/{id}")
	public Utente updateById(@PathVariable UUID id, @RequestBody UtenteUpdatePayload payload) {
		return utenteService.findByIdAndUpadate(id, payload);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable UUID id) {
		utenteService.findByIdAndDelete(id);
	}

}
