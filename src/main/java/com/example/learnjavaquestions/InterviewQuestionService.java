package com.example.learnjavaquestions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    public ResponseEntity<InterviewQuestion> randomQuestion() {
        List<InterviewQuestion> allQuestion = questionRepository.findAll();
        Random random = new Random();
        int questionNumber = random.nextInt(1, allQuestion.size() + 1);
        InterviewQuestion question = questionRepository.findById(questionNumber).orElse(null);
        return new ResponseEntity<InterviewQuestion>(question, HttpStatus.OK);
        // TODO: think how to better check and manage questions
    }
}
