package com.stackroute.moviecrusierserverapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "comments")
	private String comments;

	@Column(name = "poster_path")
	private String poster_path;

	@Column(name = "release_date")
	private String release_date;

	@Column(name="overview")
	private String overview;
	
	
	public Movie(Integer id, String title, String comments, String poster_path, String release_date, String overview) {
		super();
		this.id = id;
		this.title = title;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.overview = overview;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getPoster_path() {
		return poster_path;
	}


	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}


	public String getRelease_date() {
		return release_date;
	}


	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}


	public String getOverview() {
		return overview;
	}


	public void setOverview(String overview) {
		this.overview = overview;
	}


	public Movie() {
		super();
	}

}
