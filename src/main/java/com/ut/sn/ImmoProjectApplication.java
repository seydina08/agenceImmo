package com.ut.sn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.ut.sn.models.Agence;
import com.ut.sn.repo.AgenceRepo;
import com.ut.sn.utils.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ImmoProjectApplication implements CommandLineRunner{

	@Autowired
	private AgenceRepo agenceRepo;
	

	
	public static void main(String[] args) {
		SpringApplication.run(ImmoProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<Agence> optionalAgence = agenceRepo.findByLogin("NguiraneImmo");
		if(!optionalAgence.isPresent()) {
			 Agence agence = new Agence();
			 agence.setLogin("NguiraneImmo");
			 agence.setAdresse("Dakar Ouest foire");
			 agence.setTelephone("777872112");
			 agence.setNom("Avenir Immo");
			 agence.setPassword(PasswordEncoder.hashPassword("P@sser123", agence.getLogin()));
			 agenceRepo.save(agence);
		}
		
	}
	
	

}
