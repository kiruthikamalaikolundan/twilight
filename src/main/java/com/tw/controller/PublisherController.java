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

import com.tw.model.Publisher;
import com.tw.repository.PublisherRepository;

@RestController
@RequestMapping("/api/pb")
public class PublisherController {

	@Autowired
	private PublisherRepository publisherRepository;
	
	@GetMapping("/publishers")
	public List<Publisher> getAllPublisher()
	{
		return publisherRepository.findAll();
	}
	
	@GetMapping("/publishers/{id}")
	public Publisher getPublisher(@PathVariable Integer id)
	{
		Optional<Publisher> var=publisherRepository.findById(id);
		Publisher pb=null;
		if(var.isPresent())
		{
			pb=var.get();
			
		}
		else
		{
			System.out.println("Record not found");
		}
		return pb;
		
	}
	
	@PostMapping("/publishers")
	public Publisher createPublisher(@Valid @RequestBody Publisher publisherDetails)
	{
		return publisherRepository.save(publisherDetails);
	}
	
	@PutMapping("/publishers/{id}")
	public String publisherUpdate(@PathVariable(value="id") Integer id,@Valid @RequestBody Publisher publisherDetails)
	{
		Optional<Publisher> op=publisherRepository.findById(id);
		Publisher pu=null;
		if(op.isPresent())
		{
			pu=op.get();
			pu.setName(publisherDetails.getName());
			pu.setAddress(publisherDetails.getAddress());
			publisherRepository.save(pu);
			return "updated successfully";
		}
		else
		{
			return "Record not found";
		}
	}
	
	@DeleteMapping
	public String publisherDelete(@PathVariable(value="id") Integer id)
	{
		Optional<Publisher> op= publisherRepository.findById(id);
		Publisher pb=null;
		if(op.isPresent())
		{
			pb=op.get();
			publisherRepository.delete(pb);
			return "Record deleted Successfully";
		}
		else
		{
			return "Record not found";
		}
	}
}
