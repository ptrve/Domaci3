package rs.edu.raf.rma.homework3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.homework3.R;
import rs.edu.raf.rma.homework3.adapter.MovieAdapter;
import rs.edu.raf.rma.homework3.repository.db.entity.Movie;
import rs.edu.raf.rma.homework3.repository.web.model.Resource;
import rs.edu.raf.rma.homework3.util.Util;
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

        requestWindowFeature(Window.FEATURE_NO_TITLE);  // hide the title
        getSupportActionBar().hide();                   // hide the title bar

        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        initViews();
        initViewModel();


        test();
    }


    private void test() {
        Movie mov1 = new Movie(String.valueOf(Util.generateId()), "Avengers", "Description info", "Peter Steele", "Rope Smith", "1998", 90);
        Movie mov2 = new Movie(String.valueOf(Util.generateId()), "Batman", "Description info", "Peter Steele", "Rope Smith", "1999", 75);
        Movie mov3 = new Movie(String.valueOf(Util.generateId()), "Spiderman", "Description info", "Peter Steele", "Rope Smith", "2000", 60);

    }

    private void initViews() {
        mSearchEt = findViewById(R.id.et_filter_name);
        mYearEt = findViewById(R.id.et_filter_year);
        mScoreEt = findViewById(R.id.et_filter_score);

        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filter = mSearchEt.getText().toString();
                mViewModel.setNameFilter(filter);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mYearEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filter = mYearEt.getText().toString();
                mViewModel.setYearFilter(filter);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mScoreEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filter  = mScoreEt.getText().toString();
                mViewModel.setScoreFilter(filter);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

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

        mViewModel.getWebMovies().observe(this,
                new Observer<Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>>>() {
                    @Override
                    public void onChanged(Resource<List<rs.edu.raf.rma.homework3.repository.web.model.Movie>> listResource) {
                        if (listResource.isSuccessfull()) {
                            Toast.makeText(getApplicationContext(), "Data fetched from the server!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Data fetch failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.refreshMovies();
    }
}
