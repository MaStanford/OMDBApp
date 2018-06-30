package mark.stanford.com.omdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {

    @SerializedName("Title")
    @Expose
    public String title;

    @SerializedName("Year")
    @Expose
    public String year;

    @SerializedName("Rated")
    @Expose
    public String rated;

    @SerializedName("Released")
    @Expose
    public String released;

    @SerializedName("Runtime")
    @Expose
    public String runtime;

    @SerializedName("Genre")
    @Expose
    public String genre;

    @SerializedName("Director")
    @Expose
    public String director;

    @SerializedName("Writer")
    @Expose
    public String writer;

    @SerializedName("Actors")
    @Expose
    public String actors;

    @SerializedName("Plot")
    @Expose
    public String plot;

    @SerializedName("Language")
    @Expose
    public String language;

    @SerializedName("Country")
    @Expose
    public String country;

    @SerializedName("Awards")
    @Expose
    public String awards;

    @SerializedName("Poster")
    @Expose
    public String poster;

    @SerializedName("Ratings")
    @Expose
    public List<Rating> ratings = new ArrayList<Rating>();

    @SerializedName("Metascore")
    @Expose
    public String metascore;

    @SerializedName("imdbRating")
    @Expose
    public String imdbRating;

    @SerializedName("imdbVotes")
    @Expose
    public String imdbVotes;

    @SerializedName("imdbID")
    @Expose
    public String imdbID;

    @SerializedName("Type")
    @Expose
    public String type;

    @SerializedName("DVD")
    @Expose
    public String dVD;

    @SerializedName("BoxOffice")
    @Expose
    public String boxOffice;

    @SerializedName("Production")
    @Expose
    public String production;

    @SerializedName("Website")
    @Expose
    public String website;

    @SerializedName("Response")
    @Expose
    public String response;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(imdbID, movie.imdbID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(imdbID);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", ratings=" + ratings +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", dVD='" + dVD + '\'' +
                ", boxOffice='" + boxOffice + '\'' +
                ", production='" + production + '\'' +
                ", website='" + website + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
