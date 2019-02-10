package net.castelluciv.asynchttpsample.controller;

import net.castelluciv.asynchttpsample.controller.handler.MovieHandler;
import net.castelluciv.asynchttpsample.model.Movie;
import net.castelluciv.asynchttpsample.repository.MovieRepository;
import java.util.concurrent.CompletableFuture;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MovieController {

  @Value("${omdbapi.apikey}")
  private String apiKey;

  private final MovieRepository repository;
  private final AsyncHttpClient client = Dsl.asyncHttpClient();

  @Autowired
  public MovieController(final MovieRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/{imdbId}")
  public CompletableFuture<ResponseEntity<Movie>> getById(@PathVariable final String imdbId) {
    return repository.findByImdbId(imdbId)
        .map(movie -> CompletableFuture.completedFuture(ResponseEntity.ok(movie)))
        .orElseGet(() -> client
            .executeRequest(Dsl.get(String.format("http://www.omdbapi.com/?i=%s&apikey=%s",
                imdbId,
                apiKey))
                    .build(),
                new MovieHandler(repository))
            .toCompletableFuture());

    // Here we can do some other heavy computation or register action on completable completion (using thenXXXAsync)

  }

}
