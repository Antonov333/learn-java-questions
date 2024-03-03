package com.example.learnjavaquestions;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiResponses(@ApiResponse(responseCode = "400", description = "Null or empty InterviewQuestion supplied"))
    public ResponseEntity<InterviewQuestion> createInterviewQuestion(InterviewQuestion question){
        return questionService.createQuestion(question);
    }

    @GetMapping("/learn-all")
    public List<InterviewQuestion> learnAll(){
        return questionService.learnAll();
    }

    @GetMapping("/random")
    public ResponseEntity<InterviewQuestion> randomQuestion() {
        return questionService.randomQuestion();
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question successfully delivered"),
            @ApiResponse(responseCode = "404", description = "No question specified by provided id"),
            @ApiResponse(responseCode = "400", description = "Question id must be over zero and lesser or equal than total number of question stored in database. Then, questionUpdates must be not null and not empty")})
    public ResponseEntity<InterviewQuestion> learnQuestion(@Parameter(name = "id", description = "Question database identifier")
                                                           @PathVariable(name = "id")
                                                           int questionId) {
        return questionService.learnQuestion(questionId);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question successfully updated"),
            @ApiResponse(responseCode = "404", description = "No question specified by provided id"),
            @ApiResponse(responseCode = "400", description = "Question id must be over zero and lesser or equal than total number of question stored in database. Then, questionUpdates must be not null and not empty")}
    )
    public ResponseEntity<InterviewQuestion> updateQuestion(@Parameter(name = "id", description = "Question database identifier")
                                                            @PathVariable(name = "id")
                                                            int questionId, InterviewQuestion questionUpdates) {
        return questionService.updateQuestion(questionId, questionUpdates);
    }
}
