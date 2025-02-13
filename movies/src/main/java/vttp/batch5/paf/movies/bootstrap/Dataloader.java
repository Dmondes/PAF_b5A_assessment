package vttp.batch5.paf.movies.bootstrap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipInputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Dataloader implements CommandLineRunner {

    //TODO: Task 2
    //when the spring app starts, it will check if data from movies_post_2010.zip is loaded in mysqldb and mongodb
    //if data not loaded, it will proceed to load, else no
    //implement this bootstrapping process here
    //unzip movies_post_2010.zip
    //read movies_post_2010.json file
    //load into db


    @Override
    public void run(String... args) {
      System.out.println("Reading zip...");
      try {
            
            Path p = Paths.get("../data/movies_post_2010.zip");
            System.out.println( "reading from:" + p.toAbsolutePath());
            
            FileInputStream fis = new FileInputStream(p.toFile());
            ZipInputStream zis = new ZipInputStream(fis);
            
            zis.getNextEntry();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(zis));
            String line;
            // read JSON document, filter by release_date >= 2018, for any missing/ error attributes/ boolean false, empty string replace with 0
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                break;
            }

            br.close();
            zis.closeEntry();
            zis.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

