package com.example.imdb.rs;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.example.imdb.entity.Genre;
import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@Path("/")
@RequestScoped
public class ImdbResource {
	@Inject
	private MovieService movieSrv;

	@GET
	@Produces(value = "application/json")
	@Path(value = "/genres")
	public Collection<Genre> getAllGenres() {
		return movieSrv.findAllGenres();
	}

	@GET
	@Produces(value = "application/json")
	@Path("/movies")
	public Collection<Movie> findMovies(@QueryParam("from") Integer from, @QueryParam("to") Integer to,
			@QueryParam("genre") String genre) {
		return movieSrv.findAllMoviesByYearRangeAndGenre(genre, from, to);
	}
}
