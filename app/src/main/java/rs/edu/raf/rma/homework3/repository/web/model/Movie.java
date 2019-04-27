package rs.edu.raf.rma.homework3.repository.web.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("director")
    private String mDirector;
    @SerializedName("producer")
    private String mProducer;
    @SerializedName("release_date")
    private String mYear;
    @SerializedName("rt_score")
    private int mScore;

    public Movie(int id, String title, String description, String director, String producer, String year, int score) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDirector = director;
        mProducer = producer;
        mYear = year;
        mScore = score;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmDirector(String mDirector) {
        this.mDirector = mDirector;
    }

    public void setmProducer(String mProducer) {
        this.mProducer = mProducer;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmDirector() {
        return mDirector;
    }

    public String getmProducer() {
        return mProducer;
    }

    public String getmYear() {
        return mYear;
    }

    public int getmScore() {
        return mScore;
    }
}
