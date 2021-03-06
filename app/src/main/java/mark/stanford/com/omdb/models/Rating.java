package mark.stanford.com.omdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("Source")
    @Expose
    public String source;
    @SerializedName("Value")
    @Expose
    public String value;
}
