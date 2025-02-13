package vttp.batch5.paf.movies.bootstrap;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vttp.batch5.paf.movies.models.Movie;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;

@Component
public class Dataloader implements CommandLineRunner {

    @Autowired
    private MySQLMovieRepository mySQLRepo;

    @Autowired
    private MongoMovieRepository mongoRepo;

    // TODO: Task 2

    @Override
    public void run(String... args) {
        System.out.println("Reading zip...");
        List<Movie> movies = new ArrayList<>();
        int totalProcessed = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date afterDate;
        try {
            afterDate = dateFormat.parse("2018-01-01");

            Path p = Paths.get("../data/movies_post_2010.zip");
            System.out.println("reading from:" + p.toAbsolutePath());

            try (
                    FileInputStream fis = new FileInputStream(p.toFile());
                    ZipInputStream zis = new ZipInputStream(fis);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(zis))) {
                zis.getNextEntry(); 

                String line;
                while ((line = br.readLine()) != null) {
                    try (
                            JsonReader jsonReader = Json.createReader(
                                    new StringReader(line))) {
                        JsonObject json = jsonReader.readObject();

                        // Parse release date
                        Date releaseDate = dateFormat.parse(
                                json.getString("release_date", ""));
                        if (releaseDate.before(afterDate)) {
                            continue;
                        }

                        Movie movie = new Movie(
                            json.getString("title", ""),
                            Float.parseFloat(json.getString("vote_average", "0")),
                            json.getInt("vote_count", 0),
                            json.getString("status", ""),
                            releaseDate,
                            Long.parseLong(json.getString("revenue", "0")),
                            json.getInt("runtime", 0),
                            Long.parseLong(json.getString("budget", "0")),
                            json.getString("imdb_id", ""),
                            json.getString("original_language", ""),
                            json.getString("overview", ""),
                            Float.parseFloat(json.getString("popularity", "0")),
                            json.getString("tagline", ""),
                            json.getString("genres", ""),
                            json.getString("spoken_languages", ""),
                            json.getString("casts", ""),
                            json.getString("director", ""),
                            Float.parseFloat(json.getString("imdb_rating", "0")),
                            json.getInt("imdb_votes", 0),
                            json.getString("poster_path", "")
                        );
                                
                        movies.add(movie);

                        if (movies.size() == 25) {
                            totalProcessed += 25;
                            System.out.println("adding docs, current count:" + totalProcessed);
                            try {
                                mySQLRepo.batchInsertMovies(movies);
                                System.out.println("sql added");
                                mongoRepo.batchInsertMovies(movies);
                                System.out.println("mongo added");
                            } catch (Exception e) {
                                List<String> movieIds = new ArrayList<>();
                                for (Movie in : movies) {
                                    String imdbId = in.getImdbId();
                                    movieIds.add(imdbId);
                                }
                                // String errorMessage = e.getMessage();
                                // mongoRepo.logError(movieIds, errorMessage);
                            }
                            System.out.println("new count: " + totalProcessed);
                            movies = new ArrayList<>();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Error processing JSON line: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!movies.isEmpty()) {
            try {
                mySQLRepo.batchInsertMovies(movies);
                System.out.println("sql added");
                mongoRepo.batchInsertMovies(movies);
                System.out.println("mongo added");
            } catch (Exception e) {
                List<String> movieIds = new ArrayList<>();
                for (Movie in : movies) {
                    String imdbId = in.getImdbId();
                    movieIds.add(imdbId);
                }
                String errorMessage = e.getMessage();
                mongoRepo.logError(movieIds, errorMessage);
            }
            totalProcessed += movies.size();
            System.out.println("Final batch: Processed total of " + totalProcessed + " movies");
        }
    }
}
    
