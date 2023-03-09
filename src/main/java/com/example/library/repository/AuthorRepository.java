package com.example.library.repository;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.results.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Optional<Author> findAuthorByName(String name);

    @Query("Select new com.example.library.dto.AuthorDto (a.name,a.description,a.age) from Author a")
    //@Query(value = "select a.name,a.description,a.age from kutuphane.authors a",nativeQuery = true)
    List<AuthorDto>getAllAuthors();

}
