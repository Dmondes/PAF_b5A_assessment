package vttp.batch5.paf.movies.models;

import java.util.Date;

public class Movie {
    private String title;
    private float voteAverage;
    private int voteCount;
    private String status;
    private Date releaseDate;
    private long revenue;
    private int runtime;
    private long budget;
    private String imdbId;
    private String originalLanguage;
    private String overview;
    private float popularity;
    private String tagline;
    private String genres;
    private String spokenLanguages;
    private String casts;
    private String director;
    private float imdbRating;
    private int imdbVotes;
    private String posterPath;

    public Movie() {}

    public Movie(String title, float voteAverage, int voteCount, String status, Date releaseDate, long revenue,
            int runtime, long budget, String imdbId, String originalLanguage, String overview, float popularity,
            String tagline, String genres, String spokenLanguages, String casts, String director, float imdbRating,
            int imdbVotes, String posterPath) {
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.status = status;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.budget = budget;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.overview = overview;
        this.popularity = popularity;
        this.tagline = tagline;
        this.genres = genres;
        this.spokenLanguages = spokenLanguages;
        this.casts = casts;
        this.director = director;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.posterPath = posterPath;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(String spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getCasts() {
        return casts;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }
  
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

}
       