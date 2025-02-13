package vttp.batch5.paf.movies.repositories;

public class Queries {
    public static final String MYSQL_batchInsertMovie = 
    """
    INSERT INTO movies (imdb_id, vote_average, vote_count, release_date, revenue, budget, runtime)
    VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
}
