package com.gallery.gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;



import com.gallery.gallery.model.User;
import com.gallery.gallery.service.UserService;
import com.gallery.gallery.utils.JWTUtil;


@RestController
@RequestMapping("")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/api/login") 
    public ResponseEntity<?> login(@RequestParam String name, @RequestParam String password){
        User usr = new User();
        usr.setId(1);
        usr.setName(name);
        usr.setPassword(password);
        User userLog = userService.getUserByCredentials(usr);
        if ( userLog != null ){

            String tokenJwt = jwtUtil.create(String.valueOf(userLog.getId()), userLog.getName());

            return new ResponseEntity<>(tokenJwt, HttpStatus.OK);
        }
        return new ResponseEntity<>("FAIL",HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = ":")
    public String index(){
        return "../static/index";
    }
    
}
