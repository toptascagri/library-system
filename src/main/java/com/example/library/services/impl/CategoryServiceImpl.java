package com.example.library.services.impl;

import com.example.library.entity.Category;
import com.example.library.exception.ExceptionAlreadyFound;
import com.example.library.exception.ExceptionNotFound;
import com.example.library.repository.CategoryRepository;
import com.example.library.results.DataResult;
import com.example.library.results.Result;
import com.example.library.results.SuccessDataResult;
import com.example.library.results.SuccessResult;
import com.example.library.services.CategoryService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.InternalError;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public DataResult<List<Category>> getAllCategories() {
        try {
            List<Category>categories= categoryRepository.findAll();
            if (categories.size()==0){
                throw new InternalError("Veri tabanı bağlantı hatası");
            }
            else {
                return new SuccessDataResult<>(categories,"Kategoriler başarıyla listelendi.");
            }
        }
        catch (Exception exception){
            throw new ExceptionNotFound("Herhangi veri bulunamadı.");

        }
    }
    @Override
    public Result addCategory(Category category) {
        try {
            Optional<Category>optionalCategory =categoryRepository.findCategoryByCategoryName(category.getCategoryName());
            if (optionalCategory.isPresent()){
                throw new InstanceAlreadyExistsException("Bu isme sahip kategori zaten var.");
            }
            else {
                categoryRepository.save(category);
                return new  SuccessResult("Kategori başarıyla eklendi.");
            }
        }
        catch (Exception exception){
            throw new ExceptionAlreadyFound(exception.getMessage());

        }
    }

    @Override
    public Result deleteCategory(String categoryName) {
       try {
           Optional<Category>category=categoryRepository.findCategoryByCategoryName(categoryName);
           if (!category.isPresent()){
               throw new NotFoundException("Bu isme sahip kategori yok.");
           }
           else {
               categoryRepository.delete(category.get());
               return new  SuccessResult("Kategori başarıyla silindi.");
           }
       }
       catch (Exception exception){
           throw new ExceptionNotFound(exception.getMessage());

       }
    }

    @Override
    public DataResult<Category> findCategoryByName(String categoryName) {
        try{
            Optional<Category> optionalCategory=categoryRepository.findCategoryByCategoryName(categoryName);
            if (!optionalCategory.isPresent()){
                throw new NotFoundException("Böyle bir kategori bulunmamakta");
            }
            else {
                return new SuccessDataResult<>(optionalCategory.get(),"Kategori bulundu");
            }

        }
        catch (Exception exception){
            throw new ExceptionNotFound(exception.getMessage());

        }
    }
}
