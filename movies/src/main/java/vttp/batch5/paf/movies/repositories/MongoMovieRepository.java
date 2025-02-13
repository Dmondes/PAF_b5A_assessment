package vttp.batch5.paf.movies.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.bson.Document;
import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MongoMovieRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


 // TODO: Task 2.3
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
//  db.movies.insertMany([
//         {
//             "imdb_id": "tt1259521",
//             "vote_average": 7,
//             "vote_count": 7814,
//             "release_date": "2012-04-12"),
//             "revenue": 71038838,
//             "budget": 30000000,
//             "runtime": 95
//         }, ...
//       ])
 
 //
 public void batchInsertMovies(List<Movie> movies) {
    List<Document> toInsert = new ArrayList<>();
    
    for (Movie movie : movies) {
        Document doc = new Document();
        doc.append("imdb_id", movie.getImdbId());
        doc.append("vote_average", movie.getVoteAverage());
        doc.append("vote_count", movie.getVoteCount());
        doc.append("release_date", movie.getReleaseDate());
        doc.append("revenue", movie.getRevenue());
        doc.append("budget", movie.getBudget());
        doc.append("runtime", movie.getRuntime());
        
        toInsert.add(doc);
    }

    mongoTemplate.getCollection("movies").insertMany(toInsert);
}

 // TODO: Task 2.4
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
//  db.errors.insertOne({
//     "ids": ["tt1259521", "tt123456", ...],
//     "error": "Error message",
//     "timestamp": "ISODate("2024-01-20T10:30:15.123Z")"
// })
 //
 public void logError(String[] ids, String error) {
    Document errorBatch = new Document()
            .append("ids", ids)
            .append("error", error)
            .append("timestamp", new Date());
            mongoTemplate.getCollection("errors").insertOne(errorBatch);
 }

 // TODO: Task 3
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //


}
