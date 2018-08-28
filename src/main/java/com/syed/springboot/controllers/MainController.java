package com.syed.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syed.springboot.model.User;
import com.syed.springboot.services.UserService;


@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	
	// Rest calls for add client/pani wala
		@RequestMapping(value = "/addClient", method = RequestMethod.POST)
		public ResponseEntity<Object> createClient(@RequestBody User userbean) {

			User user = new User();
			User clientExists = new User();//userService.findUserByEmail(userbean.);

			Map<String, Object> response = new HashMap<>();
			try {
				if (clientExists != null) {
					response.put("status", "Failure");
					response.put("message", "Please provide a valid email");
					return new ResponseEntity<Object>(response, HttpStatus.OK);
				}

				else {
					System.out.println("clientdata" + userbean);
					userService.saveClient(userbean);

 

					response.put("user", userbean);
					response.put("status", "Success");
					return new ResponseEntity<Object>(response, HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.put("status", "Failure");
				response.put("message", "Something went wrong Please try again....");

				return new ResponseEntity<Object>(response, HttpStatus.OK);

			}

		}


}
