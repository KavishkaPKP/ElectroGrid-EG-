package com.service.compailnts.controller;

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

import com.service.compailnts.model.Complains;
import com.service.compailnts.repo.ComplainsRepo;

@RestController
@RequestMapping("/complain-service")
public class ComplainController {

	@Autowired
	ComplainsRepo complainsRepo;
	
	@PostMapping("/save")
	public ResponseEntity<Complains> createComplain(@RequestBody Complains complains){
		try {
			Complains complains2=complainsRepo.save(complains);
			return new ResponseEntity<>(complains2,HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find")
	 public List<Complains> getAllComplains(){
		 return complainsRepo.findAll();
	 }
	 
	 @GetMapping("/complain/{id}")
	  public ResponseEntity<Complains> getComById(@PathVariable("id") Integer id) {
	    Optional<Complains> complainData = complainsRepo.findById(id);
	    if (complainData.isPresent()) {
	      return new ResponseEntity<>(complainData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @DeleteMapping("/del-complain/{id}")
	  public ResponseEntity<HttpStatus> deleteComplain(@PathVariable("id") Integer id) {
	    try {
	      complainsRepo.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @PutMapping("/update-c/{id}")
	  public ResponseEntity<Complains> updateBill(@PathVariable("id") Integer id, @RequestBody Complains complains) {
	    Optional<Complains> complainData = complainsRepo.findById(id);
	    if (complainData.isPresent()) {
	    	Complains complains2=complainsRepo.save(complains);
			return new ResponseEntity<>(complains2,HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
