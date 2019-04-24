package com.stackroute.moviecruiser.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.moviecruiser.model.User;


public interface SecurityTokenGenerator {
	
	Map<String,String> generateTokens(User user);

}
