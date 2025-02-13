package vttp.batch5.paf.movies.repositories;

public class Queries {
    public static final String MYSQL_batchInsertMovie = 
    """ 
    INSERT INTO movies (
        title, vote_average, vote_count, status, release_date, 
        revenue, runtime, budget, imdb_id, original_language, 
        overview, popularity, tagline, genres, spoken_languages, 
        casts, director, imdb_rating, imdb_votes,poster_path
    ) VALUES (
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
    )
            """;
}

// INSERT INTO movies (imdb_id, vote_average, vote_count, release_date, revenue, budget, runtime)
//VALUES (?, ?, ?, ?, ?, ?, ?)