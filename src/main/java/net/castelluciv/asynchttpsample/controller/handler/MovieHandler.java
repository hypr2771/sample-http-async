package net.castelluciv.asynchttpsample.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.castelluciv.asynchttpsample.model.Movie;
import net.castelluciv.asynchttpsample.repository.MovieRepository;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.Response;
import org.springframework.http.ResponseEntity;

public class MovieHandler extends AsyncCompletionHandler<ResponseEntity<Movie>> {

  private final static ObjectMapper MOVIE_SERIALIZER = new ObjectMapper();

  private final MovieRepository repository;

  public MovieHandler(MovieRepository repository) {
    this.repository = repository;
  }

  @Override
  public ResponseEntity<Movie> onCompleted(final Response response) throws Exception {
    return ResponseEntity
        .ok(repository
            .save(MOVIE_SERIALIZER
                .readValue(response.getResponseBody(),
                            Movie.class)));
  }
}
