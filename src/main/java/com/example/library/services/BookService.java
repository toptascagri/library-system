package com.example.library.services;

import com.example.library.dto.BookSaveDto;
import com.example.library.dto.BookWithAuthorDto;
import com.example.library.entity.Book;
import com.example.library.results.DataResult;
import com.example.library.results.Result;

import java.util.List;

public interface BookService {

    DataResult<BookSaveDto> getBookById(Integer id);
    DataResult<BookWithAuthorDto> findBookAndAuthorUseDto (String bookName);
    Result addBook(BookSaveDto bookSaveDto);
    Result deleteBook(String bookName);
    DataResult<List<BookSaveDto>> getAllBookForUser();
}
