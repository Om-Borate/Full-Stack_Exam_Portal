package com.exam.examPortal.Controller.SubController;

import com.exam.examPortal.Service.CategoryService;
import com.exam.examPortal.model.exam.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> addcategory(@RequestBody Category category){
       Category category1 =  categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }
    @GetMapping("/{categoryID}")
    public Category getcategory(@PathVariable("categoryID")Long categoryID){
        return categoryService.getCategory(categoryID);
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllcategory(){
        return ResponseEntity.ok(categoryService.getCategories());
    }
    @PutMapping("/")
    public ResponseEntity<Category> updatecategory(@RequestBody Category category){ //in video here is change but i didn't made
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }
    @DeleteMapping("/{categoryID}")
    public void deletecategory(@PathVariable("categoryID")Long categoryID){
        this.categoryService.deleteCategory(categoryID);
    }
}
