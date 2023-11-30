package com.store.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.management.exception.ResourceNotFoundException;
import com.store.management.model.ProductDetails;
import com.store.management.repository.AdminRepository;


 
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class AdminController {
	
	@Autowired
	private AdminRepository arepo;
	
	
	//POST- http://localhost:9090/aerocom/api/registerAdmin // not for angular
		@PostMapping("/registerAdmin")
		public String saveAdmin(@RequestBody ProductDetails admin) 
		{
			arepo.save(admin);
			
			return "Admin Registered Sucessfully";
		}
		
		//View all admins
		//GET - http://localhost:9090/aerocom/api/allAdmins  //not for angular
		@GetMapping("/AllAdmins")
		public List<ProductDetails> getAdminList()
		{
			return arepo.findAll();
		}
		
		//Login Admin
		//POST- http://localhost:9090/aerocom/api/loginAdmin
		@PostMapping("/loginAdmin")
		public Boolean loginDealer(@Validated @RequestBody ProductDetails admin) throws ResourceNotFoundException
		{
			Boolean isLogin = false;
			String email =admin.getEmail();
			String password = admin.getPassword();
			
			ProductDetails a = arepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Not an Admin"));
			
			if(email.equals(a.getEmail()) && password.equals(a.getPassword()))
			{
				isLogin=true;
			}
			return isLogin;
		}


}
