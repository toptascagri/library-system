package com.example.library.controller;

import com.example.library.dto.BookSaveDto;
import com.example.library.dto.BookWithAuthorDto;
import com.example.library.results.DataResult;
import com.example.library.results.Result;
import com.example.library.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBooks")
    public DataResult<List<BookSaveDto>> getAllBooks(){
       return bookService.getAllBookForUser();
    }

    @GetMapping("/getById")
    public DataResult<BookSaveDto> getBookById(@RequestParam Integer id){
        return bookService.getBookById(id);
    }

    @GetMapping("/findBookAndAuthorUseDto")
    public DataResult<BookWithAuthorDto> findBookAndAuthorUseDto(String bookName){
        return bookService.findBookAndAuthorUseDto(bookName);
    }

    @PostMapping("/add")
    public Result addBook(@RequestBody BookSaveDto bookSaveDto){
        return this.bookService.addBook(bookSaveDto);

    }

    @PostMapping("/delete")
    public Result deleteBook(@RequestParam String bookName){
       return this.bookService.deleteBook(bookName);
    }

}

