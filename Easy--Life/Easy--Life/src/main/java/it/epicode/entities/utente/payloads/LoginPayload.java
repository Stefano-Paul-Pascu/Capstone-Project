package it.epicode.entities.utente.payloads;

import lombok.Data;

@Data
public class LoginPayload {
	private String email;
	private String password;
}
