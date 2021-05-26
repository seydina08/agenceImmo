package com.ut.sn.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.models.Agence;


@Repository
public interface AgenceRepo extends JpaRepository<Agence, Long> {
	Optional<Agence> findByLoginAndPassword(String login,String password);
	Optional<Agence> findByLogin(String login);
}