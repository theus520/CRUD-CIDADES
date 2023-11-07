package br.rabelo.especializacao.crudcidades.view.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.rabelo.especializacao.crudcidades.view.entidade.CidadeEntidade;

public interface CidadeRepository extends JpaRepository<CidadeEntidade, Long> {

public Optional<CidadeEntidade> findByNomeAndEstado(String nome, String estado);
}