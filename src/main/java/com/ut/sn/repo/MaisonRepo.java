package com.ut.sn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.models.Maison;

@Repository
public interface MaisonRepo extends JpaRepository<Maison, Long> {

}
