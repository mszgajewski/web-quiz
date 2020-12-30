package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Quiz {

    private final List<Question> questions = new ArrayList<>();

    @GetMapping(path = "/api/quizzes/{id}")
    public Question getQuestion(@PathVariable int id) {
        checkArrayBounds(id);
        return questions.get(id - 1);
    }

    private void checkArrayBounds(int id) {
        if (id < 1 || id > questions.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/api/quizzes")
    public List<Question> getAllQuestions() {
        return questions;
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public Answer checkAnswer(@RequestBody Guess guess, @PathVariable int id) {
        checkArrayBounds(id);

        System.out.println(questions.get(id - 1));
        System.out.println(guess);

        if (questions.get(id - 1).isCorrect(guess.getAnswer())) {
            return Answer.CORRECT_ANSWER;
        } else {
            return Answer.WRONG_ANSWER;
        }
    }

    @PostMapping(path = "/api/quizzes")
    public Question addQuestion(@RequestBody @Valid Question question) {
        question.setId(questions.size() + 1);
        questions.add(question);
        return question;
    }
}