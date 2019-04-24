package com.stackroute.moviecrusierserverapp.controller;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecrusierserverapp.MoviecrusierserverappApplication;
import com.stackroute.moviecrusierserverapp.domain.Movie;
import com.stackroute.moviecrusierserverapp.service.MovieService;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
@ContextConfiguration(classes=MoviecrusierserverappApplication.class)
public class MovieControllerTest {
	
	public MovieControllerTest() {
		// TODO Auto-generated constructor stub
	}
	

	@Autowired
	private transient MockMvc mvc;

	@MockBean
	private transient MovieService movieService;

	private  Movie movie;

	@InjectMocks
	private MovieController controller;
	private String token;
	private List<Movie> movieList=new ArrayList<Movie>();

	@Before
	public void setUp() {
		movie=new Movie(1,1234,"Inception","Wonderful","www.sample.com","2014-05-01",45.5,102,"A48");
		movieList.add(movie);
		movie=new Movie(2,12345,"Dark Knight","Wonderful","www.sample.com","2014-05-01",45.5,102,"A48");
		movieList.add(movie);	
		
		token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTYwOTM4MzN9.ReZpwlMPyP28cbKGskfLSaYD3fcSb6_xysVNOHYO1PQ";
	}

	@Test
	public void testSaveNewMovieSuccess() throws Exception {
		when(movieService.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/movieservice").header("Authorization",token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isCreated());
		verify(movieService,times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void testUpdateMovieSuccess() throws Exception {
		movie.setComments("beautiful");
		when(movieService.updateMovie(movie)).thenReturn(movieList.get(0));
		mvc.perform(put("/api/movieservice/{id}",1).header("Authorization", token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isOk()).andDo(print());
		verify(movieService,times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void testDeleteMovieById() throws Exception {
		when(movieService.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/movieservice/{id}",1).header("Authorization", token)).andExpect(status().isOk());
		verify(movieService,times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void testGetMovieById() throws Exception {
		when(movieService.getMovieById(1)).thenReturn(movieList.get(0));
		mvc.perform(get("/api/movieservice/{id}",1).header("Authorization", token)).andExpect(status().isOk());
		verify(movieService,times(1)).getMovieById(1);
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void testGetMyMovies() throws Exception {
		when(movieService.getMyMovies("A48")).thenReturn(movieList);
		mvc.perform(get("/api/movieservice").header("Authorization", token)).andExpect(status().isOk()).andDo(print());
		verify(movieService,times(1)).getMyMovies("101");
		verifyNoMoreInteractions(movieService);
	}

	private String jsonToString(final Object obj) {
		String result;
		try{
			final ObjectMapper mapper=new ObjectMapper();
			final String  jsonContent=mapper.writeValueAsString(obj);
			result=jsonContent;
		}
		catch(JsonProcessingException e)
		{
			result="JsonProcessingException";
		}
		return  result;
	}
}

