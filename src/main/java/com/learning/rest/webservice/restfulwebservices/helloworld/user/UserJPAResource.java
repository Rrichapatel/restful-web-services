package com.learning.rest.webservice.restfulwebservices.helloworld.user;


import java.net.URI;

import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUser(){
		
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("ïd - " +id);
		
		//HATEOAS - to return links to perform other operations along with data
		//HATEOAS - enables us to add links using the methods
		
		EntityModel<User> resource = EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUser());
	    resource.add(linkTo.withRel("all-users"));

	    return resource;
		
	}
	
	//input - details of user
	//output - status CREATED and return the created URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("jpa/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllPostsForUser(@PathVariable int id){
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("ïd - " +id);
		return user.get().getPosts();
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody Post post){
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("ïd - " +id);
		
		User user = userOptional.get();
		post.setUser(user);
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
