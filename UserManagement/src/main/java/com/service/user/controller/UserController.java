package com.service.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.user.model.user;
import com.service.user.repository.UserRepo;

@RestController
@RequestMapping("/user-service")
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/save")
	public ResponseEntity<user> createUser(@RequestBody user user){
		try {
			user users=userRepo.save(user);
			return new ResponseEntity<>(users,HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find")
	 public List<user> getAllusers(){
		 return userRepo.findAll();
	 }
	 
	 @GetMapping("/users/{id}")
	  public ResponseEntity<user> getuserById(@PathVariable("id") Integer id) {
	    Optional<user> userData = userRepo.findById(id);
	    if (userData.isPresent()) {
	      return new ResponseEntity<>(userData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @DeleteMapping("/del-user/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Integer id) {
	    try {
	      userRepo.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @PutMapping("/update-user/{id}")
	  public ResponseEntity<user> updateTutorial(@PathVariable("id") Integer id, @RequestBody user usr) {
	    Optional<user> usrData = userRepo.findById(id);
	    if (usrData.isPresent()) {
	    	user users=userRepo.save(usr);
			return new ResponseEntity<>(users,HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
