package org.wayne.bookmanagermaster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wayne.bookmanagermaster.model.Book;
import org.wayne.bookmanagermaster.model.User;
import org.wayne.bookmanagermaster.service.BookService;
import org.wayne.bookmanagermaster.service.HostHolder;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    HostHolder hostHolder;


    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public String bookList(Model model) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadAllBoosView(model);
        return "book/books";
    }

    @RequestMapping(path = {"/books/add"}, method = {RequestMethod.GET})
    public String addBook() {
        return "book/addbook";
    }

    @RequestMapping(path = {"/books/add/do"}, method = {RequestMethod.POST})
    public String doAddBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") String price
    ) {
        Book book = new Book();
        book.setBookName(name);
        book.setBookAuthor(author);
        book.setBookPrice(price);
        bookService.addBooks(book);

        return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})
    public String deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBooks(bookId);
        return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
    public String recoverBoos(@PathVariable("bookId") int bookId) {
        bookService.recoverBooks(bookId);
        return "redirect:/index";
    }

    /**
     * 为model加载所有的书籍
     *
     * @param model
     */
    private void loadAllBoosView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }
}
