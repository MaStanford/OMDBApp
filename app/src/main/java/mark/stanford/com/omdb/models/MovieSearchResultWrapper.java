package mark.stanford.com.omdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchResultWrapper {

    @SerializedName("Search")
    @Expose
    public List<Search> search = new ArrayList<Search>();
    @SerializedName("totalResults")
    @Expose
    public int totalResults;
    @SerializedName("Response")
    @Expose
    public String response;

    @Override
    public String toString() {
        return "MovieSearchResultWrapper{" +
                "search=" + search.size() +
                ", totalResults='" + totalResults + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
