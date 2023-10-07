package br.rabelo.especializacao.crudcidades.view;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CidadeController {

	@GetMapping("/")
	public String listarCidades(Model memoria) {
		
		var cidades = Set.of(
				new Cidade("Cornélio Procópio", "PR"),
				new Cidade("Jalapão", "TO"),
				new Cidade("Brasilia", "DF"),
				new Cidade("Formosa", "GO"),
				new Cidade("São Paulo", "SP")
				);
		
		memoria.addAttribute("listaCidades", cidades);
		return "/crud";
	}
}
