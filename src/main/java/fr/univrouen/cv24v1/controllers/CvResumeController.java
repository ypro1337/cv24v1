package fr.univrouen.cv24v1.controllers;

import fr.univrouen.cv24v1.model.Cv;
import fr.univrouen.cv24v1.repository.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CvResumeController {

    @Autowired
    private CvRepository cvRepository;

    @GetMapping("/cv24/resume")
    public String showCvResume(Model model) {
        // Fetch all CVs from the repository
        List<Cv> cvs = cvRepository.findAll();

        // Add the CVs to the model
        model.addAttribute("cvs", cvs);

        // Return the name of the Thymeleaf template to render
        return "resume";
    }
    @GetMapping("/cv24/html")
    public String showCvDetails(@RequestParam Long id, Model model) {
        // Fetch the CV by id from the repository
        Optional<Cv> cvOptional = cvRepository.findById(id);

        if (!cvOptional.isPresent()) {
            // Handle the case where the CV is not found
            model.addAttribute("id", id);
            model.addAttribute("status", "ERROR");
            return "error"; // You can create an error.html template to display errors
        }

        // Add the found CV to the model
        model.addAttribute("cv", cvOptional.get());
        // Return the name of the Thymeleaf template to render
        return "cvDetails";
    }
}
