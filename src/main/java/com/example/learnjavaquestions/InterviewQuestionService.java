package com.example.learnjavaquestions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewQuestionService {
    private final InterviewQuestionRepository questionRepository;
    public ResponseEntity<InterviewQuestion> createQuestion(InterviewQuestion question) {

        // null check
        if(question == null) {
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(), HttpStatus.BAD_REQUEST);}
        if (question.getQuestion() == null | question.getAnswer() == null){
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(), HttpStatus.BAD_REQUEST);}

        //emptiness check
        if(question.getQuestion().isEmpty() | question.getAnswer().isEmpty()) {
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<InterviewQuestion>(questionRepository.save(question),HttpStatus.CREATED);
    }

    public List<InterviewQuestion> learnAll() {
        return questionRepository.findAll();
    }
}
