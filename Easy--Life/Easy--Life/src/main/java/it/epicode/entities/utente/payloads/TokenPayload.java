package it.epicode.entities.utente.payloads;

import it.epicode.entities.utente.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenPayload {
	private String JwtToken;
	private Utente u;
}
