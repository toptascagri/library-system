package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author dtoToEntity(AuthorDto authorDto);

    AuthorDto entityToDto(Author author);

}
