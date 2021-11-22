package com.example.demo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	private BusService busService;
	
	@CrossOrigin(origins ="*")
	@RequestMapping(value = "/companylogin", method = RequestMethod.POST)	 
	public Company Login(@Valid @RequestBody Company company)
	{
		
		List<Company> companies = companyService.getCompany();
		System.out.println(company.getCompany_name()+ company.getPassword());
	
		
		for(int i = 0; i<companies.size();i++)
		{
			if(company.getCompany_name().equals(companies.get(i).getCompany_name()) && company.getPassword().equals(companies.get(i).getPassword()))
			{	
				return companies.get(i);
			}
		}
		return null;
	}
	
	
	
	
	
	
}
