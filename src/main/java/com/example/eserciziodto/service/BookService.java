package com.example.eserciziodto.service;

import com.example.eserciziodto.DTO.BookDTO;
import com.example.eserciziodto.entity.Book;
import com.example.eserciziodto.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public BookDTO addBook(Book book) {

        Book book1 = bookRepository.save(book);
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(book1.getId());
        bookDTO.setAuthor(book1.getAuthor());
        bookDTO.setTitle(book1.getTitle());
        bookDTO.setIsbn(book1.getIsbn());

        return bookDTO;
    }

    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();

        for(Book book : books) {
            BookDTO bookDTO = new BookDTO();

            bookDTO.setId(book.getId());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setIsbn(book.getIsbn());

            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    public Optional<BookDTO> getById(Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        if(bookOpt.isPresent()) {
            BookDTO bookDTO = new BookDTO();

            bookDTO.setId(bookOpt.get().getId());
            bookDTO.setTitle(bookOpt.get().getTitle());
            bookDTO.setAuthor(bookOpt.get().getAuthor());
            bookDTO.setIsbn(bookOpt.get().getIsbn());

            return Optional.of(bookDTO);
        }
        return Optional.empty();
    }

    public Optional<BookDTO> updateBook(Long id, Book book) {

        Optional<Book> bookOpt = bookRepository.findById(id);

        if(bookOpt.isPresent()) {
            bookOpt.get().setAuthor(book.getAuthor());
            bookOpt.get().setTitle(book.getTitle());
            bookOpt.get().setIsbn(book.getIsbn());
            Book savedBook = bookRepository.save(bookOpt.get());

            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(savedBook.getId());
            bookDTO.setAuthor(savedBook.getAuthor());
            bookDTO.setTitle(savedBook.getTitle());
            bookDTO.setIsbn(savedBook.getIsbn());

            return Optional.of(bookDTO);
        }
        return Optional.empty();
    }

    public Optional<BookDTO> deleteBook(Long id) {

        Optional<Book> bookOpt = bookRepository.findById(id);

        if(bookOpt.isPresent()) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(bookOpt.get().getId());
            bookDTO.setTitle(bookOpt.get().getTitle());
            bookDTO.setAuthor(bookOpt.get().getAuthor());
            bookDTO.setIsbn(bookOpt.get().getIsbn());

            bookRepository.deleteById(id);

            return Optional.of(bookDTO);
        }
        return Optional.empty();
    }

    public Optional<String> getStatusById(Long id) {

        Optional<String> statusOpt = bookRepository.findStatusById(id);

        if(statusOpt.isPresent()) {
            return statusOpt;
        }
        return Optional.empty();
    }

    public Optional<String> updateStatus(Long id, String status) {

        Optional<Book> bookOpt = bookRepository.findById(id);

        if(bookOpt.isPresent()) {
            bookOpt.get().setStatus(status);
            Book savedBook = bookRepository.save(bookOpt.get());

            return Optional.of(savedBook.getStatus());
        }
        return Optional.empty();
    }

}
