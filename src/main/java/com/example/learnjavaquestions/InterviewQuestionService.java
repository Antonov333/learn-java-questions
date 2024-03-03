package com.example.learnjavaquestions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class InterviewQuestionService {

    private final InterviewQuestionRepository questionRepository;

    public ResponseEntity<InterviewQuestion> createQuestion(@RequestBody InterviewQuestion question) {

        // Make sure interview question entity is correct, i.e. it is not null, question and answer fields are neither null, nor blank
        // If not then return BAD REQUEST response code
        if (!questionEntityIsCorrect(question)) {
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<InterviewQuestion>(questionRepository.save(question), HttpStatus.CREATED);
    }

    /**
     * <h3>boolean questionEntityIsCorrect(InterviewQuestion question)</h3>
     * Make sure interview question entity is correct, i.e. it is not null, question and answer fields are neither null, nor blank
     *
     * @param question {@link InterviewQuestion} entity to be checked whether it is correct, i.e. neither null, nor blank
     * @return true if supplied argument is correct
     */
    boolean questionEntityIsCorrect(InterviewQuestion question) {
        if (question == null) {
            return false;
        }
        if (question.getQuestion() == null | question.getAnswer() == null |
                question.getTopic() == null) {
            return false;
        }
        return !(question.getQuestion().isBlank() | question.getAnswer().isBlank());
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

    public ResponseEntity<InterviewQuestion> learnQuestion(int questionId) {
        // make sure id is correct. If not then return BAD REQUEST response code
        if (!questionIdIsCorrect(questionId)) {
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(), HttpStatus.BAD_REQUEST);
        }

        // look up question specified by supplied id and make sure mentioned question exists
        // If not then return NOT FOUND response code
        Optional<InterviewQuestion> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(), HttpStatus.NOT_FOUND);
        }
        // Deliver found question to requested side
        return new ResponseEntity<>(questionOptional.get(), HttpStatus.OK);
    }

    boolean questionIdIsCorrect(int questionId) {
        return (questionId > 0 & questionId <= questionRepository.count());
    }

    public ResponseEntity<InterviewQuestion> updateQuestion(int questionId,
                                                            @RequestBody InterviewQuestion questionUpdates) {
        // make sure supplied arguments are correct
        if (!questionIdIsCorrect(questionId)) {
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(),
                    HttpStatus.BAD_REQUEST);
        }
        // make sure questionUpdate neither null, nor empty. If not then return BAD REQUEST response code
        if (!questionEntityIsCorrect(questionUpdates)) {
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(),
                    HttpStatus.BAD_REQUEST);
        }

        // look up question specified by supplied id and make sure mentioned question exists
        // If not then return NOT FOUND response code
        Optional<InterviewQuestion> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            return new ResponseEntity<InterviewQuestion>(new InterviewQuestion(),
                    HttpStatus.NOT_FOUND);
        }

        // Update question entity
        InterviewQuestion updatedQuestion = questionOptional.get();
        updatedQuestion.setQuestion(questionUpdates.getQuestion());
        updatedQuestion.setAnswer(questionUpdates.getAnswer());
        updatedQuestion.setTopic(questionUpdates.getTopic());

        // Save update question in database
        updatedQuestion = questionRepository.save(updatedQuestion);

        // Deliver updated question to requested side
        return new ResponseEntity<InterviewQuestion>(updatedQuestion, HttpStatus.OK);
    }
}