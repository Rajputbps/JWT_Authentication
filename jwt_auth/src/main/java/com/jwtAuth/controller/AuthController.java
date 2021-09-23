package com.jwtAuth.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtAuth.dao.RoleDaoRepository;
import com.jwtAuth.dao.UserDaoRepository;
import com.jwtAuth.model.JwtResponse;
import com.jwtAuth.model.ResponseMessage;
import com.jwtAuth.model.Role;
import com.jwtAuth.model.RoleName;
import com.jwtAuth.model.Users;
import com.jwtAuth.request.Login;
import com.jwtAuth.request.SignUp;
import com.jwtAuth.security.JwtProvider;
import com.jwtAuth.servicedao.UserServiceDao;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	 private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceDao userServiceDao;
	
	 

	 
	 @Autowired
	 UserDaoRepository  userDao;
	 
	 @Autowired
	 RoleDaoRepository roleDao;
	 
	 @Autowired
	 private PasswordEncoder encoder;

	 @Autowired
	 private JwtProvider jwtProvider;

	 
	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getEmail(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtProvider.generateJwtToken(authentication);
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getAuthorities()));
	    }           

	 
	 
	 
	 
	  @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUp signUpRequest) {

	        if(userDao.existsByEmail(signUpRequest.getEmail())) {
	            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
	                    HttpStatus.BAD_REQUEST);
	        }

	        // Creating user's account
	        Users user = new Users(signUpRequest.getFname(),signUpRequest.getLname(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getPhone(),signUpRequest.getEmail());

 
	        Set<String> strRoles = signUpRequest.getRole();
	        Set<Role> roles = new HashSet<>();

	        strRoles.forEach(role -> {
	        	switch(role) {
		    		case "admin":
		    			Role adminRole = roleDao.findByName(RoleName.ROLE_ADMIN)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		    			roles.add(adminRole);
		    			
		    			break;
		    		case "pm":
		            	Role pmRole = roleDao.findByName(RoleName.ROLE_PM)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		            	roles.add(pmRole);
		            	
		    			break;
		    		default:
		        		Role userRole = roleDao.findByName(RoleName.ROLE_USER)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		        		roles.add(userRole);        			
	        	}
	        });
	        
	        user.setRoles(roles);
	        userDao.save(user);

	        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"),HttpStatus.OK);
	    }
	  
	  @GetMapping("/UserProfile")
	  @PreAuthorize("hasRole('USER')")
		public Optional<Users> getUser( Authentication  authentication) {
		  System.out.println(authentication.getName().toString());
	       return null;
		}
	  
}
