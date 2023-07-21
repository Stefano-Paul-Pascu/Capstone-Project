package it.epicode.entities.impegno.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.entities.impegno.Impegno;
import it.epicode.entities.impegno.payloads.ImpegnoPayload;
import it.epicode.entities.impegno.service.ImpegnoService;
import it.epicode.exceptions.BadRequestException;

@RestController
@RequestMapping("/impegni")
public class ImpegnoController {

	@Autowired
	ImpegnoService impegnoService;

	@GetMapping("/{id}")
	public Impegno findById(@PathVariable UUID id) {
		return impegnoService.findById(id);
	}

	@PutMapping("/{id}")
	public Impegno updateById(@PathVariable UUID id, @RequestBody(required = false) ImpegnoPayload payload,
			@RequestParam(required = false) String statoImpegno) {
		if (payload != null) {
			return impegnoService.findByIdAndUpadate(id, payload);
		} else if (statoImpegno != null) {
			return impegnoService.findByIdAndUpdateStatoImpegno(id, statoImpegno);
		} else {
			throw new BadRequestException("Body o Parametro necessario!");
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable UUID id) {
		impegnoService.findByIdAndDelete(id);
	}

	@PostMapping("")
	public Impegno createIndirizzo(@RequestBody ImpegnoPayload payload) {
		return impegnoService.create(payload);
	}

	@GetMapping("")
	public List<Impegno> findAll(@RequestParam(defaultValue = "id") String ordinamento) {
		return impegnoService.findAll(ordinamento);
	}

}
