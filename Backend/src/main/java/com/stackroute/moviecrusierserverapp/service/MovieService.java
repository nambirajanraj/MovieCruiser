package com.stackroute.moviecrusierserverapp.service;

import java.util.List;

import com.stackroute.moviecrusierserverapp.domain.Movie;
import com.stackroute.moviecrusierserverapp.exception.MovieAlreadyExistsException;
import com.stackroute.moviecrusierserverapp.exception.MovieNotFoundException;


public interface MovieService {
	
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;
	
	Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	boolean deleteMovieById(Integer id) throws MovieNotFoundException;
	
	Movie getMovieById(Integer id) throws MovieNotFoundException;
	
	List<Movie> getAllMovies();

}
