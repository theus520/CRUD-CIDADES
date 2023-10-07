package br.rabelo.especializacao.crudcidades.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CidadeController {

	@GetMapping("/")
	public String listarCidades() {
		return "crud.html";
	}
}
