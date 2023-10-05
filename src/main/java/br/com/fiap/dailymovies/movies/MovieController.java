package br.com.fiap.dailymovies.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("movie", service.findAll());
        return "movies/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if (service.delete(id)){
            redirect.addFlashAttribute("success", "filme apagado com sucesso");
        }else{
            redirect.addFlashAttribute("error", "filme não encontrado");
        }
        return "redirect:/movies";
    }
    
}
