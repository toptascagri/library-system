package com.example.library.mapper;

import com.example.library.dto.BookSaveDto;
import com.example.library.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookSaveMapper {

    Book dtoToEntity(BookSaveDto dto);
    BookSaveDto entityToDto(Book book);
}
