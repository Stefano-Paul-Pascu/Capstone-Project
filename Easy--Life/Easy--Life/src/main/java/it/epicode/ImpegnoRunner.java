package it.epicode;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.entities.impegno.Impegno;
import it.epicode.entities.impegno.StatoImpegno;
import it.epicode.entities.impegno.repository.ImpegnoRepository;
import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.repository.UtenteRepository;
import it.epicode.entities.utente.service.UtenteService;

@Component
public class ImpegnoRunner implements CommandLineRunner {

	@Autowired
	private ImpegnoRepository impegnoRepo;

	@Autowired
	private UtenteRepository utenteRepo;

	@Autowired
	private UtenteService utenteService;

	@Override
	public void run(String... args) throws Exception {
		List<Impegno> impegniDB = impegnoRepo.findAll();

		if (impegniDB.isEmpty()) {
			LocalDate data = LocalDate.of(2023, 7, 24);
			LocalTime ora = LocalTime.of(8, 00);
			String descrizione = "Fare la spesa";
			StatoImpegno stato = StatoImpegno.DA_FARE;

			List<Utente> allUtenti = utenteService.findAll();
			if (!allUtenti.isEmpty()) {
				Utente utente = allUtenti.get(0);

				Impegno impegno = new Impegno(data, ora, descrizione, utente, stato);

				impegnoRepo.saveAndFlush(impegno);

				utente.addImpegno(impegno);
				utenteRepo.save(utente);
			}
		}
	}
}
