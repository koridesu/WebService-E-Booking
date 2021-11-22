package com.example.demo;



import java.util.List;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins="*")
@RestController
public class BookController {

	@Autowired
	private BookService book_service;

	
	@RequestMapping(value="/confirm_ticket",method = RequestMethod.POST)
	public void Confirm_Book(@Valid @RequestBody String exp)
	{
		Book book = new Book();
		exp=exp.substring(1,exp.length()-1);
		String arr[] = exp.split(",");
	
		book.setBook_code(Integer.parseInt(arr[0]));
		book.setSeat(Integer.parseInt(arr[1]));
		book.setClient_id(Integer.parseInt(arr[2]));
		book.setPrice(Integer.parseInt(arr[3]));
		

		book_service.addBook(book);
		/*
		exp=exp.substring(1,exp.length()-1);
		String seat= exp.substring(exp.lastIndexOf(',')+1,exp.length());
		exp = exp.substring(0,exp.lastIndexOf(','));
		
		exp=exp.substring(1,exp.length()-1);
		exp=exp.replace("\"", "");
		
		String arr[] = exp.split(",");

		Book book = new Book();
		
		for(int i = 0 ; i<arr.length;i++) 
		{
			switch (arr[i].substring(0,arr[i].indexOf(":"))) {
			case "bus_id":
				book.setBook_code(Integer.parseInt(arr[i].substring(arr[i].indexOf(":")+1,arr[i].length())));
				break;
			}
		}
		
		book.setSeat(Integer.parseInt(seat));
		*/		
	}
	
	@RequestMapping(value="/mytickets",method = RequestMethod.POST)
	public List<Book> myTickets(@Valid @RequestBody Client client)
	{	
		List<Book> books = new ArrayList<Book>();
		books = book_service.getBooks();
		List<Book> temp = new ArrayList<Book>();
		for(int i = 0; i<books.size();i++ )
		{
			if(books.get(i).getClient_id()==client.getClient_id())
			{
				temp.add(books.get(i));
			}
		}
	
		return temp;
	}
	
	
	
	
	
	
}
