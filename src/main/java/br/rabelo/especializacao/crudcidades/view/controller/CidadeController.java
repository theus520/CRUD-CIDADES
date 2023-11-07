package br.rabelo.especializacao.crudcidades.view.controller;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.rabelo.especializacao.crudcidades.view.Cidade;
import br.rabelo.especializacao.crudcidades.view.repository.CidadeRepository;
import jakarta.validation.Valid;


@Controller
public class CidadeController {

    private Set<Cidade> cidades;

    @Autowired
    private final CidadeRepository cidadeRepository;

    public CidadeController(CidadeRepository cidadeRepository) {
        cidades = new HashSet<>();
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping("/")
    public String listar(Model memoria) {

        memoria.addAttribute("listaCidades", cidadeRepository.findAll()
                                                                            .stream()
        .map(cidade -> new Cidade(cidade.getNome(),cidade.getEstado())).collect(Collectors.toList()));
        return "/crud";
    }

    @PostMapping("/criar")
    public String criar(@Valid Cidade cidade, BindingResult validacao, Model memoria) {
        if (validacao.hasErrors()) {
            validacao.getFieldErrors().forEach
            (
                error -> memoria.addAttribute(error.getField(), error.getDefaultMessage())
                
                    );


                    memoria.addAttribute("nomeInformado", cidade.getNome());
                    memoria.addAttribute("estadoInformado", cidade.getEstado());
                    memoria.addAttribute("listaCidades", cidade);
                   return "/crud";
        }else{
            cidadeRepository.save(cidade.clonar());
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(
            @RequestParam String nome, 
            @RequestParam String estado) {

        var cidadeEstadoEncontrada = cidadeRepository.findByNomeAndEstado(nome, estado);
        cidadeEstadoEncontrada.ifPresent(cidadeRepository::delete);

        return "redirect:/";
    }

    @GetMapping("/preparaAlterar")
    public String preparaAlterar(
        @RequestParam String nome, 
        @RequestParam String estado,
        Model memoria) {

            var cidadeAtual = cidadeRepository.findByNomeAndEstado(nome, estado);
            cidadeAtual.ifPresent(cidadeEncontrada -> {memoria.addAttribute("cidadeAtual", cidadeEncontrada);
            memoria.addAttribute("listaCidades", cidadeRepository.findAll());
         });
            return "/crud";
    }

    @PostMapping("/alterar")
    public String alterar(
        @RequestParam String nomeAtual, 
        @RequestParam String estadoAtual,
        Cidade cidade,BindingResult validacao, Model memoria) {

            var cidadeAtual = cidadeRepository.findByNomeAndEstado(nomeAtual, estadoAtual);

            if (cidadeAtual.isPresent()) {
            
                var cidadeEncontrada = cidadeAtual.get();
                cidadeEncontrada.setNome(cidade.getNome());
                cidadeEncontrada.setEstado(cidade.getEstado());

                cidadeRepository.saveAndFlush(cidadeEncontrada);
                
            }

            return "redirect:/";
    }
}