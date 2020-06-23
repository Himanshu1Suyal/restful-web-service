package com.spring.AllFiles.controller;
//import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.AllFiles.exception.UserNotFoundException;
import com.spring.AllFiles.model.User;
import com.spring.AllFiles.service.UserDaoService;




@RestController
public class UserController {

	@Autowired
	UserDaoService service;
	
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	/*
	 * @GetMapping(path="/users/{id}") public EntityModel<User>
	 * retrieveUser(@PathVariable int id) {
	 * 
	 * User user = service.findOne(id); if(user==null) throw new
	 * UserNotFoundException("id-   "+id+ "is not found ");
	 * 
	 * 
	 * EntityModel<User> resource = new EntityModel<User>(user);
	 * 
	 * ControllerLinkBuilder linkTo =
	 * linkTo(methodOn(this.getClass()).retrieveAllUsers());
	 * resource.add(linkTo.withRel("all-users")); return resource;
	 * 
	 * }
	 */
	
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user = service.deleteById(id);
		if(user==null) 
			throw new UserNotFoundException("id- is not found " + id);
	}
	
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
	{
		User savedUser = service.save(user);
		
		
		URI location= ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	 @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	  @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<User> updateUser(@PathVariable  Integer id,
	                                                         @RequestBody User user){
	        return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
	    }
}
