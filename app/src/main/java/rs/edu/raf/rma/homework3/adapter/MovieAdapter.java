package rs.edu.raf.rma.homework3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.homework3.R;
import rs.edu.raf.rma.homework3.repository.db.entity.Movie;
import rs.edu.raf.rma.homework3.util.MovieDiffCallback;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private OnSeeMoreClickCallback mOnSeeMoreClickCallback;
    private List<Movie> mDataSet;

    public MovieAdapter() {
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = mDataSet.get(position);
        int score = movie.getRating();
        holder.tvScore.setText(String.valueOf(score));
        if (score >= 90)
            holder.tvScore.setBackgroundResource(R.drawable.rating_green);
        else if (score >= 66)
            holder.tvScore.setBackgroundResource(R.drawable.rating_yellow);
        else
            holder.tvScore.setBackgroundResource(R.drawable.rating_red);

        holder.tvName.setText(movie.getName());
        holder.tvDirector.setText(movie.getDirector());
        holder.tvYear.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<Movie> movies) {
        MovieDiffCallback callback = new MovieDiffCallback(mDataSet, movies);
        DiffUtil.DiffResult result =  DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(movies);
        result.dispatchUpdatesTo(this);
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvYear;
        TextView tvDirector;
        TextView tvScore;
        Button btnMore;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_li_name);
            tvYear = itemView.findViewById(R.id.tv_li_year);
            tvDirector = itemView.findViewById(R.id.tv_li_director);
            tvScore = itemView.findViewById(R.id.tv_li_score);
            btnMore = itemView.findViewById(R.id.btn_li_more);

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (mOnSeeMoreClickCallback != null) {
                            mOnSeeMoreClickCallback.onClick();
                        }
                    }
                }
            });
        }
    }

    public void setOnSeeMoreClickCallback(OnSeeMoreClickCallback OnSeeMoreClickCallback) {
        this.mOnSeeMoreClickCallback = OnSeeMoreClickCallback;
    }

    public interface OnSeeMoreClickCallback {
        void onClick();
    }
}
