package fr.univrouen.cv24v1.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpController {
    @GetMapping("/help")
    public String help() {
        return "help"; // Le nom de la vue (help.html)
    }
}
