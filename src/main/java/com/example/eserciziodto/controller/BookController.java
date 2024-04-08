package com.example.eserciziodto.controller;

import com.example.eserciziodto.DTO.BookDTO;
import com.example.eserciziodto.entity.Book;
import com.example.eserciziodto.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book();

        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());

        BookDTO savedBookDTO = bookService.addBook(book);

        return ResponseEntity.ok().body(savedBookDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAll() {
        List<BookDTO> books = bookService.getAll();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        Optional<BookDTO> bookDTO = bookService.getById(id);

        if(bookDTO.isPresent()) {
            return ResponseEntity.ok().body(bookDTO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book book = new Book();

        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());

        Optional<BookDTO> bookDTOOpt = bookService.updateBook(id, book);

        if (bookDTOOpt.isPresent()) {
            return ResponseEntity.ok().body(bookDTOOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        Optional<BookDTO> bookDTOOpt = bookService.deleteBook(id);

        if (bookDTOOpt.isPresent()) {
            return ResponseEntity.ok().body(bookDTOOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/book-status/{id}")
    public ResponseEntity<String> getStatusById(@PathVariable Long id) {
        Optional<String> statusOpt = bookService.getStatusById(id);

        if(statusOpt.isPresent()) {
            return ResponseEntity.ok().body(statusOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/book-status/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<String> statusOpt = bookService.updateStatus(id, status);

        if(statusOpt.isPresent()) {
            return ResponseEntity.ok().body(statusOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

}
