package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class BusController {
	
	@Autowired
	private BusService bus_service;
	@Autowired
	private BookService book_service;
	
	class responseType {
		public Bus bus;
		public List<Integer> seats = new ArrayList<Integer>();
	}

	
	
	@RequestMapping(value = "/expeditions", method = RequestMethod.POST)		 
	public List<responseType> expeditions(@Valid @RequestBody String exp) {
	
		List<responseType> res = new ArrayList<responseType>();
		String departure;
		String arrival;
		String date;
		boolean bool = false;
		int start = 0; 
		
		exp=exp.substring(1,exp.length()-1);	
		String[] s= exp.split(",");	
			
		
		for(int i = 0 ; i<s.length; i++)
			s[i] =s[i].substring(1,s[i].length()-1);
		
		departure=s[0];
		arrival=s[1];
		date=s[2];
			
		List<Bus> searched_busses = new ArrayList<Bus>();
		List<Bus> busses = new ArrayList<Bus>();
		busses = bus_service.getBusses();
		
		
		for(int i = 0 ;i<busses.size();i++)
		{
			if(busses.get(i).getDeparture().equals(departure) && busses.get(i).getArrival().equals(arrival))
			{
				if(busses.get(i).getDate().equals(date))
				{	
					searched_busses.add(busses.get(i));
				}
			}
		}
	
		List<Book> books = new ArrayList<Book>();
		books = book_service.getBooks();

		for(int i = 0;i<searched_busses.size();i++)
		{ 	responseType temp_response=new responseType();
			
			for(int index=0; index < searched_busses.get(i).getMax_seats()+1;index++)
			{	
				temp_response.seats.add(0);
			}
			
			for(int j = 0; j<books.size();j++)
			{
				if(searched_busses.get(i).getBook_code().equals(books.get(j).getBook_code()))
				{
					temp_response.seats.set(books.get(j).getSeat(), 1);
				}	
			}
			temp_response.bus=searched_busses.get(i);
			res.add(temp_response);
			temp_response=null;
		}

		
		return res;
	}
	
	
	
	
	
	
	
}

