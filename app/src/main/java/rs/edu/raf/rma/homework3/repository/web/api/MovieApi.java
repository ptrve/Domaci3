package rs.edu.raf.rma.homework3.repository.web.api;

import java.util.List;

import retrofit2.Call;
import rs.edu.raf.rma.homework3.repository.web.model.Movie;
import rs.edu.raf.rma.homework3.repository.web.service.MovieService;
import rs.edu.raf.rma.homework3.repository.web.service.ServiceGenerator;

public class MovieApi {

    private MovieService mMovieService;

    public MovieApi() {
        mMovieService = ServiceGenerator.createService(MovieService.class);
    }

    public Call<List<Movie>> getMovies() {
        return mMovieService.getMovies();
    }
}
