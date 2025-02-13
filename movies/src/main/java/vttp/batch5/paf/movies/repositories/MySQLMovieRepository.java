package vttp.batch5.paf.movies.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vttp.batch5.paf.movies.models.Movie;

public class MySQLMovieRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  public void batchInsertMovies(List<Movie> movies) {
    for (Movie movie : movies) {
      try {
        jdbcTemplate.update(Queries.MYSQL_batchInsertMovie,
            movie.getImdbId(), movie.getVoteAverage(), movie.getVoteCount(), movie.getReleaseDate(), movie.getRevenue(),
            movie.getBudget(), movie.getRuntime());
      } catch (Exception ex) {
        throw new RuntimeException(
            "Error creating new user: " + ex.getMessage());
      }
    }
  }

  // TODO: Task 3
  /*
   * 
   */

}