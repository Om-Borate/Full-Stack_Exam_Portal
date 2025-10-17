package com.exam.examPortal.Service.Imp;

import com.exam.examPortal.Service.QuizzService;
import com.exam.examPortal.model.exam.Category;
import com.exam.examPortal.model.exam.Quizz;
import com.exam.examPortal.repo.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class QuizzServiceImpl implements QuizzService {
    @Autowired
    private QuizzRepository quizzRepository;
    @Override
    public Quizz addQuizz(Quizz quizz) {
        return this.quizzRepository.save(quizz);
    }

    @Override
    public Quizz updateQuizz(Quizz quizz) {
        return this.quizzRepository.save(quizz);
    }

    @Override
    public Set<Quizz> getAllquizzes() {
        return new HashSet<>(quizzRepository.findAll());
    }

    @Override
    public Quizz getQuizz(Long quizzId) {
        return this.quizzRepository.findById(quizzId).get();
    }

    @Override
    public void deleteQuizz(Long quizzId) {
    quizzRepository.deleteById(quizzId);
    }

    @Override
    public List<Quizz> getQuizzOfCategory(Category category) {
        return this.quizzRepository.findBycategory(category);
    }   
}
