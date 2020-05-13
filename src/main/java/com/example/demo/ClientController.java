package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


@RestController
public class ClientController {
	
	private List<Client> clients = new ArrayList<Client>();
	@Autowired
	private ClientService clientService;
	
	public class Login{
		public boolean log=false;
		public Client client;
		
		public void setClient(Client c)
		{  
			this.client= c;
		}
		public void setLog(boolean b) {
			this.log=b;
		}
		public boolean getLog() {
			return this.log;
		}
		public Client getId()
		{
			return this.client;
		}
	}
	
	
	
	
	@CrossOrigin(origins ="*")
	@RequestMapping(value = "/login", method = RequestMethod.POST)	 
	public Login ClientLogin(@Valid @RequestBody Client client) {
		List<Client> clients = new ArrayList<>();
		clients= clientService.getClients();
		 Login login=new Login();
		
		 for(int i=0 ; i<clients.size();i++)
		{
			if(client.getMail().equals(clients.get(i).getMail()))	//is client in database
			{
				if(client.getPassword().equals(clients.get(i).getPassword())) { 
					
					
					login.setClient(clients.get(i));
					login.setLog(true);
					return login;
				}
				
				else {
					login.setClient(null);
					login.setLog(false);
					return login;
				}
			}
		}
		 return login;
	}

	@CrossOrigin(origins ="*")
	@PostMapping(value = "/register")
	public void registerNewClient(@Valid @RequestBody Client client) {
	
		List<Client> clients = new ArrayList<>();
		clients= clientService.getClients();
		boolean exist = false;
		if(!((client.getMail().contains("@")) && (client.getMail().contains(".com"))))
		{System.out.println("gecerli mail giriniz");
		System.out.println(client.getClient_id()+client.getClient_name());
		}
		else
		{
			for(int i=0 ; i<clients.size();i++)
			{
				if(client.getMail().equals(clients.get(i).getMail()))
				{	exist = true;
					System.out.println("gecerli mail giriniz");
					break;
				}
			}
			if(!exist)
			clientService.addClient(client); System.out.println("kayıt basarılı");
		}
		
	}
	
	
	@CrossOrigin(origins ="*")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Client> GetClients() {
		return clientService.getClients();
	}
	@CrossOrigin(origins ="*")
	@RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
	public Client getClient(@PathVariable int id){
		return clientService.getClient(id);
	}
	@CrossOrigin(origins ="*")
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public void setClient(@RequestBody Client client){
		clientService.addClient(client);
	}
	@CrossOrigin(origins ="*")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable int id){
		clientService.deleteClient(id);
	}
	
	
	@CrossOrigin(origins ="*")
	@RequestMapping(value ="/return/{user}",method = RequestMethod.GET)
	public String silinecek(@PathVariable String user) {
		if(user != null)
		return user;
		else return null;
	}

	
}
 