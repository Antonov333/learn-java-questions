package com.example.learnjavaquestions;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Learn Java Questions Application Home")
public class LearnJavaQuestionsHomeController {

    @GetMapping("")
    public String home(){
        return "<br><br><h3><a href=\"http://localhost:8080/questions\">Questions Controller</a></h3>";
    }

}
