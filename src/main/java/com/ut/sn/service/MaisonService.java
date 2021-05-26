package com.ut.sn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ut.sn.dto.DemandeDTO;
import com.ut.sn.dto.EnregistrerMaisonDTO;
import com.ut.sn.dto.MaisonDTO;
import com.ut.sn.models.Demande;
import com.ut.sn.models.Maison;
import com.ut.sn.repo.MaisonRepo;

@Service
public class MaisonService {
  @Autowired
  private MaisonRepo maisonRepo;
  
  private Maison convertirEnMaison(EnregistrerMaisonDTO enregistrerMaisonDTO) {
	  Maison maison = new Maison();
	  maison.setAdresse(enregistrerMaisonDTO.getAdresse());
	  maison.setNbreChambre(enregistrerMaisonDTO.getNbreChambre());
	  System.out.println(enregistrerMaisonDTO.getNbreChambre());
	  return maison;
  }
  
  private MaisonDTO convertirEnMaisonDTO(Maison maison) {
	  MaisonDTO maisonDTO = new MaisonDTO();
	  maisonDTO.setAdresse(maison.getAdresse());
	  maisonDTO.setNbreChambres(maison.getNbreChambre());
	  maisonDTO.setId(maison.getId());
	  maisonDTO.setStatut(maison.getStatut().name());
	  return maisonDTO;
  }
  
  public void enregistrerMaison(EnregistrerMaisonDTO enregistrerMaisonDTO ) {
	  Maison maison = convertirEnMaison(enregistrerMaisonDTO);
	  maisonRepo.save(maison);
  }
  
  public List<MaisonDTO> recupererListeMaison() {
	  List<Maison> maisons =maisonRepo.findAll();
	  List<MaisonDTO> result = new ArrayList<>();
	  for(Maison maison : maisons) {
			MaisonDTO maisonDTO = convertirEnMaisonDTO(maison);
			result.add(maisonDTO);
		}
	  return result;
  }
  public MaisonDTO recupererMaison(long id){
	  Optional<Maison> optionalMaison = maisonRepo.findById(id);
	  if(optionalMaison.isPresent()) {
		  Maison maison = optionalMaison.get();
		  MaisonDTO maisonDTO = convertirEnMaisonDTO(maison);
		  return maisonDTO;
	  }
	  else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Maison non retrouve");

	  }  
  }
  
  public void changerStatut(long id, String statutEnString) {
	  Maison.Statut statut;
	  try {
		  statut = Maison.Statut.valueOf(statutEnString);
	  } catch (Exception e) {
		 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"mauvais statut");
	  }
	  Optional<Maison> optionalMaison = maisonRepo.findById(id);
	  if(optionalMaison.isPresent()) {
		  Maison maison = optionalMaison.get();
		  maison.setStatut(statut);
		  maisonRepo.save(maison);
	  }
	  else {
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Maison non retrouve");
	  }
  }
}
