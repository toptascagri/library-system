package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveDto {
    private String bookName;
    private String description;
    private String authorName;
    private String categoryName;
}
