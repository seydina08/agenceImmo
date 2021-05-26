package com.ut.sn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.dto.DemandeDTO;
import com.ut.sn.dto.EnregistrerMaisonDTO;
import com.ut.sn.dto.MaisonDTO;
import com.ut.sn.dto.SoumettreDemandeDTO;
import com.ut.sn.service.MaisonService;


@RestController
@RequestMapping("/api/maison/")
public class MaisonController{
	
	@Autowired
	private MaisonService maisonService;
	
	@PostMapping("/enregistrer")
	public ResponseEntity<?> enregistrerMaison (@RequestBody EnregistrerMaisonDTO enregistrerMaisonDTO) {
	  maisonService.enregistrerMaison(enregistrerMaisonDTO);
	   Map<String, Object> response = new HashMap<>();
	   response.put("success", true);
	   response.put("data", null);
	   
	   return ResponseEntity.ok(response);
	}
	
	@GetMapping("/liste")
	public ResponseEntity<?> recupererListeMaisons() {
		List<MaisonDTO> resultat = maisonService.recupererListeMaison();
		 Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("data", resultat);  
		 return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> recupererMaison(@PathVariable("id") long id) {
		MaisonDTO resultat = maisonService.recupererMaison(id);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("data", resultat);  
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> changerStatutMaison(@PathVariable("id") long id, @RequestParam("statut") String statut) {
		 maisonService.changerStatut(id, statut);
		 Map<String, Object> response = new HashMap<>();
		 response.put("success", true);
		 response.put("data", null);  
		 return ResponseEntity.ok(response);
	}
	
}