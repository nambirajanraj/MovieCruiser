package com.stackroute.moviecrusierserverapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	private transient MovieRepository movieRepository;

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	@Test
	public void testSaveMovie() throws Exception {
		movieRepository.save(new Movie(1,"superman", "good movie", "www.abc.com", "2015-03-23"));
		final Movie movie = movieRepository.getOne(1);
		assertThat(movie.getId()).isEqualTo(1);
	}
	
	@Test
	public void testUpdateMovie() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23"));
		final Movie movie = movieRepository.getOne(1);
		assertEquals("superman", movie.getName());
		movie.setComments("Hi");
		movieRepository.save(movie);
		final Movie tempMovie = movieRepository.getOne(1);
		assertEquals("Hi", tempMovie.getComments());
	}
	
	@Test
	public void testDeleteMovie() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23"));
		final Movie movie = movieRepository.getOne(1);
		assertEquals("superman", movie.getName());
		movieRepository.delete(movie);
		assertEquals(Optional.empty(), movieRepository.findById(1));
	}
	
	@Test
	public void testGetMovie() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23"));
		final Movie movie = movieRepository.getOne(1);
		assertEquals("superman", movie.getName());
	}
	
	@Test
	public void testGetMyMovies() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23"));
		movieRepository.save(new Movie(2, "superman-1", "good movie", "www.abc.com", "2015-03-23"));
		final List<Movie> movies = movieRepository.findAll();
		assertEquals("superman", movies.get(0).getName());
	}

}
