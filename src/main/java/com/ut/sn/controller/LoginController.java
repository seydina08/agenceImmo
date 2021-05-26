package com.ut.sn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.dto.LoginDTO;
import com.ut.sn.dto.SoumettreDemandeDTO;
import com.ut.sn.service.LoginService;


@RestController
@RequestMapping("/api/login/")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/connexion")
	public ResponseEntity<?> connexion (@RequestBody LoginDTO loginDTO) {
	   String token = loginService.connect(loginDTO);
	   Map<String, Object> response = new HashMap<>();
	   response.put("success", true);
	   response.put("data", token);
	   
	   return ResponseEntity.ok(response);
	}
}
