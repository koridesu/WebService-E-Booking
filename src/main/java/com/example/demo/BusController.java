package com.example.demo;

import java.util.ArrayList;
import java.util.List;


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
			
			for(int index=0; index < searched_busses.get(i).getMax_seats();index++)
			{	
				temp_response.seats.add(0);
			}
			
			for(int j = 0; j<books.size();j++)
			{
				if(searched_busses.get(i).getBus_id() == books.get(j).getBook_code())
				{	
					temp_response.seats.set(books.get(j).getSeat()-1, 1);
				}	
			}
			temp_response.bus=searched_busses.get(i);
			res.add(temp_response);
			temp_response=null;
		}

		
		return res;
	}
	
	
	@RequestMapping(value = "/getBusInfo", method = RequestMethod.POST)
	public List<Bus> getBusInfo(@Valid @RequestBody String s) {
		
		s=s.substring(0,s.length()-1);	
		List<Bus> busses = new ArrayList<>();
		List<Bus> temp = new ArrayList<>();
		busses=bus_service.getBusses();
		for(int i=0; i<busses.size();i++)
		{
			if(Integer.parseInt(s)==busses.get(i).getBus_id())
			{
				temp.add(busses.get(i));
			}
		}
		return temp;
	}
	

	@RequestMapping(value="/addbus",method = RequestMethod.POST)
	public void AddBus(@Valid @RequestBody Bus bus) {
		
		Bus b = new Bus();
		b.setArrival("ankara");
		b.setDeparture("bolu");
		b.setCompany_id(2);
		b.setCompany_name("Metro");
		b.setDate("201020");
		b.setTime("20:00");
		b.setPrice(20);
		b.setMax_seats(20);
		b.setBus_id(40);
		System.out.println(b.getBus_id());
		System.out.println(b.getClass());
		bus_service.addBus(bus);
	}
	
	@RequestMapping(value="/getbusses",method = RequestMethod.POST)
	public List<Bus> GetAllBusses(@Valid @RequestBody String id){
		List<Bus> desiredbusses = new ArrayList<Bus>();
		List<Bus> allbusses = bus_service.getBusses();
		
		id=id.substring(0,id.length()-1);
		
		int Id = Integer.parseInt(id);
		System.out.println(id);
		for(int i =0;i<allbusses.size();i++)
		{
			if(allbusses.get(i).getCompany_id()==Id) {
				desiredbusses.add(allbusses.get(i));
			
			}
		}
				
		return desiredbusses;
	}
	
	
	
}

