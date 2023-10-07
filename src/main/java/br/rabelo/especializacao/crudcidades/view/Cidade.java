package br.rabelo.especializacao.crudcidades.view;

public class Cidade {

	private final String nome;
	private final String estado;
	public Cidade(String nome, String estado) {
		super();
		this.nome = nome;
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public String getEstado() {
		return estado;
	}
	
	
}
