package fr.univrouen.cv24v1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        // Ajoutez ici des attributs à votre modèle si nécessaire
        // model.addAttribute("key", "value");

        return "index"; // Cette ligne recherche un fichier nommé "index.html" dans src/main/resources/templates
    }
}
