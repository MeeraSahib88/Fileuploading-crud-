package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Serviecs.UserDetailserviecs;

@RestController
public class UserDetailController {
	
	@Autowired
	UserDetailserviecs userDetailserviecs;
	
	@PostMapping(value = "/insert/productlist")
	public ResponseEntity<?> insertUserDetail(@RequestParam String userrequest,
			@RequestPart(name = "file" ,value = "file" , required = true) MultipartFile file) {
		return userDetailserviecs.insertUserDetail(userrequest,file);
	}
	@GetMapping(value="/get/{userId}")
	public ResponseEntity<?> getByUser(@PathVariable (name = "userId") String userId){
		return userDetailserviecs.getByUser(userId);
		
	}
	@PutMapping(value="/update",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public ResponseEntity<?>updateUserDetalis(
			@RequestPart(name = "file" ,value = "file" , required = true) MultipartFile multipartImage,
			@RequestParam(name = "id" ,required = true) String id,
			@RequestParam(name = "name" ,required = true) String name,
			@RequestParam(name = "phonenumber" ,required = false) BigDecimal phonenumber,
			@RequestParam(name = "email" ,required = false ,defaultValue = "0") String email){
		return userDetailserviecs.updateUserDetalis(multipartImage, id, phonenumber, email,name);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteUser(@PathVariable(name = "id") String id){
		return userDetailserviecs.deleteUser(id);
	}
	
}
