package com.sparta.mg.librarywebapp.controller;

import com.sparta.mg.librarywebapp.model.entities.Author;
import com.sparta.mg.librarywebapp.model.entities.Book;
import com.sparta.mg.librarywebapp.model.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/{id}")
    public String getBook(Model model, @PathVariable Integer id) {
        model.addAttribute("book",
                bookRepository.findById(id).orElse(null));
        return "book";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/book/edit/{id}")
    public String getBookToEdit(@PathVariable Integer id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("bookToEdit", book);
        return "book-edit-form";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "delete-success";
    }


    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute("bookToEdit")Book editedBook) {
        bookRepository.saveAndFlush(editedBook);
        return "edit-success";
    }

    @GetMapping("/book/create")
    public String createBook() {
        return "book-add-form";
    }
    @PostMapping("/createBook")
    public String createAuthor(@ModelAttribute("bookToCreate")Book addedBook) {
        bookRepository.saveAndFlush(addedBook);
        return "create-success";
    }

    @GetMapping("/book/find")
    public String findBook() {
        return "book-find-form";
    }
    @PostMapping("/findBook")
    public String findBook(@ModelAttribute("bookToCreate") Book foundBook) {
        return "redirect:/book/"+foundBook.getId();
    }
}
