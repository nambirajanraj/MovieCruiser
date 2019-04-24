package com.stackroute.moviecruiser.service;

import com.stackroute.moviecruiser.exception.UserAlreadyExistException;
import com.stackroute.moviecruiser.exception.UserNotFoundException;
import com.stackroute.moviecruiser.model.User;

public interface UserService {

	public boolean saveUser(User user)throws UserAlreadyExistException;
	
	public User findByUserIdAndPassword(String userId,String password)throws UserNotFoundException;
}
