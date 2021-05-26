package com.ut.sn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ut.sn.dto.DemandeDTO;
import com.ut.sn.dto.MaisonDTO;
import com.ut.sn.dto.SoumettreDemandeDTO;
import com.ut.sn.models.Demande;
import com.ut.sn.models.Demande.Statut;
import com.ut.sn.models.Maison;
import com.ut.sn.repo.DemandeRepo;
import com.ut.sn.repo.MaisonRepo;

import io.jsonwebtoken.Claims;


@Service
public class DemandeService {
	
	@Autowired
	private MaisonRepo maisonRepo;
	
	@Autowired
	private DemandeRepo demandeRepo;
	
	@Autowired
	private SessionService sessionService;
	
	private Demande convertirDto(SoumettreDemandeDTO dto) {
		Demande demande = new Demande();
		demande.setNom(dto.getNom());
		demande.setPrenom(dto.getPrenom());
		demande.setNumTelephone(dto.getNumTelephone());
		
		Optional<Maison> optionalMaison = maisonRepo.findById(dto.getIdMaison());
		if(optionalMaison.isPresent())
		{
			Maison maison = optionalMaison.get();
			demande.setMaison(maison);
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Maison non retrouve");
		}
		
		return demande;
	}
	
	private MaisonDTO convertirMaison(Maison maison) {
		MaisonDTO maisonDTO = new MaisonDTO();
		maisonDTO.setAdresse(maison.getAdresse());
		maisonDTO.setId(maison.getId());
		maisonDTO.setNbreChambres(maison.getNbreChambre());
		maisonDTO.setStatut(maison.getStatut().name());
		
		return maisonDTO;
	}
	
	private DemandeDTO convertirDemande(Demande demande) {
		 DemandeDTO demandeDTO = new DemandeDTO();
		 demandeDTO.setId(demande.getId());
		 demandeDTO.setNom(demande.getNom());
		 demandeDTO.setPrenom(demande.getPrenom());
		 demandeDTO.setNumTelephone(demande.getNumTelephone());
		 MaisonDTO maison = convertirMaison(demande.getMaison());
		 demandeDTO.setMaison(maison);
		 
		 return demandeDTO;
				 
	}

	public void soumettre(SoumettreDemandeDTO soumettreDemandeDTO) {
		Demande demande = convertirDto(soumettreDemandeDTO);
		demandeRepo.save(demande);
	}
	
	public List<DemandeDTO> recupererListeDemande(HttpServletRequest request) {
		System.out.println("ok ok");
		Claims claims = sessionService.doFilterInternal(request);
		sessionService.verifyClaims(claims);
		List<Demande> demandes = demandeRepo.findAll();
		List<DemandeDTO> resultat = new ArrayList<>();
		for(Demande demande : demandes) {
			DemandeDTO demandeDTO = convertirDemande(demande);
			resultat.add(demandeDTO);
		}
		return resultat;
	}
	
	public DemandeDTO recupererDemande(HttpServletRequest request,long id) {
		Claims claims = sessionService.doFilterInternal(request);
		sessionService.verifyClaims(claims);
		Optional<Demande> optionalDemande = demandeRepo.findById(id);
		if(optionalDemande.isPresent()) {
			Demande demande = optionalDemande.get();
			DemandeDTO demandeDTO = convertirDemande(demande);
			return demandeDTO;
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"demande non retrouve");
		}
	
	}
	
	public void rejetterDemande(HttpServletRequest request,long id) {
		Claims claims = sessionService.doFilterInternal(request);
		sessionService.verifyClaims(claims);
		Optional<Demande> optionalDemande = demandeRepo.findById(id);
		if(optionalDemande.isPresent()) {
			Demande demande = optionalDemande.get();
			demande.setStatut(Statut.REJETTE);
			demandeRepo.save(demande);
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"demande non retrouve");
		}
	}
	
	public void validerDemande(HttpServletRequest request,long id) {
		Claims claims = sessionService.doFilterInternal(request);
		sessionService.verifyClaims(claims);
		Optional<Demande> optionalDemande = demandeRepo.findById(id);
		if(optionalDemande.isPresent()) {
			Demande demande = optionalDemande.get();
			demande.setStatut(Statut.VALIDE);
			demandeRepo.save(demande);
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"demande non retrouve");
		}
	}
}
