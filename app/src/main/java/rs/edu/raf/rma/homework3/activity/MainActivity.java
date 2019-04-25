package rs.edu.raf.rma.homework3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

import rs.edu.raf.rma.homework3.R;
import rs.edu.raf.rma.homework3.adapter.MovieAdapter;
import rs.edu.raf.rma.homework3.repository.db.entity.Movie;
import rs.edu.raf.rma.homework3.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private MovieAdapter mMovieAdapter;

    private EditText mSearchEt;
    private EditText mYearEt;
    private EditText mScoreEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        initViews();
        initViewModel();

    }


    private void initViews() {
        mSearchEt = findViewById(R.id.et_filter_name);
        mYearEt = findViewById(R.id.et_filter_year);
        mScoreEt = findViewById(R.id.et_filter_score);



        RecyclerView recycler = findViewById(R.id.rv_movies);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        recycler.setAdapter(mMovieAdapter);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getMoviesLiveData().observe(this,
                new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        mMovieAdapter.setData(movies);
                    }
                });

        mViewModel.getFilteredMoviesLiveData().observe(this,
                new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        mMovieAdapter.setData(movies);
                    }
                });
    }
}
