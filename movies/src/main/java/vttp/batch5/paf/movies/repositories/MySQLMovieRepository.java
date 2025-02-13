package vttp.batch5.paf.movies.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MySQLMovieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // TODO: Task 2.3
    // You can add any number of parameters and return any type from the method
    public void batchInsertMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            try {
              jdbcTemplate.update(
                Queries.MYSQL_batchInsertMovie,
                movie.getTitle(),
                movie.getVoteAverage(),
                movie.getVoteCount(),
                movie.getStatus(),
                movie.getReleaseDate(),
                movie.getRevenue(),
                movie.getRuntime(),
                movie.getBudget(),
                movie.getImdbId(),
                movie.getOriginalLanguage(),
                movie.getOverview(),
                movie.getPopularity(),
                movie.getTagline(),
                movie.getGenres(),
                movie.getSpokenLanguages(),
                movie.getCasts(),
                movie.getDirector(),
                movie.getImdbRating(),
                movie.getImdbVotes(),
                movie.getPosterPath()
            );
            } catch (Exception ex) {
                throw new RuntimeException(
                    "Error inserting movies: " + ex.getMessage()
                );
            }
        }
    }
    // TODO: Task 3
    /*
     *
     */

}
