package net.castelluciv.asynchttpsample.repository;

import net.castelluciv.asynchttpsample.model.Movie;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, BigInteger> {

  Optional<Movie> findByImdbId(final String imdbId);

}
