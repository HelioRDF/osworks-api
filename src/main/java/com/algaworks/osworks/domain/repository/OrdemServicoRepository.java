package com.algaworks.osworks.domain.repository;

import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

}
