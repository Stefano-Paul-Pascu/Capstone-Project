package it.epicode.entities.utente.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.payloads.UtentePayload;
import it.epicode.entities.utente.payloads.UtenteUpdatePayload;
import it.epicode.entities.utente.repository.UtenteRepository;
import it.epicode.exceptions.BadRequestException;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository utenteRepo;

	public Utente create(UtentePayload payload) {

		utenteRepo.findByEmail(payload.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email " + user.getEmail() + " già utilizzata!");
		});

		Utente u = new Utente(payload.getUsername(), payload.getEmail(), payload.getPassword(), payload.getNome(),
				payload.getCognome());

		return utenteRepo.save(u);
	}

	public Utente findByEmail(String email) {
		return utenteRepo.findByEmail(email)
				.orElseThrow(() -> new NoSuchElementException("Utente con " + email + " non trovato!"));
	}

	public Utente findById(UUID id) {
		return utenteRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Utente con id: " + id + " non trovato!"));
	}

	public List<Utente> find() {
		return utenteRepo.findAll();
	}

	public List<Utente> findAll() {
		return utenteRepo.findAll();
	}

	public List<Utente> find(String order) {
		return utenteRepo.findAll(Sort.by(order));
	}

	public Utente findByIdAndUpadate(UUID id, UtenteUpdatePayload body) {
		Utente u = findById(id);
		u.setUsername(body.getUsername());
		u.setEmail(body.getEmail());
		u.setNome(body.getNome());
		u.setCognome(body.getCognome());
		return utenteRepo.save(u);

	}

	public void findByIdAndDelete(UUID id) {
		Utente u = findById(id);

		utenteRepo.delete(u);

	}
}
