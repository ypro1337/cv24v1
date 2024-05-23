package fr.univrouen.cv24v1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currentPage", "accueil");
        return "index"; // Recherche un fichier "index.html" dans src/main/resources/templates
    }

    @GetMapping("/help")
    public String help(Model model) {
        model.addAttribute("currentPage", "aide");
        return "help"; // Recherche un fichier "help.html" dans src/main/resources/templates
    }
}
