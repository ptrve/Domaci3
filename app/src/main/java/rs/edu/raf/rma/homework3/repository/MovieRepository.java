package rs.edu.raf.rma.homework3.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.edu.raf.rma.homework3.repository.db.MovieDatabase;
import rs.edu.raf.rma.homework3.repository.db.dao.MovieDao;
import rs.edu.raf.rma.homework3.repository.db.entity.Movie;
import rs.edu.raf.rma.homework3.repository.web.api.MovieApi;
import rs.edu.raf.rma.homework3.repository.web.model.Resource;

public class MovieRepository {

    // Database
    private ExecutorService executorService;
    private MovieDao movieDao;

    // Web
    private MovieApi movieApi;
    private MutableLiveData<Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>>> movieLiveData;

    public MovieRepository(Context context) {
        movieDao = MovieDatabase.getDb(context).getMovieDao();
        executorService = Executors.newCachedThreadPool();
        movieApi = new MovieApi();
        movieLiveData = new MutableLiveData<>();
    }

    public LiveData<Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>>> getMoviesFromWeb() {
        fetchMovieDataFromServer();
        return movieLiveData;
    }

    private void fetchMovieDataFromServer() {
        movieApi.getMovies().enqueue(new Callback<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>>() {
            @Override
            public void onResponse(Call<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>> call, Response<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>> response) {
                Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>> resource = new Resource<>(response.body(), true);
                movieLiveData.setValue(resource);
            }

            @Override
            public void onFailure(Call<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>> call, Throwable t) {
                Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>> resource = new Resource<>(new ArrayList<>(), false);
                movieLiveData.setValue(resource);
            }
        });
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

    public LiveData<List<Movie>> getMoviesFiltered(String name, String year, int score) {
        return movieDao.getMoviesFiltered(name, year, score);
    }

    public void removeMovie(Movie movie) {
        executorService.submit(() -> movieDao.delete(movie));
    }
}
