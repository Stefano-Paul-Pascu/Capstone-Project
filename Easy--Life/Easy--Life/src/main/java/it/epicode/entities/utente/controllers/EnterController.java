package it.epicode.entities.utente.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.entities.utente.Utente;
import it.epicode.entities.utente.payloads.LoginPayload;
import it.epicode.entities.utente.payloads.TokenPayload;
import it.epicode.entities.utente.payloads.UtentePayload;
import it.epicode.entities.utente.service.UtenteService;
import it.epicode.exceptions.UnauthorizedException;
import it.epicode.security.JwtTools;

@RestController
@RequestMapping("/auth")
public class EnterController {

	@Autowired
	UtenteService utenteservice;

	@Autowired
	private PasswordEncoder bcrypt;

	@PostMapping("/login")
	public ResponseEntity<TokenPayload> login(@RequestBody LoginPayload body) {

		Utente u = utenteservice.findByEmail(body.getEmail());

		String plainPW = body.getPassword();
		String hashedPW = u.getPassword();

		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");

		String token = JwtTools.createToken(u);

		return new ResponseEntity<>(new TokenPayload(token, u), HttpStatus.OK);
	}

	@PostMapping("/register")
	public Utente register(@RequestBody UtentePayload payload) {
		payload.setPassword(bcrypt.encode(payload.getPassword()));
		return utenteservice.create(payload);
	}
}
