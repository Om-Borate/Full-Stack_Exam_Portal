package com.exam.examPortal.Service;

import com.exam.examPortal.model.exam.Category;
import com.exam.examPortal.model.exam.Quizz;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface QuizzService {
    public Quizz addQuizz(Quizz quizz);
    public Quizz updateQuizz(Quizz quizz);
    public Set<Quizz> getAllquizzes();
    public Quizz getQuizz(Long quizzId);
    public void deleteQuizz(Long quizzId);

    public List<Quizz> getQuizzOfCategory(Category category);
}
