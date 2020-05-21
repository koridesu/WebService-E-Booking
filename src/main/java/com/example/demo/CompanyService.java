package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;

	
	public Company getCompany(int id){
		Optional<Company> optionalCompany= companyRepository.findById(id);
		if(optionalCompany.isPresent()) {
			return optionalCompany.get();
		}
		else return null;
	}
	
	public List<Company> getCompany(){
		List<Company> companies= new ArrayList<>();
		companyRepository.findAll().forEach(companies::add);
		return companies;
	}
	
	public void updateCompany(Company c) {
		companyRepository.save(c);
	}
	public void addCompany(Company c) {
		companyRepository.save(c);
	}
	
	public void deleteCompany(int id) {
		companyRepository.delete(getCompany(id));
	}
}
