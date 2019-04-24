package rs.edu.raf.rma.homework3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import rs.edu.raf.rma.homework3.repository.MovieRepository;
import rs.edu.raf.rma.homework3.repository.db.entity.Movie;

public class MainViewModel extends AndroidViewModel {
    
    private LiveData<List<Movie>> mMoviesLiveData;
    private LiveData<Integer> mMoviesCountLiveData;
    private MutableLiveData<String> mFilterLiveData;
    private LiveData<List<Movie>> mFilteredMoviesLiveData;

    private MovieRepository movieRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        movieRepository = new MovieRepository(getApplication());

        mMoviesLiveData = movieRepository.getAllMovies();

        mFilterLiveData = new MutableLiveData<>();

        mFilteredMoviesLiveData = Transformations.switchMap(mFilterLiveData, new Function<String, LiveData<List<Movie>>>() {
            @Override
            public LiveData<List<Movie>> apply(String filter) {
                return movieRepository.getMoviesByName(filter);
            }
        });

        mMoviesCountLiveData = Transformations.map(mMoviesLiveData, new Function<List<Movie>, Integer>() {
            @Override
            public Integer apply(List<Movie> input) {
                return input.size();
            }
        });
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return mMoviesLiveData;
    }

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public void addMovie(Movie ...movies) {
        movieRepository.addMovie(movies);
    }

    public void addMovie(List<Movie> movies) {
        movieRepository.addMovie(movies);
    }

    public void setFilter(String filter) {
        mFilterLiveData.setValue(filter);
    }

    public LiveData<Integer> getMoviesCountLiveData() {
        return mMoviesCountLiveData;
    }

    public LiveData<List<Movie>> getFilteredMoviesLiveData() {
        return mFilteredMoviesLiveData;
    }

    public void removeMovie(Movie movie) {
        movieRepository.removeMovie(movie);
    }

}
