package org.wayne.bookmanagermaster.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.wayne.bookmanagermaster.model.Book;

import java.util.List;

@Repository
@Mapper
public interface BookDAO {

    @Select("select * from book")
    List<Book> selectAll();

    @Insert("insert into book (book_name,book_author,book_price) values (#{bookName},#{bookAuthor},#{bookPrice})")
    void addBook(Book book);

    @Update("update book set book_status = #{status} where book_id = #{id}")
    void updateBookStatus(@Param("id") int id, @Param("status") int status);
}
