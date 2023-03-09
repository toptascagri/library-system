package com.example.library.services.impl;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.exception.ExceptionAlreadyFound;
import com.example.library.exception.ExceptionNotFound;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.results.DataResult;
import com.example.library.results.Result;
import com.example.library.results.SuccessDataResult;
import com.example.library.results.SuccessResult;
import com.example.library.services.AuthorService;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;
import javax.management.InstanceAlreadyExistsException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository,AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper=authorMapper;
    }
    @Override
    public Result addAuthor(Author author) {

        try{
            Optional<Author> optionalAuthor= authorRepository.findAuthorByName(author.getName());
            if (optionalAuthor.isPresent()){
                throw new  InstanceAlreadyExistsException("Yazar zaten bulunmakta");

            }
            else {
                authorRepository.save(author);
                return new SuccessResult("Kayıt başarılı");
            }
        }
        catch (Exception exception){
            throw new ExceptionAlreadyFound(exception.getMessage());
        }
    }
    @Override
    public DataResult<AuthorDto> getSomeInformationAuthor(String name) {

        try {
            Optional<Author> optionalAuthor= authorRepository.findAuthorByName(name);
            if (optionalAuthor.isPresent()){
                Author author = optionalAuthor.get();
                AuthorDto authorDto= authorMapper.entityToDto(author);
                return new SuccessDataResult<>(authorDto,"Yazar hakkında bilgiler:");
            }
            else {
                throw new NotFoundException("Yanlış isim girdiniz.Lütfen tekrar giriniz.");
            }
        }
        catch (Exception exception){
            throw new ExceptionNotFound(exception.getMessage());

        }

    }

    @Override
    public Result deleteAuthor(String authorName) {
        try {
            Optional<Author> optionalAuthor=authorRepository.findAuthorByName(authorName);
            if (!optionalAuthor.isPresent()){
                throw new NotFoundException("Bu isme sahip yazar bulunmamakta");
            }
            else {
                authorRepository.delete(optionalAuthor.get());
                return new SuccessResult("Yazar başarıyla silinmiştir");
            }
        }
        catch (Exception exception){
            throw new ExceptionNotFound(exception.getMessage());
        }
    }

    @Override
    public DataResult<List<AuthorDto>> getAllAuthors() {
        try {
           List<Author> authors= authorRepository.findAll();
           if (authors.size()==0){
               throw new SQLException("Yazar listesi boş. Önce yazar ekleyin");
           }
           else {

                   return new SuccessDataResult<>(authorRepository.getAllAuthors(),"Tüm yazarlar listelendi.");
               }

           }
        catch (Exception exception){
            throw new ExceptionNotFound(exception.getMessage());
        }

        }
    }
