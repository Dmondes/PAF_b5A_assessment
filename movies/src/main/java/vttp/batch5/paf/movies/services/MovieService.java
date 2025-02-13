package vttp.batch5.paf.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;

public class MovieService {

  @Autowired
  private MongoRepository mongoRepo;

  @Autowired
  private MySQLMovieRepository sqlRepo;
    // TODO: Task 2

    // TODO: Task 3
    // You may change the signature of this method by passing any number of parameters
    // and returning any type
    public List<String> getProlificDirectors(int limit) {
        // return mongoRepo.getDirectorStatistics(limit);
        return null;
    }

    // TODO: Task 4
    // You may change the signature of this method by passing any number of parameters
    // and returning any type
    public void generatePDFReport() {}
}
