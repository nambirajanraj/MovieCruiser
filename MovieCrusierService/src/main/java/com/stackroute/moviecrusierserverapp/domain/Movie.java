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

	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	/**
	 * Id for a movie
	 */
	@Column(name = "movie_id")
	private int movieId;
	/**
	 * name of the movie
	 */

	@Column(name = "title")
	private String title;
	
	
	
	/**
	 * Comments for the movie
	 */
	@Column(name = "comments")
	private String comments;
	
	
	/**
	 * The path of the poster for the movie
	 */
	@Column(name = "poster_path")
	private String poster_path;
 
	
	/**
	 * The release date of the movie
	 */
	@Column(name = "release_date")
	private String release_date;
	
	/**
	 * Average vote of the movie
	 */
	@Column(name = "vote_average")
	private double voteAverage;
	
	/**
	 *Vote Count of the movie
	 */
	@Column(name = "vote_count")
	private int voteCount;
	
	@Column(name = "user_id")
	private String userId;

	public Movie(int id, int movieId, String title, String comments, String poster_path, String release_date,
			double voteAverage, int voteCount, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
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

	public double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieId=" + movieId + ", title=" + title + ", comments=" + comments
				+ ", poster_path=" + poster_path + ", release_date=" + release_date + ", voteAverage=" + voteAverage
				+ ", voteCount=" + voteCount + ", userId=" + userId + "]";
	}

	
	

}
