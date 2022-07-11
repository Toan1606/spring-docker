package com.codedecode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.API.DataAPI;
import com.codedecode.demo.entity.API.ErrorAPI;
import com.codedecode.demo.entity.API.FormAPI;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/add")
	public ResponseEntity<User> addNewPosting() {
		userService.addNewUser(null);
		return new ResponseEntity<User>(User.builder().build(), HttpStatus.CREATED);	
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Hùng 
	 * 
	 */
	@GetMapping("/getInfoCandidate/{idUser}")
	public ResponseEntity<FormAPI> getInfoCandidate(@PathVariable("idUser") int userId){
//		Language language = languageService.findLanguageByLanguageId(languageId);
		User user = userService.findUserById(userId);
		FormAPI result = new FormAPI();
		if(user != null) {
			result.setDate(new DataAPI(user));
			result.setMessage("lấy thông tin người dùng thành công");
		} else {
			result.setError(new ErrorAPI(200));
			result.setMessage("người dùng không tồn tại");
		}
		return new ResponseEntity<FormAPI>(result, HttpStatus.OK);
	}
}
