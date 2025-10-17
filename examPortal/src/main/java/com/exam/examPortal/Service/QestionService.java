package com.exam.examPortal.Service;

import com.exam.examPortal.model.exam.Questions;
import com.exam.examPortal.model.exam.Quizz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface QestionService {
    public Questions addquestions(Questions questions);
    public Questions updatequestions(Questions questions);
    public Set<Questions> getAllQuestions(Questions questions);
    public  Questions getQuestions(Long questions);
    public Set<Questions> getQuestion(Quizz quizz);
    public void deleteQuestionById(Long id);

    List<Questions> getQuestionofQuizz(Quizz quizz);
}
