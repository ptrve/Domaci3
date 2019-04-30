package rs.edu.raf.rma.homework3.repository.web.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private String mId;
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
    private String mScore;

    public Movie(String id, String title, String description, String director, String producer, String year, String score) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDirector = director;
        mProducer = producer;
        mYear = year;
        mScore = score;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setDirector(String mDirector) {
        this.mDirector = mDirector;
    }

    public void setProducer(String mProducer) {
        this.mProducer = mProducer;
    }

    public void setYear(String mYear) {
        this.mYear = mYear;
    }

    public void setScore(String mScore) {
        this.mScore = mScore;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDirector() {
        return mDirector;
    }

    public String getProducer() {
        return mProducer;
    }

    public String getYear() {
        return mYear;
    }

    public String getScore() {
        return mScore;
    }
}
