package com.example.library.services;

import com.example.library.entity.Category;
import com.example.library.results.DataResult;
import com.example.library.results.Result;

import java.util.List;

public interface CategoryService {


    DataResult<List<Category>> getAllCategories();

    Result addCategory(Category category);

    Result deleteCategory(String categoryName);

    DataResult<Category>findCategoryByName(String categoryName);
}
