package com.exam.examPortal.Controller.SubController;

import com.exam.examPortal.Service.QuizzService;
import com.exam.examPortal.model.exam.Category;
import com.exam.examPortal.model.exam.Quizz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quizz")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizzController{
    @Autowired
    private QuizzService quizzService;

    @PostMapping("/")
    public ResponseEntity<Quizz> addQuizz(@RequestBody Quizz quizz){
        Quizz quizz1 = this.quizzService.addQuizz(quizz);
        return  ResponseEntity.ok(quizz1);
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllquizz(){
        return ResponseEntity.ok(this.quizzService.getAllquizzes());
    }
    @GetMapping("{quizzID}")
    public ResponseEntity<Quizz> getQuizz(@PathVariable("quizzID")Long id){
        return ResponseEntity.ok(this.quizzService.getQuizz(id));
    }
    @PutMapping("/")
    public Quizz updateQuizz(@RequestBody Quizz quizz){
        return this.quizzService.updateQuizz(quizz);
    }
    @DeleteMapping("/{quizzID}")
    public void deleteQuizz(@PathVariable("quizzID")Long quizzId){
        this.quizzService.deleteQuizz(quizzId);
    }
    @GetMapping("/category/{cid}")
    public List<Quizz> getQuizzofCategory(@PathVariable("cid") Long cid){
        Category category = new Category();
        category.setCid(cid);
        return this.quizzService.getQuizzOfCategory(category);
    }
}