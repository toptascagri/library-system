package com.example.library.controller;

import com.example.library.entity.Category;
import com.example.library.results.DataResult;
import com.example.library.results.Result;
import com.example.library.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getCategories")
    public DataResult<List<Category>> getCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PostMapping("/deleteCategory")
    public Result deleteCategory(@RequestParam String categoryName){
        return categoryService.deleteCategory(categoryName);
    }

    @GetMapping("/findCategoryByName")
    public DataResult <Category> findCategoryByName(@RequestParam String categoryName){
        return categoryService.findCategoryByName(categoryName);
    }
}
