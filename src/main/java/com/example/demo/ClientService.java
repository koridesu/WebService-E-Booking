package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;	
	
	public List<Client> getClients(){
		List<Client> clients = new ArrayList<>();
		clientRepository.findAll().forEach(clients::add);
		return clients;
	}
	
	public Client getClient(int id){
		Optional<Client> optionalClient = clientRepository.findById(id);
		if(optionalClient.isPresent()) {
			return optionalClient.get();
		}
		else return null;
	}

	
	
	public void updateClient(Client c) {
		clientRepository.save(c);
	}
	public void addClient(Client c) {
		clientRepository.save(c);
	}
	
	public void deleteClient(int id) {
		clientRepository.delete(getClient(id));
	}
	
	
	
	
}
