package com.example.library.services.impl;
import com.example.library.dto.BookSaveDto;
import com.example.library.dto.BookWithAuthorDto;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.exception.ExceptionNotFound;
import com.example.library.mapper.BookSaveMapperImpl;
import com.example.library.mapper.BookWithAuthorDtoMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import com.example.library.results.DataResult;
import com.example.library.results.Result;
import com.example.library.results.SuccessDataResult;
import com.example.library.results.SuccessResult;
import com.example.library.services.BookService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookSaveMapperImpl bookSaveMapper;

    private final BookWithAuthorDtoMapper bookWithAuthorDtoMapper;

    private final Logger logger = LoggerFactory.getLogger(BookRepository.class);



    public BookServiceImpl (BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, BookSaveMapperImpl bookSaveMapper, BookWithAuthorDtoMapper bookWithAuthorDtoMapper){
        this.bookRepository=bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository=categoryRepository;
        this.bookSaveMapper = bookSaveMapper;
        this.bookWithAuthorDtoMapper = bookWithAuthorDtoMapper;
    }

    @Override
    public DataResult<List<BookSaveDto>> getAllBookForUser() {

        try {
            List<BookSaveDto> bookSaveDtos =  bookRepository.getAllBookForUser();
            if (bookSaveDtos.size() == 0){
                throw new NotFoundException("Sistemde Kayıtlı kitap bulunamadı");
            }
            else {
                return new SuccessDataResult<List<BookSaveDto>>(bookSaveDtos, "kitaplar listelendi");
                }
            }
        catch (Exception exception){
            throw new ExceptionNotFound(exception.getMessage());
        }
    }
    @Override
    public DataResult<BookSaveDto> getBookById(Integer id) {
        BookSaveDto bookSaveDto= bookRepository.getBookById(id);
        try {
            if (bookSaveDto==null){
                throw new NotFoundException("Geçersiz id");

            }
            else {
                return new SuccessDataResult<>(bookSaveDto,"Kitap bulundu.");
            }
        }
        catch (Exception exception){
            throw new ExceptionNotFound(exception.getMessage());
        }
    }


    @Override
    public DataResult<BookWithAuthorDto> findBookAndAuthorUseDto(String bookName) {
        try {
            Optional<Book> book =bookRepository.findBookByBookName(bookName);
            if (!book.isPresent()){
                throw new NotFoundException(" Kitap ismi hatalı girildi");
            }
            else {
               BookWithAuthorDto bookWithAuthorDto = bookWithAuthorDtoMapper.entityToDto(book.get());
               bookWithAuthorDto.setAuthorName(book.get().getAuthor().getName());

                return new SuccessDataResult<>(bookWithAuthorDto,"Kayıt bulundu");
            }
        } catch (Exception exception) {
            throw new ExceptionNotFound(exception.getMessage());
        }
       // return bookRepository.findBookAndAuthorUseDto(bookName);
    }

    @Override
    public Result addBook(BookSaveDto bookSaveDto) {


        try {
            Optional<Author> optionalAuthor = this.authorRepository.findAuthorByName(bookSaveDto.getAuthorName());
            Optional<Category> optionalCategory = this.categoryRepository.findCategoryByCategoryName(bookSaveDto.getCategoryName());

            if (optionalAuthor.isPresent() & optionalCategory.isPresent()) {

                Book book = bookSaveMapper.dtoToEntity(bookSaveDto);
                book.setAuthor(optionalAuthor.get());
                book.setCategory(optionalCategory.get());
                bookRepository.save(book);
            } else if (optionalAuthor.isPresent() & !optionalCategory.isPresent()){
                throw new SQLException("Kategori listede bulunmuyor. Kategoriyi listeye ekleyin.");

            }
            else if (!optionalAuthor.isPresent() & optionalCategory.isPresent()) {
                throw new SQLException("Yazar listede bulunmuyor. Öncelikle yazarı ekleyin");

            }
            else {
                throw new SQLException("Hem yazar hem kategori listede bulunmuyor. Lütfen tekrar girin.");
            }
        }
        catch (Exception exception) {
            throw new ExceptionNotFound(exception.getMessage());
        }

        return new SuccessResult("kitap eklendi");

    }

    @Override
    public Result deleteBook(String bookName) {
       Optional<Book>book= bookRepository.findBookByBookName(bookName);
       try {
           if (book.isPresent()){
               bookRepository.delete(book.get());
           }
           else{
               throw new NotFoundException("Kitap bulunamadı");
           }
       }
       catch (Exception exception){
           throw new ExceptionNotFound(exception.getMessage());
       }

       return new SuccessResult("kitap silindi");
    }
}
