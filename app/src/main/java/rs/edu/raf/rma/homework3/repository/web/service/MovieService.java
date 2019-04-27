package rs.edu.raf.rma.homework3.repository.web.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rs.edu.raf.rma.homework3.repository.web.model.Movie;

public interface MovieService {

    @GET("films")
    public Call<List<Movie>> getMovies();
}
