package org.wayne.bookmanagermaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wayne.bookmanagermaster.dao.BookDAO;
import org.wayne.bookmanagermaster.model.Book;
import org.wayne.bookmanagermaster.model.enums.BookStatusEnum;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDAO bookDAO;

    public List<Book> getAllBooks(){
        return bookDAO.selectAll();
    }

    public void addBooks(Book book){
        bookDAO.addBook(book);
    }

    public void deleteBooks(int id){
        bookDAO.updateBookStatus(id, BookStatusEnum.DELETE.getValue());
    }

    public void recoverBooks(int id){
        bookDAO.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
    }
}
