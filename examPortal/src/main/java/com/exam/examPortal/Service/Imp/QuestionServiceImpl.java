package com.exam.examPortal.Service.Imp;

import com.exam.examPortal.Service.QestionService;
import com.exam.examPortal.model.exam.Category;
import com.exam.examPortal.model.exam.Questions;
import com.exam.examPortal.model.exam.Quizz;
import com.exam.examPortal.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class QuestionServiceImpl implements QestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Questions addquestions(Questions questions) {
        return questionRepository.save(questions);
    }

    @Override
    public Questions updatequestions(Questions questions) {
        return questionRepository.save(questions);
    }

    @Override
    public Set<Questions> getAllQuestions(Questions questions) {
        return new HashSet<>(questionRepository.findAll());
    }

    @Override
    public Questions getQuestions(Long questionsId){
        return questionRepository.findById(questionsId).get();
    }

    @Override
    public Set<Questions> getQuestion(Quizz quizz) {
        return questionRepository.findByQuizz(quizz);
    }

    @Override
    public void deleteQuestionById(Long id) {
        Questions questions = new Questions();
        questions.setQuesId(id);
        questionRepository.delete(questions);
    }

    @Override
    public List<Questions> getQuestionofQuizz(Quizz quizz) {
        return this.questionRepository.findByquizz(quizz);
    }


}
