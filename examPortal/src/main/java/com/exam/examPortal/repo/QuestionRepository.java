package com.exam.examPortal.repo;

import com.exam.examPortal.model.exam.Questions;
import com.exam.examPortal.model.exam.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Questions,Long> {
    Set<Questions> findByQuizz(Quizz quizz);

    List<Questions> findByquizz(Quizz quizz);
}
