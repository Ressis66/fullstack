package com.alexwork.fullstack.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(String id){
    super("Could not the user with id: " +id);
  }
}
