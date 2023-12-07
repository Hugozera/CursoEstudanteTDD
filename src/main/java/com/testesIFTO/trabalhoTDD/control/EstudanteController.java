package com.testesIFTO.trabalhoTDD.control;

import com.testesIFTO.trabalhoTDD.model.entity.Estudante;
import com.testesIFTO.trabalhoTDD.model.repositories.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @GetMapping("/listar")
    public ModelAndView listarEstudantes() {
        List<Estudante> estudantes = estudanteRepository.findAll();
        System.out.println("Número de estudantes: " + estudantes.size());
        ModelAndView modelAndView = new ModelAndView("estudante/lista");
        modelAndView.addObject("estudantes", estudantes);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhesEstudante(@PathVariable("id") Long id) {
        Estudante estudante = estudanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado"));
        ModelAndView modelAndView = new ModelAndView("estudante/detalhes");
        modelAndView.addObject("estudante", estudante);
        return modelAndView;
    }


    @GetMapping("/cadastrar")
    public ModelAndView mostrarFormularioCadastro(Model model) {
        ModelAndView modelAndView = new ModelAndView("estudante/cadastro");
        modelAndView.addObject("estudante", new Estudante(1, 22, "hugo", "2001", "informatica")); // Cria um novo objeto Estudante
        modelAndView.addObject("isEdicao", false); // Indica que é uma operação de cadastro
        return modelAndView;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastrarEstudante(@ModelAttribute("estudante") Estudante estudante) {
        estudanteRepository.save(estudante);
        return new ModelAndView("redirect:/estudantes/lista");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView mostrarFormularioEdicao(@PathVariable("id") Long id) {
        Estudante estudante = estudanteRepository.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("estudante/cadastro");
        modelAndView.addObject("estudante", estudante);
        modelAndView.addObject("isEdicao", true); // Indica que é uma operação de edição
        return modelAndView;
    }

    @PostMapping("/editar")
    public ModelAndView editarEstudante(@ModelAttribute("estudante") Estudante estudante) {
        estudanteRepository.save(estudante);
        return new ModelAndView("redirect:/estudantes");
    }

    @GetMapping("/remover/{id}")
    public ModelAndView removerEstudante(@PathVariable("id") Long id) {
        estudanteRepository.deleteById(id);
        return new ModelAndView("redirect:/estudantes/lista");
    }
}
