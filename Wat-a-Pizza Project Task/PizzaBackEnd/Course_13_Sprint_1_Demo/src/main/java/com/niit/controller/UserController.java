package com.niit.controller;

import com.niit.domain.User;
import com.niit.services.ISecurityTokenGenerator;
import com.niit.services.InterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//http://loacalhost:8095/api/authUser/register
@RestController
@CrossOrigin
@RequestMapping("/api/authUser")
public class UserController {

    InterService interService;
    ISecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(InterService interService, ISecurityTokenGenerator securityTokenGenerator) {
        this.interService = interService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addAll(@RequestBody User user)
   {
       return new ResponseEntity<>(interService.addData(user), HttpStatus.CREATED);
   }
   @PostMapping("/login")
   public ResponseEntity<?> loginCheck(@RequestBody User user)
   {
       User result=interService.loginCheck(user.getEmail(), user.getPassword());
       if (result!=null)
       {
           Map<String,String> map=securityTokenGenerator.tokenGenerator(result);
           return new ResponseEntity<>(map,HttpStatus.OK);
       }
       else
       {
           return new ResponseEntity<>("invalid User or User Does not Exist",HttpStatus.NOT_FOUND);
       }
   }


   @GetMapping("/user")
    public  ResponseEntity<?> getAll()
   {
       return new  ResponseEntity<>(interService.getAllData(),HttpStatus.OK);
   }

   @PutMapping("/user")
    public ResponseEntity<?> updateData(@RequestBody User user)
   {
        return  new ResponseEntity<>(interService.Update(user),HttpStatus.OK);
   }

   @DeleteMapping ("/user{email}")
    public  ResponseEntity<?> deleteData(@PathVariable String email)
   {
       return new ResponseEntity<>(interService.DeleteData(email),HttpStatus.OK);
   }

   @GetMapping ("/user/{firstName}")
    public  ResponseEntity<?> findByName(@PathVariable String firstName)
   {
       return  new ResponseEntity<>(interService.getAllUserByFirstName(firstName),HttpStatus.FOUND);
   }
}
