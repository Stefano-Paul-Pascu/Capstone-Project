package it.epicode.entities.impegno.payloads;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.Data;

@Data

public class ImpegnoPayload {

	private LocalDate data;
	private LocalTime ora;
	private String impegno;
	private UUID idUtente;
	private String statoImpegno;
}
