package com.example.library.repository;

import com.example.library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findCategoryByCategoryName(String categoryName);

}
