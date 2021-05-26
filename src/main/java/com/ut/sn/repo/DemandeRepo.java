package com.ut.sn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.models.Demande;

@Repository
public interface DemandeRepo extends JpaRepository<Demande, Long> {

}
