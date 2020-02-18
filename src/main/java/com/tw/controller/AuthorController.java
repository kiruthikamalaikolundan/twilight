package com.tw.controller;

import java.util.List;
//import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.model.Author;
import com.tw.repository.AuthorRepository;

@RestController
@RequestMapping("/api/aut")
public class AuthorController {
	@Autowired
	private AuthorRepository authorRepository;
	
	@GetMapping("/author")
	public List<Author> getAllAuthorDetiails()
	{
		return authorRepository.findAll();
	}
	
	
	@GetMapping("/author/{id}")
	public Author getDetails(@PathVariable Integer id)
	{
		Optional<Author> var=authorRepository.findById(id);
		Author au=null;
		if(var.isPresent())
		{
			au=var.get();
		}
		else
		{
			System.out.println("Record not found");
		}
		return au;
	}
	
	
	@PostMapping("/author")
	public Author postDetails(@Valid @RequestBody Author au)
	{
		return authorRepository.save(au);
	}
	
	@PutMapping("/author/{id}")
	public String updateDetails(@PathVariable(value="id") int id,@Valid @RequestBody Author authorDetails)
	{
		Optional<Author> op=authorRepository.findById(id);
		Author au=null;
		if(op.isPresent())
		{
			au=op.get();
			au.setName(authorDetails.getName());
			au.setAddress(authorDetails.getAddress());
			au.setDescription(authorDetails.getDescription());
			authorRepository.save(au);
			return "updated successfully";
		}
		else
		{
			return "Record not found";
		}
		
	}
	
	@DeleteMapping("/author/{id}")
	public String deleteDetails(@PathVariable(value="id") int id)
	{
		Optional<Author> op=authorRepository.findById(id);
		Author au=null;
		if(op.isPresent())
		{
			au=op.get();
			authorRepository.delete(au);
			return "Record deleted Successfully";
		}
		else
		{
			return "Record not found";
		}
	}
	

}
