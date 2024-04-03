package fr.univrouen.cv24v1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
@GetMapping("/resume")
public String getListCVinXML() {
return "Envoi de la liste des CV";
}
@GetMapping("/cvid")
public String getCVinXML(
@RequestParam(value = "texte") String texte) {
return ("Détail du CV n°" + texte);
}
@GetMapping("/test")
public String getTestData(
@RequestParam(value = "id") int id,
@RequestParam(value = "titre") String titre)
{ // Params order is irrelevant / if one or many params are missing we get a White Laber Error Page
return ("Test :<br>id = "+ id + "<br>titre = " + titre);
}

}
