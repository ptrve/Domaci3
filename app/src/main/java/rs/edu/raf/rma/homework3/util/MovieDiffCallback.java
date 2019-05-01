package rs.edu.raf.rma.homework3.util;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import rs.edu.raf.rma.homework3.repository.db.entity.Movie;

public class MovieDiffCallback extends DiffUtil.Callback {

    private List<Movie> mOldList;
    private List<Movie> mNewList;

    public MovieDiffCallback(List<Movie> oldList, List<Movie> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Movie oldMovie = mOldList.get(oldItemPosition);
        Movie newMovie = mNewList.get(newItemPosition);
        return oldMovie.getId().equals(newMovie.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Movie oldMovie = mOldList.get(oldItemPosition);
        Movie newMovie = mNewList.get(newItemPosition);
        return oldMovie.getName().equals(newMovie.getName()) && oldMovie.getYear().equals(newMovie.getYear());
    }
}
