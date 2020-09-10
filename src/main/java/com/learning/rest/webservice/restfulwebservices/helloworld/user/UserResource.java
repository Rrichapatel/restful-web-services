package com.learning.rest.webservice.restfulwebservices.helloworld.user;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUser(){
		
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		
		//Optional<User> user = service.findOne(id);
		//if(!user.isPresent())
		
		User user = service.findOne(id);
		if(user == null)
			throw new UserNotFoundException("ïd - " +id);
		
		//return user;
		
		//HATEOAS - to return links to perform other operations along with data
		//HATEOAS - enables us to add links using the methods
		
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUser());
	    resource.add(linkTo.withRel("all-users"));

	    return resource;
		
	}
	
	//input - details of user
	//output - status CREATED and return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> saveeUser(@Valid @RequestBody User user){
		
		//return service.save(user);
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		
		User user = service.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("ïd - " +id);
		
		return ResponseEntity.noContent().build();
	}

}
