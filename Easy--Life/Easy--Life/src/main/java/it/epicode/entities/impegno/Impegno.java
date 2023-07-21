package it.epicode.entities.impegno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import it.epicode.entities.utente.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Impegno {

	@Id
	@GeneratedValue
	private UUID id;
	private LocalDate data;
	private LocalTime ora;
	private String impegno;
	@ManyToOne
	@JsonBackReference
	private Utente utente;
	@Enumerated(EnumType.STRING)
	private StatoImpegno statoImpegno;

	public Impegno(LocalDate data, LocalTime ora, String impegno, Utente utente, StatoImpegno statoImpegno) {
		this.data = data;
		this.ora = ora;
		this.impegno = impegno;
		this.utente = utente;
		this.statoImpegno = statoImpegno;
	}

}
