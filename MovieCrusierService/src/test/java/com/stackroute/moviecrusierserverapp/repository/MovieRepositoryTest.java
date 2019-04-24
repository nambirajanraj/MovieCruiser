package com.stackroute.moviecrusierserverapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecrusierserverapp.domain.Movie;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional

public class MovieRepositoryTest {

	
	@Autowired
	private transient MovieRepository movieRepo;

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepo = movieRepository;
	}
	
	@Test
	public void testSaveMovie() throws Exception {
		Movie mov =movieRepo.save(new Movie(0,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"A8"));
		final Movie movie=movieRepo.findById(mov.getId()).get();
		assertThat(movie.getId()).isEqualTo(mov.getId());
	}
	
	@Test
	public void testUpdateMovie() throws Exception {
		Movie mov = movieRepo.save(new Movie(0,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"A48"));
		final Movie movie=movieRepo.findById(mov.getId()).get();		
		assertThat(movie.getTitle()).isEqualTo("Inception");
		
		movie.setComments("hello");
		movieRepo.save(movie);
		final Movie updatedMovie=movieRepo.findById(mov.getId()).get();
		Assert.assertEquals("hello",updatedMovie.getComments());
	}
	
	@Test
	public void testDeleteMovie() throws Exception {
		Movie mov = movieRepo.save(new Movie(0,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"A48"));
		System.out.println(mov.getId());
		final Movie movie=movieRepo.findById(mov.getId()).get();		
		assertThat(movie.getTitle()).isEqualTo("Inception");
		
		movieRepo.delete(movie);
		Assert.assertEquals(Optional.empty(),movieRepo.findById(1));
	}
	
	@Test
	public void testGetMovie() throws Exception {
		Movie mov = movieRepo.save(new Movie(0,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"A48"));
		final Movie movie=movieRepo.findById(mov.getId()).get();		
		Assert.assertEquals(movie.getTitle(),"Inception");
	}
	
	@Test
	public void testGetMyMovies() throws Exception {
		movieRepo.save(new Movie(0,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"A48"));
		movieRepo.save(new Movie(2,12345,"Dark Knight","Wonderful","www.sample.com","2014-05-01",45.5,102,"A48"));
		
		final List<Movie> movieList=movieRepo.findByUserId("A48");
		Assert.assertEquals(movieList.get(1).getTitle(),"Dark Knight");
	}

}
