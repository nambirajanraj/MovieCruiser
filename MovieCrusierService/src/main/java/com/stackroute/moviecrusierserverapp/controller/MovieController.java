package com.stackroute.moviecrusierserverapp.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecrusierserverapp.domain.Movie;
import com.stackroute.moviecrusierserverapp.exception.MovieAlreadyExistsException;
import com.stackroute.moviecrusierserverapp.exception.MovieNotFoundException;
import com.stackroute.moviecrusierserverapp.service.MovieService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(path="/api/movieservice")
@CrossOrigin
public class MovieController {
	
	private MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@PostMapping
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie, final ServletRequest req, final ServletResponse res) {
		ResponseEntity<?> responseEntity;
		try {
			final HttpServletRequest request=(HttpServletRequest)req;
			final String authHeader=request.getHeader("authorization");
			final String token=authHeader.substring(7);
			final String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			
			movie.setUserId(userId);
			this.movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}		
		return responseEntity;
		
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id, @RequestBody Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			final Movie fetchedMovie = movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		} 
		catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;		
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);
			responseEntity = new ResponseEntity<String>("Movie deleted successfully", HttpStatus.OK);
		} 
		catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		Movie movie=null;
		try {
			movie = movieService.getMovieById(id);		
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} 
		catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	@GetMapping
	public ResponseEntity<?> fetchAllMovies(final ServletRequest req, final ServletResponse res)
	{
		ResponseEntity<?> responseEntity;
		final HttpServletRequest request=(HttpServletRequest)req;
		final String authHeader=request.getHeader("authorization");
		final String token=authHeader.split(" ")[1];
		final String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
	
		
		final List<Movie> movieList=this.movieService.getMyMovies(userId);
		
		responseEntity=new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
		
		return responseEntity;
	}


}
