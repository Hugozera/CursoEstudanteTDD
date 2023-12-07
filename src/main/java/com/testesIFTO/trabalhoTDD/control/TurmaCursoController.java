package com.testesIFTO.trabalhoTDD.control;
import com.testesIFTO.trabalhoTDD.control.services.EstudanteService;
import com.testesIFTO.trabalhoTDD.control.services.TurmaCursoService;
import com.testesIFTO.trabalhoTDD.model.entity.Estudante;
import com.testesIFTO.trabalhoTDD.model.entity.TurmaCurso;
import com.testesIFTO.trabalhoTDD.model.repositories.EstudanteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/turmas")
public class TurmaCursoController {

    @Autowired
    private TurmaCursoService turmaCursoService;
    @Autowired
    private EstudanteRepository estudanteRepository;

    @GetMapping("/cadastrar")
    public ModelAndView cadastro(Model model) {
        ModelAndView modelAndView = new ModelAndView("turmas/cadastro");
        model.addAttribute("turma", new TurmaCurso());
        model.addAttribute("cursos", turmaCursoService.listarCursos());
        return modelAndView;
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("turmas/lista");
        List<TurmaCurso> turmas = turmaCursoService.listarTurmas();
        modelAndView.addObject("turmas", turmas);
        return modelAndView;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("turma") TurmaCurso turma,
                               BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("turmas/cadastro");
            modelAndView.addObject("cursos", turmaCursoService.listarCursos());
            return modelAndView;
        }

        // Converte as datas para String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        turma.setInicioMatriculas(turma.getInicioMatriculas().format(String.valueOf(formatter)) + "T00:00:00");
        turma.setFimMatriculas(turma.getFimMatriculas().format(String.valueOf(formatter)) + "T23:59:59");

        turmaCursoService.cadastrarTurma(turma);
        return new ModelAndView("redirect:/turmas/listar");
    }
    @GetMapping("/gestao")
    public ModelAndView gerenciar(Model model) {
        ModelAndView modelAndView = new ModelAndView("turmas/gestao");
        List<TurmaCurso> turmas = turmaCursoService.listarTurmas();
        modelAndView.addObject("turmas", turmas);

        // Lógica para obter a lista de estudantes do serviço ou repositório
        List<Estudante> estudantes = estudanteRepository.findAll();
        modelAndView.addObject("estudantes", estudantes);

        return modelAndView;
    }
    @GetMapping("/adicionarEstudantes")
    public String adicionarEstudantes(@RequestParam("turmaId") Long turmaId,
                                      @RequestParam("estudantesId") Long estudanteId) {

        // Recupere a turma e o estudante do banco de dados
        TurmaCurso turma = turmaCursoService.obterTurmaPorId(turmaId);
        Estudante estudante = turmaCursoService.obterEstudantePorId(estudanteId);

        // Verifique se a turma e o estudante existem
        if (turma != null && estudante != null) {
            // Adicione o estudante à turma
            turma.adicionarEstudante(estudante);

            // Salve a relação entre turma e estudante no banco de dados
            turmaCursoService.salvarRelacaoTurmaEstudante(turma, estudante);
        }

        // Redirecione para a página desejada após a operação
        return "redirect:/turmas/listar";
    }

}
