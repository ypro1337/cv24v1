package fr.univrouen.cv24v1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
@GetMapping("/")
public String index() {
return "Hello cv24 !";
}
//adding a basic PostMapping displaying the request body
@PostMapping("/")
public String handlePostRequest(@RequestBody String requestBody) {
    return "Received POST request with body: " + requestBody;
}
}

