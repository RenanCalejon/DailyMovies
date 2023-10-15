package br.com.fiap.dailymovies.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService service;

    @GetMapping
    public String index(Model model , @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
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
    
    @GetMapping("new")
    public String form(Movie movie){
        return "movies/form";
    }

    @PostMapping
    public String create(@Valid Movie movie, BindingResult binding, RedirectAttributes redirect){
        if (binding.hasErrors()) return "/movies/form";

        service.save(movie);
        redirect.addFlashAttribute("success", "Avaliação cadastrada com sucesso");
        return "redirect:/movies";
    }
    
}

