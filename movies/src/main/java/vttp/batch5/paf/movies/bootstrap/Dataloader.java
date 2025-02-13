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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date afterDate;
        try {
            afterDate = dateFormat.parse("2018-01-01");

            Path p = Paths.get("../data/movies_post_2010.zip");
            System.out.println("reading from:" + p.toAbsolutePath());

            FileInputStream fis = new FileInputStream(p.toFile());
            ZipInputStream zis = new ZipInputStream(fis);
            zis.getNextEntry();
            BufferedReader br = new BufferedReader(new InputStreamReader(zis));
            String line;

            while ((line = br.readLine()) != null) {
                Date releaseDate;
                try (
                        JsonReader jsonReader = Json.createReader(
                                new StringReader(line))) {
                    JsonObject json = jsonReader.readObject();
                    try {
                        releaseDate = dateFormat.parse(
                                json.getString("release_date", ""));
                        if (releaseDate.before(afterDate))
                            continue; // skip before date
                    } catch (Exception e) {
                        continue; // skip error
                    }

                    Movie movie = new Movie(
                            json.getString("imdb_id", "0"),
                            Float.parseFloat(json.getString("vote_average", "0")),
                            json.getInt("vote_count", 0),
                            releaseDate,
                            Float.parseFloat(json.getString("revenue", "0")),
                            Float.parseFloat(json.getString("budget", "0")),
                            json.getInt("runtime", 0));
                    movies.add(movie);

                    if (movies.size() == 25) {
                        processBatch(movies);
                        movies = new ArrayList<>();
                    }
                } catch (Exception e) {
                    System.out.println(
                            "Error processing JSON: " + e.getMessage());
                }
            }

            // Process any remaining movies
            if (!movies.isEmpty()) {
                processBatch(movies);
            }

            br.close();
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processBatch(List<Movie> movies) {
        try {
            mySQLRepo.batchInsertMovies(movies);
            mongoRepo.batchInsertMovies(movies);
        } catch (Exception e) {
            List<String> movieIds = new ArrayList<>();
            for (Movie movie : movies) {
                String imdbId = movie.getImdbId();
                movieIds.add(imdbId);
            }
            String[] idArray = movieIds.toArray(new String[0]);
            String errorMessage = e.getMessage();   
            mongoRepo.logError(idArray, errorMessage);
        }
    }
}
