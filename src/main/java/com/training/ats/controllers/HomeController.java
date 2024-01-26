package com.training.ats.controllers;

import com.training.ats.auth.JwtService;
import com.training.ats.models.ATSUser;
import com.training.ats.models.Applicant;
import com.training.ats.models.Recruiter;
import com.training.ats.repositories.ATSUserRepository;
import com.training.ats.repositories.ApplicantRepository;
import com.training.ats.repositories.RecruiterRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

  @Autowired
  AuthenticationManager manager;

  @Autowired
  ATSUserRepository userRepository;
  @Autowired
  JwtService jwtService;

  @GetMapping("/")
  public ResponseEntity<String> homePage() {
    return ResponseEntity.ok("this is an authenticated endpoint");
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {


    ATSUser user = new ATSUser(registerRequest.getUsername(), "test", "test", registerRequest.getPassword());

    userRepository.save(user);
    String token = jwtService.generateJwt(user);
    System.out.println(token);
    return ResponseEntity.ok(token);
  }




}
