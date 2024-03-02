package com.example.learnjavaquestions;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
@Tag(name = "Questions Controller")
@RequiredArgsConstructor
public class QuestionsController {
    private final InterviewQuestionService questionService;

    @GetMapping("")
    public String questionsHome(){
        return "<br><br><h3>Welcome to Questions Controller</h3>";
    }
    @PostMapping("")
    public ResponseEntity<InterviewQuestion> createInterviewQuestion(InterviewQuestion question){
        return questionService.createQuestion(question);
    }

    @GetMapping("/learn-all")
    public List<InterviewQuestion> learnAll(){
        return questionService.learnAll();
    }
}
