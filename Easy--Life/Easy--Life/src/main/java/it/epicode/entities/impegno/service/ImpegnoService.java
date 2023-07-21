package it.epicode.entities.impegno.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.entities.impegno.Impegno;
import it.epicode.entities.impegno.StatoImpegno;
import it.epicode.entities.impegno.payloads.ImpegnoPayload;
import it.epicode.entities.impegno.repository.ImpegnoRepository;
import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.service.UtenteService;
import it.epicode.exceptions.BadRequestException;

@Service

public class ImpegnoService {

	@Autowired
	private ImpegnoRepository impegnoRepo;
	@Autowired
	private UtenteService us;

	public Impegno create(LocalDate data, LocalTime ora, String impegno, Utente utente, StatoImpegno statoImpegno) {

		Impegno i = new Impegno(data, ora, impegno, utente, statoImpegno);
		return impegnoRepo.save(i);
	}

	public List<Impegno> find() {
		return impegnoRepo.findAll();
	}

	public List<Impegno> findAll(String ordinamento) {
		Sort sort = Sort.by(ordinamento);
		return impegnoRepo.findAll(sort);
	}

	public Impegno findById(UUID id) {
		return impegnoRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Impegno non trovato!"));
	}

	public Impegno findByIdAndUpadate(UUID id, ImpegnoPayload payload) {
		Impegno i = findById(id);
		i.setData(payload.getData());
		i.setOra(payload.getOra());
		i.setImpegno(payload.getImpegno());
		Utente u = us.findById(payload.getIdUtente());
		i.setUtente(u);
		i.setStatoImpegno(StatoImpegno.valueOf(payload.getStatoImpegno()));
		return impegnoRepo.save(i);
	}

	public void findByIdAndDelete(UUID id) {
		Impegno i = findById(id);

		impegnoRepo.delete(i);
	}

	public Impegno create(ImpegnoPayload payload) {
		Utente u = us.findById(payload.getIdUtente());
		Impegno i = new Impegno(payload.getData(), payload.getOra(), payload.getImpegno(), u,
				StatoImpegno.valueOf(payload.getStatoImpegno()));
		return impegnoRepo.save(i);
	}

	public Page<Impegno> findByData(int page, String order, LocalDate data) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(order));
		return impegnoRepo.findByData(pagina, data);
	}

	public Impegno findByIdAndUpdateStatoImpegno(UUID id, String statoImpegno) {
		Impegno found = this.findById(id);
		StatoImpegno stato = StatoImpegno.valueOf(statoImpegno);
		if (found.getStatoImpegno().equals(stato))
			throw new BadRequestException("L'impegno con id " + found.getId() + " è " + stato);
		found.setStatoImpegno(stato);
		return impegnoRepo.save(found);
	}

}
