package com.example.library.controller;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.results.DataResult;
import com.example.library.results.Result;
import com.example.library.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/save")
    public Result saveAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }


    @GetMapping("/infoAbouthAuthor")
    public DataResult<AuthorDto> giveInfo(@RequestParam String name){
        return authorService.getSomeInformationAuthor(name);
    }

    @PostMapping("/deleteAuthor")
    public Result deleteAuthor(@RequestParam String authorName){
        return authorService.deleteAuthor(authorName);
    }


    @GetMapping("/getAllAuthors")
    public DataResult<List<AuthorDto>> getAllAuthors(){
        return authorService.getAllAuthors();
    }
}

