package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	
	@Autowired
	private BusRepository busRepository;
	
	public List<Bus> getBusses(){
		List<Bus> busses= new ArrayList<>();
		busRepository.findAll().forEach(busses::add);
		return busses;
	}
	
	public Bus getBus(int id){
		Optional<Bus> optionalBus= busRepository.findById(id);
		if(optionalBus.isPresent()) {
			return optionalBus.get();
		}
		else return null;
	}

	public void updateBus(Bus b) {
		busRepository.save(b);
	}
	public void addBus(Bus b) {
		busRepository.save(b);
	}
	
	public void deleteBook(int id) {
		busRepository.delete(getBus(id));
	}
	
	
	
}
