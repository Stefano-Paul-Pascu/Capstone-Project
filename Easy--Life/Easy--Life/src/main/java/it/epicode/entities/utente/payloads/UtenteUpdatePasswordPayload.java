package it.epicode.entities.utente.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UtenteUpdatePasswordPayload {
	private String email;
	private String currentPassword;
	private String newPassword;
}
