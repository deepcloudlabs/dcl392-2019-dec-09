package com.example.imdb.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.imdb.controller.tasks.MovieSearchTask;
import com.example.imdb.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@WebServlet(urlPatterns = "/asynclist", asyncSupported = true)
public class AsyncImdbController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private MovieService movieSrv;
	@Resource
	private ManagedExecutorService executorService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AsyncContext actx = request.startAsync();
		actx.addListener(new AsyncListener() {

			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				event.getAsyncContext().getRequest().setAttribute("error", event.getThrowable().getMessage());
				try {
					event.getAsyncContext().getRequest().getRequestDispatcher("error.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				System.err.println("Task is triggered.");
			}

			@Override
			public void onError(AsyncEvent event) throws IOException {
				event.getAsyncContext().getRequest().setAttribute("error", event.getThrowable().getMessage());
				try {
					event.getAsyncContext().getRequest().getRequestDispatcher("error.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				try {
					event.getAsyncContext().getRequest().getRequestDispatcher("view.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}
		});
		executorService.execute(new MovieSearchTask(actx, movieSrv));
	}

}
