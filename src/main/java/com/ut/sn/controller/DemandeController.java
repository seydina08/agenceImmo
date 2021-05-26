package com.ut.sn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.dto.DemandeDTO;
import com.ut.sn.dto.SoumettreDemandeDTO;
import com.ut.sn.exception.ResourceNotFoundException;
import com.ut.sn.service.DemandeService;

@RestController
@RequestMapping("/api/demande/")
public class DemandeController {

	@Autowired
	private DemandeService demandeService;
	
	
	@PostMapping("/soumettre")
	public ResponseEntity<?> soumettreDemande (@RequestBody SoumettreDemandeDTO soumettreDemandeDTO) {
	   demandeService.soumettre(soumettreDemandeDTO);
	   Map<String, Object> response = new HashMap<>();
	   response.put("success", true);
	   response.put("data", null);
	   
	   return ResponseEntity.ok(response);
	}
	
	@GetMapping("/liste")
	public ResponseEntity<?> recupererListeDemandes(HttpServletRequest request) {
		List<DemandeDTO> resultat = demandeService.recupererListeDemande(request);
		 Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("data", resultat);  
		 return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> recupererDemande(@PathVariable("id") long id,HttpServletRequest request) {
		 DemandeDTO resultat = demandeService.recupererDemande(request,id);
		 Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("data", resultat);  
		 return ResponseEntity.ok(response);
	}
	
	@PutMapping("/rejetter/{id}")
	public ResponseEntity<?> rejetterDemande(@PathVariable("id") long id, HttpServletRequest request) {
		 demandeService.rejetterDemande(request,id);
		 Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("data", null);  
		 return ResponseEntity.ok(response);
	}
	
	@PutMapping("/valider/{id}")
	public ResponseEntity<?> validerDemande(HttpServletRequest request,@PathVariable("id") long id) {
		 demandeService.validerDemande(request,id);
		 Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("data", null);  
		 return ResponseEntity.ok(response);
	}
}
