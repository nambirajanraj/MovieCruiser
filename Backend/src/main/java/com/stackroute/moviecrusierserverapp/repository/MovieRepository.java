package com.stackroute.moviecrusierserverapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.moviecrusierserverapp.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer>{

}
