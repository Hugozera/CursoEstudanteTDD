package com.testesIFTO.trabalhoTDD.control;

import com.testesIFTO.trabalhoTDD.control.services.CursoService;
import com.testesIFTO.trabalhoTDD.model.entity.Curso;
import com.testesIFTO.trabalhoTDD.model.entity.TurmaCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/cadastrar")
    public ModelAndView cadastro(TurmaCurso turmaCurso) {
        ModelAndView modelAndView = new ModelAndView("cursos/cadastro");
        modelAndView.addObject("turmaCurso", turmaCurso);
        modelAndView.addObject("cursos", cursoService.listarCursos()); // Adiciona a lista de cursos ao modelo
        return modelAndView;
    }
    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("cursos/lista");
        List<Curso> cursos = cursoService.listarCursos();
        modelAndView.addObject("cursos", cursos);
        return modelAndView;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid Curso curso, BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("/cursos/cadastro");
        cursoService.cadastrarCurso(curso);
        return new ModelAndView("redirect:/cursos/listar");
    }

    @GetMapping("/editar")
    public ModelAndView editar(@RequestParam("id") Long id) {
        Curso curso = cursoService.obterCurso(id);
        ModelAndView modelAndView = new ModelAndView("cursos/cadastro");
        return modelAndView.addObject("curso", curso);
    }

    @PostMapping("/update")
    public ModelAndView editar(@ModelAttribute("curso") Curso curso) {
        //cursoService.atualizarCurso(curso);
        return listar();
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
       // cursoService.removerCurso(id);
        return listar();
    }
}
