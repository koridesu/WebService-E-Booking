package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BUS")
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int bus_id;

	@Column 
	private int company_id;
	@Column
	private String company_name;
	
	@Column
	private String departure;
	@Column
	private String arrival;
	
	@Column
	private int max_seats;
	
	@Column
	private String book_code;

	@Column 
	private String date;
	
	public int getBus_id() {
		return bus_id;
	}

	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public int getMax_seats() {
		return max_seats;
	}

	public void setMax_seats(int max_seats) {
		this.max_seats = max_seats;
	}

	public String getBook_code() {
		return book_code;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
