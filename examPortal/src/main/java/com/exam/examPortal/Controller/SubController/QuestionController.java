package com.exam.examPortal.Controller.SubController;

import com.exam.examPortal.Service.QestionService;
import com.exam.examPortal.Service.QuizzService;
import com.exam.examPortal.model.exam.Category;
import com.exam.examPortal.model.exam.Questions;
import com.exam.examPortal.model.exam.Quizz;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("Question")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {
    @Autowired
    private QestionService qestionService;
    @Autowired
    private QuizzService quizzService;

    @PostMapping("/") // add questions
    public ResponseEntity<Questions> addQuestions(@RequestBody Questions questions){
        return ResponseEntity.ok(this.qestionService.addquestions(questions));
    }
    @PutMapping("/")//update
    public ResponseEntity<Questions> updateQuestion(@RequestBody Questions questions){
        return ResponseEntity.ok(this.qestionService.updatequestions(questions));
    }
    @DeleteMapping("/{queID}")//delete by id
    public void deleteQuestion(@PathVariable("queID")Long qid){
         this.qestionService.deleteQuestionById(qid);
    }
        @GetMapping("/quizz/{quid}")//get quizz single quizz
    public ResponseEntity<?> getQuestionofQuizz(@PathVariable("quid")Long id){
//        Quizz quizz = new Quizz();
//        quizz.setQid(id);
//        Set<Questions> questions = this.qestionService.getQuestion(quizz);
//        return ResponseEntity.ok(questions);
        Quizz quizz = this.quizzService.getQuizz(id);
        Set<Questions> questions = quizz.getQuestions();
        List list  = new ArrayList<>(questions);
        if (list.size()>Integer.parseInt(quizz.getNumberofQuestions())){
            list = list.subList(0,Integer.parseInt(quizz.getNumberofQuestions()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{quid}")
    public Questions getQuestions(@PathVariable("quid")Long id){
        return this.qestionService.getQuestions(id);
    }
    @GetMapping("/question/{qid}")
    public List<Questions> getQuestionOfQuizz(@PathVariable("qid") Long qid){
        Quizz quizz = new Quizz();
        quizz.setQid(qid);
        return this.qestionService.getQuestionofQuizz(quizz);
    }
}

