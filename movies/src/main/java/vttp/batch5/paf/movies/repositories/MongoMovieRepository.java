package vttp.batch5.paf.movies.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
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
    //             "title": "The Cabin in the Woods",
                //   "vote_average": 7,
                //   "vote_count": 7814,
                //   "status": "Released",
                //   "release_date": "2012-04-12",
                //   "revenue": 71038838,
                //   "runtime": 95,
                //   "budget": 30000000,
                //   "imdb_id": "tt1259521",
                //   "original_language": "en",
                //   "overview": "A group of teens journey to a remote cabin in the woods where their fate is unknowingly controlled by technicians as part of a worldwide conspiracy where all horror movie clichés are revealed to be part of an elaborate sacrifice ritual.",
                //   "popularity": 48,
                //   "tagline": "You think you know the story.",
                //   "genres": "Horror, Mystery",
                //   "spoken_languages": "English, 日本語",
                //   "casts": "Brad Dryborough, Heather Doerksen, Alicia Takase Lui, Patrick Sabongui, Alyssandra Yamamoto, Richard Jenkins, Tom Lenk, Jodi Tabuchi, Naomi Dane, Sara Taira, Matt Phillips, Serena Akane Chi, Dan Payne, Nels Lennarson, Jesse Williams, Rukiya Bernard, Sigourney Weaver, Bradley Whitford, Maya Massar, Marina Ishibashi, Lori Stewart, Chelah Horsdal, Matt Drake, Kristen Connolly, Aya Furukawa, Maria Go, Phoebe Galvan, Dan Shea, Tim DeZarn, Adrian Holmes, Simon Pidgeon, Brian J. White, Richard Cetrone, Abbey Imai, Greg Zach, Ellie Harvie, Chris Hemsworth, Patrick Gilmore, Jodelle Ferland, Miku Katsuura, Fran Kranz, Amy Acker, Terry Notary, Terry Chen, Emili Kawashima, Phillip Mitchell, Anna Hutchison, Peter Kelamis",
                //   "director": "Drew Goddard",
                //   "imdb_rating": 7,
                //   "imdb_votes": 465176,
                //   "poster_path": "/kjDXrK3ReIwuDrpWElI5OQkKYTA.jpg"
                //         }, ...
    //       ])

    //
    public void batchInsertMovies(List<Movie> movies) {
        List<Document> toInsert = new ArrayList<>();

        for (Movie movie : movies) {
            Document doc = new Document()
            .append("title", movie.getTitle())
            .append("vote_average", movie.getVoteAverage())
            .append("vote_count", movie.getVoteCount())
            .append("status", movie.getStatus())
            .append("release_date", movie.getReleaseDate())
            .append("revenue", movie.getRevenue())
            .append("runtime", movie.getRuntime())
            .append("budget", movie.getBudget())
            .append("imdb_id", movie.getImdbId())
            .append("original_language", movie.getOriginalLanguage())
            .append("overview", movie.getOverview())
            .append("popularity", movie.getPopularity())
            .append("tagline", movie.getTagline())
            .append("genres", movie.getGenres())
            .append("spoken_languages", movie.getSpokenLanguages())
            .append("casts", movie.getCasts())
            .append("director", movie.getDirector())
            .append("imdb_rating", movie.getImdbRating())
            .append("imdb_votes", movie.getImdbVotes())
            .append("poster_path", movie.getPosterPath());

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
    public void logError(List<String> ids, String error) {
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
    /*
     * db.movies.aggregate([
    {
        $match: {
            director: { $ne: "" }
        }
    },
    
    {
        $group: {
            _id: "$director",
            movies_count: { $sum: 1 },
            total_revenue: { $sum: "$revenue" },
            total_budget: { $sum: "$budget" }
        }
    },
    
    {
        $project: {
            director_name: "$_id",
            movies_count: 1,
            total_revenue: 1,
            total_budget: 1,
            profit_loss: { $subtract: ["$total_revenue", "$total_budget"] }
        }
    },
    {
        $sort: {
            movies_count: -1 
        }
    },
    
    {
        $limit: 1 //default value
    }
])
     */

}
