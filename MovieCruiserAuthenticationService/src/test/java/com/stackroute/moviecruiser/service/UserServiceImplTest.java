package com.stackroute.moviecruiser.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiser.exception.UserAlreadyExistException;
import com.stackroute.moviecruiser.exception.UserNotFoundException;
import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.repository.UserRepository;

public class UserServiceImplTest {

	public UserServiceImplTest() {
		// TODO Auto-generated constructor stub
	}

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	public User user;

	Optional<User> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("Aritra", "Aritra", "Chowdhury", "aRITRA48", new Date());
		options = Optional.of(user);
	}

	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistException {
		when(userRepository.save(user)).thenReturn(user);
		final boolean flag = userServiceImpl.saveUser(user);
		assertEquals("Cannot Register User ", true, flag);
		verify(userRepository, times(1)).save(user);
		verify(userRepository, times(1)).findById(user.getUserId());

	}

	@Test(expected = UserAlreadyExistException.class)
	public void testSaveUserFailure() throws UserAlreadyExistException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		final boolean flag = userServiceImpl.saveUser(user);
		assertFalse("Cannot Register User ", flag);
		verify(userRepository, times(1)).save(user);
		verify(userRepository, times(1)).findById(user.getUserId());

	}

	@Test
	public void testValidateSuccess() throws UserAlreadyExistException, UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("Aritra", userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

	@Test(expected=UserNotFoundException.class)
	public void testValidateFailure() throws  UserNotFoundException
	{
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		
	}

}
