package com.example.imdb.viewmodel;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@SuppressWarnings("serial")
@Named("searchView")
@SessionScoped
public class SearchViewModel implements Serializable {
	private Integer fromYear;
	private Integer toYear;
	private String genre;
	private Collection<Movie> movies;
	@Inject
	transient private MovieService movieSrv;

	public SearchViewModel() {
	}

	public Integer getFromYear() {
		return fromYear;
	}

	public void setFromYear(Integer fromYear) {
		this.fromYear = fromYear;
	}

	public Integer getToYear() {
		return toYear;
	}

	public void setToYear(Integer toYear) {
		this.toYear = toYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Collection<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Collection<Movie> movies) {
		this.movies = movies;
	}

	public void doSearch(AjaxBehaviorEvent event) {
		this.movies = movieSrv.findAllMoviesByYearRangeAndGenre(genre, fromYear, toYear);
	}
}
