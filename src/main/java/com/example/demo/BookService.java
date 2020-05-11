package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	 
	
	public List<Book> getBooks(){
		List<Book> books= new ArrayList<>();
		bookRepository.findAll().forEach(books::add);
		return books;
	}
	
	public Book getBook(int id){
		Optional<Book> optionalBook= bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}
		else return null;
	}

	public void updateBook(Book b) {
		bookRepository.save(b);
	}
	public void addBook(Book b) {
		bookRepository.save(b);
	}
	
	public void deleteBook(int id) {
		bookRepository.delete(getBook(id));
	}
}
