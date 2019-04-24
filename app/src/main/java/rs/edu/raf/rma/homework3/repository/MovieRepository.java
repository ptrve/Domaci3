package rs.edu.raf.rma.homework3.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rs.edu.raf.rma.homework3.repository.db.MovieDatabase;
import rs.edu.raf.rma.homework3.repository.db.dao.MovieDao;
import rs.edu.raf.rma.homework3.repository.db.entity.Movie;

public class MovieRepository {

    private ExecutorService executorService;

    private MovieDao movieDao;

    public MovieRepository(Context context) {
        movieDao = MovieDatabase.getDb(context).getMovieDao();
        executorService = Executors.newCachedThreadPool();
    }

    public void addMovie(Movie movie) {
        executorService.submit(() -> movieDao.insert(movie));
    }

    public void addMovie(Movie ...movies) {
        executorService.submit(() -> movieDao.insert(movies));
    }

    public void addMovie(List<Movie> movies) {
        executorService.submit(() -> movieDao.insert(movies));
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public LiveData<List<Movie>> getMoviesByName(String name) {
        return movieDao.getMoviesByName(name);
    }

    public void removeMovie(Movie movie) {
        executorService.submit(() -> movieDao.delete(movie));
    }
}
