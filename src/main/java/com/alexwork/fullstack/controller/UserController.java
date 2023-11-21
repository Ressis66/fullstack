package com.alexwork.fullstack.controller;

import com.alexwork.fullstack.exception.UserNotFoundException;
import com.alexwork.fullstack.model.User;
import com.alexwork.fullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/user")
  User newUser(@RequestBody User newUser){
    return userRepository.save(newUser);
  }

  @GetMapping("/users")
  List<User> getAllUsers(){
    return  userRepository.findAll();
  }

  @GetMapping("/user/{id}")
  User getUserById(@PathVariable String id){
    return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
  }

  @PutMapping("/user/{id}")
  User updateUser(@RequestBody User newUser, @PathVariable String id){
    return userRepository.findById(id).map(user -> {
      user.setName(newUser.getName());
      user.setPhone(newUser.getPhone());
      user.setEmail(newUser.getEmail());
      return  userRepository.save(user);
    }).orElseThrow(()-> new UserNotFoundException(id));
  }

  @DeleteMapping("/user/{id}")
  String deleteUser(@PathVariable String id){
    if(!userRepository.existsById(id)){
      throw  new UserNotFoundException(id);
    }
    userRepository.deleteById(id);
    return"Id " +id +" deleted successfully!";
  }
}
