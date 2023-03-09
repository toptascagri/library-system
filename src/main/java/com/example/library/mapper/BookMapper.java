package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book dtoToEntity(BookDto dto);
    BookDto entityToDto(Book entity);
}
