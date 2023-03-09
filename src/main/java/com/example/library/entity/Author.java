package com.example.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column (name = "age")
    private int age;
    @Column(name = "nationality")
    private String nationality;

    @Column(name = "gender")
    private String gender;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "author")
    private List<Book> booksHasAuthor;





}
