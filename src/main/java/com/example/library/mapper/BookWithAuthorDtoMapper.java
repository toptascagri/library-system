package com.example.library.mapper;

import com.example.library.dto.BookWithAuthorDto;
import com.example.library.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookWithAuthorDtoMapper {

    BookWithAuthorDto entityToDto(Book book);
    Book dtoToEntity(BookWithAuthorDto dto);
}
