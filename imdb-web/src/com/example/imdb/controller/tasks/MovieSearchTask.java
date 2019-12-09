package com.example.imdb.controller.tasks;

import java.util.Collection;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

public class MovieSearchTask implements Runnable {

	private AsyncContext ctx;
	private MovieService movieSrv;

	public MovieSearchTask(AsyncContext actx, MovieService movieSrv) {
		this.ctx = actx;
		this.movieSrv = movieSrv;
	}

	@Override
	public void run() {
		ServletRequest request = ctx.getRequest();
		String genre = request.getParameter("genre");
		Collection<Movie> movies = movieSrv.findAllMoviesByGenre(genre);
		movies.add(new Movie(3001, "A sync servlet", 2019, "epias"));
		request.setAttribute("movies", movies); // ${movies}
		ctx.complete();
	}

}
