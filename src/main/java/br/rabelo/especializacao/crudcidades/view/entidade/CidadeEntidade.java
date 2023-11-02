
package br.rabelo.especializacao.crudcidades.view.entidade;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity (name = "cidade")
public class CidadeEntidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}