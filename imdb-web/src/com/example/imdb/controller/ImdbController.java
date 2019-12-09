package com.example.imdb.controller;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@WebServlet(urlPatterns = "/list")
public class ImdbController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private MovieService movieSrv;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String genre = request.getParameter("genre");
		Collection<Movie> movies = movieSrv.findAllMoviesByGenre(genre);
		request.setAttribute("movies", movies); // ${movies}
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}

}
