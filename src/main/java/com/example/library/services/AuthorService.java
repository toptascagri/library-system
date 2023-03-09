package com.example.library.services;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.results.DataResult;
import com.example.library.results.Result;

import java.util.List;


public interface AuthorService {

    public Result addAuthor(Author author);

    public DataResult<AuthorDto> getSomeInformationAuthor(String name);

    public Result deleteAuthor(String authorName);

    public DataResult<List<AuthorDto>>getAllAuthors();


}
