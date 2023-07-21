package it.epicode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.payloads.UtentePayload;
import it.epicode.entities.utente.repository.UtenteRepository;
import it.epicode.entities.utente.service.UtenteService;

@Component
public class UtenteRunner implements CommandLineRunner {

	@Autowired
	UtenteService utenteService;

	@Autowired
	UtenteRepository utenteRepo;

	@Autowired
	private PasswordEncoder bcrypt;

	@Override
	public void run(String... args) throws Exception {

		List<Utente> utentiDB = utenteRepo.findAll();
		if (utentiDB.isEmpty()) {

			String username = "mariorossi";
			String email = "mariorossi@gmail.com";
			String password = "1234";
			String nome = "Mario";
			String cognome = "Rossi";
			UtentePayload payload = new UtentePayload(username, email, password, nome, cognome);
			payload.setPassword(bcrypt.encode(payload.getPassword()));
			utenteService.create(payload);

			utentiDB.add(new Utente(username, email, password, nome, cognome));
		}
	}

}
