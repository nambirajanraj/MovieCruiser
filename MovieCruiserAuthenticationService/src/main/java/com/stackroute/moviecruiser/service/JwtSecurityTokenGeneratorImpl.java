package com.stackroute.moviecruiser.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.moviecruiser.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator{

	public JwtSecurityTokenGeneratorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, String> generateTokens(User user) {
		// TODO Auto-generated method stub
		
		String jwtToken=null;
		
		jwtToken = Jwts.builder()
				.setSubject(user.getUserId())
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.compact();
		
		Map<String,String> map=new HashMap<>();
		map.put("token", jwtToken);
		map.put("meassage","succesfully logged in");
		return map;
	}

}
