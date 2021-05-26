package com.ut.sn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ut.sn.dto.LoginDTO;
import com.ut.sn.models.Agence;
import com.ut.sn.repo.AgenceRepo;
import com.ut.sn.utils.PasswordEncoder;

@Service
public class LoginService {
	@Autowired
	private AgenceRepo agenceRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionService sessionService;
	
	public String connect(LoginDTO loginDTO) {
		String login = loginDTO.getLogin();
		Optional<Agence> optionalAgence = agenceRepo.findByLogin(login);
		if(optionalAgence.isPresent()) {
			Agence agence = optionalAgence.get();
			boolean correct = passwordEncoder.isPasswordValid(loginDTO.getPassword(), agence.getPassword());
			if(correct) {
				String token = sessionService.getJWTToken(agence);
				return token;
			}
			else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login ou mot de passe incorrect.");
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login ou mot de passe incorrect.");
		}
	}
	

}
