package vttp.batch5.paf.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp.batch5.paf.movies.services.MovieService;

@RestController
@RequestMapping("/")
public class MainController {

  @Autowired
  private MovieService movieService;

  // TODO: Task 3
  @GetMapping("/top")
    public ResponseEntity<List<?>> getProlificDirectors(
            @RequestParam(required = true, defaultValue = "1") Integer limit) {
        try {
            List<String> directors = movieService.getProlificDirectors(limit);
            return ResponseEntity.ok(directors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
   

  
  // TODO: Task 4


}
  
