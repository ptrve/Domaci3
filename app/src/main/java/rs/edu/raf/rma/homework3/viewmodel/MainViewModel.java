package rs.edu.raf.rma.homework3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.homework3.repository.MovieRepository;
import rs.edu.raf.rma.homework3.repository.db.entity.Movie;
import rs.edu.raf.rma.homework3.repository.web.model.Resource;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> mMoviesLiveData;
    private LiveData<Integer> mMoviesCountLiveData;
    private MutableLiveData<String> mFilterLiveData;
    private LiveData<List<Movie>> mFilteredMoviesLiveData;

    private MutableLiveData<List<String>> mFilterArrayLiveData;
    private List<String> filterArray;

    private MovieRepository movieRepository;

    private MediatorLiveData<Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>>> mWebMovieMediatorLiveData;
    private LiveData<Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>>> mWebMovieLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mWebMovieLiveData = new MutableLiveData<>();
        mWebMovieMediatorLiveData = new MediatorLiveData<>();

        movieRepository = new MovieRepository(getApplication());

        mMoviesLiveData = movieRepository.getAllMovies();

        mFilterLiveData = new MutableLiveData<>();

//        mFilteredMoviesLiveData = Transformations.switchMap(mFilterLiveData, new Function<String, LiveData<List<Movie>>>() {
//            @Override
//            public LiveData<List<Movie>> apply(String filter) {
//                return movieRepository.getMoviesByName(filter);
//            }
//        });

        mFilterArrayLiveData = new MutableLiveData<>();
        filterArray = new ArrayList<>();
        filterArray.add("");
        filterArray.add("");
        filterArray.add("0");

        mFilteredMoviesLiveData = Transformations.switchMap(mFilterArrayLiveData, new Function<List<String>, LiveData<List<Movie>>>() {
            @Override
            public LiveData<List<Movie>> apply(List<String> input) {
                return movieRepository.getMoviesFiltered(input.get(0), input.get(1), Integer.parseInt(input.get(2)));
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

    public void setNameFilter(String filter) {
        filterArray.set(0, filter);
        mFilterArrayLiveData.setValue(filterArray);
    }
    public void setYearFilter(String filter) {
        filterArray.set(1, filter);
        mFilterArrayLiveData.setValue(filterArray);
    }
    public void setScoreFilter(String filter) {
        if (filter.equals(""))
            filter = "0";
        filterArray.set(2, filter);
        mFilterArrayLiveData.setValue(filterArray);
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


    public void refreshMovies() {
        mWebMovieMediatorLiveData.removeSource(mWebMovieLiveData);
        mWebMovieLiveData = movieRepository.getMoviesFromWeb();
        mWebMovieMediatorLiveData.addSource(mWebMovieLiveData,
                new Observer<Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>>>() {
                    @Override
                    public void onChanged(Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>> listResource) {
                        mWebMovieMediatorLiveData.setValue(listResource);

                        List<rs.edu.raf.rma.homework3.repository.web.model.Movie> newMovies = listResource.getData();
                        for (rs.edu.raf.rma.homework3.repository.web.model.Movie movie : newMovies) {
                            addMovie(new Movie(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getDirector(), movie.getProducer(), movie.getYear(), Integer.parseInt(movie.getScore())));
                        }
                    }
                });
    }

    public LiveData<Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>>> getWebMovies() {
        return mWebMovieMediatorLiveData;
    }
}
