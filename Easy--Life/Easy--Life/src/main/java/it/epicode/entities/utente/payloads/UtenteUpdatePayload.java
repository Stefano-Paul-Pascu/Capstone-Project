package it.epicode.entities.utente.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UtenteUpdatePayload {
	private String username;
	private String email;
	private String nome;
	private String cognome;
}
