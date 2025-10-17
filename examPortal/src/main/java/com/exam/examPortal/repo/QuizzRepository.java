package com.exam.examPortal.repo;

import com.exam.examPortal.model.exam.Category;
import com.exam.examPortal.model.exam.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizzRepository extends JpaRepository<Quizz,Long> {
    public List<Quizz> findBycategory(Category category);
}
