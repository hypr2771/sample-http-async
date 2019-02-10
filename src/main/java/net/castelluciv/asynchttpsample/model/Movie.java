package net.castelluciv.asynchttpsample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE", schema = "public")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  @JsonIgnore
  private BigInteger id;

  @JsonProperty("imdbID")
  @Column(name = "IMDB_ID")
  private String imdbId;

  @JsonProperty("Title")
  @Column(name = "TITLE")
  private String title;

  public Movie() {
  }

  public Movie(String imdbId, String title) {
    this.imdbId = imdbId;
    this.title = title;
  }

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getImdbId() {
    return imdbId;
  }

  public void setImdbId(String imdbId) {
    this.imdbId = imdbId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
