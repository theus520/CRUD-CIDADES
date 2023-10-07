package br.rabelo.especializacao.crudcidades.view;

import org.springframework.stereotype.Controller;

@Controller
public class CidadeController {

	public String listarCidades() {
		return "crud.html";
	}
}
