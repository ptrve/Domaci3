package rs.edu.raf.rma.homework3.repository.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {

    @PrimaryKey
    private int mId;
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "description")
    private String mDescription;
    @NonNull
    @ColumnInfo(name = "director")
    private String mDirector;
    @ColumnInfo(name = "producer")
    private String mProducer;
    @NonNull
    @ColumnInfo(name = "year")
    private String mYear;
    @NonNull
    @ColumnInfo(name = "rating")
    private int mRating;

    public Movie(int mId, @NonNull String mName, String mDescription, @NonNull String mDirector, String mProducer, @NonNull String mYear, @NonNull int mRating) {
        this.mId = mId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mDirector = mDirector;
        this.mProducer = mProducer;
        this.mYear = mYear;
        this.mRating = mRating;
    }

    public int getId() {
        return mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    @NonNull
    public String getDirector() {
        return mDirector;
    }

    public String getProducer() {
        return mProducer;
    }

    @NonNull
    public String getYear() {
        return mYear;
    }

    @NonNull
    public int getRating() {
        return mRating;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setDirector(@NonNull String mDirector) {
        this.mDirector = mDirector;
    }

    public void setProducer(String mProducer) {
        this.mProducer = mProducer;
    }

    public void setYear(@NonNull String mYear) {
        this.mYear = mYear;
    }

    public void setRating(@NonNull int mRating) {
        this.mRating = mRating;
    }


}