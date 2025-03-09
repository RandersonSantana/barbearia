package com.barbearia.repositories;

import com.barbearia.models.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    Optional <Barbeiro> findByNome(String nome);
}
