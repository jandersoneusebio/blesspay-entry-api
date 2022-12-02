package blesspay.entry.controllers;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blesspay.entry.model.to.ErrorTO;
import blesspay.entry.model.to.UserLoginResponse;
import blesspay.entry.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestHeader("document") String document, @RequestHeader("password") String password){
		UserLoginResponse response = userService.login(document, password);
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorTO(400, "User not found, try sign-up."), HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@PostMapping("sign-up")
	public ResponseEntity<?> signUp(){
		userService.signUp();
		return new ResponseEntity<>("Salve", HttpStatus.OK);
	}
	
	@GetMapping("validate-session")
	public ResponseEntity<?> validateSession(@RequestHeader("token") String token){
		
		if(token != null && token.contains("-")) {
			return new ResponseEntity<>("SESSION_OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorTO(401, "Your session has expired, please sign in."), HttpStatus.UNAUTHORIZED);
		}
	}
	
}
