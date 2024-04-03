package fr.univrouen.cv24v1.controllers;


import org.springframework.web.bind.annotation.*;


import fr.univrouen.cv24v1.model.TestCV;
import jakarta.ws.rs.core.MediaType;


@RestController
public class PostController {
	@RequestMapping(value = "/testpost", method = RequestMethod.POST, 
			consumes = "application/xml")
			public String postTest(@RequestBody String flux) {
			return ("<result><response>Message reçu : </response>" 
			+ flux + "</result>");
			}
	
	@RequestMapping(value="/testxml", produces=MediaType.APPLICATION_XML)
	public @ResponseBody TestCV getXML() {
	TestCV cv = new TestCV("HAMILTON","Margaret","1969/07/21", 
	"Appollo11@nasa.us");
	return cv;
	}


	
}