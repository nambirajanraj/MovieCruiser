package com.stackroute.moviecrusierserverapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecrusierserverapp.domain.Movie;
import com.stackroute.moviecrusierserverapp.exception.MovieAlreadyExistsException;
import com.stackroute.moviecrusierserverapp.exception.MovieNotFoundException;
import com.stackroute.moviecrusierserverapp.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{

    public final transient MovieRepository movieRepo;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}

	
	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		// TODO Auto-generated method stub
		final Optional<Movie> object=movieRepo.findById(movie.getId());
		if(object.isPresent())
		{
			throw new MovieAlreadyExistsException("Could not save movie , movie already exits");
		}
		movieRepo.save(movie);
		return true;

	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie updateMovie=movieRepo.findById(movie.getId()).orElse(null);
		if (updateMovie == null) {
			throw new MovieNotFoundException("Could not update. Movie not found");
		}
		updateMovie.setComments(movie.getComments());
		movieRepo.save(updateMovie);
		return updateMovie;

	}

	@Override
	public boolean deleteMovieById(Integer id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie=movieRepo.findById(id).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Could not delete. Movie not found");
		}
        movieRepo.delete(movie);
		return true;

	}

	@Override
	public Movie getMovieById(Integer id) throws MovieNotFoundException {
		
		final Movie movie = movieRepo.findById(id).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found");
		}
		return movie;

	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		// TODO Auto-generated method stub
		return this.movieRepo.findByUserId(userId);
	}

}
