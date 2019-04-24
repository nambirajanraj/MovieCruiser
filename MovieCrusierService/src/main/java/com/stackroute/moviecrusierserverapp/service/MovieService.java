package com.stackroute.moviecrusierserverapp.service;

import java.util.List;

import com.stackroute.moviecrusierserverapp.domain.Movie;
import com.stackroute.moviecrusierserverapp.exception.MovieAlreadyExistsException;
import com.stackroute.moviecrusierserverapp.exception.MovieNotFoundException;


public interface MovieService {
	
public Movie getMovieById(final Integer id) throws MovieNotFoundException;
	
	public boolean saveMovie(final Movie movie)throws MovieAlreadyExistsException;
	
	public Movie updateMovie(final Movie movie)throws MovieNotFoundException;
	
	public List<Movie> getMyMovies(String userId);
	
	public boolean deleteMovieById(final Integer id)throws MovieNotFoundException;

}
