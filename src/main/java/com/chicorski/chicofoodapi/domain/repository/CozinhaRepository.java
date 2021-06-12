package com.chicorski.chicofoodapi.domain.repository;

import com.chicorski.chicofoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    Optional<Cozinha> findByNome(String nome);

    List<Cozinha> findByNomeContaining(String nome);
}
