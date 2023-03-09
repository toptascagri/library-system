package com.example.library.repository;
import com.example.library.dto.BookSaveDto;
import com.example.library.dto.BookWithAuthorDto;
import com.example.library.entity.Book;
import com.example.library.results.DataResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface BookRepository extends JpaRepository <Book,Integer> {



    Optional<Book> findBookByBookName(String bookName);

    @Query(value = "select * from books b inner join authors a on (a.id = b.author_id) and b.book_name =:bookName",nativeQuery = true)
    BookWithAuthorDto findBooksWithAuthor(String bookName);

    @Query("Select new com.example.library.dto.BookWithAuthorDto (b.bookName,a.name) from Book b Inner Join Author a on (a.id = b.author.id) and b.bookName =:bookName")
    List<BookWithAuthorDto> findBookAndAuthorUseDto(String bookName);

    @Query("Select new com.example.library.dto.BookSaveDto (b.bookName,b.description,a.name,c.categoryName) from Book b Inner Join Author a on a.id=b.author.id Inner Join Category c on c.id=b.category.id")
    List<BookSaveDto>getAllBookForUser();

    @Query("Select new com.example.library.dto.BookSaveDto (b.bookName,b.description,a.name,c.categoryName) from Book b Inner Join Author a on a.id=b.author.id Inner Join Category c on c.id=b.category.id and b.id=:id")
    BookSaveDto getBookById(Integer id);

/*
     @Query(value = "select * from  employee where extract(MONTH from birthday) = :currentMount",nativeQuery = true)
     List<EmployeeEntity> getEmployeeListByBirthday(Integer currentMount);



    @Query("Select new tr.com.gabim.izinyonetimi.dto.employee.EmployeeWithDepartmentAndPositionDto (p.id,p.tcNo,p.name,p.surname,p.startDateOfWork,p.leaveDateOfWork,p.birthday,p.phoneNumber,p.email,p.userType, c.name,m.name)
     From  DepartmentEntity  c  Inner Join c.employees p Inner Join p.position m ")
    List<EmployeeWithDepartmentAndPositionDto> getEmployeeWithPosition();
  */

}
